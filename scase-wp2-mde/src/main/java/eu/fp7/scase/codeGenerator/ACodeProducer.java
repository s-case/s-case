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

package eu.fp7.scase.codeGenerator;

import eu.fp7.scase.codeMetaModel.SystemCode;
import eu.fp7.scase.psmMetaModel.SystemPSM;


public abstract class ACodeProducer{
	
	protected SystemPSM oSystemPSM;
	protected SystemCode oSystemCode;
	protected String strProjectName;
	protected String strOutputPath;
	protected String strDatabaseIp;
	protected String strDatabasePort;
	protected String strDatabaseUsername;
	protected String strDatabasePassword;
	
	public ACodeProducer(SystemPSM oSystemPSM, String strProjectName, String strOutputPath, String strDatabaseIp, String strDatabasePort, String strDatabaseUsername, String strDatabasePassword){
		this.oSystemPSM = oSystemPSM;
		this.oSystemCode = new SystemCode();
		this.strProjectName = strProjectName;
		this.strOutputPath = strOutputPath;
		this.strDatabaseIp = strDatabaseIp;
		this.strDatabasePort = strDatabasePort;
		this.strDatabaseUsername  = strDatabaseUsername;
		this.strDatabasePassword = strDatabasePassword;
	}
	
	public abstract SystemCode produceCode();
	
	public String getProjectName(){
		return this.strProjectName; 
	}
	
	public String getOutputPath(){
		return this.strOutputPath;
	}
	
	public String getDatabaseIp(){
		return this.strDatabaseIp;
	}
	
	public String getDatabasePort(){
		return this.strDatabasePort;
	}
	
	public String getDatabaseUsername(){
		return this.strDatabaseUsername;
	}
	
	public String getDatabasePassword(){
		return this.strDatabasePassword;
	}
}