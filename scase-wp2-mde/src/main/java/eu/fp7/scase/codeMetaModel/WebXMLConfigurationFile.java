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

package eu.fp7.scase.codeMetaModel;

public class WebXMLConfigurationFile{
	
	private String strWebXMLText;
	private JavaFileIdentation oFileIdentation;
	private String strProjectName;
	
	public WebXMLConfigurationFile(String strProjectName){
		this.strWebXMLText = "";
		this.oFileIdentation = new JavaFileIdentation();
		this.oFileIdentation.changeTabSize(4);
		this.strProjectName = strProjectName;
	}
	
	public String getProjectName(){
		return this.strProjectName;
	}
	
	public String exportWebXMLText(){
		this.strWebXMLText = String.format("%s%s<web-app id=\"WebApp_ID\" version=\"2.4\"%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation());
		this.oFileIdentation.changeTabSize(1);
		this.strWebXMLText = String.format("%s%sxmlns=\"http://java.sun.com/xml/ns/j2ee\"%n", this.strWebXMLText, this.oFileIdentation.increaseIdentation());
		this.strWebXMLText = String.format("%s%sxmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation());
		this.strWebXMLText = String.format("%s%sxsi:schemaLocation=\"http://java.sun.com/xml/ns/j2ee%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation());
		this.strWebXMLText = String.format("%s%shttp://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd\">%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation());
		this.strWebXMLText = String.format("%s%s<display-name> %s </display-name>%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation(), this.strProjectName);
		this.strWebXMLText = String.format("%s%s<servlet>%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation());
		this.oFileIdentation.changeTabSize(4);
		this.strWebXMLText = String.format("%s%s<servlet-name>jersey</servlet-name>%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation());
		this.strWebXMLText = String.format("%s%s<servlet-class>com.sun.jersey.spi.container.servlet.ServletContainer</servlet-class>%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation());
		this.strWebXMLText = String.format("%s%s<init-param>%n", this.strWebXMLText, this.oFileIdentation.increaseIdentation());
		this.strWebXMLText = String.format("%s%s<param-name>javax.ws.rs.Application</param-name>%n", this.strWebXMLText, this.oFileIdentation.increaseIdentation());
		this.strWebXMLText = String.format("%s%s<param-value> src.main.java.%s.utilities.JAXRSServicePublisher</param-value>%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation(), this.strProjectName.toLowerCase());
		this.strWebXMLText = String.format("%s%s</init-param>%n", this.strWebXMLText, this.oFileIdentation.decreaseIdentation());
		this.oFileIdentation.decreaseIdentation();
		this.strWebXMLText = String.format("%s%s</servlet>%n", this.strWebXMLText, this.oFileIdentation.decreaseIdentation());
		this.strWebXMLText = String.format("%s%s<servlet-mapping>%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation());
		this.strWebXMLText = String.format("%s%s<servlet-name>jersey</servlet-name>%n", this.strWebXMLText, this.oFileIdentation.increaseIdentation());
		this.strWebXMLText = String.format("%s%s<url-pattern>/*</url-pattern>%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation());
		this.strWebXMLText = String.format("%s%s</servlet-mapping>%n", this.strWebXMLText, this.oFileIdentation.decreaseIdentation());
		this.strWebXMLText = String.format("%s%s</web-app>%n", this.strWebXMLText, this.oFileIdentation.getCurrentIdentation());
		return this.strWebXMLText;
	}
}