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

import main.java.scase.customUtilities.UniqueIdProducer;


public class CRUDActivityHandler{
	
	private int iCRUDActivityHandlerId;
	private String strCRUDActivityHandlerName;
	private String strCRUDActivityHandlerVerb;
	private ResourceControllerCRUDActivity oParentControllerCRUDActivity;
	private ResourceController oParentResourceController;
	private ResourceControllerManager oParentResourceControllerManager;
	private AlgoResourceController oParentAlgoResourceController;
	private CreateHypermediaPIMFunction oCreateHypermediaPIMFunction;
	private ResourceModel oIncomingResourceModel; //see explanation at the according property of ResourceControllerCRUDActivity.java
	
	
	public CRUDActivityHandler(){
		this.iCRUDActivityHandlerId = UniqueIdProducer.getNewUniqueId();
	}
	
	public CRUDActivityHandler(String strCRUDActivityHandlerName, String strCRUDActivityHandlerVerb, ResourceControllerCRUDActivity oParentControllerCRUDActivity, ResourceController oParentResourceController){
		this.iCRUDActivityHandlerId = UniqueIdProducer.getNewUniqueId();
		this.strCRUDActivityHandlerName = strCRUDActivityHandlerName;
		this.strCRUDActivityHandlerVerb = strCRUDActivityHandlerVerb;
		this.oParentControllerCRUDActivity = oParentControllerCRUDActivity;
		this.oParentResourceController = oParentResourceController;
		this.oCreateHypermediaPIMFunction = new CreateHypermediaPIMFunction(this, this.oParentResourceController, oParentControllerCRUDActivity);
	}
	
	public CRUDActivityHandler(String strCRUDActivityHandlerName, String strCRUDActivityHandlerVerb, ResourceControllerCRUDActivity oParentControllerCRUDActivity, ResourceControllerManager oParentResourceControllerManager){
		this.iCRUDActivityHandlerId = UniqueIdProducer.getNewUniqueId();
		this.strCRUDActivityHandlerName = strCRUDActivityHandlerName;
		this.strCRUDActivityHandlerVerb = strCRUDActivityHandlerVerb;
		this.oParentControllerCRUDActivity = oParentControllerCRUDActivity;
		this.oParentResourceControllerManager = oParentResourceControllerManager;
		this.oCreateHypermediaPIMFunction = new CreateHypermediaPIMFunction(this, this.oParentResourceControllerManager, oParentControllerCRUDActivity);
	}
	
	public CRUDActivityHandler(String strCRUDActivityHandlerName, String strCRUDActivityHandlerVerb, ResourceControllerCRUDActivity oParentControllerCRUDActivity, ResourceControllerManager oParentResourceControllerManager, ResourceModel oIncomingResourceModel){
		this.iCRUDActivityHandlerId = UniqueIdProducer.getNewUniqueId();
		this.strCRUDActivityHandlerName = strCRUDActivityHandlerName;
		this.strCRUDActivityHandlerVerb = strCRUDActivityHandlerVerb;
		this.oParentControllerCRUDActivity = oParentControllerCRUDActivity;
		this.oParentResourceControllerManager = oParentResourceControllerManager;
		this.oIncomingResourceModel = oIncomingResourceModel;
		this.oCreateHypermediaPIMFunction = new CreateHypermediaPIMFunction(this, this.oParentResourceControllerManager, oParentControllerCRUDActivity, oIncomingResourceModel);
	}
	
	public CRUDActivityHandler(String strCRUDActivityHandlerName, String strCRUDActivityHandlerVerb, ResourceControllerCRUDActivity oParentControllerCRUDActivity, AlgoResourceController oParentAlgoController){
		this.iCRUDActivityHandlerId = UniqueIdProducer.getNewUniqueId();
		this.strCRUDActivityHandlerName = strCRUDActivityHandlerName;
		this.strCRUDActivityHandlerVerb = strCRUDActivityHandlerVerb;
		this.oParentControllerCRUDActivity = oParentControllerCRUDActivity;
		this.oParentAlgoResourceController = oParentAlgoController;
		this.oCreateHypermediaPIMFunction = new CreateHypermediaPIMFunction(this, this.oParentAlgoResourceController, oParentControllerCRUDActivity);
	}
	
	public int getCRUDActivityHandlerId(){
		return this.iCRUDActivityHandlerId;
	}
	
	public String getCRUDActivityHandlerName(){
		return this.strCRUDActivityHandlerName;
	}
	
	public String getCRUDActivityHandlerVerb(){
		return this.strCRUDActivityHandlerVerb;
	}
	
	public ResourceController getParentResourceController(){
		return this.oParentResourceController;
	}
	
	public ResourceControllerManager getParentResourceControllerManager(){
		return this.oParentResourceControllerManager;
	}
	
	public ResourceControllerCRUDActivity getParentControllerCRUDActivity(){
		return this.oParentControllerCRUDActivity;
	}
	
	public void printCRUDActivityHandler(){
		System.out.println(this.strCRUDActivityHandlerName + " is added to the PIM because " + (this.oParentResourceController == null? (this.oParentAlgoResourceController == null? this.oParentResourceControllerManager.getResourceControllerManagerName() : this.oParentAlgoResourceController.getAlgoResourceControllerName()) : this.oParentResourceController.getResourceControllerName()) + " has a " + this.strCRUDActivityHandlerVerb + " activity");
		this.oCreateHypermediaPIMFunction.printPIMFunction();
	}
	
	public AlgoResourceController getParentAlgoResourceController(){
		return this.oParentAlgoResourceController;
	}
	
	public CreateHypermediaPIMFunction getHypermediaFunction(){
		return this.oCreateHypermediaPIMFunction;
	}
	
	public ResourceModel getIncomingResourceModel(){
		return this.oIncomingResourceModel;
	}
}