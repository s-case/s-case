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

package eu.fp7.scase.psmMetaModel;

import java.util.ArrayList;

public class SystemPSM{
	
	private ArrayList<JavaResourceModel> listOfPSMResourceModels;
	private ArrayList<JavaResourceModelManager> listOfPSMResourceModelManagers;
	private ArrayList<JavaResourceControllerManager> listOfPSMResourceControllerManagers;
	private ArrayList<JavaResourceController> listOfPSMResourceControllers;
	private ArrayList<JavaAlgoResourceModel> listOfPSMAlgoResourceModels;
	private ArrayList<JavaAlgoResourceController> listOfPSMAlgoResourceControllers;
	private HibernateController oHibernateController;
	
	public SystemPSM(){
		this.listOfPSMResourceModelManagers = new ArrayList<JavaResourceModelManager>();
		this.listOfPSMResourceModels = new ArrayList<JavaResourceModel>();
		this.listOfPSMResourceControllerManagers = new ArrayList<JavaResourceControllerManager>();
		this.listOfPSMResourceControllers = new ArrayList<JavaResourceController>();
		this.listOfPSMAlgoResourceModels = new ArrayList<JavaAlgoResourceModel>();
		this.listOfPSMAlgoResourceControllers = new ArrayList<JavaAlgoResourceController>();
	}
	
	public ArrayList<JavaResourceModel> getPSMResourceModels(){
		return this.listOfPSMResourceModels;
	}
	
	public ArrayList<JavaResourceModelManager> getPSMResourceModelManagers(){
		return this.listOfPSMResourceModelManagers;
	}
	
	public ArrayList<JavaResourceControllerManager> getPSMResourceControllerManagers(){
		return this.listOfPSMResourceControllerManagers;
	}
	
	public ArrayList<JavaResourceController> getPSMResourceControllers(){
		return this.listOfPSMResourceControllers;
	}
	
	public ArrayList<JavaAlgoResourceModel> getPSMAlgoResourceModels(){
		return this.listOfPSMAlgoResourceModels;
	}
	
	public ArrayList<JavaAlgoResourceController> getPSMAlgoResourceControllers(){
		return this.listOfPSMAlgoResourceControllers;
	}
	
	public HibernateController getHibernateController(){
		return this.oHibernateController;
	}
	
	public void setHibernateController(HibernateController oHibernateController){
		this.oHibernateController = oHibernateController;
	}
	
	public void printPSM(){
		printResourceModels();
		printResourceModelManagers();
		printResourceControllerManagers();
		printResourceControllers();
		printAlgoResourceModels();
		printAlgoResourceControllers();
		HibernateController.getHibernateControllerHandle().printHibernateController();
	}
	
	private void printResourceModels(){
		for(int n = 0; n < this.listOfPSMResourceModels.size(); n++){
			this.listOfPSMResourceModels.get(n).printJavaResourceModel();
		}
	}
	
	private void printResourceModelManagers(){
		for(int n = 0; n < this.listOfPSMResourceModelManagers.size(); n++){
			this.listOfPSMResourceModelManagers.get(n).printJavaResourceModelManager();
		}
	}
	
	private void printResourceControllerManagers(){
		for(int n = 0; n < this.listOfPSMResourceControllerManagers.size(); n++){
			this.listOfPSMResourceControllerManagers.get(n).printJavaResourceControllerManager();
		}
	}
	
	private void printResourceControllers(){
		for(int n = 0; n < this.listOfPSMResourceControllers.size(); n++){
			this.listOfPSMResourceControllers.get(n).printJavaResourceController();
		}
	}
	
	private void printAlgoResourceModels(){
		for(int n = 0; n < this.listOfPSMAlgoResourceModels.size(); n++){
			this.listOfPSMAlgoResourceModels.get(n).printJavaAlgoResourceModel();
		}
	}
	
	private void printAlgoResourceControllers(){
		for(int n = 0; n < this.listOfPSMAlgoResourceControllers.size(); n++){
			this.listOfPSMAlgoResourceControllers.get(n).printJavaAlgoResourceController();
		}
	}
}