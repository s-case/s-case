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

public class SetterFunction extends APIMFunction{
	
	private PIMComponentProperty oAccessedProperty;
	
	public SetterFunction(PIMComponentProperty oAccessedProperty){
		super(String.format("set%s", oAccessedProperty.getPIMComponentProeprtyName()));
		this.setFunctionReturnParameter(new FunctionParameter("", "void", false, true));
		this.getFunctionParameters().add(new FunctionParameter(oAccessedProperty.getPIMComponentProeprtyName(), oAccessedProperty.getPIMComponentPropertyType(), oAccessedProperty.getPIMComponentPropertyUniqueness(), false));
		this.oAccessedProperty = oAccessedProperty;
	}
	
	public PIMComponentProperty getAccessedProperty(){
		return this.oAccessedProperty;
	}
	
	@Override
	public void printPIMFunction(){
		System.out.println("The setter function: " + this.getPIMFunctionName() + " is added to PIM to set the value of " + this.oAccessedProperty.getPIMComponentProeprtyName());
		super.printPIMFunction();
	}
}