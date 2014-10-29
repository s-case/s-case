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

import java.util.ArrayList;

import eu.fp7.scase.customUtilities.UniqueIdProducer;
import eu.fp7.scase.pimMetaModel.ResourceControllerCRUDActivity;
import eu.fp7.scase.pimMetaModel.ResourceInputRepresentation;
import eu.fp7.scase.pimMetaModel.ResourceOutputRepresentation;


public class HTTPActivity{
	
	private int iHTTPActivityId;
	private String strActivityHTTPVerb;
	private ResourceControllerCRUDActivity oParentPIMCRUDActivity;
	private String strHTTPActivityName;
	private String strHTTPActivityURI;
	private JAXRSAnnotation oHTTPActivityJAXRSPathAnnotation;
	private JAXRSAnnotation oHTTPActivityJAXRSVerbAnnotation;
	private JAXRSAnnotation oConsumesJAXRSAnnotation;
	private JAXRSAnnotation oProducesJAXRSAnnotation;
	private ArrayList<HTTPActivityFunctionParameter> listOfHTTPActivityFunctionParameters;
	
	public HTTPActivity(ResourceControllerCRUDActivity oCRUDActivity, String strHTTPVerb, String strHTTPActivityName){
		this.iHTTPActivityId = UniqueIdProducer.getNewUniqueId();
		this.oParentPIMCRUDActivity = oCRUDActivity;
		this.strActivityHTTPVerb = strHTTPVerb;
		this.strHTTPActivityName = strHTTPActivityName;
		this.strHTTPActivityURI = oCRUDActivity.getCRUDActivityURI();
		this.oHTTPActivityJAXRSPathAnnotation = new JAXRSAnnotation(String.format("@Path(\"%s\")", this.strHTTPActivityURI));
		this.oHTTPActivityJAXRSVerbAnnotation = new JAXRSAnnotation(String.format("@%s", this.strActivityHTTPVerb));
		this.listOfHTTPActivityFunctionParameters = new ArrayList<HTTPActivityFunctionParameter>();
	}
	
	public int getHTTPActivityId(){
		return this.iHTTPActivityId;
	}
	
	public String getHTTPActivityVerb(){
		return this.strActivityHTTPVerb;
	}
	
	public String getHTTPActivityName(){
		return this.strHTTPActivityName;
	}
	
	public String getHTTPActivityURI(){
		return this.strHTTPActivityURI;
	}
	
	public ArrayList<HTTPActivityFunctionParameter> getHTTPActivityFunctionParameters(){
		return this.listOfHTTPActivityFunctionParameters;
	}
	
	public ResourceControllerCRUDActivity getParentPIMCRUDActivity(){
		return this.oParentPIMCRUDActivity;
	}
	
	public JAXRSAnnotation getHTTPActivityJAXRSPathAnnotation(){
		return this.oHTTPActivityJAXRSPathAnnotation;
	}
	
	public JAXRSAnnotation getHTTPActivityJAXRSVerbAnnotation(){
		return this.oHTTPActivityJAXRSVerbAnnotation;
	}
	
	public JAXRSAnnotation getJAXRSConsumeAnnotation(){
		return this.oConsumesJAXRSAnnotation;
	}
	
	public JAXRSAnnotation getJAXRSProduceAnnotation(){
		return this.oProducesJAXRSAnnotation;
	}
	
	public void createJAXRSConsumeAnnotation(ArrayList<ResourceInputRepresentation> listOfInputRepresentations){
		String strJAXRSConsumeAnnotation;
		if(listOfInputRepresentations.size() == 1){
			strJAXRSConsumeAnnotation = "@Consumes(";
		}
		else{
			strJAXRSConsumeAnnotation = "@Consumes({";
		}
		
		for(int n = 0; n < listOfInputRepresentations.size(); n++){
			if( n == 0){
				strJAXRSConsumeAnnotation = String.format("%s\"%s\"", strJAXRSConsumeAnnotation, listOfInputRepresentations.get(n).getResourceInputRepresentation());
			}
			else{
				strJAXRSConsumeAnnotation = String.format("%s, \"%s\"", strJAXRSConsumeAnnotation, listOfInputRepresentations.get(n).getResourceInputRepresentation());
			}
		}
		if(listOfInputRepresentations.size() == 1){
			strJAXRSConsumeAnnotation = String.format("%s)", strJAXRSConsumeAnnotation);
		}
		else{
			strJAXRSConsumeAnnotation = String.format("%s})", strJAXRSConsumeAnnotation);
		}
		this.oConsumesJAXRSAnnotation = new JAXRSAnnotation(strJAXRSConsumeAnnotation);
	}
	
	public void createJAXRSProduceAnnotaiton(ArrayList<ResourceOutputRepresentation> listOfOutputRepresentations){
		String strJAXRSProduceAnnotation;
		if(listOfOutputRepresentations.size() == 1){
			strJAXRSProduceAnnotation = "@Produces(";
		}
		else{
			strJAXRSProduceAnnotation = "@Produces({";
		}
		for(int n = 0; n < listOfOutputRepresentations.size(); n++){
			if( n == 0){
				strJAXRSProduceAnnotation = String.format("%s\"%s\"", strJAXRSProduceAnnotation, listOfOutputRepresentations.get(n).getResourceOutputRepresentation());
			}
			else{
				strJAXRSProduceAnnotation = String.format("%s, \"%s\"", strJAXRSProduceAnnotation, listOfOutputRepresentations.get(n).getResourceOutputRepresentation());
			}
		}
		if(listOfOutputRepresentations.size() == 1){
			strJAXRSProduceAnnotation = String.format("%s)", strJAXRSProduceAnnotation);
		}
		else{
			strJAXRSProduceAnnotation = String.format("%s})", strJAXRSProduceAnnotation);
		}
		this.oProducesJAXRSAnnotation = new JAXRSAnnotation(strJAXRSProduceAnnotation);
	}
	
	public void printHTTPActivity(){
		System.out.println("HTTP Activity: " + this.strHTTPActivityName + " is added to PSM because " + this.oParentPIMCRUDActivity.getCRUDActivityName() + " exists in PIM.");
		System.out.println("URI: " + this.strHTTPActivityURI);
		System.out.println("JAXRS Path Annotation: " + this.oHTTPActivityJAXRSPathAnnotation.getJAXRSAnnotationText());
		System.out.println("JAXRS Verb Annotation: " + this.oHTTPActivityJAXRSVerbAnnotation.getJAXRSAnnotationText());
		if( this.oConsumesJAXRSAnnotation != null){
			System.out.println("JAXRS Consumes Annotation: " + this.oConsumesJAXRSAnnotation.getJAXRSAnnotationText());
		}
		if( this.oProducesJAXRSAnnotation != null){
			System.out.println("JAXRS Produces Annotation: " + this.oProducesJAXRSAnnotation.getJAXRSAnnotationText());
		}
	}
}