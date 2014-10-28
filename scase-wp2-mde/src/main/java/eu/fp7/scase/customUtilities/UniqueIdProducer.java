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


package eu.fp7.scase.customUtilities;


public class UniqueIdProducer {
    private static final UniqueIdProducer oUniqueIdProducer = new UniqueIdProducer();
    private static int iIdCounter;
    private UniqueIdProducer() {
    	iIdCounter = 1;
    }
    
    public static int getNewUniqueId()
    {
    	return iIdCounter++;
    }
}