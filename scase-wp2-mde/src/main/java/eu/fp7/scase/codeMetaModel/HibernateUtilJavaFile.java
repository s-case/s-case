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

import java.util.ArrayList;

import eu.fp7.scase.psmMetaModel.JavaResourceModel;

public class HibernateUtilJavaFile extends AJavaFile{
	
	private ArrayList<JavaResourceModel> listOfSystemJavaModels;
	
	public HibernateUtilJavaFile(String strFileName, String strProjectName, String strPackageStamp, ArrayList<JavaResourceModel> listOfSystemJavaModels){
		super(strFileName, strProjectName, strPackageStamp);
		this.listOfSystemJavaModels = listOfSystemJavaModels;
	}
	
	@Override
	public void printJavaFile(){
		System.out.println("The Hibernate Utilities Java File: " + this.getJavaFileName() + " is added to software code project because it provides a hibernate session factory");
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
		this.strFileImports = String.format("%s%simport org.hibernate.SessionFactory;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport org.hibernate.cfg.AnnotationConfiguration;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		for(int n = 0; n < this.listOfSystemJavaModels.size(); n++){
			this.strFileImports = String.format("%s%simport src.main.java.%s.%s.%s;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation(),
					this.strProjectName.toLowerCase(),
					this.listOfSystemJavaModels.get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName().toLowerCase(),
					this.listOfSystemJavaModels.get(n).getJavaResourceModelName());
		}

		return this.strFileImports;
	}

	@Override
	public String addClassHeader() {
		this.strClassHeader = String.format("%s%spublic class HibernateUtil{\n\n", this.strClassHeader, this.oJavaFileIdentation.getCurrentIdentation());
		this.oJavaFileIdentation.increaseIdentation();
		return this.strClassHeader;
	}

	@Override
	public String addClassProperties() {
		this.strClassProperties = String.format("%s%sprivate static final SessionFactory sessionFactory = buildSessionFactory();\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
		return this.strClassProperties;
	}

	@Override
	public String addClassFunctions() {
		this.strClassFunctions = String.format("%s%sprivate static SessionFactory buildSessionFactory(){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%stry {\n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%sreturn new AnnotationConfiguration().configure()\n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.oJavaFileIdentation.increaseIdentation();
		this.oJavaFileIdentation.increaseIdentation();
		for(int n = 0; n < this.listOfSystemJavaModels.size(); n++){
			this.strClassFunctions = String.format("%s%s.addAnnotatedClass(%s.class)\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.listOfSystemJavaModels.get(n).getJavaResourceModelName());
		}
		this.strClassFunctions = String.format("%s%s.buildSessionFactory();\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.oJavaFileIdentation.decreaseIdentation();
		this.oJavaFileIdentation.decreaseIdentation();
		this.strClassFunctions = String.format("%s%s}\n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%scatch (Throwable ex){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sSystem.err.println(\"Initial SessionFactory creation failed.\" + ex);\n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%sthrow new ExceptionInInitializerError(ex);\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%s}\n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%s}\n\n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		this.strClassFunctions = String.format("%s%spublic static SessionFactory getSessionFactory(){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sreturn sessionFactory;\n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%s}\n\n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		return this.strClassFunctions;
	}

	@Override
	public String addClassTail() {
		this.strClassTail = "}";
		return this.strClassTail;
	}
}