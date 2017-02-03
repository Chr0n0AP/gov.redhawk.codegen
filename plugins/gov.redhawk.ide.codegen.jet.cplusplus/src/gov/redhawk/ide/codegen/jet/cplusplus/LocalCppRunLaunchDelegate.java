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
package gov.redhawk.ide.codegen.jet.cplusplus;

import org.eclipse.cdt.debug.core.ICDTLaunchConfigurationConstants;
import org.eclipse.cdt.launch.internal.LocalRunLaunchDelegate;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

import gov.redhawk.ide.debug.SpdLauncherUtil;
import mil.jpeojtrs.sca.spd.SoftPkg;

/**
 * Used when a C++ component, device, etc. in the <b>workspace</b> is launched in the sandbox in <b>run</b> mode.
 * @since 11.0
 */
@SuppressWarnings("restriction")
public class LocalCppRunLaunchDelegate extends LocalRunLaunchDelegate {

	public static final String ID = "gov.redhawk.ide.codegen.jet.cplusplus.launchComponentApplication";

	public LocalCppRunLaunchDelegate() {
	}

	@Override
	public void launch(final ILaunchConfiguration config, final String mode, final ILaunch launch, final IProgressMonitor monitor) throws CoreException {
		final int WORK_LAUNCH = 10;
		final int WORK_POST_LAUNCH = 100;
		SubMonitor subMonitor = SubMonitor.convert(monitor, WORK_LAUNCH + WORK_POST_LAUNCH);

		// Validate all XML before doing anything else
		final SoftPkg spd = SpdLauncherUtil.getSpd(config);
		IStatus status = SpdLauncherUtil.validateAllXML(spd);
		if (!status.isOK()) {
			throw new CoreException(status);
		}

		final ILaunchConfigurationWorkingCopy copy = config.getWorkingCopy();
		insertProgramArguments(spd, launch, copy);

		try {
			super.launch(copy, mode, launch, subMonitor.newChild(WORK_LAUNCH));
			SpdLauncherUtil.postLaunch(spd, copy, mode, launch, subMonitor.newChild(WORK_POST_LAUNCH));
		} finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}

	/**
	 * @since 10.0
	 */
	protected void insertProgramArguments(final SoftPkg spd, final ILaunch launch, final ILaunchConfigurationWorkingCopy configuration) throws CoreException {
		final String args = configuration.getAttribute(ICDTLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS, "");

		final String newArgs = SpdLauncherUtil.insertProgramArguments(spd, args, launch, configuration);
		configuration.setAttribute(ICDTLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS, newArgs);
	}
}
