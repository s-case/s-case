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

package main.java.scase.psmGenerator;

import main.java.scase.pimMetaModel.SystemPIM;
import main.java.scase.psmMetaModel.SystemPSM;


public abstract class APSMProducer{
	
	protected SystemPIM oSystemPIM;
	protected SystemPSM oSystemPSM;
	
	
	public APSMProducer(SystemPIM oSystemPIM){
		this.oSystemPSM = new SystemPSM();
		this.oSystemPIM = oSystemPIM;
	}
	
	public abstract SystemPSM producePSM();
}