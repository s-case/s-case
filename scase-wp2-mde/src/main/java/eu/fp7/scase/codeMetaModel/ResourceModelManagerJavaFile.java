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

import main.java.scase.psmMetaModel.JavaResourceModelManager;

public class ResourceModelManagerJavaFile extends AJavaFile{
	
	private JavaResourceModelManager oParentJavaModelManager;
	
	public ResourceModelManagerJavaFile(JavaResourceModelManager oParentJavaModelManager, String strProjectName, String strPackageStamp){
		super(oParentJavaModelManager.getJavaResourceModelManagerName(), strProjectName, strPackageStamp);
		this.oParentJavaModelManager = oParentJavaModelManager;
	}
	
	public JavaResourceModelManager getParentJavaModelManager(){
		return this.oParentJavaModelManager;
	}
	
	@Override
	public void printJavaFile(){
		System.out.println("The Java Resource Model Manager Java File: " + this.getJavaFileName() + " is added to software code project because " + this.oParentJavaModelManager.getJavaResourceModelManagerName() + " exists in PSM");
		super.printJavaFile();
	}
	
	@Override
	public void transformFile(){
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
		this.strFileImports = String.format("%s%simport java.util.ArrayList;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport java.util.List;\n\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport src.main.java.%s.utilities.HypermediaLink;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation(), this.strProjectName.toLowerCase());
		this.strFileImports = String.format("%s%simport javax.xml.bind.annotation.XmlRootElement;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());		
		return this.strFileImports;
	}

	@Override
	public String addClassHeader() {
		this.strClassHeader = String.format("%s\n", this.oParentJavaModelManager.getModelManagerJAXBAnnotaiton().getJAXBAnnotationText());
		this.strClassHeader = String.format("%s%spublic class %s{\n\n", this.strClassHeader, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModelManager.getJavaResourceModelManagerName());
		this.oJavaFileIdentation.increaseIdentation();
		return this.strClassHeader;
	}

	@Override
	public String addClassProperties() {
		for(int n = 0; n < this.oParentJavaModelManager.getJavaModelManagerProperties().size(); n++){
			if(this.oParentJavaModelManager.getJavaModelManagerProperties().get(n).getPSMComponentPropertyUniqueness()){
				this.strClassProperties = String.format("%s%sprivate %s %s;\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModelManager.getJavaModelManagerProperties().get(n).getPSMComponentPropertyType(), this.oParentJavaModelManager.getJavaModelManagerProperties().get(n).getPSMComponentPropertyName());
			}
			else{ //it is linklist
				this.strClassProperties = String.format("%s%sprivate List<%s> %s = new ArrayList<%s>();\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModelManager.getJavaModelManagerProperties().get(n).getPSMComponentPropertyType(), this.oParentJavaModelManager.getJavaModelManagerProperties().get(n).getPSMComponentPropertyName(), this.oParentJavaModelManager.getJavaModelManagerProperties().get(n).getPSMComponentPropertyType());
			}
		}
		this.strClassProperties = String.format("%s\n", this.strClassProperties);
		return this.strClassProperties;
	}

	@Override
	public String addClassFunctions() {
		for(int n = 0; n < this.oParentJavaModelManager.getJavaManagerGetterFunctions().size(); n++){
			if(this.oParentJavaModelManager.getJavaManagerGetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyUniqueness()){
				this.strClassFunctions = String.format("%s%spublic %s %s(){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModelManager.getJavaManagerGetterFunctions().get(n).getReturnParameter().getParameterType(), this.oParentJavaModelManager.getJavaManagerGetterFunctions().get(n).getJavaFunctionName());
			}
			else{ //it is linklist getter
				this.strClassFunctions = String.format("%s%spublic List<%s> %s(){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModelManager.getJavaManagerGetterFunctions().get(n).getReturnParameter().getParameterType(), this.oParentJavaModelManager.getJavaManagerGetterFunctions().get(n).getJavaFunctionName());
			}
			this.strClassFunctions = String.format("%s%sreturn this.%s;\n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), this.oParentJavaModelManager.getJavaManagerGetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyName());
			this.strClassFunctions = String.format("%s%s}\n\n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		}
		
		for(int n = 0; n < this.oParentJavaModelManager.getJavaManagerSetterFunctions().size(); n++){
			if(this.oParentJavaModelManager.getJavaManagerSetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyUniqueness()){
				this.strClassFunctions = String.format("%s%spublic void %s(%s %s){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModelManager.getJavaManagerSetterFunctions().get(n).getJavaFunctionName(), this.oParentJavaModelManager.getJavaManagerSetterFunctions().get(n).getFunctionParameters().get(0).getParameterType(), this.oParentJavaModelManager.getJavaManagerSetterFunctions().get(n).getFunctionParameters().get(0).getFunctionParameterName());
			}
			else{ //it is link list setter
				this.strClassFunctions = String.format("%s%spublic void %s(List<%s> %s){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModelManager.getJavaManagerSetterFunctions().get(n).getJavaFunctionName(), this.oParentJavaModelManager.getJavaManagerSetterFunctions().get(n).getFunctionParameters().get(0).getParameterType(), this.oParentJavaModelManager.getJavaManagerSetterFunctions().get(n).getFunctionParameters().get(0).getFunctionParameterName());
			}
			this.strClassFunctions = String.format("%s%sthis.%s = %s;\n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), this.oParentJavaModelManager.getJavaManagerSetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyName(), this.oParentJavaModelManager.getJavaManagerSetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyName());
			this.strClassFunctions = String.format("%s%s}\n\n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		}
		
		return this.strClassFunctions;
	}

	@Override
	public String addClassTail() {
		this.strClassTail = "}";
		return this.strClassTail;
	}
}