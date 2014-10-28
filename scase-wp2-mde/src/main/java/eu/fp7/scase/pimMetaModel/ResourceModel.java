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


public class ResourceModel{
	
	private int iResourceModelId;
	private String strResourceModelName;
	private Resource oParentCIMResource;
	private ArrayList<ResourceInputRepresentation> listOfResourceInputResourceRepresentations;
	private ArrayList<ResourceOutputRepresentation> listOfResourceOutputResourceRepresentations;
	private ArrayList<ResourceModelProperty> listOfResourceProperties;
	private ArrayList<ResourceModelManager> listOfRelatedResourceModelManagers;
	private ArrayList<AlgoResourceModel> listOfRelatedAlgoResourceModels;
	private ArrayList<APIMFunction> listOfResourceModelFunctions;
	
	public ResourceModel(){
		this.iResourceModelId = UniqueIdProducer.getNewUniqueId();
		this.listOfResourceInputResourceRepresentations = new ArrayList<ResourceInputRepresentation>();
		this.listOfResourceOutputResourceRepresentations = new ArrayList<ResourceOutputRepresentation>();
		this.listOfResourceProperties = new ArrayList<ResourceModelProperty>();
		this.listOfRelatedResourceModelManagers = new ArrayList<ResourceModelManager>();
		this.listOfRelatedAlgoResourceModels = new ArrayList<AlgoResourceModel>();
		this.listOfResourceModelFunctions = new ArrayList<APIMFunction>();
	}
	
	public ResourceModel(String strResourceModelName, Resource oParentCIMResource){
		this.iResourceModelId = UniqueIdProducer.getNewUniqueId();
		this.strResourceModelName = strResourceModelName;
		this.oParentCIMResource = oParentCIMResource;
		this.listOfResourceInputResourceRepresentations = new ArrayList<ResourceInputRepresentation>();
		this.listOfResourceOutputResourceRepresentations = new ArrayList<ResourceOutputRepresentation>();
		this.listOfResourceProperties = new ArrayList<ResourceModelProperty>();
		this.listOfRelatedResourceModelManagers = new ArrayList<ResourceModelManager>();
		this.listOfRelatedAlgoResourceModels = new ArrayList<AlgoResourceModel>();
		this.listOfResourceModelFunctions = new ArrayList<APIMFunction>();
	}
	
	public int getResourceModelId(){
		return this.iResourceModelId;
	}
	
	public void setResourceModelName(String strResourceModelName){
		this.strResourceModelName = strResourceModelName;
	}
	
	public String getResourceModelName(){
		return this.strResourceModelName;
	}
	
	public Resource getParentCIMResource(){
		return this.oParentCIMResource;
	}
	
	public ArrayList<ResourceInputRepresentation> getResourceInputRepresentations(){
		return this.listOfResourceInputResourceRepresentations;
	}
	
	public ArrayList<ResourceOutputRepresentation> getResourceOutputRepresentations(){
		return this.listOfResourceOutputResourceRepresentations;
	}
	
	public void addResourceInputRepresentations(){
		for(int n = 0; n < this.oParentCIMResource.getResourceInputRepresentations().size(); n++){
			this.listOfResourceInputResourceRepresentations.add(new ResourceInputRepresentation(this.oParentCIMResource.getResourceInputRepresentations().get(n)));
		}
	}
	
	public void addResourceOutputRepresentations(){
		for(int n = 0; n < this.oParentCIMResource.getResourceOutputRepresentations().size(); n++){
			this.listOfResourceOutputResourceRepresentations.add(new ResourceOutputRepresentation(this.oParentCIMResource.getResourceOutputRepresentations().get(n)));
		}
	}
	
	public void printResourceModel(){
		System.out.println("Resource Model " + this.getResourceModelName() + " with id " + this.getResourceModelId() + " is added to the PIM because parent " + this.getParentCIMResource().getResourceName() + " exists");
		printResourceInputRepresentations();
		printResourceOutputRepresentations();
		printResourceModelProperties();
		printResourceModelFunctions();
		printResourceModelRelatedResourceModelManagers();
		printRelatedAlgoResourceModels();
	}
	
	public ArrayList<ResourceModelProperty> getResourceModelProperties(){
		return this.listOfResourceProperties;
	}
	
	public void addResourceModelProperties(){
		for(int n = 0; n < this.oParentCIMResource.getResourceProperties().size(); n++){
			this.listOfResourceProperties.add(new ResourceModelProperty(this.oParentCIMResource.getResourceProperties().get(n)));
		}
	}
	
	public ArrayList<ResourceModelManager> getResourceModelRelatedResourceModelManagers(){
		return this.listOfRelatedResourceModelManagers;
	}
	
	private void printResourceInputRepresentations(){
		for(int n = 0; n < this.listOfResourceInputResourceRepresentations.size(); n++){
			System.out.println("Input Representation: " + this.listOfResourceInputResourceRepresentations.get(n).getResourceInputRepresentation());
		}
	}
	
	private void printResourceOutputRepresentations(){
		for(int n = 0; n < this.listOfResourceOutputResourceRepresentations.size(); n++){
			System.out.println("Output Representation: " + this.listOfResourceOutputResourceRepresentations.get(n).getResourceOutputRepresentation());
		}
	}
	
	private void printResourceModelProperties(){
		for(int n = 0; n < this.listOfResourceProperties.size(); n++){
			this.listOfResourceProperties.get(n).printResourceModelProperty();
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
	
	public boolean hasRelatedAlgoResourceModel(AlgoResourceModel oAlgoResourceModel){
		for(int n = 0; n < this.oParentCIMResource.getResourceOutgoingRelations().size(); n++){
			if(oAlgoResourceModel.getParentCIMResource().getResourceId() == this.oParentCIMResource.getResourceOutgoingRelations().get(n)){
				return true;
			}			
		}
		return false;
	}
	
	private void printResourceModelRelatedResourceModelManagers(){
		for(int n = 0; n < this.listOfRelatedResourceModelManagers.size(); n++){
			System.out.println("Related resource Model Manager: " + this.listOfRelatedResourceModelManagers.get(n).getResourceModelManagerName());
		}
	}
	
	public ArrayList<AlgoResourceModel> getRelatedAlgoResourceModels(){
		return this.listOfRelatedAlgoResourceModels;
	}
	
	private void printRelatedAlgoResourceModels(){
		for(int n = 0; n < this.listOfRelatedAlgoResourceModels.size(); n++){
			System.out.println("Related Algorithmic Resource Model: " + this.listOfRelatedAlgoResourceModels.get(n).getAlgoResourceModelName());
		}
	}
	
	public void addIncomingRelationsToRelatedResourceModelManagers(){
		for(int n = 0; n < this.listOfRelatedResourceModelManagers.size(); n++){
			this.listOfRelatedResourceModelManagers.get(n).getIncomingResourceModelRelations().add(this);
		}
	}
	
	public void addIncomingRelationsToRelatedAlgoResourceModel(){
		for(int n = 0; n < this.listOfRelatedAlgoResourceModels.size(); n++){
			this.listOfRelatedAlgoResourceModels.get(n).getIncomingResourceModelRelations().add(this);
		}
	}
	
	public void addPrimaryIdentifier(){
		this.listOfResourceProperties.add(new ResourceModelProperty(String.format("%sId", this.oParentCIMResource.getResourceName()), "int"));
	}
	
	public void addLinkListProperty(){
		this.listOfResourceProperties.add(new ResourceModelProperty("linkList", "HypermediaLink", false));
	}
	
	public void addPropertyAccessFunctions(){
		for(int n = 0; n < this.listOfResourceProperties.size(); n++){
			this.listOfResourceModelFunctions.add(new SetterFunction(this.listOfResourceProperties.get(n)));
			this.listOfResourceModelFunctions.add(new GetterFunction(this.listOfResourceProperties.get(n)));
		}
	}
	
	public ArrayList<APIMFunction> getResourceModelFunctions(){
		return this.listOfResourceModelFunctions;
	}
	
	private void printResourceModelFunctions(){
		for(int n = 0; n < this.listOfResourceModelFunctions.size(); n++){
			this.listOfResourceModelFunctions.get(n).printPIMFunction();
		}
	}
	
	public void addRepresentationUnmarshallingFunctions(){
		for(int n = 0; n < this.listOfResourceInputResourceRepresentations.size(); n++){
			this.listOfResourceModelFunctions.add(new ResourceRepresentationParseFunction(this, this.listOfResourceInputResourceRepresentations.get(n)));
		}
	}
	
	public void addRepresentationMarshallingFunctions(){
		for(int n = 0; n < this.listOfResourceOutputResourceRepresentations.size(); n++){
			this.listOfResourceModelFunctions.add(new ResourceRepresentationMarshallingFunction(this, this.listOfResourceOutputResourceRepresentations.get(n)));
		}
	}
	
	public String getModelPrimaryIdentifierName(){
		for(int n = 0; n < this.listOfResourceProperties.size(); n++){
			if( this.listOfResourceProperties.get(n).getResourceModelIdentifyingAbility()){
				return this.listOfResourceProperties.get(n).getPIMComponentProeprtyName();
			}
		}
		
		try {
			throw new Exception("The PIM is invalid! Could not retrieve resource model primary identifier of model " + this.strResourceModelName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}