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

package main.java.scase.pimMetaModel;

import main.java.scase.cimMetaModel.Property;
import main.java.scase.customUtilities.UniqueIdProducer;


public class PIMComponentProperty{
	
	protected int iResourceModelPropertyId;
	protected String strResourceModelPropertyName;
	protected String strResourceModelPropertyType;
	protected boolean bIsUnique;
	protected Property oParentProperty;
	
	public PIMComponentProperty(Property oResourceProperty){
		this.iResourceModelPropertyId = UniqueIdProducer.getNewUniqueId();
		this.strResourceModelPropertyName = oResourceProperty.getPropertyName();
		this.strResourceModelPropertyType = oResourceProperty.getPropertyType();
		this.bIsUnique = oResourceProperty.getPropertyUniqueness();
		this.oParentProperty = oResourceProperty;
		capitalizeFirstNameLetter();
	}
	
	public PIMComponentProperty(String strResourceModelPropertyName, String strResourceModelPropertyType){
		this.iResourceModelPropertyId = UniqueIdProducer.getNewUniqueId();
		this.strResourceModelPropertyName = strResourceModelPropertyName;
		this.strResourceModelPropertyType = strResourceModelPropertyType;
		this.bIsUnique = true;
		this.oParentProperty = null;
		capitalizeFirstNameLetter();
	}
	
	public PIMComponentProperty(String strResourceModelPropertyName, String strResourceModelPropertyType, boolean bIsUnique){
		this.iResourceModelPropertyId = UniqueIdProducer.getNewUniqueId();
		this.strResourceModelPropertyName = strResourceModelPropertyName;
		this.strResourceModelPropertyType = strResourceModelPropertyType;
		this.bIsUnique = bIsUnique;
		this.oParentProperty = null;
		capitalizeFirstNameLetter();
	}
	
	private void capitalizeFirstNameLetter(){
		this.strResourceModelPropertyName = String.format("%s%s", this.strResourceModelPropertyName.substring(0, 1).toUpperCase(), this.strResourceModelPropertyName.substring(1));
	}
	
	public int getPIMComponentPropertyId(){
		return this.iResourceModelPropertyId;
	}
	
	public String getPIMComponentProeprtyName(){
		return this.strResourceModelPropertyName;
	}
	
	public String getPIMComponentPropertyType(){
		return this.strResourceModelPropertyType;
	}
	
	public boolean getPIMComponentPropertyUniqueness(){
		return this.bIsUnique;
	}
	
	public Property getParentProperty(){
		return this.oParentProperty;
	}
	
	public void printPIMComponentProperty(){
		System.out.println("Property " + this.strResourceModelPropertyName + " with id " + this.iResourceModelPropertyId + " is added to PIM because " + (this.oParentProperty != null ? this.oParentProperty.getPropertyName() + " exists in CIM" : " it is primary identifier or link list property"));
		System.out.println("Property Type: " + this.strResourceModelPropertyType);
		System.out.println("Property multiplicity: " + (this.bIsUnique == true ? "unique":"multiple"));
	}
}