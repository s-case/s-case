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

public class MavenConfigurationFile{
	
	private String strMavenText;
	private JavaFileIdentation oFileIdentation;
	private String strProjectName;
	
	public MavenConfigurationFile(String strProjectName){
		this.strMavenText = "";
		this.oFileIdentation = new JavaFileIdentation();
		this.oFileIdentation.changeTabSize(1);
		this.strProjectName = strProjectName;
	}
	
	public String getProjectName(){
		return this.strProjectName;
	}
	
	public String exportMavenText(){
		this.strMavenText = String.format("%s%s<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation());
		this.strMavenText = String.format("%s%sxsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n", this.strMavenText, this.oFileIdentation.increaseIdentation());
		this.strMavenText = String.format("%s%s<modelVersion>4.0.0</modelVersion>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation());
		this.strMavenText = String.format("%s%s<groupId>scase-auto-generated</groupId>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation());
		this.strMavenText = String.format("%s%s<artifactId>%s</artifactId>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation(), this.strProjectName);
		this.strMavenText = String.format("%s%s<packaging>war</packaging>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation());
		this.strMavenText = String.format("%s%s<version>1.0-SNAPSHOT</version>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation());
		this.strMavenText = String.format("%s%s<name>%s</name>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation(), this.strProjectName);
		this.strMavenText = String.format("%s%s<url>http://maven.apache.org</url>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation());
		this.strMavenText = String.format("%s%s<dependencies>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation());
		this.oFileIdentation.changeTabSize(4);
		this.addDependency("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api", "1.0.1.Final");
		this.addDependency("com.sun.jersey", "jersey-core", "1.18");
		this.addDependency("org.hibernate", "hibernate-core", "4.2.13.Final");
		this.addDependency("com.sun.jersey", "jersey-servlet", "1.18");
		this.addDependency("com.sun.jersey.samples", "json-from-jaxb", "1.18");
		this.addDependency("org.postgresql", "postgresql", "9.3-1100-jdbc4");
		this.strMavenText = String.format("%s%s</dependencies>\n", this.strMavenText, this.oFileIdentation.decreaseIdentation());
		this.strMavenText = String.format("%s%s<build>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation());
		this.strMavenText = String.format("%s%s<finalName>%s</finalName>\n", this.strMavenText, this.oFileIdentation.increaseIdentation(), this.strProjectName);
		this.strMavenText = String.format("%s%s</build>\n", this.strMavenText, this.oFileIdentation.decreaseIdentation());
		this.strMavenText = String.format("%s%s</project>\n", this.strMavenText, this.oFileIdentation.decreaseIdentation());
		
		return this.strMavenText;
	}
	
	private void addDependency(String strGroupId, String strArtifactId, String strVersion){
		this.strMavenText = String.format("%s%s<dependency>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation());
		this.strMavenText = String.format("%s%s<groupId>%s</groupId>\n", this.strMavenText, this.oFileIdentation.increaseIdentation(), strGroupId);
		this.strMavenText = String.format("%s%s<artifactId>%s</artifactId>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation(), strArtifactId);
		this.strMavenText = String.format("%s%s<version>%s</version>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation(), strVersion);
		this.strMavenText = String.format("%s%s<scope>compile</scope>\n", this.strMavenText, this.oFileIdentation.getCurrentIdentation());
		this.strMavenText = String.format("%s%s</dependency>\n", this.strMavenText, this.oFileIdentation.decreaseIdentation());
	}
}