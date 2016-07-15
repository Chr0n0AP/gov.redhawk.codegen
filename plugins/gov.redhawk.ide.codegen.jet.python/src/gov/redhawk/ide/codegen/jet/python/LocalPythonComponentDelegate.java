/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.ide.codegen.jet.python;

import gov.redhawk.ide.debug.SpdLauncherUtil;
import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.core.externaltools.internal.IExternalToolConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.python.pydev.debug.ui.launching.RegularLaunchConfigurationDelegate;

/**
 * Used when a Python component, device, etc. in the workspace is launched in the sandbox.
 * @since 8.0
 */
public class LocalPythonComponentDelegate extends RegularLaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	public static final String ID = "gov.redhawk.ide.codegen.jet.python.launchComponent";

	@Override
	public void launch(final ILaunchConfiguration conf, final String mode, final ILaunch launch, final IProgressMonitor monitor) throws CoreException {
		final int WORK_LAUNCH = 10;
		final int WORK_POST_LAUNCH = 100;
		SubMonitor subMonitor = SubMonitor.convert(monitor, WORK_LAUNCH + WORK_POST_LAUNCH);

		// Validate all XML before doing anything else
		final SoftPkg spd = SpdLauncherUtil.getSpd(conf);
		IStatus status = SpdLauncherUtil.validateAllXML(spd);
		if (!status.isOK()) {
			throw new CoreException(status);
		}

		final ILaunchConfigurationWorkingCopy workingCopy = conf.getWorkingCopy();
		insertProgramArguments(spd, launch, workingCopy);

		try {
			super.launch(workingCopy, mode, launch, subMonitor.newChild(WORK_LAUNCH));
			SpdLauncherUtil.postLaunch(spd, workingCopy, mode, launch, subMonitor.newChild(WORK_POST_LAUNCH));
		} finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}

	/**
	 * @since 9.0
	 */
	protected void insertProgramArguments(final SoftPkg spd, final ILaunch launch, final ILaunchConfigurationWorkingCopy configuration) throws CoreException {
		final String args = configuration.getAttribute(IExternalToolConstants.ATTR_TOOL_ARGUMENTS, "");
		final String scaArgs = SpdLauncherUtil.insertProgramArguments(spd, args, launch, configuration);
		configuration.setAttribute(IExternalToolConstants.ATTR_TOOL_ARGUMENTS, scaArgs);
	}

}
