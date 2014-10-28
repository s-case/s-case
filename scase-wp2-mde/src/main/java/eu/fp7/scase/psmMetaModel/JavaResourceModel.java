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
import main.java.scase.pimMetaModel.ResourceModel;

public class JavaResourceModel{
	
	private int iJavaResourceModelId;
	private ResourceModel oPIMParentResourceModel;
	private String strJavaResourceModelName;
	private JAXBAnnotation oJAXBRootElementAnnotation;
	private ArrayList<JavaResourceModelProperty> listOfJavaModelProperties;
	private ArrayList<JavaResourceModelManager> listOfRelatedJavaResourceModelManagers;
	private ArrayList<JavaAlgoResourceModel> listOfRelatedJavaAlgoResourceModels;
	private HibernateAnnotation oEntityHibernateAnnotation;
	private HibernateAnnotation oTableHibernateAnnotation;
	private ArrayList<JavaSetterFunction> listOfJavaModelSetterFunctions;
	private ArrayList<JavaGetterFunction> listOfJavaModelGetterFunctions;
	private ArrayList<JavaResourceModel> listOfIncomingJavaResourceModels;
	private JavaResourceController oAssociatedJavaResourceController;
	
	
	public JavaResourceModel(ResourceModel oResourceModel){
		this.iJavaResourceModelId = UniqueIdProducer.getNewUniqueId();
		this.oPIMParentResourceModel = oResourceModel;
		this.strJavaResourceModelName = String.format("Java%s", oResourceModel.getResourceModelName());
		this.oJAXBRootElementAnnotation = new JAXBAnnotation("@XmlRootElement");
		this.listOfJavaModelProperties = new ArrayList<JavaResourceModelProperty>();
		this.listOfRelatedJavaResourceModelManagers = new ArrayList<JavaResourceModelManager>();
		this.listOfRelatedJavaAlgoResourceModels = new ArrayList<JavaAlgoResourceModel>();
		this.oEntityHibernateAnnotation = new HibernateAnnotation("@Entity");
		this.oTableHibernateAnnotation = new HibernateAnnotation(String.format("@Table(name=\"%s\")", this.oPIMParentResourceModel.getParentCIMResource().getResourceName().toLowerCase()));
		this.listOfJavaModelSetterFunctions = new ArrayList<JavaSetterFunction>();
		this.listOfJavaModelGetterFunctions = new ArrayList<JavaGetterFunction>();
		this.listOfIncomingJavaResourceModels = new ArrayList<JavaResourceModel>();
	}
	
	public ArrayList<JavaResourceModel> getIncomingJavaModels(){
		return this.listOfIncomingJavaResourceModels;
	}
	
	public void setAssociatedJavaResourceController(JavaResourceController oAssociatedJavaResourceController){
		this.oAssociatedJavaResourceController = oAssociatedJavaResourceController;
	}
	
	public JavaResourceController getAssociatedResourceController(){
		return this.oAssociatedJavaResourceController;
	}
	
	public void setIncomingJavaModels(ArrayList<JavaResourceModel> listOfIncomingJavaModels){
		this.listOfIncomingJavaResourceModels = listOfIncomingJavaModels;
	}
	
	public int getJavaResourceModelId(){
		return this.iJavaResourceModelId;
	}
	
	public ResourceModel getPIMParentResourceModel(){
		return this.oPIMParentResourceModel;
	}
	
	public String getJavaResourceModelName(){
		return this.strJavaResourceModelName;
	}
	
	public JAXBAnnotation getModelJAXBAnnotaiton(){
		return this.oJAXBRootElementAnnotation;
	}
	
	public ArrayList<JavaResourceModelProperty> getJavaResourceModelProperties(){
		return this.listOfJavaModelProperties;
	}
	
	public ArrayList<JavaAlgoResourceModel> getRelatedJavaAlgoResourceModels(){
		return this.listOfRelatedJavaAlgoResourceModels;
	}
	
	public HibernateAnnotation getEntityHibernateAnnotation(){
		return this.oEntityHibernateAnnotation;
	}
	
	public HibernateAnnotation getTableHibernateAnnotation(){
		return this.oTableHibernateAnnotation;
	}
	
	public void addJavaResourceModelProperties(){
		for(int n = 0; n < this.oPIMParentResourceModel.getResourceModelProperties().size(); n++){
			this.listOfJavaModelProperties.add(new JavaResourceModelProperty(this.oPIMParentResourceModel.getResourceModelProperties().get(n), this));
		}
	}
	
	public ArrayList<JavaSetterFunction> getModelJavaSetterFunctions(){
		return this.listOfJavaModelSetterFunctions;
	}
	
	public ArrayList<JavaGetterFunction> getModelJavaGetterFunctions(){
		return this.listOfJavaModelGetterFunctions;
	}
	
	public ArrayList<JavaResourceModelManager> getRelatedJavaResourceModelManagers(){
		return this.listOfRelatedJavaResourceModelManagers;
	}
	
	public void printJavaResourceModel(){
		System.out.println("Java Resource Model: " + this.strJavaResourceModelName + " is added to PSM because " + this.oPIMParentResourceModel.getResourceModelName() + " exists in PIM");
		System.out.println("JAXBAnnotation: " + this.oJAXBRootElementAnnotation.getJAXBAnnotationText());
		System.out.println("Hibernate @Entity annotation: " + this.oEntityHibernateAnnotation.getHibernateAnnotationText());
		System.out.println("Hibernate @Table annotation: " + this.oTableHibernateAnnotation.getHibernateAnnotationText());
		printJavaResourceModelProperties();
		printRelatedJavaResourceModelManagers();
		printRelatedJavaAlgoResourceModels();
		printSetterFunctions();
		printGetterFunctions();
	}
	
	private void printJavaResourceModelProperties(){
		for(int n = 0; n < this.listOfJavaModelProperties.size(); n++){
			this.listOfJavaModelProperties.get(n).printJavaResourceModelProperty();
		}
	}
	
	private void printRelatedJavaResourceModelManagers(){
		for(int n = 0; n < this.listOfRelatedJavaResourceModelManagers.size(); n++){
			System.out.println("Related Java Resource Model Manager: " + this.listOfRelatedJavaResourceModelManagers.get(n).getJavaResourceModelManagerName());
		}
	}
	
	private void printRelatedJavaAlgoResourceModels(){
		for(int n = 0; n < this.listOfRelatedJavaAlgoResourceModels.size(); n++){
			System.out.println("Related Java Algorithmic Model: " + this.listOfRelatedJavaAlgoResourceModels.get(n).getJavaAlgoResourceModelName());
		}
	}
	
	private void printSetterFunctions(){
		for(int n = 0; n < this.listOfJavaModelSetterFunctions.size(); n++){
			this.listOfJavaModelSetterFunctions.get(n).printJavaFunction();
		}
	}
	
	private void printGetterFunctions(){
		for(int n = 0; n < this.listOfJavaModelGetterFunctions.size(); n++){
			this.listOfJavaModelGetterFunctions.get(n).printJavaFunction();
		}
	}
	
	public boolean hasRelatedJavaResourceModelManager(JavaResourceModelManager oJavaResourceModelManager){
		for(int n = 0; n < this.oPIMParentResourceModel.getResourceModelRelatedResourceModelManagers().size(); n++){
			if(this.oPIMParentResourceModel.getResourceModelRelatedResourceModelManagers().get(n).getResourceModelManagerId() == oJavaResourceModelManager.getPIMParentResourceModelManager().getResourceModelManagerId()){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasRelatedJavaAlgoResourceModel(JavaAlgoResourceModel oJavaAlgoResourceModel){
		for(int n = 0; n < this.oPIMParentResourceModel.getRelatedAlgoResourceModels().size(); n++){
			if(this.oPIMParentResourceModel.getRelatedAlgoResourceModels().get(n).getAlgoResourceModelId() == oJavaAlgoResourceModel.getParentPIMAlgoResourceModel().getAlgoResourceModelId()){
				return true;
			}
		}
		
		return false;
	}
	
	public void addIncomingRelationToRelatedJavaModelManager(){
		for(int n = 0; n < this.listOfRelatedJavaResourceModelManagers.size(); n++){
			this.listOfRelatedJavaResourceModelManagers.get(n).getIncomingJavaModelRelations().add(this);
		}
	}
	
	public void addHibernateRelationProperties(){
		for(int n = 0; n < this.listOfRelatedJavaResourceModelManagers.size(); n++){
			this.listOfJavaModelProperties.add(new JavaResourceModelProperty(String.format("setOf%s",this.listOfRelatedJavaResourceModelManagers.get(n).getRelatedJavaResourceModel().getJavaResourceModelName()), this.listOfRelatedJavaResourceModelManagers.get(n).getRelatedJavaResourceModel().getJavaResourceModelName(), this.oPIMParentResourceModel.getParentCIMResource().getResourceName()));
			this.listOfRelatedJavaResourceModelManagers.get(n).getRelatedJavaResourceModel().getJavaResourceModelProperties().add(new JavaResourceModelProperty(String.format("o%s", this.oPIMParentResourceModel.getParentCIMResource().getResourceName()), this.getJavaResourceModelName(), (this.iJavaResourceModelId != this.getRelatedJavaResourceModelManagers().get(n).getRelatedJavaResourceModel().getJavaResourceModelId() ? this.getPIMParentResourceModel().getModelPrimaryIdentifierName().toLowerCase() : String.format("source%s", this.listOfRelatedJavaResourceModelManagers.get(n).getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName())), String.format("fk_%s_%s", this.oPIMParentResourceModel.getParentCIMResource().getResourceName(), this.listOfRelatedJavaResourceModelManagers.get(n).getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName()).toLowerCase()));
		}
	}
	
	public void createAccessFunctions(){
		for(int n = 0; n < this.listOfJavaModelProperties.size(); n++){
			if( this.listOfJavaModelProperties.get(n).getOneToManyHibernateAnnotation() != null || this.listOfJavaModelProperties.get(n).getManyToOneHibernateAnnotation() != null){
				this.listOfJavaModelSetterFunctions.add(new JavaSetterFunction(this.listOfJavaModelProperties.get(n), true));
			}
			else{
				this.listOfJavaModelSetterFunctions.add(new JavaSetterFunction(this.listOfJavaModelProperties.get(n), false));
			}
			this.listOfJavaModelGetterFunctions.add(new JavaGetterFunction(this.listOfJavaModelProperties.get(n)));
		}
	}
	
	public String getNamingPropertyNameValue(){
		for(int n = 0; n < this.listOfJavaModelProperties.size(); n++){
			if(this.listOfJavaModelProperties.get(n).getJavaModelPropertyNamingAbility()){
				for(int i = 0; i < this.listOfJavaModelGetterFunctions.size(); i++){
					if(this.listOfJavaModelProperties.get(n).getPSMComponentPropertyId() == this.listOfJavaModelGetterFunctions.get(i).getAccessedProperty().getPSMComponentPropertyId()){
						return String.format("o%s.%s()", this.strJavaResourceModelName, this.listOfJavaModelGetterFunctions.get(i).getJavaFunctionName());
					}
				}
			}
		}
		//this should never happen, as every resource must have exactly one naming ability
		return null;
	}
	
	public String getIdentifyingPropertyIdValue(){
		for(int n = 0; n < this.listOfJavaModelProperties.size(); n++){
			if(this.listOfJavaModelProperties.get(n).getJavaModelPropertyIdentifyingAbility()){
				for(int i = 0; i < this.listOfJavaModelGetterFunctions.size(); i++){
					if(this.listOfJavaModelProperties.get(n).getPSMComponentPropertyId() == this.listOfJavaModelGetterFunctions.get(i).getAccessedProperty().getPSMComponentPropertyId()){
						return String.format("o%s.%s()", this.strJavaResourceModelName, this.listOfJavaModelGetterFunctions.get(i).getJavaFunctionName());
					}
				}
			}
		}
		//this should never happen, as every resource must have exactly one identifying ability
		return null;
	}
	
	public String getNamingPropertyNameGetter(){
		for(int n = 0; n < this.listOfJavaModelProperties.size(); n++){
			if(this.listOfJavaModelProperties.get(n).getJavaModelPropertyNamingAbility()){
				for(int i = 0; i < this.listOfJavaModelGetterFunctions.size(); i++){
					if(this.listOfJavaModelProperties.get(n).getPSMComponentPropertyId() == this.listOfJavaModelGetterFunctions.get(i).getAccessedProperty().getPSMComponentPropertyId()){
						return String.format("%s()", this.listOfJavaModelGetterFunctions.get(i).getJavaFunctionName());
					}
				}
			}
		}
		//this should never happen, as every resource must have exactly one naming ability
		return null;
	}
	
	public String getIdentifyingPropertyIdGetter(){
		for(int n = 0; n < this.listOfJavaModelProperties.size(); n++){
			if(this.listOfJavaModelProperties.get(n).getJavaModelPropertyIdentifyingAbility()){
				for(int i = 0; i < this.listOfJavaModelGetterFunctions.size(); i++){
					if(this.listOfJavaModelProperties.get(n).getPSMComponentPropertyId() == this.listOfJavaModelGetterFunctions.get(i).getAccessedProperty().getPSMComponentPropertyId()){
						return String.format("%s()", this.listOfJavaModelGetterFunctions.get(i).getJavaFunctionName());
					}
				}
			}
		}
		//this should never happen, as every resource must have exactly one identifying ability
		return null;
	}
	
	public boolean isRelatedResource(){
		if(this.listOfIncomingJavaResourceModels.size() != 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean hasUniqueIncomingRelation(){
		if(this.listOfIncomingJavaResourceModels.size() == 1){
			return true;
		}
		else{
			return false;
		}
	}
}