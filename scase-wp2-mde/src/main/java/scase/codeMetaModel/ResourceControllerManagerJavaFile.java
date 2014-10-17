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

import main.java.scase.psmMetaModel.HTTPActivity;
import main.java.scase.psmMetaModel.HTTPActivityHandler;
import main.java.scase.psmMetaModel.JavaResourceControllerManager;

public class ResourceControllerManagerJavaFile extends AJavaFile{
	
	private JavaResourceControllerManager oParentJavaControllerManager;
	
	public ResourceControllerManagerJavaFile(JavaResourceControllerManager oParentJavaControllerManager, String strProjectName, String strPackageStamp){
		super(oParentJavaControllerManager.getJavaResourceControllerManagerName(), strProjectName, strPackageStamp);
		this.oParentJavaControllerManager = oParentJavaControllerManager;
	}
	
	public JavaResourceControllerManager getParentJavaControllerManager(){
		return this.oParentJavaControllerManager;
	}
	
	@Override
	public void printJavaFile(){
		System.out.println("The Java Resource Controller Manager File: " + this.getJavaFileName() + " is added to software code project because " + this.oParentJavaControllerManager.getJavaResourceControllerManagerName() + " exists in PSM");
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
		this.strFileImports = String.format("%s%simport javax.ws.rs.Consumes;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.ws.rs.DELETE;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.ws.rs.GET;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.ws.rs.POST;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.ws.rs.PUT;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.ws.rs.Path;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.ws.rs.PathParam;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.ws.rs.Produces;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.ws.rs.core.Context;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.ws.rs.core.UriInfo;\n\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());

		return this.strFileImports;
	}

	@Override
	public String addClassHeader() {
		this.strClassHeader = String.format("%s%s\n", this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaControllerManager.getControllerManagerJAXRSAnnotation().getJAXRSAnnotationText());
		this.strClassHeader = String.format("%s%spublic class %s{\n\n", this.strClassHeader, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaControllerManager.getJavaResourceControllerManagerName());
		this.oJavaFileIdentation.increaseIdentation();
		return this.strClassHeader;
	}

	@Override
	public String addClassProperties() {
		this.strClassProperties = String.format("%s@Context\n", this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassProperties = String.format("%s%sprivate UriInfo oApplicationUri;\n\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
		return this.strClassProperties;
	}

	@Override
	public String addClassFunctions() {
		for(int n = 0; n < this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().size(); n++){
			HTTPActivityHandler oHTTPActivityHandler = findHTTPHandlerOfHTTPActivity(this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().get(n));
			
			this.strClassFunctions = String.format("%s%s%s\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().get(n).getHTTPActivityJAXRSPathAnnotation().getJAXRSAnnotationText());
			this.strClassFunctions = String.format("%s%s%s\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().get(n).getHTTPActivityJAXRSVerbAnnotation().getJAXRSAnnotationText());
			this.strClassFunctions = String.format("%s%s%s\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().get(n).getJAXRSProduceAnnotation().getJAXRSAnnotationText());
			
			String strSourceIdentifier = "";
			String strSourceIdentifierFunctionSignature = "";
			
			if(this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().get(n).getParentPIMCRUDActivity().getIncomingResourceModel() != null){//if this resource is related of some other
				strSourceIdentifier = this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().get(n).getParentPIMCRUDActivity().getIncomingResourceModel().getModelPrimaryIdentifierName();
				strSourceIdentifierFunctionSignature = String.format("@PathParam(\"%s\") int %s", strSourceIdentifier, strSourceIdentifier);
			}
			
			if(this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().get(n).getHTTPActivityVerb().equalsIgnoreCase("GET")){
				this.strClassFunctions = String.format("%s%spublic %s %s(%s){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaControllerManager.getRelatedJavaResourceModelManager().getJavaResourceModelManagerName(), this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().get(n).getHTTPActivityName(), strSourceIdentifierFunctionSignature);
				this.strClassFunctions = String.format("%s%s%s o%s = new %s(%soApplicationUri);\n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), oHTTPActivityHandler.getHTTPActivityHandlerName(), oHTTPActivityHandler.getHTTPActivityHandlerName(), oHTTPActivityHandler.getHTTPActivityHandlerName(), (strSourceIdentifier.equalsIgnoreCase("") ? "" : strSourceIdentifier + ", " ));
				this.strClassFunctions = String.format("%s%sreturn o%s.get%s();\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), oHTTPActivityHandler.getHTTPActivityHandlerName(), this.oParentJavaControllerManager.getRelatedJavaResourceModelManager().getJavaResourceModelManagerName());
			}
			else if(this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().get(n).getHTTPActivityVerb().equalsIgnoreCase("POST")){
				this.strClassFunctions = String.format("%s%s%s\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().get(n).getJAXRSConsumeAnnotation().getJAXRSAnnotationText());
				this.strClassFunctions = String.format("%s%spublic %s %s(%s%s o%s){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaControllerManager.getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentJavaControllerManager.getJavaControllerManagerHTTPActivities().get(n).getHTTPActivityName(), (strSourceIdentifierFunctionSignature.equalsIgnoreCase("") ? "": strSourceIdentifierFunctionSignature + ", "), this.oParentJavaControllerManager.getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentJavaControllerManager.getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
				this.strClassFunctions = String.format("%s%s%s o%s = new %s(%so%s, oApplicationUri);\n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), oHTTPActivityHandler.getHTTPActivityHandlerName(), oHTTPActivityHandler.getHTTPActivityHandlerName(), oHTTPActivityHandler.getHTTPActivityHandlerName(), (strSourceIdentifier.equalsIgnoreCase("") ? "": strSourceIdentifier + ", "), this.oParentJavaControllerManager.getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
				this.strClassFunctions = String.format("%s%sreturn o%s.post%s();\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), oHTTPActivityHandler.getHTTPActivityHandlerName(), this.oParentJavaControllerManager.getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
			}

			this.strClassFunctions = String.format("%s%s}\n\n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		}
		return this.strClassFunctions;
	}

	@Override
	public String addClassTail() {
		this.strClassTail = "}";
		return this.strClassTail;
	}
	
	private HTTPActivityHandler findHTTPHandlerOfHTTPActivity(HTTPActivity oHTTPActivity){
		for(int n = 0; n < this.oParentJavaControllerManager.getJavaControllerManagerHTTPHandlers().size(); n++){
			if(this.oParentJavaControllerManager.getJavaControllerManagerHTTPHandlers().get(n).getParentPIMCRUDActivityHandler().getParentControllerCRUDActivity().getResourceControllerCRUDActivityId() == oHTTPActivity.getParentPIMCRUDActivity().getResourceControllerCRUDActivityId()){
				return this.oParentJavaControllerManager.getJavaControllerManagerHTTPHandlers().get(n);
			}
		}
		try {
			throw new Exception("Corrupted PSM! Could not find corresponding HTTP Activity Handler of the HTTP Activity: " + oHTTPActivity.getHTTPActivityName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; //this should never happen
	}
}