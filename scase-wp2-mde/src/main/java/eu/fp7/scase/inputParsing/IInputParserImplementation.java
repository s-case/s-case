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


package eu.fp7.scase.inputParsing;

import java.util.ArrayList;

import eu.fp7.scase.cimMetaModel.Resource;


public interface IInputParserImplementation
{
	public ArrayList<String> parseResourceList();
	public Resource parseResourceByName(Resource oResource);
	public ArrayList<String> parseResourceOutgoingRelations(Resource oResource);
	public ArrayList<String> parseResourceIncomingRelations(Resource oResource);
}