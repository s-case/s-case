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

import java.util.ArrayList;

import main.java.scase.cimMetaModel.Resource;
import main.java.scase.customUtilities.UniqueIdProducer;

public class JavaPackage{
	
	private int iJavaPackageId;
	private String strJavaPackageName;
	private ArrayList<AJavaFile> listOfPackageJavaFiles;
	private String strJavaPackageStamp;
	private Resource oParentResource;
	private String strFileSystemPath;
	
	public JavaPackage(Resource oParentResource, String strProjectName, String strFileSystemPath){
		this.iJavaPackageId = UniqueIdProducer.getNewUniqueId();
		this.oParentResource = oParentResource;
		this.strJavaPackageName = this.oParentResource.getResourceName();
		this.listOfPackageJavaFiles = new ArrayList<AJavaFile>();
		this.strJavaPackageStamp = String.format("package src.main.java.%s.%s;", strProjectName.toLowerCase(), this.strJavaPackageName.toLowerCase());
		this.strFileSystemPath = strFileSystemPath;
	}
	
	public JavaPackage(String strPackageName, String strProjectName, String strFileSystemPath){
		this.iJavaPackageId = UniqueIdProducer.getNewUniqueId();
		this.strJavaPackageName = strPackageName;
		this.listOfPackageJavaFiles = new ArrayList<AJavaFile>();
		this.strJavaPackageStamp = String.format("package src.main.java.%s.%s;", strProjectName.toLowerCase(), this.strJavaPackageName.toLowerCase());
		this.strFileSystemPath = strFileSystemPath;
	}
	
	public int getJavaPackageId(){
		return this.iJavaPackageId;
	}
	
	public String getJavaPackageName(){
		return this.strJavaPackageName;
	}
	
	public String getJavaPackageStamp(){
		return this.strJavaPackageStamp;
	}
	
	public ArrayList<AJavaFile> getPackageJavaFiles(){
		return this.listOfPackageJavaFiles;
	}
	
	public Resource getParentResource(){
		return this.oParentResource;
	}
	
	public String getFileSystemPath(){
		return this.strFileSystemPath;
	}
	
	public void printJavaPackage(){
		System.out.println("The Java Package: " + this.strJavaPackageName + " is added to the software project system because " + (this.strJavaPackageName.equalsIgnoreCase("Utilities")? " because every software code project must have exactly one Utilities package" : (this.strJavaPackageName + " resource exists in CIM")));
		System.out.println("Java Package Stamp: " + this.strJavaPackageStamp);
		System.out.println("File System Path: " + this.strFileSystemPath);
		printJavaFiles();
	}
	
	private void printJavaFiles(){
		for(int n = 0; n < this.listOfPackageJavaFiles.size(); n++){
			this.listOfPackageJavaFiles.get(n).printJavaFile();
		}
	}
	
	public void transfromPackageFiles(){
		for(int n = 0; n < this.listOfPackageJavaFiles.size(); n++){
			this.listOfPackageJavaFiles.get(n).transformFile();
		}
	}
}