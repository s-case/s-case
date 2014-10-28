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


public class AlgoResourceModel{
	
	private int iAlgorResourceModelId;
	private String strAlgoResourceModelName;
	private Resource oParentCIMResource;
	private ArrayList<ResourceInputRepresentation> listOfResourceInputRepresentations;
	private ArrayList<ResourceOutputRepresentation> listOfResourceOutputRepresentations;
	private ArrayList<PIMComponentProperty> listOfAlgoResourceProperties;
	private ArrayList<APIMFunction> listOfAlgoResourceModelFunctions;
	private ArrayList<ResourceModelManager> listOfRelatedResourceModelManagers;
	private ArrayList<AlgoResourceModel> listOfRelatedAlgoResourceModels;
	private ArrayList<ResourceModel> listOfIncomingResourceModelRelations;
	private ArrayList<AlgoResourceModel> listOfIncomingAlgoResourceModelRelations;
	
	public AlgoResourceModel(){
		this.iAlgorResourceModelId = UniqueIdProducer.getNewUniqueId();
		this.listOfResourceInputRepresentations = new ArrayList<ResourceInputRepresentation>();
		this.listOfResourceOutputRepresentations = new ArrayList<ResourceOutputRepresentation>();
		this.listOfAlgoResourceProperties = new ArrayList<PIMComponentProperty>();
		this.listOfAlgoResourceModelFunctions = new ArrayList<APIMFunction>();
		this.listOfRelatedResourceModelManagers = new ArrayList<ResourceModelManager>();
		this.listOfRelatedAlgoResourceModels = new ArrayList<AlgoResourceModel>();
		this.listOfIncomingResourceModelRelations = new ArrayList<ResourceModel>();
		this.listOfIncomingAlgoResourceModelRelations = new ArrayList<AlgoResourceModel>();
	}

	public AlgoResourceModel(String strAlgoResourceModelName, Resource oParentCIMResource){
		this.iAlgorResourceModelId = UniqueIdProducer.getNewUniqueId();
		this.strAlgoResourceModelName = strAlgoResourceModelName;
		this.oParentCIMResource = oParentCIMResource;
		this.listOfResourceInputRepresentations = new ArrayList<ResourceInputRepresentation>();
		this.listOfResourceOutputRepresentations = new ArrayList<ResourceOutputRepresentation>();
		this.listOfAlgoResourceProperties = new ArrayList<PIMComponentProperty>();
		this.listOfAlgoResourceModelFunctions = new ArrayList<APIMFunction>();
		this.listOfRelatedResourceModelManagers = new ArrayList<ResourceModelManager>();
		this.listOfRelatedAlgoResourceModels = new ArrayList<AlgoResourceModel>();
		this.listOfIncomingResourceModelRelations = new ArrayList<ResourceModel>();
		this.listOfIncomingAlgoResourceModelRelations = new ArrayList<AlgoResourceModel>();
	}
	
	public int getAlgoResourceModelId(){
		return this.iAlgorResourceModelId;
	}
	
	public void setAlgoResourceModelName(String strAlgoResourceModelName){
		this.strAlgoResourceModelName = strAlgoResourceModelName;
	}
	
	public String getAlgoResourceModelName(){
		return this.strAlgoResourceModelName;
	}
	
	public Resource getParentCIMResource(){
		return this.oParentCIMResource;
	}
	
	public void printAlgoResourceModel(){
		System.out.println("Resource Algorithmic Model " + this.getAlgoResourceModelName() + " with id " + this.getAlgoResourceModelId() + " is added in the PIM because " + this.getParentCIMResource().getResourceName() + " exists");
		printResourceInputRepresentations();
		printResourceOutputRepresentations();
		printResourceModelProperties();
		printResourceModelFunctions();
		printAlgoResourceModelRelatedResourceModelManagers();
		printRelatedAlgoResourceModels();
		printIncomingRelations();
	}
	
	public void addResourceInputRepresentations(){
		for(int n = 0; n < this.oParentCIMResource.getResourceOutputRepresentations().size(); n++){
			this.listOfResourceInputRepresentations.add(new ResourceInputRepresentation(this.oParentCIMResource.getResourceInputRepresentations().get(n)));
		}
	}
	
	public void addResourceOutputRepresentations(){
		for(int n = 0; n < this.oParentCIMResource.getResourceOutputRepresentations().size(); n++){
			this.listOfResourceOutputRepresentations.add(new ResourceOutputRepresentation(this.oParentCIMResource.getResourceOutputRepresentations().get(n)));
		}
	}
	
	public void printResourceInputRepresentations(){
		for(int n = 0; n < this.listOfResourceInputRepresentations.size(); n++){
			System.out.println("Input Representation: " + this.listOfResourceInputRepresentations.get(n).getResourceInputRepresentation());
		}
	}
	
	public void printResourceOutputRepresentations(){
		for(int n = 0; n < this.listOfResourceOutputRepresentations.size(); n++){
			System.out.println("Output Representation: " + this.listOfResourceOutputRepresentations.get(n).getResourceOutputRepresentation());
		}
	}
	
	public void addLinkListProperty(){
		this.listOfAlgoResourceProperties.add(new PIMComponentProperty("linkList", "HypermediaLink", false));
	}
	
	public ArrayList<PIMComponentProperty> getAlgoResourceModelProperties(){
		return this.listOfAlgoResourceProperties;
	}
	
	public void addPropertyAccessFunctions(){
		for(int n = 0; n < this.listOfAlgoResourceProperties.size(); n++){
			this.listOfAlgoResourceModelFunctions.add(new SetterFunction(this.listOfAlgoResourceProperties.get(n)));
			this.listOfAlgoResourceModelFunctions.add(new GetterFunction(this.listOfAlgoResourceProperties.get(n)));
		}
	}
	
	public ArrayList<APIMFunction> getAlgoResourceModelFunctions(){
		return this.listOfAlgoResourceModelFunctions;
	}
	
	public void addRepresentationUnmarshallingFunctions(){
		for(int n = 0; n < this.listOfResourceInputRepresentations.size(); n++){
			this.listOfAlgoResourceModelFunctions.add(new ResourceRepresentationParseFunction(this, this.listOfResourceInputRepresentations.get(n)));
		}
	}
	
	public void addRepresentationMarshallingFunctions(){
		for(int n = 0; n < this.listOfResourceOutputRepresentations.size(); n++){
			this.listOfAlgoResourceModelFunctions.add(new ResourceRepresentationMarshallingFunction(this, this.listOfResourceOutputRepresentations.get(n)));
		}
	}
	
	public ArrayList<ResourceInputRepresentation> getAlgoModelInputRepresentations(){
		return this.listOfResourceInputRepresentations;
	}
	
	public ArrayList<ResourceOutputRepresentation> getAlgoModelOutputRepresentations(){
		return this.listOfResourceOutputRepresentations;
	}
	
	private void printResourceModelProperties(){
		for(int n = 0; n < this.listOfAlgoResourceProperties.size(); n++){
			this.listOfAlgoResourceProperties.get(n).printPIMComponentProperty();;
		}
	}
	
	private void printResourceModelFunctions(){
		for(int n = 0; n < this.listOfAlgoResourceModelFunctions.size(); n++){
			this.listOfAlgoResourceModelFunctions.get(n).printPIMFunction();
		}
	}
	
	public boolean hasRelatedModelManager(ResourceModelManager oResourceModelManager){
		for(int n = 0; n < this.oParentCIMResource.getResourceOutgoingRelations().size(); n++){
			if(oResourceModelManager.getParentCIMResource().getResourceId() == this.oParentCIMResource.getResourceOutgoingRelations().get(n)){
				return true;
			}			
		}
		
		return false;
	}
	
	public ArrayList<ResourceModelManager> getAlgoResourceModelRelatedResourceModelManagers(){
		return this.listOfRelatedResourceModelManagers;
	}
	
	public boolean hasRelatedAlgoResourceModel(AlgoResourceModel oAlgoResourceModel){
		for(int n = 0; n < this.oParentCIMResource.getResourceOutgoingRelations().size(); n++){
			if(oAlgoResourceModel.getParentCIMResource().getResourceId() == this.oParentCIMResource.getResourceOutgoingRelations().get(n)){
				return true;
			}			
		}
		return false;
	}
	
	public ArrayList<AlgoResourceModel> getRelatedAlgoResourceModels(){
		return this.listOfRelatedAlgoResourceModels;
	}
	
	private void printAlgoResourceModelRelatedResourceModelManagers(){
		for(int n = 0; n < this.listOfRelatedResourceModelManagers.size(); n++){
			System.out.println("Related resource Model Manager: " + this.listOfRelatedResourceModelManagers.get(n).getResourceModelManagerName());
		}
	}
	
	private void printRelatedAlgoResourceModels(){
		for(int n = 0; n < this.listOfRelatedAlgoResourceModels.size(); n++){
			System.out.println("Related Algorithmic Resource Model: " + this.listOfRelatedAlgoResourceModels.get(n).getAlgoResourceModelName());
		}
	}
	
	public ArrayList<ResourceModel> getIncomingResourceModelRelations(){
		return this.listOfIncomingResourceModelRelations;
	}
	
	public boolean isRelatedOfAlgoResourceModel(AlgoResourceModel oAlgoResourceModel){
		for(int n = 0; n < oAlgoResourceModel.getParentCIMResource().getResourceOutgoingRelations().size(); n++){
			if(this.getParentCIMResource().getResourceId() == oAlgoResourceModel.getParentCIMResource().getResourceOutgoingRelations().get(n)){
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<AlgoResourceModel> getIncomingAlgoResourceModelRelations(){
		return this.listOfIncomingAlgoResourceModelRelations;
	}
	
	private void printIncomingRelations(){
		printIncomingResourceModelRelations();
		printIncomingAlgoResourceModelRelations();
	}
	
	private void printIncomingResourceModelRelations(){
		for(int n = 0; n < this.listOfIncomingResourceModelRelations.size(); n++){
			System.out.println("Incoming Resource Model Relation from: " + this.listOfIncomingResourceModelRelations.get(n).getResourceModelName());
		}
	}
	
	private void printIncomingAlgoResourceModelRelations(){
		for(int n = 0; n < this.listOfIncomingAlgoResourceModelRelations.size(); n++){
			System.out.println("Incoming Algorithmic Model Relation from: " + this.listOfIncomingAlgoResourceModelRelations.get(n).getAlgoResourceModelName());
		}
	}
}