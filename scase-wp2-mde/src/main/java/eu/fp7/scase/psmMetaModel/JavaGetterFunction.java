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

import eu.fp7.scase.pimMetaModel.FunctionParameter;

public class JavaGetterFunction extends JavaFunction{
	
	private PSMComponentProperty oAccessedProeprty;
	
	public JavaGetterFunction(PSMComponentProperty oAccessedProperty){
		super(String.format("get%s", oAccessedProperty.getPSMComponentPropertyName()));
		this.oAccessedProeprty = oAccessedProperty;
		this.setReturnParameter(new FunctionParameter("", this.oAccessedProeprty.getPSMComponentPropertyType(), this.oAccessedProeprty.getPSMComponentPropertyUniqueness(), true));
	}
	
	public PSMComponentProperty getAccessedProperty(){
		return this.oAccessedProeprty;
	}
	
	@Override
	public void printJavaFunction(){
		System.out.println("The Java Getter Function: " + this.getJavaFunctionName() + " is added to PSM to get the value of " + this.oAccessedProeprty.getPSMComponentPropertyName() + " property");
		super.printJavaFunction();
	}
}