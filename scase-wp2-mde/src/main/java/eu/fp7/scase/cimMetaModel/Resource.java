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

package eu.fp7.scase.cimMetaModel;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eu.fp7.scase.cimMetaModel.Property;
import eu.fp7.scase.customUtilities.UniqueIdProducer;

public class Resource
{
	private int iResourceId;
	private String strResourceName;
	private boolean bIsAlgorithmicResource;
	private String strURI;
	private List<Property> listOfProperties = new ArrayList<Property>();
	private List<String> listOfInputRepresentations = new ArrayList<String>();
	private List<String> listOfOutputRepresentations = new ArrayList<String>();
	private List<Integer> listOfOutgoingRelations = new ArrayList<Integer>();
	private List<Integer> listOfIncomingRelations = new ArrayList<Integer>();
	private List<CRUDActivity> listOfCRUDActivities = new ArrayList<CRUDActivity>();
	
	public Resource(String resourceName, boolean isAlgorithmic){
		this.strResourceName = resourceName;
		this.bIsAlgorithmicResource = isAlgorithmic;
		this.iResourceId = UniqueIdProducer.getNewUniqueId();
	}
	
	public Resource(String resourceName){
		this.strResourceName = resourceName;
		this.iResourceId = UniqueIdProducer.getNewUniqueId();
	}
	
	public int getResourceId(){
		return this.iResourceId;
	}
	
	public String getResourceName(){
		return this.strResourceName;
	}
	
	public void setResourceName(String name){
		this.strResourceName = name;
	}
	
	public boolean getResourceType(){
		return this.bIsAlgorithmicResource;
	}
	
	public void setResourceType(Boolean bIsAlgorithmicResource){
		this.bIsAlgorithmicResource = bIsAlgorithmicResource;
	}
	
	public List<Property> getResourceProperties(){
		return this.listOfProperties;
	}
	
	public List<String> getResourceInputRepresentations(){
		return this.listOfInputRepresentations;
	}
	
	public List<String> getResourceOutputRepresentations(){
		return this.listOfOutputRepresentations;
	}
	
	public List<Integer> getResourceOutgoingRelations(){
		return this.listOfOutgoingRelations;
	}
	
	public List<Integer> getResourceIncomingRelations(){
		return this.listOfIncomingRelations;
	}
	
	public void addProperty(Property newProperty){
		this.listOfProperties.add(newProperty);
	}
	
	public void addInputRepresentation(String inputRepresentation){
		this.listOfInputRepresentations.add(inputRepresentation);
	}
	
	public void addOutputRepresentation(String outputRepresentation){
		this.listOfOutputRepresentations.add(outputRepresentation);
	}
	
	public Resource addOutgoingRelation(int relatedResourceId){
		
		if(relatedResourceId == -1){
			try {
				throw new Exception("Invalid resource Id! Check input file format!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			this.listOfOutgoingRelations.add(relatedResourceId);

		}
		return this;
	}
	
	public Resource addIncomingRelation(int relatedResourceId){
		
		if(relatedResourceId == -1){
			try {
				throw new Exception("Invalid resource Id! Check input file format!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			this.listOfIncomingRelations.add(relatedResourceId);

		}
		return this;
	}
	
	public void addCRUDActivity(CRUDActivity newCRUDActivity){
		this.listOfCRUDActivities.add(newCRUDActivity);
	}
	
	public List<CRUDActivity> getResourceCRUDActivities(){
		return this.listOfCRUDActivities;
	}
	
	
	public void printResourceDetails(){
		System.out.println("Resource Name: " + this.strResourceName);
		System.out.println("Resource type: " + (this.bIsAlgorithmicResource == true ? "algorithmic" : "non-algorithmic"));
		System.out.println("Resource Id: " + this.iResourceId);
		System.out.println("Resource unique URI: " + this.strURI);
		
		printResourceProperties();
		printResourceInputRepresentations();
		printResourceOutputRepresentations();
		printResourceCRUDActivities();
		printResourceOutgoingRelations();
		printResourceIncomingRelations();
	}
	
	private void printResourceProperties(){
		Iterator<Property> iteratorOfProperties = this.listOfProperties.iterator();
		
		while(iteratorOfProperties.hasNext()){
			Property oCurrentProperty = iteratorOfProperties.next();
			
			oCurrentProperty.printPropertyDetails();
		}
	}
	
	private void printResourceInputRepresentations(){
		Iterator<String> iteratorOfInputRepresentations = this.listOfInputRepresentations.iterator();
		
		while(iteratorOfInputRepresentations.hasNext()){
			System.out.println("Resource input representation type :" + iteratorOfInputRepresentations.next());
		}
	}
	
	private void printResourceOutputRepresentations(){
		Iterator<String> iteratorOfOutputRepresentations = this.listOfOutputRepresentations.iterator();
		
		while(iteratorOfOutputRepresentations.hasNext()){
			System.out.println("Resource output representations type :" + iteratorOfOutputRepresentations.next());
		}
	}
	
	private void printResourceCRUDActivities(){
		Iterator<CRUDActivity> iteratorOfCRUDActivites = this.listOfCRUDActivities.iterator();
		
		while(iteratorOfCRUDActivites.hasNext()){
			CRUDActivity oCurrentCRUDActivity = iteratorOfCRUDActivites.next();
			
			oCurrentCRUDActivity.printCRUDActivityDetails();
		}
	}
	
	private void printResourceOutgoingRelations(){
		Iterator<Integer> iteratorOfResourceOutgoingRelations = this.listOfOutgoingRelations.iterator();
		
		while(iteratorOfResourceOutgoingRelations.hasNext()){
			System.out.println("Resource " + this.strResourceName + " has as related resource the one with id " + iteratorOfResourceOutgoingRelations.next());
		}
	}
	
	private void printResourceIncomingRelations(){
		Iterator<Integer> iteratorOfResourceIncomingRelations = this.listOfIncomingRelations.iterator();
		
		while(iteratorOfResourceIncomingRelations.hasNext()){
			System.out.println("Resource " + this.strResourceName + " is related resource of the one with id " + iteratorOfResourceIncomingRelations.next());
		}
	}
	
	public String getResourceURI(){
		return this.strURI;
	}
	
	public void setResourceURI(String strURI){
		this.strURI = strURI;
	}
}