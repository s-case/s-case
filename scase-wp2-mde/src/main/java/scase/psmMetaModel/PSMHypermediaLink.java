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
import main.java.scase.pimMetaModel.PIMHypermediaLink;

public class PSMHypermediaLink{
	
	private int iPSMHypermediaLinkId;
	private String strPSMHypermediaLinkVerb;
	private String strPSMHypermediaLinkType;
	private PIMHypermediaLink oPIMParentHypermediaLink;
	
	public PSMHypermediaLink(PIMHypermediaLink oPIMParentHypermediaLink){
		this.iPSMHypermediaLinkId = UniqueIdProducer.getNewUniqueId();
		this.oPIMParentHypermediaLink = oPIMParentHypermediaLink;
		this.strPSMHypermediaLinkType = this.oPIMParentHypermediaLink.getLinkType();
		this.strPSMHypermediaLinkVerb = transformCRUDtoHTTPVerb(this.oPIMParentHypermediaLink.getLinkCRUDVerb());
	}
	
	public int getPSMHypermediaLinkId(){
		return this.iPSMHypermediaLinkId;
	}
	
	public String getPSMHypermediaLinkVerb(){
		return this.strPSMHypermediaLinkVerb;
	}
	
	public String getPSMHypermediaLinkType(){
		return this.strPSMHypermediaLinkType;
	}
	
	public PIMHypermediaLink getPIMParentHypermediaLink(){
		return this.oPIMParentHypermediaLink;
	}
	
	private String transformCRUDtoHTTPVerb(String strCRUDVerb){
		if(strCRUDVerb.equalsIgnoreCase("CREATE")){
			return "POST";
		}
		else if(strCRUDVerb.equalsIgnoreCase("READ")){
			return "GET";
		}
		else if(strCRUDVerb.equalsIgnoreCase("UPDATE")){
			return "PUT";
		}
		else if(strCRUDVerb.equalsIgnoreCase("DELETE")){
			return "DELETE";
		}
		else{
			//this case should never happen
			try {
				throw new Exception("Invalid PIM detected! Unkown CRUD verb! " + strCRUDVerb);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}