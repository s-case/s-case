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

package eu.fp7.scase.psmGenerator;

import eu.fp7.scase.pimMetaModel.SystemPIM;
import eu.fp7.scase.psmMetaModel.SystemPSM;


public abstract class APSMProducer{
	
	protected SystemPIM oSystemPIM;
	protected SystemPSM oSystemPSM;
	
	
	public APSMProducer(SystemPIM oSystemPIM){
		this.oSystemPSM = new SystemPSM();
		this.oSystemPIM = oSystemPIM;
	}
	
	public abstract SystemPSM producePSM();
}