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


package main.java.scase.mdeEngine;


import main.java.scase.cimGenerator.ACIMProducer;
import main.java.scase.cimGenerator.CoreCIMProducer;
import main.java.scase.pimGenerator.APIMProducer;
import main.java.scase.pimGenerator.CorePIMProducer;
import main.java.scase.pimMetaModel.SystemPIM;
import main.java.scase.psmGenerator.APSMProducer;
import main.java.scase.psmGenerator.CorePSMProducer;
import main.java.scase.psmMetaModel.SystemPSM;
import main.java.scase.codeGenerator.ACodeProducer;
import main.java.scase.codeGenerator.CoreCodeProducer;
import main.java.scase.codeMetaModel.SystemCode;
import main.java.scase.cimMetaModel.SystemCIM;
import main.java.scase.inputParsing.TextFileInputParserImplementation;


public class MDEEngineStarter
{
	private static ACIMProducer oACIMProducer;
	private static APIMProducer oAPIMProducer;
	private static APSMProducer oAPSMProducer;
	private static ACodeProducer oACodeProducer;
	
	
	public static void main(String[] args)
	{
		System.out.println("S-CASE MDE engine started.");
		
		//check input arguments sanity
		if(args.length != 7)
		{
			System.out.println("Usage: java -cp target/RESTfulMDEEngine-1.0-SNAPSHOT.jar main.java.scase.mdeEngine.MDEEngineStarter input_CIM_file_path project_Name output_path database_ip database_port database_username database_password");
			return;
		}
		
		//initiate CIM generator to create the envisioned system's CIM
		System.out.println("------------------------------------------------------------------------");
		System.out.println("CIM DEFINITION START");
		System.out.println("------------------------------------------------------------------------");
		oACIMProducer = new CoreCIMProducer(new TextFileInputParserImplementation(args[0]));
		SystemCIM oSystemCIM = oACIMProducer.produceCIM();
		System.out.println("------------------------------------------------------------------------");
		System.out.println("CIM DEFINITION END");
		System.out.println("------------------------------------------------------------------------");
		//initiate PIM generator to create the envisioned system's PIM
		System.out.println("------------------------------------------------------------------------");
		System.out.println("PIM DEFINITION START");
		System.out.println("------------------------------------------------------------------------");
		oAPIMProducer = new CorePIMProducer(oSystemCIM);
		SystemPIM oSystemPIM = oAPIMProducer.producePIM();
		System.out.println("------------------------------------------------------------------------");
		System.out.println("PIM DEFINITION END");
		System.out.println("------------------------------------------------------------------------");
		//initiate PSM generator to create the envisioned system's PSM
		System.out.println("------------------------------------------------------------------------");
		System.out.println("PSM DEFINITION START");
		System.out.println("------------------------------------------------------------------------");
		oAPSMProducer = new CorePSMProducer(oSystemPIM);
		SystemPSM oSystemPSM = oAPSMProducer.producePSM();
		System.out.println("------------------------------------------------------------------------");
		System.out.println("PSM DEFINITION END");
		System.out.println("------------------------------------------------------------------------");
		//initiate code generator to create the envisioned system's code
		System.out.println("------------------------------------------------------------------------");
		System.out.println("CODE DEFINITION START");
		System.out.println("------------------------------------------------------------------------");
		oACodeProducer = new CoreCodeProducer(oSystemPSM, args[1], args[2], args[3], args[4], args[5], args[6]);
		SystemCode oSystemCode = oACodeProducer.produceCode();
		System.out.println("------------------------------------------------------------------------");
		System.out.println("CODE DEFINITION END");
		System.out.println("------------------------------------------------------------------------");
	}
}