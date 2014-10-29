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


import eu.fp7.scase.customUtilities.UniqueIdProducer;
public class CRUDActivity
{
	private int iActivityId;
	private String strActivityCRUDVerb;
	
	public CRUDActivity(){
		this.iActivityId = UniqueIdProducer.getNewUniqueId();
	}
	
	public CRUDActivity(String activityCRUDVerb){
		this.iActivityId = UniqueIdProducer.getNewUniqueId();
		this.strActivityCRUDVerb = activityCRUDVerb;
	}
	
	public int getActivityId(){
		return this.iActivityId;
	}
	
	public String getActivityCRUDVerb(){
		return this.strActivityCRUDVerb;
	}
	
	public void setActivityCRUDVerb(String verb){
		this.strActivityCRUDVerb = verb;
	}
	
	public void printCRUDActivityDetails(){
		System.out.println("CRUDActivity Id: " + this.iActivityId);
		System.out.println("CRUDActivity CRUD Verb " + this.strActivityCRUDVerb);
	}
}