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

public class HypermediaLinkJavaFile extends AJavaFile{
	
	public HypermediaLinkJavaFile(String strProjectName, String strPackageStamp){
		super("HypermediaLink", strProjectName, strPackageStamp);
	}
	
	@Override
	public void printJavaFile(){
		System.out.println("The Java File: " + this.getJavaFileName() + " is added to software code project because it describes the hypermedia links");
		super.printJavaFile();
	}

	@Override
	public void transformFile() {
		super.transformFile();
		
	}

	@Override
	public String addAuthorComment() {
		oFileAuthorComment = new FileAuthorComment();
		oFileAuthorComment.setProject(this.strProjectName);
		oFileAuthorComment.setWorkFile("");
		oFileAuthorComment.setCompiler("");
		oFileAuthorComment.setFileDescription("");
		oFileAuthorComment.setDocumentDescription("");
		oFileAuthorComment.setRelatedDocuments("");
		oFileAuthorComment.setNote("");
		return oFileAuthorComment.exportFileAuthorComment();
	}

	@Override
	public String addFileImports() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String addClassHeader() {
		this.strClassHeader = String.format("%s%spublic class HypermediaLink{%n%n", this.strClassHeader, this.oJavaFileIdentation.getCurrentIdentation());
		this.oJavaFileIdentation.increaseIdentation();
		return this.strClassHeader;
	}

	@Override
	public String addClassProperties() {
		this.strClassProperties = String.format("%s%sprivate String LinkURI;%n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassProperties = String.format("%s%sprivate String LinkRel;%n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassProperties = String.format("%s%sprivate String LinkVerb;%n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassProperties = String.format("%s%sprivate String LinkType;%n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassProperties = String.format("%s%sprivate int IdType;%n%n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
		return this.strClassProperties;
	}

	@Override
	public String addClassFunctions() {
		this.strClassFunctions = String.format("%s%spublic HypermediaLink(){}%n%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%spublic HypermediaLink(String strLinkURI, String strLinkRel, String strLinkVerb, String strLinkType, int iIdType){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkURI = strLinkURI;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkRel = strLinkRel;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkVerb = strLinkVerb;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkType = strLinkType;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.IdType = iIdType;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%spublic HypermediaLink(String strLinkURI, String strLinkRel, String strLinkVerb, String strLinkType){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkURI = strLinkURI;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkRel = strLinkRel;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkVerb = strLinkVerb;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkType = strLinkType;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		
		addSetterFunctions();
		addGetterFunctions();
		
		return this.strClassFunctions;
	}

	@Override
	public String addClassTail() {
		this.strClassTail = "}";
		return this.strClassTail;
	}
	
	private void addSetterFunctions(){
		this.strClassFunctions = String.format("%s%spublic void setLinkURI(String strLinkURI){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkURI = strLinkURI;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%spublic void setLinkRel(String strLinkRel){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkRel = strLinkRel;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%spublic void setLinkVerb(String strLinkVerb){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkVerb = strLinkVerb;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%spublic void setLinkType(String strLinkType){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.LinkType = strLinkType;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%spublic void setIdType(int iIdType){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.IdType = iIdType;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		
	}
	
	private void addGetterFunctions(){
		this.strClassFunctions = String.format("%s%spublic String getLinkURI(){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sreturn this.LinkURI;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%spublic String getLinkRel(){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sreturn this.LinkRel;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%spublic String getLinkVerb(){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sreturn this.LinkVerb;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%spublic String getLinkType(){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sreturn this.LinkType;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%spublic int getIdType(){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sreturn this.IdType;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
	}
}