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

import eu.fp7.scase.customUtilities.UniqueIdProducer;
import eu.fp7.scase.pimMetaModel.CreateHypermediaPIMFunction;

public class JavaHypermediaFunction extends JavaFunction{
	
	private int iJavaHypermediaFunctionId;
	private ArrayList<PSMHypermediaLink> listOfPSMHypermediaLinks;
	private CreateHypermediaPIMFunction oPIMParentHypermediaFunction;
	
	public JavaHypermediaFunction(CreateHypermediaPIMFunction oPIMParentHypermediaFunction){
		super("createHypermedia");
		this.oPIMParentHypermediaFunction = oPIMParentHypermediaFunction;
		this.iJavaHypermediaFunctionId = UniqueIdProducer.getNewUniqueId();
		this.listOfPSMHypermediaLinks = new ArrayList<PSMHypermediaLink>();
		this.setReturnParameter(this.oPIMParentHypermediaFunction.getFuntionReturnParameter());
		for(int n = 0; n < this.oPIMParentHypermediaFunction.getFunctionParameters().size(); n++){
			this.getFunctionParameters().add(this.oPIMParentHypermediaFunction.getFunctionParameters().get(n));
		}
	}
	
	public int getJavaHypermediaFunctionId(){
		return this.iJavaHypermediaFunctionId;
	}
	
	public ArrayList<PSMHypermediaLink> getHypermediaLinks(){
		return this.listOfPSMHypermediaLinks;
	}
	
	public CreateHypermediaPIMFunction getPIMParentHypermediaFunction(){
		return this.oPIMParentHypermediaFunction;
	}
	
	@Override
	public void printJavaFunction(){
		System.out.println("The Java Hypermedia Function: " + this.getJavaFunctionName() + " is added to PSM because " + this.oPIMParentHypermediaFunction.getPIMFunctionName() + " exists in PIM");
		super.printJavaFunction();
		printHypermediaLinks();
	}
	
	private void printHypermediaLinks(){
		for(int n = 0; n < this.listOfPSMHypermediaLinks.size(); n++){
			System.out.println("Hypermedia Link: " + this.getHypermediaLinks().get(n).getPSMHypermediaLinkId());
			System.out.println("CRUD Verb: " + this.getHypermediaLinks().get(n).getPSMHypermediaLinkVerb());
			System.out.println("Type: " + this.getHypermediaLinks().get(n).getPSMHypermediaLinkType());
			System.out.println("Links to: " + (this.getPIMParentHypermediaFunction().getHypermediaLinkList().get(n).getTargetResourceController() == null? (this.getPIMParentHypermediaFunction().getHypermediaLinkList().get(n).getTargetResourceControllerManager() == null? this.getPIMParentHypermediaFunction().getHypermediaLinkList().get(n).getTargetAlgoResourceController().getAlgoResourceControllerName() : this.getPIMParentHypermediaFunction().getHypermediaLinkList().get(n).getTargetResourceControllerManager().getResourceControllerManagerName()) : this.getPIMParentHypermediaFunction().getHypermediaLinkList().get(n).getTargetResourceController().getResourceControllerName()));
		}
	}
}