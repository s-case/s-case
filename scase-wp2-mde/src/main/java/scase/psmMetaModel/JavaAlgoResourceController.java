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

import java.util.ArrayList;

import main.java.scase.customUtilities.UniqueIdProducer;
import main.java.scase.pimMetaModel.AlgoResourceController;

public class JavaAlgoResourceController{
	
	private int iJavaAlgoResourceControllerId;
	private String strJavaAlgoResourceControllerName;
	private String strJavaAlgoResourceControllerURI;
	private AlgoResourceController oParentPIMAlgoResourceController;
	private JavaAlgoResourceModel oRelatedAlgoResourceModel;
	private ArrayList<HTTPActivity> listOfAlgoControllerHTTPActivities;
	private ArrayList<HTTPActivityHandler> listOfAlgoControllerActivityHandlers;
	
	public JavaAlgoResourceController(AlgoResourceController oAlgoResourceController){
		this.iJavaAlgoResourceControllerId = UniqueIdProducer.getNewUniqueId();
		this.oParentPIMAlgoResourceController = oAlgoResourceController;
		this.strJavaAlgoResourceControllerName = oAlgoResourceController.getAlgoResourceControllerName();
		this.strJavaAlgoResourceControllerURI = oAlgoResourceController.getAlgoResourceControllerURI();
		this.listOfAlgoControllerHTTPActivities = new ArrayList<HTTPActivity>();
		this.listOfAlgoControllerActivityHandlers = new ArrayList<HTTPActivityHandler>();
	}
	
	public int getJavaAlgoResourceControllerId(){
		return this.iJavaAlgoResourceControllerId;
	}
	
	public String getJavaAlgoResourceControllerName(){
		return this.strJavaAlgoResourceControllerName;
	}
	
	public String getJavaAlgoResourceControllerURI(){
		return this.strJavaAlgoResourceControllerURI;
	}
	
	public AlgoResourceController getParentPIMAlgoResourceController(){
		return this.oParentPIMAlgoResourceController;
	}
	
	public void setRelatedAlgoResourceModel(JavaAlgoResourceModel oRelatedAlgoResourceModel){
		this.oRelatedAlgoResourceModel = oRelatedAlgoResourceModel;
	}
	
	public ArrayList<HTTPActivity> getAlgoControllerHTTPActivities(){
		return this.listOfAlgoControllerHTTPActivities;
	}
	
	public ArrayList<HTTPActivityHandler> getAlgoControllerActivityHandlers(){
		return this.listOfAlgoControllerActivityHandlers;
	}
	
	public void addAlgoControllerHTTPActivities(){
		for(int n = 0; n < this.oParentPIMAlgoResourceController.getAlgoControllerCRUDActivities().size(); n++){
			this.listOfAlgoControllerHTTPActivities.add(new HTTPActivity(this.oParentPIMAlgoResourceController.getAlgoControllerCRUDActivities().get(n), transformCRUDVerb(this.oParentPIMAlgoResourceController.getAlgoControllerCRUDActivities().get(n).getResourceControllerCRUDActivityVerb()), transformCRUDActivityName(this.oParentPIMAlgoResourceController.getAlgoControllerCRUDActivities().get(n).getCRUDActivityName(), this.oParentPIMAlgoResourceController.getAlgoControllerCRUDActivities().get(n).getResourceControllerCRUDActivityVerb())));
		}
	}
	
	private String transformCRUDVerb(String strCRUDVerb){
		if(strCRUDVerb.equalsIgnoreCase("CREATE")){
			return "POST";
		}
		else if(strCRUDVerb.equalsIgnoreCase("READ")){
			return "GET";
		}
		else if(strCRUDVerb.equalsIgnoreCase("UPDATE")){
			return "PUT";
		}
		else if(strCRUDVerb.equalsIgnoreCase("DELETE")){
			return "DELETE";
		}
		else{
			
			try {
				throw new Exception("Unkown CRUD verb! Possibly the PIM is corrupted!");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	private String transformCRUDActivityName(String strCRUDActivityName, String strCRUDVerb){
		if(strCRUDVerb.equalsIgnoreCase("CREATE")){
			return String.format("post%s", strCRUDActivityName.substring(6));
		}
		else if(strCRUDVerb.equalsIgnoreCase("READ")){
			return String.format("get%s", strCRUDActivityName.substring(4));
		}
		else if(strCRUDVerb.equalsIgnoreCase("UPDATE")){
			return String.format("put%s", strCRUDActivityName.substring(6));
		}
		else if(strCRUDVerb.equalsIgnoreCase("DELETE")){
			return strCRUDActivityName;
		}
		else{
			
			try {
				throw new Exception("Unkown CRUD verb! Possibly the PIM is corrupted!");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	private String transformCRUDActivityHandlerName(String strCRUDActivityHandlerName, String strCRUDVerb){
		return transformCRUDActivityName(strCRUDActivityHandlerName, strCRUDVerb);
	}
	
	public void printJavaAlgoResourceController(){
		System.out.println("The Java Algorithmic Resource Controller: " + this.strJavaAlgoResourceControllerName + " with related algorithmic resource model " + this.oRelatedAlgoResourceModel.getJavaAlgoResourceModelName() +  " is added to PSM because " + this.oParentPIMAlgoResourceController.getAlgoResourceControllerName() + " exists in PIM");
		System.out.println("URI: " + this.strJavaAlgoResourceControllerURI);
		printAlgoControllerHTTPActivities();
		printAlgoControllerActivityHandlers();
	}
	
	private void printAlgoControllerHTTPActivities(){
		for(int n = 0; n < this.listOfAlgoControllerHTTPActivities.size(); n++){
			this.listOfAlgoControllerHTTPActivities.get(n).printHTTPActivity();
		}
	}
	
	private void printAlgoControllerActivityHandlers(){
		for(int n = 0; n < this.listOfAlgoControllerActivityHandlers.size(); n++){
			this.listOfAlgoControllerActivityHandlers.get(n).printHTTPActivityHandler();
		}
	}
	
	public void addAlgoHTTPActivityHandlers(){
		for(int n = 0; n < this.oParentPIMAlgoResourceController.getAlgoControllerCRUDActivityHandlers().size(); n++){
			this.listOfAlgoControllerActivityHandlers.add(new HTTPActivityHandler(this.oParentPIMAlgoResourceController.getAlgoControllerCRUDActivityHandlers().get(n), transformCRUDVerb(this.oParentPIMAlgoResourceController.getAlgoControllerCRUDActivityHandlers().get(n).getCRUDActivityHandlerVerb()), transformCRUDActivityHandlerName(this.oParentPIMAlgoResourceController.getAlgoControllerCRUDActivityHandlers().get(n).getCRUDActivityHandlerName(), this.oParentPIMAlgoResourceController.getAlgoControllerCRUDActivityHandlers().get(n).getCRUDActivityHandlerVerb()), this));
		}
	}
	
	public void addJAXRSInputOutputAnnotations(){
		for(int n = 0; n < this.listOfAlgoControllerHTTPActivities.size(); n++){
			this.listOfAlgoControllerHTTPActivities.get(n).createJAXRSConsumeAnnotation(this.oRelatedAlgoResourceModel.getParentPIMAlgoResourceModel().getAlgoModelInputRepresentations());
			this.listOfAlgoControllerHTTPActivities.get(n).createJAXRSProduceAnnotaiton(this.oRelatedAlgoResourceModel.getParentPIMAlgoResourceModel().getAlgoModelOutputRepresentations());
		}
	}
	
}