/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI
 * Copyright (C) 2014
 * Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering
 * Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 *
 * Project             : S-CASE
 * WorkFile            : 
 * Compiler            : 
 * File Description    :
 * Document Description:
* Related Documents	   :
* Note				   :
* Programmer		   : Christoforos Zolotas
* Contact			   : christopherzolotas@issel.ee.auth.gr
*/

package eu.fp7.scase.psmMetaModel;

import eu.fp7.scase.customUtilities.UniqueIdProducer;
import eu.fp7.scase.pimMetaModel.PIMComponentProperty;


public class PSMComponentProperty{
	
	private int iPSMComponentPropertyId;
	private String strPSMComponentPropertyName;
	private String strPSMComponentPropertyType;
	private boolean bIsUnique;
	private PIMComponentProperty oParentPIMComponentProperty;
	
	public PSMComponentProperty(PIMComponentProperty oPIMComponentProperty){
		this.iPSMComponentPropertyId = UniqueIdProducer.getNewUniqueId();
		this.strPSMComponentPropertyName = oPIMComponentProperty.getPIMComponentProeprtyName();
		this.strPSMComponentPropertyType = oPIMComponentProperty.getPIMComponentPropertyType();
		this.bIsUnique = oPIMComponentProperty.getPIMComponentPropertyUniqueness();
		this.oParentPIMComponentProperty = oPIMComponentProperty;
		capitalizeFirstNameLetter();
	}

	public PSMComponentProperty(String strJavaPropertyName, String strJavaPropertyType, boolean bIsUnique){
		this.iPSMComponentPropertyId = UniqueIdProducer.getNewUniqueId();
		this.strPSMComponentPropertyName = strJavaPropertyName;
		this.strPSMComponentPropertyType = strJavaPropertyType;
		this.bIsUnique = bIsUnique;
		capitalizeFirstNameLetter();
	}
	
	private void capitalizeFirstNameLetter(){
		this.strPSMComponentPropertyName = String.format("%s%s", this.strPSMComponentPropertyName.substring(0, 1).toUpperCase(), this.strPSMComponentPropertyName.substring(1));
	}
	
	public int getPSMComponentPropertyId(){
		return this.iPSMComponentPropertyId;
	}
	
	public String getPSMComponentPropertyName(){
		return this.strPSMComponentPropertyName;
	}
	
	public String getPSMComponentPropertyType(){
		return this.strPSMComponentPropertyType;
	}
	
	public boolean getPSMComponentPropertyUniqueness(){
		return this.bIsUnique;
	}
	
	public PIMComponentProperty getParentPIMComponentProperty(){
		return this.oParentPIMComponentProperty;
	}
	
	public void printPSMComponentProperty(){
		System.out.println("Java Property " + this.strPSMComponentPropertyName + " with id " + this.iPSMComponentPropertyId + " is added to PSM because " +  (this.oParentPIMComponentProperty != null? this.oParentPIMComponentProperty.getPIMComponentProeprtyName()  + " exists in PIM" : " because it is a PSM constraint for hibernate"));
		System.out.println("Java Property Type: " + this.strPSMComponentPropertyType);
		System.out.println("Java Property multiplicity: " + (this.bIsUnique == true ? "unique":"multiple"));
	}
}