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

import java.util.ArrayList;

import main.java.scase.customUtilities.UniqueIdProducer;

public abstract class APIMFunction{
	
	private int iPIMFunctionId;
	private String strPIMFunctionName;
	private ArrayList<FunctionParameter> listOfFunctionParamters;
	private FunctionParameter oFunctionReturnParameter;
	
	public APIMFunction(String strFunctionName){
		this.iPIMFunctionId = UniqueIdProducer.getNewUniqueId();
		this.strPIMFunctionName = strFunctionName;
		this.listOfFunctionParamters = new ArrayList<FunctionParameter>();
	}
	
	public int getPIMFunctionId(){
		return this.iPIMFunctionId;
	}
	
	public ArrayList<FunctionParameter> getFunctionParameters(){
		return this.listOfFunctionParamters;
	}
	
	public FunctionParameter getFuntionReturnParameter(){
		return this.oFunctionReturnParameter;
	}
	
	public String getPIMFunctionName(){
		return this.strPIMFunctionName;
	}
	
	public void printPIMFunction(){
		System.out.println("Function return type: " + (this.oFunctionReturnParameter == null? "void" : this.oFunctionReturnParameter.getParameterType()));
		for(int n = 0; n < this.listOfFunctionParamters.size(); n++){
			this.listOfFunctionParamters.get(n).printFunctionParameter();
		}
	}
	
	public void setFunctionReturnParameter(FunctionParameter oFunctionReturnParameter){
		this.oFunctionReturnParameter = oFunctionReturnParameter;
	}
}