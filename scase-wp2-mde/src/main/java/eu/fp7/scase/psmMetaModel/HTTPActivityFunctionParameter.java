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

public class HTTPActivityFunctionParameter extends FunctionParameter{

	private JAXRSAnnotation oPathParamJAXRSAnnotation;
	
	public HTTPActivityFunctionParameter(String strFunctionParameterName, String strFunctionParameterType, boolean bIsPathParam){
		super(strFunctionParameterName, strFunctionParameterType, true, false);
		this.oPathParamJAXRSAnnotation = new JAXRSAnnotation("@PathParam");
	}
	
	public JAXRSAnnotation getPathParamJAXRSAnnotation(){
		return this.oPathParamJAXRSAnnotation;
	}
}