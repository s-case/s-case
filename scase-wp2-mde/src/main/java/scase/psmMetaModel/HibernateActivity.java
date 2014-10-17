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

import main.java.scase.customUtilities.UniqueIdProducer;

public class HibernateActivity{
	
	private int iHibernateActivityId;
	private String strHibernateActivityName;
	private String strHibernateActivityVerb;
	private JavaResourceController oParentJavaResourceController;
	private JavaResourceControllerManager oParentJavaControllerManager;
	private JavaResourceModel oIncomingJavaModel;
	
	public HibernateActivity(String strHibernateActivityName, String strHibernateActivityVerb, JavaResourceController oParentJavaResourceController){
		this.iHibernateActivityId = UniqueIdProducer.getNewUniqueId();
		this.strHibernateActivityName = strHibernateActivityName;
		this.strHibernateActivityVerb = strHibernateActivityVerb;
		this.oParentJavaResourceController = oParentJavaResourceController;
	}
	
	public HibernateActivity(String strHibernateActivityName, String strHibernateActivityVerb, JavaResourceControllerManager oParentJavaControllerManager, JavaResourceModel oIncomingJavaModel){
		this.iHibernateActivityId = UniqueIdProducer.getNewUniqueId();
		this.strHibernateActivityName = strHibernateActivityName;
		this.strHibernateActivityVerb = strHibernateActivityVerb;
		this.oParentJavaControllerManager = oParentJavaControllerManager;
		this.oIncomingJavaModel = oIncomingJavaModel;
	}
	
	public JavaResourceModel getIncomingJavaModel(){
		return this.oIncomingJavaModel;
	}
	
	public int getHibernateActivityId(){
		return this.iHibernateActivityId;
	}
	
	public String getHibernateActivityName(){
		return this.strHibernateActivityName;
	}
	
	public String getHibernateActivityVerb(){
		return this.strHibernateActivityVerb;
	}
	
	public JavaResourceController getParentJavaResourceController(){
		return this.oParentJavaResourceController;
	}
	
	public JavaResourceControllerManager getParentJavaControllerManager(){
		return this.oParentJavaControllerManager;
	}
	
	public void printHibernateActivity(){
		System.out.println("Hibernate Activity: " + this.strHibernateActivityName + " is added to PSM because " + (this.oParentJavaControllerManager == null? this.oParentJavaResourceController.getJavaResourceControllerName() : this.oParentJavaControllerManager.getJavaResourceControllerManagerName()) + " has a " + this.strHibernateActivityVerb + " HTTP activity");
	}
}
