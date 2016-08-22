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
package gov.redhawk.ide.codegen.frontend.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.databinding.viewers.ObservableSetContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeColumnViewerLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import FRONTEND.FE_TUNER_DEVICE_KIND;
import gov.redhawk.ide.codegen.frontend.ui.wizard.FrontEndProp;
import gov.redhawk.ide.codegen.frontend.ui.wizard.FrontEndPropLabelProvider;
import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.Enumerations;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;

public enum FrontEndDeviceUIUtils {
	INSTANCE;

	public static final String TUNER_DEVICE_KIND_NAME = FE_TUNER_DEVICE_KIND.value;

	// Antenna device kind name is still in flux but should eventually be defined within the IDL
	public static final String ANTENNA_DEVICE_KIND_NAME = "FRONTEND::RFSOURCE";

	public static final String TUNER_STATUS_STRUCT_NAME = "frontend_tuner_status_struct";
	public static final String TUNER_STATUS_STRUCT_ID = "FRONTEND::tuner_status_struct";

	public static final String TUNER_STATUS_STRUCT_SEQ_NAME = "frontend_tuner_status";
	public static final String TUNER_STATUS_STRUCT_SEQ_ID = "FRONTEND::tuner_status";
	public static final String TUNER_STATUS_STRUCT_SEQ_DESCRIPTION = "Status of each tuner, including entries for both allocated and un-allocated tuners. Each entry represents a single tuner.";

	private List<FrontEndProp> allFrontEndProps = null;
	private List<FrontEndProp> optionalFrontEndProps = null;
	private List<FrontEndProp> requiredFrontEndProps = null;

	private FrontEndDeviceUIUtils() {
		initFriProps();
	}

	private void initFriProps() {

		allFrontEndProps = new ArrayList<FrontEndProp>();
		optionalFrontEndProps = new ArrayList<FrontEndProp>();
		requiredFrontEndProps = new ArrayList<FrontEndProp>();

		// Required Props

		allFrontEndProps.add(new FrontEndProp(createSimple("tuner_type", "FRONTEND::tuner_status::tuner_type", PropertyValueType.STRING,
				"Example Tuner Types: TX, RX, CHANNELIZER, DDC, RX_DIGITIZER, RX_DIGTIZIER_CHANNELIZER"), true));

		allFrontEndProps.add(new FrontEndProp(createSimple("allocation_id_csv", "FRONTEND::tuner_status::allocation_id_csv", PropertyValueType.STRING,
				"Comma separated list of current Allocation IDs."), true));

		Simple centerFreq = createSimple("center_frequency", "FRONTEND::tuner_status::center_frequency", PropertyValueType.DOUBLE,
				"Current center frequency in Hz.");

		centerFreq.setUnits("Hz");

		allFrontEndProps.add(new FrontEndProp(centerFreq, true));

		Simple bandwidth = createSimple("bandwidth", "FRONTEND::tuner_status::bandwidth", PropertyValueType.DOUBLE, "Current bandwidth in Hz");

		bandwidth.setUnits("Hz");

		allFrontEndProps.add(new FrontEndProp(bandwidth, true));

		Simple sampleRate = createSimple("sample_rate", "FRONTEND::tuner_status::sample_rate", PropertyValueType.DOUBLE,
				"Current sample rate in samples per second.");

		sampleRate.setUnits("sps");

		allFrontEndProps.add(new FrontEndProp(sampleRate, true));

		allFrontEndProps.add(new FrontEndProp(createSimple("group_id", "FRONTEND::tuner_status::group_id", PropertyValueType.STRING,
				"Unique ID that specifies a group of Device."), true));

		allFrontEndProps.add(new FrontEndProp(createSimple("rf_flow_id", "FRONTEND::tuner_status::rf_flow_id", PropertyValueType.STRING,
				"Specifies a certain RF flow to allocate against."), true));

		allFrontEndProps.add(new FrontEndProp(createSimple("enabled", "FRONTEND::tuner_status::enabled", PropertyValueType.BOOLEAN,
				"Indicates if tuner is enabled, in reference to the output state of the tuner."), true));

		// Optional Props

		Simple bandwidthTolerence = createSimple("bandwidth_tolerance", "FRONTEND::tuner_status::bandwidth_tolerance", PropertyValueType.DOUBLE,
				"Allowable percentage over requested bandwidth. This value is provided by the requester during allocation.");

		bandwidthTolerence.setUnits("%");

		allFrontEndProps.add(new FrontEndProp(bandwidthTolerence, false));

		Simple sampleRateTolerence = createSimple("sample_rate_tolerance", "FRONTEND::tuner_status::sample_rate_tolerance", PropertyValueType.DOUBLE,
				"Allowable percentage over requested sample rate. This value is provided by the requester during allocation.");

		sampleRateTolerence.setUnits("%");

		allFrontEndProps.add(new FrontEndProp(sampleRateTolerence, false));

		allFrontEndProps.add(new FrontEndProp(createSimple("complex", "FRONTEND::tuner_status::complex", PropertyValueType.BOOLEAN,
				"Indicates if the output data is complex."), false));

		Simple gain = createSimple("gain", "FRONTEND::tuner_status::gain", PropertyValueType.DOUBLE, "Current gain in dB.");

		gain.setUnits("dB");

		allFrontEndProps.add(new FrontEndProp(gain, false));

		allFrontEndProps.add(new FrontEndProp(createSimple("agc", "FRONTEND::tuner_status::agc", PropertyValueType.BOOLEAN,
				"Indicates if the tuner has Automatic Gain Control enabled."), false));

		allFrontEndProps.add(new FrontEndProp(
			createSimple(
				"valid",
				"FRONTEND::tuner_status::valid",
				PropertyValueType.BOOLEAN,
					"Indicates if the tuner is in a valid state. For DDCs, false indicates the attached CHANNELIZER has moved such that the DDC can no longer tune to the appropriate frequency."),
					false));

		Simple availableFreq = createSimple("available_frequency", "FRONTEND::tuner_status::available_frequency", PropertyValueType.STRING,
				"Available frequencies (Hz) in range (XX-YY) or csv (X,Y,Z) format. Do not put units in field.");

		availableFreq.setUnits("Hz");

		allFrontEndProps.add(new FrontEndProp(availableFreq, false));

		Simple availableBandwidth = createSimple("available_bandwidth", "FRONTEND::tuner_status::available_bandwidth", PropertyValueType.STRING,
				"Available bandwidth (Hz) in range (XX-YY) or csv (X,Y,Z) format. Do not put units in field.");

		availableBandwidth.setUnits("Hz");

		allFrontEndProps.add(new FrontEndProp(availableBandwidth, false));

		Simple availableGain = createSimple("available_gain", "FRONTEND::tuner_status::available_gain", PropertyValueType.STRING,
				"Available gain (dB) in range (XX-YY) or csv (X,Y,Z) format. Do not put units in field.");

		availableGain.setUnits("dB");

		allFrontEndProps.add(new FrontEndProp(availableGain, false));

		Simple availableSampleRate = createSimple("available_sample_rate", "FRONTEND::tuner_status::available_sample_rate", PropertyValueType.STRING,
				"Available sample_rate (sps) in range (XX-YY) or csv (X,Y,Z) format. Do not put units in field.");

		availableSampleRate.setUnits("sps");

		allFrontEndProps.add(new FrontEndProp(availableSampleRate, false));

		Simple referenceSource = createSimple("reference_source", "FRONTEND::tuner_status::reference_source", PropertyValueType.LONG,
				"Indicates internal (0) vs external (1) reference source.");

		Enumerations refSourceEnumerations = PrfFactory.eINSTANCE.createEnumerations();

		Enumeration internalEnum = PrfFactory.eINSTANCE.createEnumeration();
		internalEnum.setLabel("INTERNAL");
		internalEnum.setValue("0");
		refSourceEnumerations.getEnumeration().add(internalEnum);

		Enumeration externalEnum = PrfFactory.eINSTANCE.createEnumeration();
		externalEnum.setLabel("EXTERNAL");
		externalEnum.setValue("1");
		refSourceEnumerations.getEnumeration().add(externalEnum);

		referenceSource.setEnumerations(refSourceEnumerations);

		allFrontEndProps.add(new FrontEndProp(referenceSource, false));

		Simple outputFormat = createSimple("output_format", "FRONTEND::tuner_status::output_format", PropertyValueType.STRING,
				"Indicates the SDDS digraph that describes the current output data format.");

		Enumerations enumList = PrfFactory.eINSTANCE.createEnumerations();

		Enumeration sp = PrfFactory.eINSTANCE.createEnumeration();
		sp.setLabel("SP");
		sp.setValue("SP");
		enumList.getEnumeration().add(sp);

		Enumeration sb = PrfFactory.eINSTANCE.createEnumeration();
		sb.setLabel("SB");
		sb.setValue("SB");
		enumList.getEnumeration().add(sb);

		Enumeration si = PrfFactory.eINSTANCE.createEnumeration();
		si.setLabel("SI");
		si.setValue("SI");
		enumList.getEnumeration().add(si);

		Enumeration sl = PrfFactory.eINSTANCE.createEnumeration();
		sl.setLabel("SL");
		sl.setValue("SL");
		enumList.getEnumeration().add(sl);

		Enumeration sx = PrfFactory.eINSTANCE.createEnumeration();
		sx.setLabel("SX");
		sx.setValue("SX");
		enumList.getEnumeration().add(sx);

		Enumeration sf = PrfFactory.eINSTANCE.createEnumeration();
		sf.setLabel("SF");
		sf.setValue("SF");
		enumList.getEnumeration().add(sf);

		Enumeration sd = PrfFactory.eINSTANCE.createEnumeration();
		sd.setLabel("SD");
		sd.setValue("SD");
		enumList.getEnumeration().add(sd);

		Enumeration cb = PrfFactory.eINSTANCE.createEnumeration();
		cb.setLabel("CB");
		cb.setValue("CB");
		enumList.getEnumeration().add(cb);

		Enumeration ci = PrfFactory.eINSTANCE.createEnumeration();
		ci.setLabel("CI");
		ci.setValue("CI");
		enumList.getEnumeration().add(ci);

		Enumeration cl = PrfFactory.eINSTANCE.createEnumeration();
		cl.setLabel("CL");
		cl.setValue("CL");
		enumList.getEnumeration().add(cl);

		Enumeration cx = PrfFactory.eINSTANCE.createEnumeration();
		cx.setLabel("CX");
		cx.setValue("CX");
		enumList.getEnumeration().add(cx);

		Enumeration cf = PrfFactory.eINSTANCE.createEnumeration();
		cf.setLabel("CF");
		cf.setValue("CF");
		enumList.getEnumeration().add(cf);

		Enumeration cd = PrfFactory.eINSTANCE.createEnumeration();
		cd.setLabel("CD");
		cd.setValue("CD");
		enumList.getEnumeration().add(cd);

		outputFormat.setEnumerations(enumList);

		allFrontEndProps.add(new FrontEndProp(outputFormat, false));

		allFrontEndProps.add(new FrontEndProp(createSimple("output_multicast", "FRONTEND::tuner_status::output_multicast", PropertyValueType.STRING,
				"Multicast address for SDDS output."), false));

		allFrontEndProps.add(new FrontEndProp(createSimple("output_vlan", "FRONTEND::tuner_status::output_vlan", PropertyValueType.LONG,
				"VLAN number for SDDS output. A value of zero (0) indicates no VLAN is used."), false));

		allFrontEndProps.add(new FrontEndProp(createSimple("output_port", "FRONTEND::tuner_status::output_port", PropertyValueType.LONG,
				"Port number for SDDS output."), false));

		allFrontEndProps.add(new FrontEndProp(createSimple("decimation", "FRONTEND::tuner_status::decimation", PropertyValueType.LONG,
				"Current decimation of tuner. For DDC tuners, this is the ratio of input sample rate to output sample rate regardless of data format."), false));

		allFrontEndProps.add(new FrontEndProp(createSimple("tuner_number", "FRONTEND::tuner_status::tuner_number", PropertyValueType.SHORT,
				"Physical tuner ID."), false));

		for (FrontEndProp prop : allFrontEndProps) {
			if (prop.isRequired()) {
				this.requiredFrontEndProps.add(prop);
			} else {
				this.optionalFrontEndProps.add(prop);
			}
		}
	}

	private Simple createSimple(String name, String id, PropertyValueType type, String description) {
		Simple tmpProp = PrfFactory.eINSTANCE.createSimple();
		tmpProp.setName(name);
		tmpProp.setId(id);
		tmpProp.setType(type);
		tmpProp.setDescription(description);

		return tmpProp;
	}

	/**
	 * Provides a List of the front end props each indicating if they are required or not.
	 * @return The list of Front end properties.
	 */
	public List<FrontEndProp> getAllFrontEndProps() {
		return allFrontEndProps;
	}

	public List<FrontEndProp> getRequiredFrontEndProps() {
		return requiredFrontEndProps;
	}

	public List<FrontEndProp> getOptionalFrontEndProps() {
		return optionalFrontEndProps;
	}

	public CheckboxTableViewer getCheckboxTableViewer(Composite parent) {
		CheckboxTableViewer theTableViewer = new CheckboxTableViewer(createTable(parent, SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL
			| SWT.H_SCROLL));
		theTableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		theTableViewer.setSorter(getTableSorter());

		addColumns(theTableViewer);

		return theTableViewer;
	}

	public TableViewer getTableViewer(Composite parent) {
		// Define the Table Viewer
		TableViewer theTableViewer = new TableViewer(createTable(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL));

		theTableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		theTableViewer.setSorter(getTableSorter());

		addColumns(theTableViewer);

		return theTableViewer;
	}

	private Table createTable(Composite parent, int style) {
		// Define table layout
		TableLayout theTableLayout = new TableLayout();
		theTableLayout.addColumnData(new ColumnWeightData(30));
		theTableLayout.addColumnData(new ColumnWeightData(10));
		theTableLayout.addColumnData(new ColumnWeightData(10));
		theTableLayout.addColumnData(new ColumnWeightData(50));

		// Define the table
		Table theTable = new Table(parent, style);
		theTable.setLinesVisible(true);
		theTable.setHeaderVisible(true);
		theTable.setLayout(theTableLayout);

		return theTable;
	}

	private void addColumns(TableViewer theTableViewer) {
		ColumnViewerToolTipSupport.enableFor(theTableViewer);

		final FrontEndPropLabelProvider lp = new FrontEndPropLabelProvider();

		TableViewerColumn nameIDColumn = new TableViewerColumn(theTableViewer, SWT.NONE);
		nameIDColumn.setLabelProvider(new TreeColumnViewerLabelProvider(lp) {
			@Override
			public String getText(Object element) {
				return lp.getColumnText(element, 0);
			}
		});
		nameIDColumn.getColumn().setText("Name/ID");

		TableViewerColumn typeColumn = new TableViewerColumn(theTableViewer, SWT.NONE);
		typeColumn.setLabelProvider(new TreeColumnViewerLabelProvider(lp) {
			@Override
			public String getText(Object element) {
				return lp.getColumnText(element, 1);
			}
		});
		typeColumn.getColumn().setText("Type");

		TableViewerColumn required = new TableViewerColumn(theTableViewer, SWT.NONE);
		required.setLabelProvider(new TreeColumnViewerLabelProvider(lp) {
			@Override
			public String getText(Object element) {
				return lp.getColumnText(element, 2);
			}
		});
		required.getColumn().setText("Required");

		TableViewerColumn descColumn = new TableViewerColumn(theTableViewer, SWT.NONE);
		descColumn.getColumn().setText("Description");
		descColumn.setLabelProvider(new TreeColumnViewerLabelProvider(lp) {

			@Override
			public String getText(Object element) {
				return lp.getColumnText(element, 3);
			}

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 50;
			}

			@Override
			public String getToolTipText(Object element) {
				return getText(element);
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 5000;
			}

			@Override
			public boolean useNativeToolTip(Object object) {
				return true;
			}
		});

		theTableViewer.setContentProvider(new ObservableSetContentProvider());
	}

	private ViewerSorter getTableSorter() {
		ViewerSorter vs = new ViewerSorter() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				FrontEndProp frontEndProp1 = (FrontEndProp) e1;
				FrontEndProp frontEndProp2 = (FrontEndProp) e2;

				// Required properties should be displayed last since the user will only really interact with non-required.
				if (frontEndProp1.isRequired() != frontEndProp2.isRequired()) {
					if (frontEndProp1.isRequired()) {
						return 1;
					} else {
						return -1;
					}
				}

				// If they are both in the same category sort by Name/Id alphabetically
				return frontEndProp1.getProp().getId().compareTo(frontEndProp2.getProp().getId());
			}
		};
		return vs;
	}
}
