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

package eu.fp7.scase.cimMetaModel;


import eu.fp7.scase.customUtilities.UniqueIdProducer;


public class Property
{
	private int iPropertyId;
	private String strPropertyName;
	private String strPropertyType;
	private boolean bIsUnique;
	private boolean bIsNamingProperty;
	
	public Property(){
		this.iPropertyId = UniqueIdProducer.getNewUniqueId();
	}
	
	public Property(String propertyName, String propertyType, boolean isUnique, boolean isNamingProperty){
		this.iPropertyId = UniqueIdProducer.getNewUniqueId();
		this.strPropertyName = propertyName;
		this.strPropertyType = propertyType;
		this.bIsUnique = isUnique;
		this.bIsNamingProperty = isNamingProperty;
	}

	public int getPropertyId(){
		return this.iPropertyId;
	}
	
	public String getPropertyName(){
		return this.strPropertyName;
	}
	
	public void setPropertyName(String name){
		this.strPropertyName = name;
	}
	
	public String getPropertyType(){
		return this.strPropertyType;
	}
	
	public void setPropertyType(String type){
		this.strPropertyType = type;
	}
	
	public boolean getPropertyUniqueness(){
		return this.bIsUnique;
	}
	
	public void setPropertyUniqueness(boolean bIsUnique){
		this.bIsUnique = bIsUnique;
	}
	
	public boolean getPropertyNamingAbility(){
		return this.bIsNamingProperty;
	}
	
	public void setPropertyNamingAbility(boolean bIsNamingProperty){
		this.bIsNamingProperty = bIsNamingProperty;
	}
	
	public void printPropertyDetails(){
		System.out.println("Property Id: " + this.iPropertyId);
		System.out.println("Property Name: " + this.strPropertyName);
		System.out.println("Property Type: " + this.strPropertyType);
		System.out.println("Property multiplicity: " + (this.bIsUnique == true ? "unique" : "multiple"));
		System.out.println("Property naming ability: " + (this.bIsNamingProperty == true? "yes" : "no"));
	}
	
}