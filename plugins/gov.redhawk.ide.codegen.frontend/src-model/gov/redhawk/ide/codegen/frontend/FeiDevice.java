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
// BEGIN GENERATED CODE
package gov.redhawk.ide.codegen.frontend;

import gov.redhawk.eclipsecorba.idl.Definition;

import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fei Device</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isAntenna <em>Antenna</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isIngestsGPS <em>Ingests GPS</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isOutputsGPS <em>Outputs GPS</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isRxTuner <em>Rx Tuner</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getNumberOfAnalogInputs <em>Number Of Analog Inputs</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isHasDigitalInput <em>Has Digital Input</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getDigitalInputType <em>Digital Input Type</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isHasDigitalOutput <em>Has Digital Output</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getDigitalOutputType <em>Digital Output Type</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isMultiOut <em>Multi Out</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isTxTuner <em>Tx Tuner</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getNumberOfDigitalInputsForTx <em>Number Of Digital Inputs For
 * Tx</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getDigitalInputTypeForTx <em>Digital Input Type For
 * Tx</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isScanner <em>Scanner</em>}</li>
 * <li>{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getTunerStatusStruct <em>Tuner Status Struct</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice()
 * @model
 * @generated
 */
public interface FeiDevice extends EObject {
	/**
	 * Returns the value of the '<em><b>Antenna</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * @deprecated Creating FEI antenna devices is not supported. Do not use.
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Antenna</em>' attribute.
	 * @see #setAntenna(boolean)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_Antenna()
	 * @model unique="false"
	 * @generated
	 */
	@Deprecated
	boolean isAntenna();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isAntenna <em>Antenna</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Antenna</em>' attribute.
	 * @see #isAntenna()
	 * @generated
	 */
	void setAntenna(boolean value);

	/**
	 * Returns the value of the '<em><b>Ingests GPS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ingests GPS</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ingests GPS</em>' attribute.
	 * @see #setIngestsGPS(boolean)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_IngestsGPS()
	 * @model unique="false"
	 * @generated
	 */
	boolean isIngestsGPS();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isIngestsGPS <em>Ingests GPS</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ingests GPS</em>' attribute.
	 * @see #isIngestsGPS()
	 * @generated
	 */
	void setIngestsGPS(boolean value);

	/**
	 * Returns the value of the '<em><b>Outputs GPS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outputs GPS</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outputs GPS</em>' attribute.
	 * @see #setOutputsGPS(boolean)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_OutputsGPS()
	 * @model unique="false"
	 * @generated
	 */
	boolean isOutputsGPS();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isOutputsGPS <em>Outputs GPS</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outputs GPS</em>' attribute.
	 * @see #isOutputsGPS()
	 * @generated
	 */
	void setOutputsGPS(boolean value);

	/**
	 * Returns the value of the '<em><b>Rx Tuner</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rx Tuner</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rx Tuner</em>' attribute.
	 * @see #setRxTuner(boolean)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_RxTuner()
	 * @model default="true" unique="false"
	 * @generated
	 */
	boolean isRxTuner();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isRxTuner <em>Rx Tuner</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rx Tuner</em>' attribute.
	 * @see #isRxTuner()
	 * @generated
	 */
	void setRxTuner(boolean value);

	/**
	 * Returns the value of the '<em><b>Number Of Analog Inputs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Analog Inputs</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Analog Inputs</em>' attribute.
	 * @see #setNumberOfAnalogInputs(int)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_NumberOfAnalogInputs()
	 * @model unique="false"
	 * @generated
	 */
	int getNumberOfAnalogInputs();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getNumberOfAnalogInputs <em>Number Of
	 * Analog Inputs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Analog Inputs</em>' attribute.
	 * @see #getNumberOfAnalogInputs()
	 * @generated
	 */
	void setNumberOfAnalogInputs(int value);

	/**
	 * Returns the value of the '<em><b>Has Digital Input</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Digital Input</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Digital Input</em>' attribute.
	 * @see #setHasDigitalInput(boolean)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_HasDigitalInput()
	 * @model default="false" unique="false"
	 * @generated
	 */
	boolean isHasDigitalInput();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isHasDigitalInput <em>Has Digital
	 * Input</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Digital Input</em>' attribute.
	 * @see #isHasDigitalInput()
	 * @generated
	 */
	void setHasDigitalInput(boolean value);

	/**
	 * Returns the value of the '<em><b>Digital Input Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Digital Input Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Digital Input Type</em>' attribute.
	 * @see #setDigitalInputType(Definition)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_DigitalInputType()
	 * @model unique="false" dataType="gov.redhawk.ide.codegen.frontend.IDLDef"
	 * @generated
	 */
	Definition getDigitalInputType();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getDigitalInputType <em>Digital Input
	 * Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Digital Input Type</em>' attribute.
	 * @see #getDigitalInputType()
	 * @generated
	 */
	void setDigitalInputType(Definition value);

	/**
	 * Returns the value of the '<em><b>Has Digital Output</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Digital Output</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Digital Output</em>' attribute.
	 * @see #setHasDigitalOutput(boolean)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_HasDigitalOutput()
	 * @model default="true" unique="false"
	 * @generated
	 */
	boolean isHasDigitalOutput();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isHasDigitalOutput <em>Has Digital
	 * Output</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Digital Output</em>' attribute.
	 * @see #isHasDigitalOutput()
	 * @generated
	 */
	void setHasDigitalOutput(boolean value);

	/**
	 * Returns the value of the '<em><b>Digital Output Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Digital Output Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Digital Output Type</em>' attribute.
	 * @see #setDigitalOutputType(Definition)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_DigitalOutputType()
	 * @model unique="false" dataType="gov.redhawk.ide.codegen.frontend.IDLDef"
	 * @generated
	 */
	Definition getDigitalOutputType();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getDigitalOutputType <em>Digital Output
	 * Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Digital Output Type</em>' attribute.
	 * @see #getDigitalOutputType()
	 * @generated
	 */
	void setDigitalOutputType(Definition value);

	/**
	 * Returns the value of the '<em><b>Multi Out</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multi Out</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multi Out</em>' attribute.
	 * @see #setMultiOut(boolean)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_MultiOut()
	 * @model default="true" unique="false"
	 * @generated
	 */
	boolean isMultiOut();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isMultiOut <em>Multi Out</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multi Out</em>' attribute.
	 * @see #isMultiOut()
	 * @generated
	 */
	void setMultiOut(boolean value);

	/**
	 * Returns the value of the '<em><b>Tx Tuner</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tx Tuner</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tx Tuner</em>' attribute.
	 * @see #setTxTuner(boolean)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_TxTuner()
	 * @model unique="false"
	 * @generated
	 */
	boolean isTxTuner();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isTxTuner <em>Tx Tuner</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tx Tuner</em>' attribute.
	 * @see #isTxTuner()
	 * @generated
	 */
	void setTxTuner(boolean value);

	/**
	 * Returns the value of the '<em><b>Number Of Digital Inputs For Tx</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Digital Inputs For Tx</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Digital Inputs For Tx</em>' attribute.
	 * @see #setNumberOfDigitalInputsForTx(int)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_NumberOfDigitalInputsForTx()
	 * @model unique="false"
	 * @generated
	 */
	int getNumberOfDigitalInputsForTx();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getNumberOfDigitalInputsForTx <em>Number
	 * Of Digital Inputs For Tx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Digital Inputs For Tx</em>' attribute.
	 * @see #getNumberOfDigitalInputsForTx()
	 * @generated
	 */
	void setNumberOfDigitalInputsForTx(int value);

	/**
	 * Returns the value of the '<em><b>Digital Input Type For Tx</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Digital Input Type For Tx</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Digital Input Type For Tx</em>' attribute.
	 * @see #setDigitalInputTypeForTx(Definition)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_DigitalInputTypeForTx()
	 * @model unique="false" dataType="gov.redhawk.ide.codegen.frontend.IDLDef"
	 * @generated
	 */
	Definition getDigitalInputTypeForTx();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getDigitalInputTypeForTx <em>Digital
	 * Input Type For Tx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Digital Input Type For Tx</em>' attribute.
	 * @see #getDigitalInputTypeForTx()
	 * @generated
	 */
	void setDigitalInputTypeForTx(Definition value);

	/**
	 * Returns the value of the '<em><b>Scanner</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scanner</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scanner</em>' attribute.
	 * @see #setScanner(boolean)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_Scanner()
	 * @model default="false" unique="false"
	 * @generated
	 */
	boolean isScanner();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#isScanner <em>Scanner</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scanner</em>' attribute.
	 * @see #isScanner()
	 * @generated
	 */
	void setScanner(boolean value);

	/**
	 * Returns the value of the '<em><b>Tuner Status Struct</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuner Status Struct</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuner Status Struct</em>' attribute.
	 * @see #setTunerStatusStruct(ScaStructProperty)
	 * @see gov.redhawk.ide.codegen.frontend.FrontendPackage#getFeiDevice_TunerStatusStruct()
	 * @model unique="false" dataType="gov.redhawk.ide.codegen.frontend.TunerStatusStruct"
	 * @generated
	 */
	ScaStructProperty getTunerStatusStruct();

	/**
	 * Sets the value of the '{@link gov.redhawk.ide.codegen.frontend.FeiDevice#getTunerStatusStruct <em>Tuner Status
	 * Struct</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tuner Status Struct</em>' attribute.
	 * @see #getTunerStatusStruct()
	 * @generated
	 */
	void setTunerStatusStruct(ScaStructProperty value);

} // FeiDevice
