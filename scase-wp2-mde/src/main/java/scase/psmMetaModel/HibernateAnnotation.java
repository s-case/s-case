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

public class HibernateAnnotation{
	
	private int iHibernateAnnotationId;
	private String strHibernateAnnotationText;
	
	public HibernateAnnotation(String strHibernateAnnotationText){
		this.iHibernateAnnotationId = UniqueIdProducer.getNewUniqueId();
		this.strHibernateAnnotationText = strHibernateAnnotationText;
	}
	
	public int getHibernateAnnotationId(){
		return this.iHibernateAnnotationId;
	}
	
	public String getHibernateAnnotationText(){
		return this.strHibernateAnnotationText;
	}
}