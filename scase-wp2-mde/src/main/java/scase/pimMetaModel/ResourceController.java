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

import main.java.scase.cimMetaModel.Resource;
import main.java.scase.customUtilities.UniqueIdProducer;



public class ResourceController{
	
	private int iResourceControllerId;
	private String strResourceControllerName;
	private Resource oParentCIMResource;
	private ResourceModel oAssociatedResourceModel;
	private ArrayList<ResourceControllerCRUDActivity> listOfControllerCRUDActivities;
	private ArrayList<CRUDActivityHandler> listOfControllerCRUDActivityHandlers;
	private String strResourceControllerURI;
	
	public ResourceController(){
		this.iResourceControllerId = UniqueIdProducer.getNewUniqueId();
		this.listOfControllerCRUDActivities = new ArrayList<ResourceControllerCRUDActivity>();
		this.listOfControllerCRUDActivityHandlers = new ArrayList<CRUDActivityHandler>();
	}
	
	public ResourceController(String strResourceControllerName, Resource oParentCIMResource, ResourceModel oAssociatedResourceModel){
		this.iResourceControllerId = UniqueIdProducer.getNewUniqueId();
		this.strResourceControllerName = strResourceControllerName;
		this.oParentCIMResource = oParentCIMResource;
		this.oAssociatedResourceModel = oAssociatedResourceModel; 
		this.listOfControllerCRUDActivities = new ArrayList<ResourceControllerCRUDActivity>();
		this.listOfControllerCRUDActivityHandlers = new ArrayList<CRUDActivityHandler>();
	}
	
	public int getResourceControllerId(){
		return this.iResourceControllerId;
	}
	
	public void setResourceControllerName(String strResourceControllerName){
		this.strResourceControllerName = strResourceControllerName;
	}
	
	public String getResourceControllerName(){
		return this.strResourceControllerName;
	}
	
	public Resource getParentCIMResource(){
		return this.oParentCIMResource;
	}
	
	public ResourceModel getAssociatedResourceModel(){
		return this.oAssociatedResourceModel;
	}
	
	public ArrayList<ResourceControllerCRUDActivity> getResourceControllerCRUDActivities(){
		return this.listOfControllerCRUDActivities;
	}
	
	public void printResourceController(){
		System.out.println("Resource Controller " + this.getResourceControllerName() + " with id " + this.getResourceControllerId() + " and associated resource model " + this.getAssociatedResourceModel().getResourceModelName() + " is added to the PIM because " + this.getParentCIMResource().getResourceName() + " exists");
		System.out.println("URI: " + this.strResourceControllerURI);
		printControllerCRUDActivities();
		printControllerCRUDActivityHandlers();
	}
	
	public void addCRUDActivities(String strCRUDActivityNamePatch, String strCRUDActivityURI, ResourceModel oIncomingResourceModel){
		for(int n = 0; n < this.oParentCIMResource.getResourceCRUDActivities().size(); n++){
			//since only READ, UPDATE, DELETE activities are allowed for PIM resource controllers from the meta-model
			if(this.oParentCIMResource.getResourceCRUDActivities().get(n).getActivityCRUDVerb().equalsIgnoreCase("READ")){ //check if the CRUD verb is the READ one
				this.listOfControllerCRUDActivities.add(new ResourceControllerCRUDActivity("READ", this.oParentCIMResource.getResourceCRUDActivities().get(n), "read" + strCRUDActivityNamePatch + this.oParentCIMResource.getResourceName(), strCRUDActivityURI, oIncomingResourceModel)); //and add one READ CRUD activity to PIM
			}
			else if(this.oParentCIMResource.getResourceCRUDActivities().get(n).getActivityCRUDVerb().equalsIgnoreCase("UPDATE")){ //check if the CRUD verb is the UPDATE one
				this.listOfControllerCRUDActivities.add(new ResourceControllerCRUDActivity("UPDATE", this.oParentCIMResource.getResourceCRUDActivities().get(n), "update" + strCRUDActivityNamePatch + this.oParentCIMResource.getResourceName(), strCRUDActivityURI, oIncomingResourceModel)); //and add one UPDATE CRUD activity to PIM
			}
			else if(this.oParentCIMResource.getResourceCRUDActivities().get(n).getActivityCRUDVerb().equalsIgnoreCase("DELETE")){ //check if the CRUD verb is the DELETE one
				this.listOfControllerCRUDActivities.add(new ResourceControllerCRUDActivity("DELETE", this.oParentCIMResource.getResourceCRUDActivities().get(n), "delete" + strCRUDActivityNamePatch + this.oParentCIMResource.getResourceName(), strCRUDActivityURI, oIncomingResourceModel)); //and add one DELETE CRUD activity to PIM
			}
		}
		//check if the PIM meta-model constraint that there exists at least one CRUD activity per resource controller is satisfied
		if(this.listOfControllerCRUDActivities.size() == 0){ //if no CRUD activities where added
			try {
				throw new Exception("Error! Disatisfied PIM meta-model constraint: The resource controller " + this.strResourceControllerName + " does not have any CRUD activites!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void printControllerCRUDActivities(){
		for(int n = 0; n < this.listOfControllerCRUDActivities.size(); n++){
			System.out.println("CRUD Activity: " + this.listOfControllerCRUDActivities.get(n).getCRUDActivityName() + " is added to the PIM because " + this.listOfControllerCRUDActivities.get(n).getParentCRUDActivity().getActivityCRUDVerb() + " Activity exists in CIM");
			System.out.println("CRUD Activity URI: " + this.listOfControllerCRUDActivities.get(n).getCRUDActivityURI());
		}
	}
	
	public void addCRUDActivityHandlers(){
		for(int n = 0; n < this.listOfControllerCRUDActivities.size(); n++){
			this.listOfControllerCRUDActivityHandlers.add(new CRUDActivityHandler(String.format("%sHandler", this.listOfControllerCRUDActivities.get(n).getCRUDActivityName()), this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb(), this.listOfControllerCRUDActivities.get(n), this));
		}
	}
	
	public ArrayList<CRUDActivityHandler> getControllerCRUDActivityHandlers(){
		return this.listOfControllerCRUDActivityHandlers;
	}
	
	private void printControllerCRUDActivityHandlers(){
		for(int n = 0; n < this.listOfControllerCRUDActivityHandlers.size(); n++){
			this.listOfControllerCRUDActivityHandlers.get(n).printCRUDActivityHandler();
		}
	}
	
	public void addRDBMSActivities(){
		for(int n = 0; n < this.listOfControllerCRUDActivities.size(); n++){
			if(this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb().equalsIgnoreCase("read")){
				DatabaseController.getDatabaseControllerHandle().getDatabaseControllerActivities().add(new RDBMSActivity(this.listOfControllerCRUDActivities.get(n).getCRUDActivityName().replace("read", "select"), "SELECT", this.listOfControllerCRUDActivities.get(n)));
			}
			else if(this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb().equalsIgnoreCase("update")){
				DatabaseController.getDatabaseControllerHandle().getDatabaseControllerActivities().add(new RDBMSActivity(this.listOfControllerCRUDActivities.get(n).getCRUDActivityName().replace("update", "update"), "UPDATE", this.listOfControllerCRUDActivities.get(n)));
			}
			else if(this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb().equalsIgnoreCase("delete")){
				DatabaseController.getDatabaseControllerHandle().getDatabaseControllerActivities().add(new RDBMSActivity(this.listOfControllerCRUDActivities.get(n).getCRUDActivityName().replace("delete", "delete"), "DELETE", this.listOfControllerCRUDActivities.get(n)));
			}
		}
	}
	
	public String getModelPrimaryIdentifierName(){
		return this.oAssociatedResourceModel.getModelPrimaryIdentifierName();
	}
	
	public void setResourceControllerURI(String strResourceControllerURI){
		this.strResourceControllerURI = strResourceControllerURI;
	}
	
	public boolean hasReadActivity(){
		for(int n = 0; n < this.listOfControllerCRUDActivities.size(); n++){
			if(this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb().equalsIgnoreCase("READ")){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasUpdateActivity(){
		for(int n = 0; n < this.listOfControllerCRUDActivities.size(); n++){
			if(this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb().equalsIgnoreCase("UPDATE")){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasDeleteActivity(){
		for(int n = 0; n < this.listOfControllerCRUDActivities.size(); n++){
			if(this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb().equalsIgnoreCase("DELETE")){
				return true;
			}
		}
		return false;
	}
	
	public void createSiblingHypermediaLinks(){
		for(int n = 0; n < this.listOfControllerCRUDActivityHandlers.size(); n++){
			if(this.hasReadActivity() && !this.listOfControllerCRUDActivityHandlers.get(n).getCRUDActivityHandlerVerb().equalsIgnoreCase("DELETE")){
				this.listOfControllerCRUDActivityHandlers.get(n).getHypermediaFunction().getHypermediaLinkList().add(new PIMHypermediaLink("READ", "Sibling", this));
			}
			
			if(this.hasUpdateActivity() && !this.listOfControllerCRUDActivityHandlers.get(n).getCRUDActivityHandlerVerb().equalsIgnoreCase("DELETE")){
				this.listOfControllerCRUDActivityHandlers.get(n).getHypermediaFunction().getHypermediaLinkList().add(new PIMHypermediaLink("UPDATE", "Sibling", this));
			}
			
			if(this.hasDeleteActivity() && !this.listOfControllerCRUDActivityHandlers.get(n).getCRUDActivityHandlerVerb().equalsIgnoreCase("DELETE")){
				this.listOfControllerCRUDActivityHandlers.get(n).getHypermediaFunction().getHypermediaLinkList().add(new PIMHypermediaLink("DELETE", "Sibling", this));
			}
		}
	}
	
	public void createSpecificChildHypermediaLinks(ResourceControllerManager oChildResourceControllerManager){
		for(int n = 0; n < this.listOfControllerCRUDActivityHandlers.size(); n++){
			if(!this.listOfControllerCRUDActivityHandlers.get(n).getCRUDActivityHandlerVerb().equalsIgnoreCase("DELETE")){
				this.listOfControllerCRUDActivityHandlers.get(n).getHypermediaFunction().getHypermediaLinkList().add(new PIMHypermediaLink("READ", "Children", oChildResourceControllerManager));
			}
				
			if(!this.listOfControllerCRUDActivityHandlers.get(n).getCRUDActivityHandlerVerb().equalsIgnoreCase("DELETE")){
				this.listOfControllerCRUDActivityHandlers.get(n).getHypermediaFunction().getHypermediaLinkList().add(new PIMHypermediaLink("CREATE", "Children", oChildResourceControllerManager));
			}
		}
	}
	
	public void createSpecificChildHypermediaLinks(AlgoResourceController oChildAlgoResourceController){
		for(int n = 0; n < this.listOfControllerCRUDActivityHandlers.size(); n++){
			if(!this.listOfControllerCRUDActivityHandlers.get(n).getCRUDActivityHandlerVerb().equalsIgnoreCase("DELETE")){
				this.listOfControllerCRUDActivityHandlers.get(n).getHypermediaFunction().getHypermediaLinkList().add(new PIMHypermediaLink(oChildAlgoResourceController.getAlgoControllerCRUDActivities().get(0).getResourceControllerCRUDActivityVerb(), "Children", oChildAlgoResourceController));
			}
		}
	}
	
	public void createParentHypermediaLinks(ResourceControllerManager oParentResourceControllerMangaer){
		for(int n = 0; n < this.listOfControllerCRUDActivityHandlers.size(); n++){
			this.listOfControllerCRUDActivityHandlers.get(n).getHypermediaFunction().getHypermediaLinkList().add(new PIMHypermediaLink("READ", "Parent", oParentResourceControllerMangaer));
			this.listOfControllerCRUDActivityHandlers.get(n).getHypermediaFunction().getHypermediaLinkList().add(new PIMHypermediaLink("CREATE", "Parent", oParentResourceControllerMangaer));
		}
	}
	
	public String getResourceControllerURI(){
		return this.strResourceControllerURI;
	}
}