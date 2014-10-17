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
import main.java.scase.pimMetaModel.ResourceControllerManager;

public class JavaResourceControllerManager{
	
	private int iJavaResourceControllerManagerId;
	private ResourceControllerManager oPIMParentResourceControllerManager;
	private String strJavaResourceControllerManagerName;
	private String strJavaResourceControllerManagerURI;
	private JAXRSAnnotation oControllerManagerJAXRSAnnotation;
	private ArrayList<HTTPActivity> listOfJavaControllerManagerHTTPActivities;
	private JavaResourceModelManager oRelatedJavaResourceModelManager;
	private JavaResourceController oAssociatedJavaResourceController;
	private ArrayList<HTTPActivityHandler> listOfJavaControllerManagerHTTPHandlers;
	
	public JavaResourceControllerManager(ResourceControllerManager oResourceControllerManager){
		this.iJavaResourceControllerManagerId = UniqueIdProducer.getNewUniqueId();
		this.oPIMParentResourceControllerManager = oResourceControllerManager;
		this.strJavaResourceControllerManagerName = String.format("Java%s", oResourceControllerManager.getResourceControllerManagerName());
		this.strJavaResourceControllerManagerURI = oResourceControllerManager.getResourceControllerManagerURI();
		this.oControllerManagerJAXRSAnnotation = new JAXRSAnnotation(String.format("@Path(\"%s\")", this.strJavaResourceControllerManagerURI));
		this.listOfJavaControllerManagerHTTPActivities = new ArrayList<HTTPActivity>();
		this.listOfJavaControllerManagerHTTPHandlers = new ArrayList<HTTPActivityHandler>();
	}
	
	public int getJavaResourceControllerManagerId(){
		return this.iJavaResourceControllerManagerId;
	}
	
	public ResourceControllerManager getPIMParentResourceControllerManager(){
		return this.oPIMParentResourceControllerManager;
	}
	
	public String getJavaResourceControllerManagerName(){
		return this.strJavaResourceControllerManagerName;
	}
	
	public String getJavaResourceControllerManagerURI(){
		return this.strJavaResourceControllerManagerURI;
	}
	
	public JAXRSAnnotation getControllerManagerJAXRSAnnotation(){
		return this.oControllerManagerJAXRSAnnotation;
	}
	
	public ArrayList<HTTPActivity> getJavaControllerManagerHTTPActivities(){
		return this.listOfJavaControllerManagerHTTPActivities;
	}
	
	public void setRelatedJavaResourceModelManager(JavaResourceModelManager oRelatedJavaResourceModelManager){
		this.oRelatedJavaResourceModelManager = oRelatedJavaResourceModelManager;
	}
	
	public JavaResourceModelManager getRelatedJavaResourceModelManager(){
		return this.oRelatedJavaResourceModelManager;
	}
	
	public JavaResourceController getAssociatedJavaResourceController(){
		return this.oAssociatedJavaResourceController;
	}
	
	public void setAssociatedJavaResourceController(JavaResourceController oAssociatedJavaResourceController){
		this.oAssociatedJavaResourceController = oAssociatedJavaResourceController;
	}
	
	public void addHTTPActivities(){
		for(int n = 0; n < this.oPIMParentResourceControllerManager.getControllerCRUDActivities().size(); n++){
			this.listOfJavaControllerManagerHTTPActivities.add(new HTTPActivity(this.oPIMParentResourceControllerManager.getControllerCRUDActivities().get(n), transformCRUDVerb(this.oPIMParentResourceControllerManager.getControllerCRUDActivities().get(n).getResourceControllerCRUDActivityVerb()), transformCRUDActivityName(this.oPIMParentResourceControllerManager.getControllerCRUDActivities().get(n).getCRUDActivityName(), this.oPIMParentResourceControllerManager.getControllerCRUDActivities().get(n).getResourceControllerCRUDActivityVerb())));
		}
	}
	
	public void addHTTPActivityHandlers(){
		for(int n = 0; n < this.oPIMParentResourceControllerManager.getControllerCRUDActivityHandlers().size(); n++){
			this.listOfJavaControllerManagerHTTPHandlers.add(new HTTPActivityHandler(this.oPIMParentResourceControllerManager.getControllerCRUDActivityHandlers().get(n), transformCRUDVerb(this.oPIMParentResourceControllerManager.getControllerCRUDActivityHandlers().get(n).getCRUDActivityHandlerVerb()), transformCRUDActivityHandlerName(this.oPIMParentResourceControllerManager.getControllerCRUDActivityHandlers().get(n).getCRUDActivityHandlerName(), this.oPIMParentResourceControllerManager.getControllerCRUDActivityHandlers().get(n).getCRUDActivityHandlerVerb()), this));
		}
	}
	
	public ArrayList<HTTPActivityHandler> getJavaControllerManagerHTTPHandlers(){
		return this.listOfJavaControllerManagerHTTPHandlers;
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
	
	public void printJavaResourceControllerManager(){
		System.out.println("The Java Resource Controller Manager: " + this.strJavaResourceControllerManagerName + " with related java resource model manager " + this.oRelatedJavaResourceModelManager.getJavaResourceModelManagerName() + " is added to PSM because " + this.oPIMParentResourceControllerManager.getResourceControllerManagerName() + " exists in PIM");
		System.out.println("URI: " + this.strJavaResourceControllerManagerURI);
		System.out.println("Controller Manager JAXRS @PATH Annotation: " + this.oControllerManagerJAXRSAnnotation.getJAXRSAnnotationText());
		printControllerManagerHTTPActivities();
		printControllerManagerHTTPActivityHandlers();
	}
	
	private void printControllerManagerHTTPActivities(){
		for(int n = 0; n < this.listOfJavaControllerManagerHTTPActivities.size(); n++){
			this.listOfJavaControllerManagerHTTPActivities.get(n).printHTTPActivity();
		}
	}
	
	private void printControllerManagerHTTPActivityHandlers(){
		for(int n = 0; n < this.listOfJavaControllerManagerHTTPHandlers.size(); n++){
			this.listOfJavaControllerManagerHTTPHandlers.get(n).printHTTPActivityHandler();
		}
	}
	
	public void createHibernateActivities(){
		HibernateController.getHibernateControllerHandle().getControllerHibernateActivities().add(new HibernateActivity(String.format("post%s", this.oRelatedJavaResourceModelManager.getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName()), "POST", this, null));

		if( this.oRelatedJavaResourceModelManager.getIncomingJavaModelRelations().size() > 1){
			for(int n = 0; n < this.oRelatedJavaResourceModelManager.getIncomingJavaModelRelations().size(); n++){
				HibernateController.getHibernateControllerHandle().getControllerHibernateActivities().add(new HibernateActivity(String.format("get%s%sList", this.oRelatedJavaResourceModelManager.getIncomingJavaModelRelations().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName(), this.oRelatedJavaResourceModelManager.getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName()), "GET", this, this.oRelatedJavaResourceModelManager.getIncomingJavaModelRelations().get(n)));
			}	
		}
		else{
			HibernateController.getHibernateControllerHandle().getControllerHibernateActivities().add(new HibernateActivity(String.format("get%sList", this.oRelatedJavaResourceModelManager.getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName()), "GET", this, (this.oRelatedJavaResourceModelManager.getIncomingJavaModelRelations().size() != 0 ? this.oRelatedJavaResourceModelManager.getIncomingJavaModelRelations().get(0) : null)));
		}
	}
	
	public void addJAXRSInputOutputAnnotations(){
		for(int n = 0; n < this.listOfJavaControllerManagerHTTPActivities.size(); n++){
			if( this.listOfJavaControllerManagerHTTPActivities.get(n).getHTTPActivityVerb().equalsIgnoreCase("POST") || this.listOfJavaControllerManagerHTTPActivities.get(n).getHTTPActivityVerb().equalsIgnoreCase("PUT")){
				this.listOfJavaControllerManagerHTTPActivities.get(n).createJAXRSConsumeAnnotation(this.oRelatedJavaResourceModelManager.getPIMParentResourceModelManager().getResourceInputRepresentations());
			}
			this.listOfJavaControllerManagerHTTPActivities.get(n).createJAXRSProduceAnnotaiton(this.oRelatedJavaResourceModelManager.getPIMParentResourceModelManager().getResourceOutputRepresentations());
		}
	}
}