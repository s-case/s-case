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
import main.java.scase.pimMetaModel.DatabaseController;

public class HibernateController{
	
	private int iHibernateControllerId;
	private String strHibernateControllerName;
	private static DatabaseController oPIMParentDabaseController;
	private static HibernateController oHibernateController = new HibernateController();
	private ArrayList<HibernateActivity> listOfHibernateActivities;
	
	private HibernateController(){
		this.iHibernateControllerId = UniqueIdProducer.getNewUniqueId();
		this.strHibernateControllerName = "HibernateController";
		this.listOfHibernateActivities = new ArrayList<HibernateActivity>();
	}
	
	public static void initializeHibernateController(DatabaseController oParentDatabaseController){
		oPIMParentDabaseController = oParentDatabaseController;
	}
	
	public static HibernateController getHibernateControllerHandle(){
		return oHibernateController;
	}
	
	public int getHibernateControllerId(){
		return this.iHibernateControllerId;
	}
	
	public String getHibernateControllerName(){
		return this.strHibernateControllerName;
	}
	
	public ArrayList<HibernateActivity> getControllerHibernateActivities(){
		return this.listOfHibernateActivities;
	}
	
	public DatabaseController getPIMParentDatabaseController(){
		return oPIMParentDabaseController;
	}
	
	public void printHibernateController(){
		System.out.println("Hibernate Controller: " + this.strHibernateControllerName + " is added to PSM because " + oPIMParentDabaseController.getDatabaseControllerName() + " exists in PIM");
		printHibernateActivities();
	}
	
	private void printHibernateActivities(){
		for(int n = 0; n < this.listOfHibernateActivities.size(); n++){
			this.listOfHibernateActivities.get(n).printHibernateActivity();
		}
	}
}