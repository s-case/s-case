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
import main.java.scase.pimMetaModel.ResourceModelManager;

public class JavaResourceModelManager{
	
	private int iJavaResourceModelManagerId;
	private ResourceModelManager oPIMParentResourceModelManager;
	private String strJavaResourceModelManagerName;
	private JAXBAnnotation oJAXBRootElementAnnotation;
	private ArrayList<PSMComponentProperty> listOfJavaModelManagerProperties;
	private JavaResourceModel oRelatedJavaResourceModel;
	private ArrayList<JavaResourceModel> listOfIncomingJavaModelRelations;
	private ArrayList<JavaGetterFunction> listOfJavaManagerGetterFunctions;
	private ArrayList<JavaSetterFunction> listOfJavaManagerSetterFunctions;
	
	public JavaResourceModelManager(ResourceModelManager oResourceModelManager){
		this.iJavaResourceModelManagerId = UniqueIdProducer.getNewUniqueId();
		this.oPIMParentResourceModelManager = oResourceModelManager;
		this.strJavaResourceModelManagerName = String.format("Java%s", oResourceModelManager.getResourceModelManagerName());
		this.oJAXBRootElementAnnotation = new JAXBAnnotation("@XmlRootElement");
		this.listOfJavaModelManagerProperties = new ArrayList<PSMComponentProperty>();
		this.listOfIncomingJavaModelRelations = new ArrayList<JavaResourceModel>();
		this.listOfJavaManagerGetterFunctions = new ArrayList<JavaGetterFunction>();
		this.listOfJavaManagerSetterFunctions = new ArrayList<JavaSetterFunction>();
	}
	
	public int getJavaResourceModelMangerId(){
		return this.iJavaResourceModelManagerId;
	}
	
	public ResourceModelManager getPIMParentResourceModelManager(){
		return this.oPIMParentResourceModelManager;
	}
	
	public String getJavaResourceModelManagerName(){
		return this.strJavaResourceModelManagerName;
	}
	
	public JAXBAnnotation getModelManagerJAXBAnnotaiton(){
		return this.oJAXBRootElementAnnotation;
	}
	
	public ArrayList<PSMComponentProperty> getJavaModelManagerProperties(){
		return this.listOfJavaModelManagerProperties;
	}
	
	public JavaResourceModel getRelatedJavaResourceModel(){
		return this.oRelatedJavaResourceModel;
	}
	
	public void setRelatedJavaResourceModel(JavaResourceModel oRelatedJavaResourceModel){
		this.oRelatedJavaResourceModel = oRelatedJavaResourceModel;
	}
	
	public ArrayList<JavaResourceModel> getIncomingJavaModelRelations(){
		return this.listOfIncomingJavaModelRelations;
	}
	
	public ArrayList<JavaGetterFunction> getJavaManagerGetterFunctions(){
		return this.listOfJavaManagerGetterFunctions;
	}
	
	public ArrayList<JavaSetterFunction> getJavaManagerSetterFunctions(){
		return this.listOfJavaManagerSetterFunctions;
	}
	
	public void addJavaResourceModelManagerProperties(){
		for(int n = 0; n < this.oPIMParentResourceModelManager.getResourceModelManagerProperties().size(); n++){
			this.listOfJavaModelManagerProperties.add(new PSMComponentProperty(this.oPIMParentResourceModelManager.getResourceModelManagerProperties().get(n)));
		}
	}
	
	public void printJavaResourceModelManager(){
		System.out.println("The Java Resource Model Manager: " + this.strJavaResourceModelManagerName + " is added to PSM because " + this.oPIMParentResourceModelManager.getResourceModelManagerName() + " exists in PIM" );
		System.out.println("JAXBAnnotation: " + this.oJAXBRootElementAnnotation.getJAXBAnnotationText());
		printJavaModelManagerProperties();
		printIncomingJavaModelRelations();
		printJavaSetterFunctions();
		printJavaGetterFunctions();
	}
	
	private void printJavaModelManagerProperties(){
		for(int n = 0; n < this.listOfJavaModelManagerProperties.size(); n++){
			this.listOfJavaModelManagerProperties.get(n).printPSMComponentProperty();
		}
	}
	
	private void printIncomingJavaModelRelations(){
		for(int n = 0; n < this.listOfIncomingJavaModelRelations.size(); n++){
			System.out.println("Is Related of " + this.listOfIncomingJavaModelRelations.get(n).getJavaResourceModelName() + " Java Resource Model");
		}
	}
	
	private void printJavaSetterFunctions(){
		for(int n = 0; n < this.listOfJavaManagerSetterFunctions.size(); n++){
			this.listOfJavaManagerSetterFunctions.get(n).printJavaFunction();
		}
	}
	
	private void printJavaGetterFunctions(){
		for(int n = 0; n < this.listOfJavaManagerGetterFunctions.size(); n++){
			this.listOfJavaManagerGetterFunctions.get(n).printJavaFunction();
		}
	}
	
	public void createAccessFunctions(){
		for(int n = 0; n < this.listOfJavaModelManagerProperties.size(); n++){
			this.listOfJavaManagerSetterFunctions.add(new JavaSetterFunction(this.listOfJavaModelManagerProperties.get(n), false));
			this.listOfJavaManagerGetterFunctions.add(new JavaGetterFunction(this.listOfJavaModelManagerProperties.get(n)));
		}
	}
}