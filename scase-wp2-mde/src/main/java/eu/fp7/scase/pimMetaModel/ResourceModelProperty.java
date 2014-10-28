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


public class ResourceModelProperty extends PIMComponentProperty{
	
	private boolean bIsNamingProperty;
	private boolean bIsPrimaryIdentifier;
	
	public ResourceModelProperty(Property oResourceProperty){ //constructor used to build a PIM property that is corresponding to a CIM one
		super(oResourceProperty);
		this.bIsNamingProperty = oResourceProperty.getPropertyNamingAbility();
		this.bIsPrimaryIdentifier = false;
	}
	
	//Constructor used to build a PIM property that is primary identifier
	public ResourceModelProperty(String strPropertyName, String strPropertyType){ 
		super(strPropertyName, strPropertyType);
		this.bIsNamingProperty = false;
		this.bIsPrimaryIdentifier = true;
	}
	
	//Constructor used to build other PIM properties such as linklist etc
	public ResourceModelProperty(String strPropertyName, String strPropertyType, boolean bIsUnique){ 
		super(strPropertyName, strPropertyType, bIsUnique);
		this.bIsNamingProperty = false;
		this.bIsPrimaryIdentifier = false;
	}
	
	public boolean getResourceModelPropertyNamingAbility(){
		return this.bIsNamingProperty;
	}
	
	public void printResourceModelProperty(){
		super.printPIMComponentProperty();
		System.out.println("Property naming ability: " + (this.bIsNamingProperty == true ? "It is naming property" : "It is not naming property"));
		System.out.println("Property identifying ability: " + (this.bIsPrimaryIdentifier == true? "It is primary identifier" : "It is not primary identifier"));
	}
	
	public boolean getResourceModelIdentifyingAbility(){
		return this.bIsPrimaryIdentifier;
	}
}