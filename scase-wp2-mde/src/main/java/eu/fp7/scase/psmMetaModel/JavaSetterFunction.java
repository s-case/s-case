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

package main.java.scase.psmMetaModel;

import main.java.scase.pimMetaModel.FunctionParameter;

public class JavaSetterFunction extends JavaFunction{
	
	private PSMComponentProperty oAccessedProperty;
	private JAXBAnnotation oXMLTransientJAXBAnnotation;
	
	public JavaSetterFunction(PSMComponentProperty oJavaModelProperty, boolean isHibernatePropertySetter){
		super(String.format("set%s", oJavaModelProperty.getPSMComponentPropertyName()));
		this.oAccessedProperty = oJavaModelProperty;
		this.setReturnParameter(new FunctionParameter("", "void", false, true));
		this.getFunctionParameters().add(new FunctionParameter(this.oAccessedProperty.getPSMComponentPropertyName(), this.oAccessedProperty.getPSMComponentPropertyType(), this.oAccessedProperty.getPSMComponentPropertyUniqueness(), false));
		if(isHibernatePropertySetter){
			this.oXMLTransientJAXBAnnotation = new JAXBAnnotation("@XmlTransient");
		}
	}
	
	public PSMComponentProperty getAccessedProperty(){
		return this.oAccessedProperty;
	}
	
	public JAXBAnnotation getXMLTransientJAXBAnnotation(){
		return this.oXMLTransientJAXBAnnotation;
	}
	
	public void setXMLTransientJAXBAnnotation(JAXBAnnotation oXMLTransientJAXBAnnotation){
		this.oXMLTransientJAXBAnnotation = oXMLTransientJAXBAnnotation;
	}
	
	@Override
	public void printJavaFunction(){
		System.out.println("The Java Setter Function: " + this.getJavaFunctionName() + " is added to PSM to set the value of " + this.oAccessedProperty.getPSMComponentPropertyName() + " property");
		super.printJavaFunction();
		if( this.oXMLTransientJAXBAnnotation != null){
			System.out.println("JAXB Annotation: " + this.getXMLTransientJAXBAnnotation().getJAXBAnnotationText());			
		}
	}
}