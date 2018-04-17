/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ide.codegen.python;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.python.pydev.ast.interpreter_managers.InterpreterManagersAPI;
import org.python.pydev.core.IInterpreterManager;
import org.python.pydev.core.IPythonNature;
import org.python.pydev.core.IPythonPathNature;
import org.python.pydev.plugin.nature.PythonNature;

import gov.redhawk.ide.codegen.AbstractCodeGenerator;
import gov.redhawk.ide.codegen.FileToCRCMap;
import gov.redhawk.ide.codegen.ImplementationSettings;
import gov.redhawk.ide.codegen.python.utils.PythonGeneratorUtils;
import gov.redhawk.ide.codegen.util.CodegenFileHelper;
import gov.redhawk.model.sca.util.ModelUtil;
import mil.jpeojtrs.sca.spd.Implementation;
import mil.jpeojtrs.sca.spd.SoftPkg;

/**
 * An abstract class for Python implementation generation. It handles most aspects of configuring PyDev, but leaves
 * generating code files up to the sub-class.
 */
public abstract class AbstractPythonGenerator extends AbstractCodeGenerator {
	public static final String CODEGEN_ID = "gov.redhawk.ide.codegen.python.PythonGenerator";

	public AbstractPythonGenerator() {

	}

	@Override
	public IStatus generate(final ImplementationSettings implSettings, final Implementation impl, final PrintStream out, final PrintStream err, // SUPPRESS CHECKSTYLE NumParameters
		final IProgressMonitor monitor, final String[] generateFiles, final boolean shouldGenerate, final List<FileToCRCMap> crcMap) {
		Assert.isNotNull(implSettings);
		Assert.isNotNull(impl);
		Assert.isNotNull(out);
		Assert.isNotNull(err);

		final int PYTHON_NATURE_WORK = 10;
		final int ADD_SOURCE_PATH_WORK = 10;
		final int ADD_BUILDER_WORK = 10;
		final int GENERATE_CODE_WORK = 70;
		final int CLEANUP_PROJECT_WORK = 10;
		final SubMonitor progress;
		if (shouldGenerate) {
			progress = SubMonitor.convert(monitor, "Configuring Project", PYTHON_NATURE_WORK + ADD_SOURCE_PATH_WORK + ADD_BUILDER_WORK + GENERATE_CODE_WORK
				+ CLEANUP_PROJECT_WORK);
		} else {
			progress = SubMonitor.convert(monitor, "Configuring Project", PYTHON_NATURE_WORK + ADD_SOURCE_PATH_WORK + ADD_BUILDER_WORK);
		}
		final IResource resource = ModelUtil.getResource(implSettings);
		final MultiStatus retStatus = new MultiStatus(PythonGeneratorPlugin.PLUGIN_ID, IStatus.OK, "Python code generation problems", null);
		final SoftPkg softPkg = impl.getSoftPkg();
		final String componentName = CodegenFileHelper.getPreferredFilePrefix(softPkg, implSettings);
		final IProject project = resource.getProject();
		final String destinationDirectory = project.getFolder(implSettings.getOutputDir()).getFullPath().toString();

		// Check to see if interpeterManager is configured
		final IInterpreterManager interpreterManager = InterpreterManagersAPI.getPythonInterpreterManager();
		if (interpreterManager.isConfigured()) {
			out.println("Interpreter is configured, attempting to add nature.");
		} else {
			out.println("Interpreter is NOT configured. Aborting generation.");
			retStatus.add(new Status(IStatus.ERROR, PythonGeneratorPlugin.PLUGIN_ID, "You must configure a python interpreter to generate code."));
		}

		// Add the Python nature
		final IPythonNature pythonNature;
		try {
			pythonNature = PythonGeneratorUtils.addPythonProjectNature(project, progress.newChild(PYTHON_NATURE_WORK));
		} catch (final CoreException e) {
			retStatus.add(new Status(IStatus.ERROR, PythonGeneratorPlugin.PLUGIN_ID,
				"Unable to determine if the project has been configured with the python nature; cannot proceed with code generation", e));
			return retStatus;
		}

		// Add our source path
		try {
			PythonGeneratorUtils.addPythonSourcePath(project, destinationDirectory, progress.newChild(ADD_SOURCE_PATH_WORK));
		} catch (final CoreException e) {
			retStatus.add(new Status(IStatus.ERROR, PythonGeneratorPlugin.PLUGIN_ID,
				"Unable to set the python source path; cannot proceed with code generation", e));
			return retStatus;
		}

		// Add our auto-inclusion builder
		try {
			addAutoInclusionBuilder(project, progress.newChild(ADD_BUILDER_WORK));
		} catch (final CoreException e) {
			retStatus.add(new Status(IStatus.ERROR, PythonGeneratorPlugin.PLUGIN_ID, "Unable to add the auto inclusion builder", e));
			return retStatus;
		}

		if (shouldGenerate) {
			out.println("Targeting location " + destinationDirectory + " for code generation...");

			try {
				generateCode(impl, implSettings, project, componentName, out, err, progress.newChild(GENERATE_CODE_WORK), generateFiles, crcMap);
			} catch (final CoreException e) {
				retStatus.add(new Status(IStatus.ERROR, PythonGeneratorPlugin.PLUGIN_ID, "Unable to generate code", e));
				return retStatus;
			}
			final IStatus status = cleanupSourceFolders(project, progress.newChild(CLEANUP_PROJECT_WORK));
			if (!status.isOK()) {
				retStatus.add(status);
				if (status.getSeverity() == IStatus.ERROR) {
					return retStatus;
				}
			}
		}

		pythonNature.rebuildPath();

		return retStatus;
	}

	/**
	 * Ensures the the auto-inclusion builder is added to a project.
	 * 
	 * @param project The project to add the auto-inclusion builder to
	 * @param progress The progress monitor to use for reporting progress to the
	 *            user. It is the caller's responsibility to call done() on the
	 *            given monitor. Accepts <code>null</code>, indicating that no
	 *            progress should be reported and that the operation cannot be
	 *            canceled.
	 * @throws CoreException There is a problem adding the builder to the project
	 */
	private void addAutoInclusionBuilder(final IProject project, final IProgressMonitor progress) throws CoreException {
		final ICommand[] oldBuildCommands = project.getDescription().getBuildSpec();
		for (final ICommand buildCommand : oldBuildCommands) {
			if (buildCommand.getBuilderName().equals(PythonSourceInclude.BUILDER_NAME)) {
				return;
			}
		}
		final IProjectDescription description = project.getDescription();
		final ICommand newBuildCommand = description.newCommand();
		newBuildCommand.setBuilderName(PythonSourceInclude.BUILDER_NAME);
		final ICommand[] newBuildCommands = new ICommand[oldBuildCommands.length + 1];
		System.arraycopy(oldBuildCommands, 0, newBuildCommands, 1, oldBuildCommands.length);
		newBuildCommands[0] = newBuildCommand;
		description.setBuildSpec(newBuildCommands);
		project.setDescription(description, progress);
	}

	@Override
	public IStatus cleanupSourceFolders(final IProject project, final IProgressMonitor monitor) {
		final SubMonitor progress = SubMonitor.convert(monitor, "Correcting source folders", 1);

		IPythonNature pythonNature;
		try {
			pythonNature = (IPythonNature) project.getNature(PythonNature.PYTHON_NATURE_ID);
		} catch (final CoreException e) {
			return new Status(IStatus.WARNING, PythonGeneratorPlugin.PLUGIN_ID, "Unable to get Python nature to clean-up source folders", e);
		}

		Set<String> sourcePaths = Collections.emptySet();
		if (pythonNature != null) {
			final IPythonPathNature pathNature = pythonNature.getPythonPathNature();
			if (pathNature != null) {
				try {
					sourcePaths = pathNature.getProjectSourcePathSet(true);
				} catch (final CoreException e) {
					return new Status(IStatus.WARNING, PythonGeneratorPlugin.PLUGIN_ID, "Unable to get the list of source code folders for the project");
				}
			}
		}

		final StringBuilder newPath = new StringBuilder();
		for (String sourcePath : sourcePaths) {
			if (project.getWorkspace().getRoot().getFolder(new Path(sourcePath)).exists()) {
				if (newPath.length() > 0) {
					newPath.append("|");
				}
				newPath.append(sourcePath);
			}
		}

		if (newPath.length() > 0) {
			if (pythonNature != null) {
				try {
					pythonNature.getPythonPathNature().setProjectSourcePath(newPath.toString());
				} catch (final CoreException e) {
					return new Status(IStatus.WARNING, PythonGeneratorPlugin.PLUGIN_ID, "Unable to adjust the list of source code folders for the project");
				}
			}
			progress.worked(1);
		} else {
			try {
				PythonNature.removeNature(project, progress.newChild(1));
			} catch (final CoreException e) {
				return new Status(IStatus.WARNING, PythonGeneratorPlugin.PLUGIN_ID, "Unable to remove Python nature when cleaning up source folders");
			}
		}

		return new Status(IStatus.OK, PythonGeneratorPlugin.PLUGIN_ID, "No problems performing source folder clean-up");
	}

	/**
	 * @param monitor The progress monitor to use for reporting progress to the user. It is the caller's responsibility
	 *  to call done() on the given monitor. Accepts null, indicating that no progress should be
	 *  reported and that the operation cannot be canceled.
	 * @since 5.0
	 */
	protected abstract void generateCode(Implementation impl, ImplementationSettings implSettings, IProject project, String componentName, // SUPPRESS CHECKSTYLE Parameters
		PrintStream out, PrintStream err, IProgressMonitor monitor, String[] generateFiles, List<FileToCRCMap> crcMap) throws CoreException;

	/**
	 * @since 5.0
	 */
	@Override
	public IStatus validate() {
		final IInterpreterManager interpreterManager = InterpreterManagersAPI.getPythonInterpreterManager();
		if (!interpreterManager.isConfigured()) {
			return new Status(IStatus.ERROR, PythonGeneratorPlugin.PLUGIN_ID, "Configure the Python Interpreter before attempting code generation.");
		} else {
			return new Status(IStatus.OK, PythonGeneratorPlugin.PLUGIN_ID, "Validation ok");
		}
	}

}
