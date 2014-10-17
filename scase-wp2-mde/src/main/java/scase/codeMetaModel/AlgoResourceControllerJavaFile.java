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

package main.java.scase.codeMetaModel;

import main.java.scase.psmMetaModel.JavaAlgoResourceController;

public class AlgoResourceControllerJavaFile extends AJavaFile{
	
	private JavaAlgoResourceController oParentJavaController;
	
	public AlgoResourceControllerJavaFile(JavaAlgoResourceController oParentJavaController, String strProjectName, String strPackageStamp){
		super(oParentJavaController.getJavaAlgoResourceControllerName(), strProjectName, strPackageStamp);
		this.oParentJavaController = oParentJavaController;
	}
	
	public JavaAlgoResourceController getParentJavaController(){
		return this.oParentJavaController;
	}
	
	@Override
	public void printJavaFile(){
		System.out.println("This Java Algorithmic Controller File: " + this.getJavaFileName() + " is added to software code project because " + this.oParentJavaController.getJavaAlgoResourceControllerName() + " exists in PSM"); 
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
		this.strFileImports = String.format("//Please add any needed imports here.");
		return this.strFileImports;
	}

	@Override
	public String addClassHeader() {
		this.strClassHeader = String.format("%s%spublic class %s{\n", this.strClassHeader, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaController.getJavaAlgoResourceControllerName());
		this.oJavaFileIdentation.increaseIdentation();
		return this.strClassHeader;
	}

	@Override
	public String addClassProperties() {
		this.strClassProperties = String.format("%s%s//Please add any properties of this model here.\n\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
		return this.strClassProperties;
	}

	@Override
	public String addClassFunctions() {
		this.strClassFunctions = String.format("%s%s//Please add the constructors and any functions of this model here.\n\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
		return this.strClassFunctions;
	}

	@Override
	public String addClassTail() {
		this.strClassTail = "}";
		return this.strClassTail;
	}
}