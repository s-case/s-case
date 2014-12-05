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

import eu.fp7.scase.psmMetaModel.HTTPActivityHandler;
import eu.fp7.scase.psmMetaModel.HibernateController;
import eu.fp7.scase.psmMetaModel.PSMHypermediaLink;

public class HTTPHandlerJavaFile extends AJavaFile{

	private final HTTPActivityHandler oParentHTTPHandler;
	String strSelfRelationPatch = "";
	String strSourceIdentifier = "";
	String strSourceIdentifierSignature = "";

	public HTTPHandlerJavaFile(HTTPActivityHandler oParentHTTPHandler, String strProjectName, String strPackageStamp){
		super(oParentHTTPHandler.getHTTPActivityHandlerName(), strProjectName, strPackageStamp);
		this.oParentHTTPHandler = oParentHTTPHandler;
		if(this.oParentHTTPHandler.getJavaAlgoResourceController() == null){
			if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
				this.strSourceIdentifier = this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getModelPrimaryIdentifierName();
				if(this.oParentHTTPHandler.getParentJavaController() != null){
					if(this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceId() == this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceId()){
						this.strSelfRelationPatch = "Source";
					}
				}
				else if(this.oParentHTTPHandler.getParentJavaControllerManager() != null){
					if(this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceId() == this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceId()){
						this.strSelfRelationPatch = "Source";
					}
				}
				this.strSourceIdentifierSignature = String.format("int %s%s, ", this.strSelfRelationPatch, this.strSourceIdentifier);
			}
		}
	}

	public HTTPActivityHandler getParentHTTPHandler(){
		return this.oParentHTTPHandler;
	}

	@Override
	public void printJavaFile(){
		System.out.println("The HTTP Activity Handler File: " + getJavaFileName() + " is added to software code project because: " + this.oParentHTTPHandler.getHTTPActivityHandlerName() + " exists in PSM");
	}

	@Override
	public void transformFile() {
		super.transformFile();
	}

	@Override
	public String addAuthorComment() {
		this.oFileAuthorComment = new FileAuthorComment();
		this.oFileAuthorComment.setProject(this.strProjectName);
		this.oFileAuthorComment.setWorkFile("");
		this.oFileAuthorComment.setCompiler("");
		this.oFileAuthorComment.setFileDescription("");
		this.oFileAuthorComment.setDocumentDescription("");
		this.oFileAuthorComment.setRelatedDocuments("");
		this.oFileAuthorComment.setNote("");
		return this.oFileAuthorComment.exportFileAuthorComment();
	}

	@Override
	public String addFileImports() {
		this.strFileImports = String.format("%s%simport javax.ws.rs.core.UriInfo;%n%n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport java.util.Iterator;%n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport src.main.java.%s.utilities.HypermediaLink;%n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation(), this.strProjectName.toLowerCase());
		this.strFileImports = String.format("%s%simport src.main.java.%s.utilities.HibernateController;%n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation(), this.strProjectName.toLowerCase());
		if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
			this.strFileImports = String.format("%s%simport src.main.java.%s.%s.%s;%n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation(),
					this.strProjectName.toLowerCase(),
					this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName().toLowerCase(),
					this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
		}

		return this.strFileImports;
	}

	@Override
	public String addClassHeader() {
		this.strClassHeader = String.format("%spublic class %s{%n%n", this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getHTTPActivityHandlerName());
		this.oJavaFileIdentation.increaseIdentation();
		return this.strClassHeader;
	}

	@Override
	public String addClassProperties() {
		if(this.oParentHTTPHandler.getJavaAlgoResourceController() == null){
			this.strClassProperties = String.format("%s%sprivate %s o%s;%n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), HibernateController.getHibernateControllerHandle().getHibernateControllerName(), HibernateController.getHibernateControllerHandle().getHibernateControllerName());
			this.strClassProperties = String.format("%s%sprivate UriInfo oApplicationUri;%n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
			if(!((this.oParentHTTPHandler.getParentJavaControllerManager() != null) && this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("GET"))){//if this HTTP activity handler does not handle a POST request of a controller manager
				this.strClassProperties = String.format("%s%sprivate %s o%s;%n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), (this.oParentHTTPHandler.getParentJavaControllerManager() == null ? this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName() : this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName()), (this.oParentHTTPHandler.getParentJavaControllerManager() == null ? this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName() : this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName()));
			}
			else if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
				String strSelfRelatedStringPatch = "";
				if((this.oParentHTTPHandler.getParentJavaControllerManager() != null) && (this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceId() == this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceId())){
					strSelfRelatedStringPatch = "Source";
				}
				this.strClassProperties = String.format("%s%sprivate %s o%s%s;%n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(), strSelfRelatedStringPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
			}
			if(this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("PUT") || this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("POST")){
				if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
					String strSelfRelatedStringPatch = "";
					if((this.oParentHTTPHandler.getParentJavaController() != null) && (this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceId() == this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceId())){
						strSelfRelatedStringPatch = "Source";
					}
					else if((this.oParentHTTPHandler.getParentJavaControllerManager() != null) && (this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceId() == this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceId())){
						strSelfRelatedStringPatch = "Source";
					}
					this.strClassProperties = String.format("%s%sprivate %s o%s%s;%n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(), strSelfRelatedStringPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
				}
			}
		}
		return this.strClassProperties;
	}

	@Override
	public String addClassFunctions() {
		produceHandlerConstructor();
//		produceHandlerPropertyAccessors();
		produceHibernateAccessor();
		produceHypermediaFunction();
		return this.strClassFunctions;
	}

	@Override
	public String addClassTail() {
		this.strClassTail = "}";
		return this.strClassTail;
	}

	private void produceHandlerConstructor(){
		if(this.oParentHTTPHandler.getJavaAlgoResourceController() == null){
			if(this.oParentHTTPHandler.getParentJavaControllerManager() != null){
				if(this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("POST")){
					producePostHandlerConstructor();
				}
				else{ //it is GET
					produceGetListHandlerConstructor();
				}
			}
			else{
				if(this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("GET")){
					produceGetHandlerConstructor();
				}
				else if(this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("PUT")){
					producePutHandlerConstructor();
				}
				else{ //it is DELETE
					produceDeleteHandlerConstructor();
				}
			}
		}
	}

	private void producePostHandlerConstructor(){
		this.strClassFunctions = String.format("%s%spublic %s(%s%s o%s, UriInfo oApplicationUri){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getHTTPActivityHandlerName(), this.strSourceIdentifierSignature, this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.strClassFunctions = String.format("%s%sthis.o%s = o%s;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.strClassFunctions = String.format("%s%sthis.oHibernateController = HibernateController.getHibernateControllerHandle();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.oApplicationUri = oApplicationUri;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
			this.strClassFunctions = String.format("%s%so%s%s = new %s();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(), this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
			this.strClassFunctions = String.format("%s%so%s%s.set%s(%s%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(), this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getModelPrimaryIdentifierName());
			this.strClassFunctions = String.format("%s%so%s.setO%s(this.o%s%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
		}
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
	}

	private void produceGetListHandlerConstructor(){
		this.strClassFunctions = String.format("%s%spublic %s(%sUriInfo oApplicationUri){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getHTTPActivityHandlerName(), this.strSourceIdentifierSignature);
		this.strClassFunctions = String.format("%s%sthis.oHibernateController = HibernateController.getHibernateControllerHandle();%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
		this.strClassFunctions = String.format("%s%sthis.oApplicationUri = oApplicationUri;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
			this.strClassFunctions = String.format("%s%so%s%s = new %s();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(), this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
			this.strClassFunctions = String.format("%s%so%s%s.set%s(%s%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(), this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getModelPrimaryIdentifierName());
		}
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
	}

	private void produceGetHandlerConstructor(){
		this.strClassFunctions = String.format("%s%spublic %s(int %s, UriInfo oApplicationUri){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getHTTPActivityHandlerName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName());
		this.strClassFunctions = String.format("%s%so%s = new %s();%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.strClassFunctions = String.format("%s%so%s.set%s(%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName());
		this.strClassFunctions = String.format("%s%sthis.oHibernateController = HibernateController.getHibernateControllerHandle();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.oApplicationUri = oApplicationUri;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
	}

	private void producePutHandlerConstructor(){
		this.strClassFunctions = String.format("%s%spublic %s(%sint %s, %s o%s, UriInfo oApplicationUri){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getHTTPActivityHandlerName(), this.strSourceIdentifierSignature, this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.strClassFunctions = String.format("%s%sthis.o%s = o%s;%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.strClassFunctions = String.format("%s%sthis.o%s.set%s(%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName());
		this.strClassFunctions = String.format("%s%sthis.oHibernateController = HibernateController.getHibernateControllerHandle();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.strClassFunctions = String.format("%s%sthis.oApplicationUri = oApplicationUri;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
			this.strClassFunctions = String.format("%s%so%s%s = new %s();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(), this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
			this.strClassFunctions = String.format("%s%so%s%s.set%s(%s%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(), this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getModelPrimaryIdentifierName());
			this.strClassFunctions = String.format("%s%so%s.setO%s(this.o%s%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
		}
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
	}

	private void produceDeleteHandlerConstructor(){
		produceGetHandlerConstructor(); //since it is the same code
	}

	private void produceHibernateAccessor(){
		if(this.oParentHTTPHandler.getJavaAlgoResourceController() == null){
			if(this.oParentHTTPHandler.getParentJavaControllerManager() == null){
				this.strClassFunctions = String.format("%s%spublic %s %s%s(){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentHTTPHandler.getHTTPActivityHandlerVerb().toLowerCase(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName());
				this.strClassFunctions = String.format("%s%sreturn %s(oHibernateController.%s%s(o%s));%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getJavaFunctionName(), this.oParentHTTPHandler.getHTTPActivityHandlerVerb().toLowerCase(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName());
				this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
			}
			else{
				if(this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("POST"))
				{
					this.strClassFunctions = String.format("%s%spublic %s %s%s(){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentHTTPHandler.getHTTPActivityHandlerVerb().toLowerCase(), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
					this.strClassFunctions = String.format("%s%sreturn %s(oHibernateController.%s%s(o%s));%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getJavaFunctionName(), this.oParentHTTPHandler.getHTTPActivityHandlerVerb().toLowerCase(), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
					this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
				}
				else{ //it is GET List
					this.strClassFunctions = String.format("%s%spublic %s %s%s(){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName(), this.oParentHTTPHandler.getHTTPActivityHandlerVerb().toLowerCase(), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName());
					this.oJavaFileIdentation.increaseIdentation();
					if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
						this.strClassFunctions = String.format("%s%so%s%s = oHibernateController.%s%s%sList(o%s%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(), this.oParentHTTPHandler.getHTTPActivityHandlerVerb().toLowerCase(), (this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getIncomingJavaModelRelations().size() > 1 ? this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName() : ""), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(), this.strSelfRelationPatch, this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
					}
					this.strClassFunctions = String.format("%s%sreturn %s(%s%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getJavaFunctionName(), (this.oParentHTTPHandler.getIncomingJavaModel() != null ? String.format("o%s", this.strSelfRelationPatch) : ""), ( this.oParentHTTPHandler.getIncomingJavaModel() != null ? this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName() : ""));
					this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
				}
			}
		}
	}

	private void produceHypermediaFunction(){
		if(this.oParentHTTPHandler.getJavaAlgoResourceController() == null){
			if(this.oParentHTTPHandler.getParentJavaControllerManager() == null){
				if(this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("GET")){
					produceGetHypermediaFunction();
				}
				else if(this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("PUT")){
					producePutHypermediaFunction();
				}
				else if(this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("DELETE")){
					produceDeleteHypermediaFunction();
				}
			}
			else{
				if(this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("POST")){
					producePostHypermediaFunction();
				}
				else if(this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("GET")){
					produceGetListHypermediaFunction();
				}
			}
		}
	}

	private void produceGetHypermediaFunction(){
		this.strClassFunctions = String.format("%s%spublic %s %s(%s o%s){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
												this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getJavaFunctionName(),
												this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.oJavaFileIdentation.increaseIdentation();
		if(this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().hasReadActivity()){
			addSiblingHypermediLink("GET", "Sibling", false);
		}
		if(this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().hasUpdateActivity()){
			addSiblingHypermediLink("PUT", "Sibling", false);
		}
		if(this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().hasDeleteActivity()){
			addSiblingHypermediLink("DELETE", "Sibling" , false);
		}

		this.strClassFunctions = String.format("%s%sString oRelativePath;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		for(int n = 0; n < this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().size(); n++){
			//if this hypermedia link is of "child" type and has as target a controller manager
			if(this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPSMHypermediaLinkType().equalsIgnoreCase("Children") &&
			   this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPIMParentHypermediaLink().getTargetResourceControllerManager() != null){
				calculateChildUri(this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n));
				this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s/%%s\", oApplicationUri.getBaseUri(), oRelativePath, \"%s\"), \"%s\", \"%s\", \"Child\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
						this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPIMParentHypermediaLink().getTargetResourceControllerManager().getParentCIMResource().getResourceName(),
						this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPSMHypermediaLinkVerb().equalsIgnoreCase("POST") ? String.format("Create a new %s for this %s", this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPIMParentHypermediaLink().getTargetResourceControllerManager().getParentCIMResource().getResourceName(),
						this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()) :
						 String.format("Get all the %ss of this %s", this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPIMParentHypermediaLink().getTargetResourceControllerManager().getParentCIMResource().getResourceName(),
																	this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()),
						this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPSMHypermediaLinkVerb());
			}
		}

		if(this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().isRelatedResource() &&
				this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().hasUniqueIncomingRelation()){
			this.strClassFunctions = String.format("%s%sint iLastSlashIndex = String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oApplicationUri.getPath()).lastIndexOf(\"/\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oApplicationUri.getPath()).substring(0, iLastSlashIndex), \"%s\", \"POST\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					String.format("Create a new %s", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()));

			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oApplicationUri.getPath()).substring(0, iLastSlashIndex), \"%s\", \"GET\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					String.format("Get all %ss", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()) +
							( this.oParentHTTPHandler.getIncomingJavaModel() != null ? " of this " + this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName() : ""));
		}
		else{
			this.strClassFunctions = String.format("%s%sint iLastSlashIndex = String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), String.format(\"%%s\", oApplicationUri.getPath()).replaceAll(\"multi%s\",\"multi%sManager\")).lastIndexOf(\"/\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName());
			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), String.format(\"%%s\", oApplicationUri.getPath()).replaceAll(\"multi%s\",\"multi%sManager\")).substring(0, iLastSlashIndex), \"%s\", \"POST\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					String.format("Create a new %s", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()));

			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), String.format(\"%%s\", oApplicationUri.getPath()).replaceAll(\"multi%s\",\"multi%sManager\")).substring(0, iLastSlashIndex), \"%s\", \"GET\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					String.format("Get all %ss", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()) +
							( this.oParentHTTPHandler.getIncomingJavaModel() != null ? " of this " + this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName() : ""));
		}

		this.strClassFunctions = String.format("%s%sreturn o%s;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation() );
	}

	private void producePutHypermediaFunction(){
		this.strClassFunctions = String.format("%s%spublic %s %s(%s o%s){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
												this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getJavaFunctionName(),
												this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.oJavaFileIdentation.increaseIdentation();
		if(this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().hasReadActivity()){
			addSiblingHypermediLink("GET", "Sibling", false);
		}
		if(this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().hasUpdateActivity()){
			addSiblingHypermediLink("PUT", "Sibling", false);
		}
		if(this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().hasDeleteActivity()){
			addSiblingHypermediLink("DELETE", "Sibling" , false);
		}

		this.strClassFunctions = String.format("%s%sString oRelativePath;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		for(int n = 0; n < this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().size(); n++){
			//if this hypermedia link is of "child" type and has as target a controller manager
			if(this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPSMHypermediaLinkType().equalsIgnoreCase("Children") &&
			   this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPIMParentHypermediaLink().getTargetResourceControllerManager() != null){
				calculateChildUri(this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n));
				this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s/%%s\", oApplicationUri.getBaseUri(), oRelativePath, \"%s\"), \"%s\", \"%s\", \"Child\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
						this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPIMParentHypermediaLink().getTargetResourceControllerManager().getParentCIMResource().getResourceName(),
						this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPSMHypermediaLinkVerb().equalsIgnoreCase("POST") ? String.format("Create a new %s for this %s", this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPIMParentHypermediaLink().getTargetResourceControllerManager().getParentCIMResource().getResourceName(),
						this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()) :
						 String.format("Get all the %ss of this %s", this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPIMParentHypermediaLink().getTargetResourceControllerManager().getParentCIMResource().getResourceName(),
																	this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()),
						this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getHypermediaLinks().get(n).getPSMHypermediaLinkVerb());
			}
		}

		if(this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().isRelatedResource() &&
				this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().hasUniqueIncomingRelation()){
			this.strClassFunctions = String.format("%s%sint iLastSlashIndex = String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oApplicationUri.getPath()).lastIndexOf(\"/\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oApplicationUri.getPath()).substring(0, iLastSlashIndex), \"%s\", \"POST\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					String.format("Create a new %s", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()));

			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oApplicationUri.getPath()).substring(0, iLastSlashIndex), \"%s\", \"GET\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					String.format("Get all %ss", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()) +
							( this.oParentHTTPHandler.getIncomingJavaModel() != null ? " of this " + this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName() : ""));
		}
		else{
			this.strClassFunctions = String.format("%s%sint iLastSlashIndex = String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), String.format(\"%%s\", oApplicationUri.getPath()).replaceAll(\"multi%s\",\"multi%sManager\")).lastIndexOf(\"/\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName());
			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), String.format(\"%%s\", oApplicationUri.getPath()).replaceAll(\"multi%s\",\"multi%sManager\")).substring(0, iLastSlashIndex), \"%s\", \"POST\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					String.format("Create a new %s", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()));

			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), String.format(\"%%s\", oApplicationUri.getPath()).replaceAll(\"multi%s\",\"multi%sManager\")).substring(0, iLastSlashIndex), \"%s\", \"GET\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					String.format("Get all %ss", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()) +
							( this.oParentHTTPHandler.getIncomingJavaModel() != null ? " of this " + this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName() : ""));
		}
		this.strClassFunctions = String.format("%s%sreturn o%s;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation() );
	}

	// 		this.strClassFunctions = String.format("%s%s %n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation() );


	private void produceDeleteHypermediaFunction(){
		this.strClassFunctions = String.format("%s%spublic %s %s(%s o%s){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getJavaFunctionName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName());
		if(this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().isRelatedResource() &&
				this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().hasUniqueIncomingRelation()){
			this.strClassFunctions = String.format("%s%sint iLastSlashIndex = String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oApplicationUri.getPath()).lastIndexOf(\"/\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oApplicationUri.getPath()).substring(0, iLastSlashIndex), \"%s\", \"POST\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					String.format("Create a new %s", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()));

			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oApplicationUri.getPath()).substring(0, iLastSlashIndex), \"%s\", \"GET\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					String.format("Get all %ss", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()) +
							( this.oParentHTTPHandler.getIncomingJavaModel() != null ? " of this " + this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName() : ""));
		}
		else{
			this.strClassFunctions = String.format("%s%sint iLastSlashIndex = String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), String.format(\"%%s\", oApplicationUri.getPath()).replaceAll(\"multi%s\",\"multi%sManager\")).lastIndexOf(\"/\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName());
			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), String.format(\"%%s\", oApplicationUri.getPath()).replaceAll(\"multi%s\",\"multi%sManager\")).substring(0, iLastSlashIndex), \"%s\", \"POST\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					String.format("Create a new %s", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()));

			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), String.format(\"%%s\", oApplicationUri.getPath()).replaceAll(\"multi%s\",\"multi%sManager\")).substring(0, iLastSlashIndex), \"%s\", \"GET\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					String.format("Get all %ss", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName()) +
							( this.oParentHTTPHandler.getIncomingJavaModel() != null ? " of this " + this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName() : ""));
		}
		this.strClassFunctions = String.format("%s%sreturn o%s;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation() );
	}

	private void producePostHypermediaFunction(){
		this.strClassFunctions = String.format("%s%spublic %s %s(%s o%s){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getJavaFunctionName(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.oJavaFileIdentation.increaseIdentation();
		addSiblingHypermediLink("GET", "Sibling", true);
		addSiblingHypermediLink("POST", "Sibling", true);

		this.strClassFunctions = String.format("%s%sString oRelativePath;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		if(this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().hasUniqueIncomingRelation() || !this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().hasIncomingRelations()){
			this.strClassFunctions = String.format("%s%soRelativePath = oApplicationUri.getPath();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		}
		else{
			this.strClassFunctions = String.format("%s%soRelativePath = oApplicationUri.getPath().replaceAll(\"multi%sManager/\", \"multi%s/\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName());
		}
		if(this.oParentHTTPHandler.getParentJavaControllerManager().getAssociatedJavaResourceController().hasGetActivity()){
			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s/%%d\", oApplicationUri.getBaseUri(), oRelativePath, o%s.get%s()), %s, \"GET\", \"Child\", %s));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getNamingPropertyNameValue(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getIdentifyingPropertyIdValue());
		}
		if(this.oParentHTTPHandler.getParentJavaControllerManager().getAssociatedJavaResourceController().hasPutActivity()){
			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s/%%d\", oApplicationUri.getBaseUri(), oRelativePath, o%s.get%s()), %s, \"PUT\", \"Child\", %s));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getNamingPropertyNameValue(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getIdentifyingPropertyIdValue());
		}
		if(this.oParentHTTPHandler.getParentJavaControllerManager().getAssociatedJavaResourceController().hasDeleteActivity()){
			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s/%%d\", oApplicationUri.getBaseUri(), oRelativePath, o%s.get%s()), %s, \"DELETE\", \"Child\", %s));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getNamingPropertyNameValue(),
												this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getIdentifyingPropertyIdValue());
		}

		if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
			//if both this resource and the and the parent ones are related of at most one resource
			if(this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().hasUniqueIncomingRelation() &&
			   (!this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource() || this.oParentHTTPHandler.getIncomingJavaModel().hasUniqueIncomingRelation())){
				if(!this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource()){
					this.strClassFunctions = String.format("%s%soRelativePath = oApplicationUri.getPath();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
				}
				else{
					this.strClassFunctions = String.format("%s%sthis.o%s = HibernateController.getHibernateControllerHandle().get%s(this.o%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
					this.strClassFunctions = String.format("%s%soRelativePath = String.format(\"%s/%%d/%%s\", this.o%s.getO%s().get%s(), oApplicationUri.getPath());%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getModelPrimaryIdentifierName());
				}
			}//else if this resource is related of at most one other resource and the parent resource is related of at least two others
			else if(this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().hasUniqueIncomingRelation() &&
					   (this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource() && !this.oParentHTTPHandler.getIncomingJavaModel().hasUniqueIncomingRelation())){
				this.strClassFunctions = String.format("%s%sthis.o%s = HibernateController.getHibernateControllerHandle().get%s(this.o%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
						this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
				for(int n = 0; n < this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().size(); n++){
					if( n == 0){
						this.strClassFunctions = String.format("%s%sif(this.o%s.getO%s() != null){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
								this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
								this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName());
					}
					else{
						this.strClassFunctions = String.format("%s%selse if(this.o%s.getO%s() != null){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
								this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
								this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName());
					}
					this.strClassFunctions = String.format("%s%soRelativePath = String.format(\"%%s/%%s\", \"multi%s\", String.format(\"%s/%%d/%%s\", this.o%s.getO%s().get%s(), oApplicationUri.getPath()));%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getModelPrimaryIdentifierName());
					this.strClassFunctions = String.format("%s%s}%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
				}
			}//else if this resource is related of at least two other resources and the parent one is related of at most one other resource
			else if(!this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().hasUniqueIncomingRelation() &&
					   (!this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource() || this.oParentHTTPHandler.getIncomingJavaModel().hasUniqueIncomingRelation())){
				if(!this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource()){
					this.strClassFunctions = String.format("%s%soRelativePath = oApplicationUri.getPath().replaceAll(\"multi%sManager/\",\"\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
							this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName());
				}
				else{
					this.strClassFunctions = String.format("%s%sthis.o%s = HibernateController.getHibernateControllerHandle().get%s(this.o%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
					this.strClassFunctions = String.format("%s%soRelativePath = String.format(\"%s/%%d/%%s\", this.o%s.getO%s().get%s(), oApplicationUri.getPath().replaceAll(\"multi%sManager/\",\"\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getModelPrimaryIdentifierName(),
							this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName());
				}
			}//if both this resource and the parent one are related resources of at least two other
			else if(!this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().hasUniqueIncomingRelation() &&
					   (this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource() && !this.oParentHTTPHandler.getIncomingJavaModel().hasUniqueIncomingRelation())){
				this.strClassFunctions = String.format("%s%sthis.o%s%s = HibernateController.getHibernateControllerHandle().get%s(this.o%s%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch,
						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
						this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
						this.strSelfRelationPatch,
						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
				for(int n = 0; n < this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().size(); n++){
					if( n == 0){
						this.strClassFunctions = String.format("%s%sif(this.o%s%s.getO%s() != null){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch,
								this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
								this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName());
					}
					else{
						this.strClassFunctions = String.format("%s%selse if(this.o%s%s.getO%s() != null){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch,
								this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
								this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName());
					}
					this.strClassFunctions = String.format("%s%soRelativePath = String.format(\"multi%s/%s/%%d/%%s/%%d\", this.o%s%s.getO%s().get%s(), oApplicationUri.getPath().substring(0, oApplicationUri.getPath().lastIndexOf(\"/%s\")).replaceAll(\"multi%sManager/\", \"\"), this.o%s%s.get%s());%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.strSelfRelationPatch,
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getModelPrimaryIdentifierName(),
							this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
							this.strSelfRelationPatch,
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getModelPrimaryIdentifierName());
					this.strClassFunctions = String.format("%s%s}%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
				}
			}

			//if it self relation
//			if(this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceId() ==
//					this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceId()){
//				this.strClassFunctions = String.format("%s%soSource%s = oHibernateController.get%s(oSource%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
//						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
//						this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
//						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
//				this.strClassFunctions = String.format("%s%sif(oSource%s.getO%s() != null){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
//						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
//						this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName());
//				this.strClassFunctions = String.format("%s%soRelativePath = oRelativePath.replaceAll(String.format(\"%s/[0-9]*/%s\"), String.format(\"%s/%%d/%s/%%d/%s\", oSource%s.getO%s().get%s() ,oSource%s.get%s()));%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(),
//						this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
//						this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
//						this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
//						this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
//						this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
//						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
//						this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
//						this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(),
//						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
//						this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName());
//				this.strClassFunctions = String.format("%s%s}%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
//			}

			this.strClassFunctions = String.format("%s%sint iLastSlashIndex = String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oRelativePath).lastIndexOf(\"/\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
			if(this.oParentHTTPHandler.getIncomingJavaModel().getAssociatedResourceController().hasDeleteActivity()){
				this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oRelativePath).substring(0, iLastSlashIndex), \"%s\", \"DELETE\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
						String.format("Delete the parent %s", this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName()));
			}
			if(this.oParentHTTPHandler.getIncomingJavaModel().getAssociatedResourceController().hasGetActivity()){
				this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oRelativePath).substring(0, iLastSlashIndex), \"%s\", \"GET\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
						String.format("Get the parent %s", this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName()));
			}
			if(this.oParentHTTPHandler.getIncomingJavaModel().getAssociatedResourceController().hasPutActivity()){
				this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oRelativePath).substring(0, iLastSlashIndex), \"%s\", \"PUT\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
						String.format("Update the %s", this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName()));
			}
		}

		this.strClassFunctions = String.format("%s%sreturn o%s;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation() );
	}

	private void produceGetListHypermediaFunction(){
		if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
			this.strClassFunctions = String.format("%s%spublic %s %s(%s o%s){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName(),
					this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getJavaFunctionName(),
					this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
		}
		else{
			this.strClassFunctions = String.format("%s%spublic %s %s(){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName(),
					this.oParentHTTPHandler.getHTTPHandlerHypermediaFunction().getJavaFunctionName());
		}
		this.strClassFunctions = String.format("%s%s%s o%s = new %s();%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(),
				this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName(),
				this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName(),
				this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName());
		addSiblingHypermediLink("GET", "Sibling", true);
		addSiblingHypermediLink("POST", "Sibling", true);
		if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
			this.strClassFunctions = String.format("%s%sString oRelativePath;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
			if(this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().hasUniqueIncomingRelation()){
				this.strClassFunctions = String.format("%s%soRelativePath = oApplicationUri.getPath();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
			}
			else{
				this.strClassFunctions = String.format("%s%soRelativePath = oApplicationUri.getPath().replaceAll(\"multi%sManager/\", \"multi%s/\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
						this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName());
			}
			this.strClassFunctions = String.format("%s%sIterator<%s> setIterator = o%s%s.getSetOf%s().iterator();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.strSelfRelationPatch,
					this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
			this.strClassFunctions = String.format("%s%swhile(setIterator.hasNext()){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
			this.strClassFunctions = String.format("%s%s%s oNext%s = new %s();%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
			this.strClassFunctions = String.format("%s%soNext%s = setIterator.next();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName());
			this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s/%%d\", oApplicationUri.getBaseUri(), oRelativePath, oNext%s.get%s()), String.format(\"%%s\", oNext%s.%s), \"GET\", \"Child\", oNext%s.%s));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getNamingPropertyNameGetter(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName(),
					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getIdentifyingPropertyIdGetter());
			this.strClassFunctions = String.format("%s%s}%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation() );
		}

		if(this.oParentHTTPHandler.getIncomingJavaModel() != null){
			//if both this resource and the and the parent ones are related of at most one resource
			if(this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().hasUniqueIncomingRelation() &&
			   (!this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource() || this.oParentHTTPHandler.getIncomingJavaModel().hasUniqueIncomingRelation())){
				if(!this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource()){
					this.strClassFunctions = String.format("%s%soRelativePath = oApplicationUri.getPath();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
				}
				else{
					this.strClassFunctions = String.format("%s%sthis.o%s = HibernateController.getHibernateControllerHandle().get%s(this.o%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
					this.strClassFunctions = String.format("%s%soRelativePath = String.format(\"%s/%%d/%%s\", this.o%s.getO%s().get%s(), oApplicationUri.getPath());%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getModelPrimaryIdentifierName());
				}
			}//else if this resource is related of at most one other resource and the parent resource is related of at least two others
			else if(this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().hasUniqueIncomingRelation() &&
					   (this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource() && !this.oParentHTTPHandler.getIncomingJavaModel().hasUniqueIncomingRelation())){
				this.strClassFunctions = String.format("%s%sthis.o%s = HibernateController.getHibernateControllerHandle().get%s(this.o%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
						this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
				for(int n = 0; n < this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().size(); n++){
					if( n == 0){
						this.strClassFunctions = String.format("%s%sif(this.o%s.getO%s() != null){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
								this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
								this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName());
					}
					else{
						this.strClassFunctions = String.format("%s%selse if(this.o%s.getO%s() != null){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
								this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
								this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName());
					}
					this.strClassFunctions = String.format("%s%soRelativePath = String.format(\"%%s/%%s\", \"multi%s\", String.format(\"%s/%%d/%%s\", this.o%s.getO%s().get%s(), oApplicationUri.getPath()));%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getModelPrimaryIdentifierName());
					this.strClassFunctions = String.format("%s%s}%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
				}
			}//else if this resource is related of at least two other resources and the parent one is related of at most one other resource
			else if(!this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().hasUniqueIncomingRelation() &&
					   (!this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource() || this.oParentHTTPHandler.getIncomingJavaModel().hasUniqueIncomingRelation())){
				if(!this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource()){
					this.strClassFunctions = String.format("%s%soRelativePath = oApplicationUri.getPath().replaceAll(\"multi%sManager/\",\"\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
							this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName());
				}
				else{
					this.strClassFunctions = String.format("%s%sthis.o%s = HibernateController.getHibernateControllerHandle().get%s(this.o%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
					this.strClassFunctions = String.format("%s%soRelativePath = String.format(\"%s/%%d/%%s\", this.o%s.getO%s().get%s(), oApplicationUri.getPath().replaceAll(\"multi%sManager/\",\"\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(0).getPIMParentResourceModel().getModelPrimaryIdentifierName(),
							this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName());
				}
			}//if both this resource and the parent one are related resources of at least two other
			else if(!this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().hasUniqueIncomingRelation() &&
					   (this.oParentHTTPHandler.getIncomingJavaModel().isRelatedResource() && !this.oParentHTTPHandler.getIncomingJavaModel().hasUniqueIncomingRelation())){
				this.strClassFunctions = String.format("%s%sthis.o%s%s = HibernateController.getHibernateControllerHandle().get%s(this.o%s%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch,
						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
						this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
						this.strSelfRelationPatch,
						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
				for(int n = 0; n < this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().size(); n++){
					if( n == 0){
						this.strClassFunctions = String.format("%s%sif(this.o%s%s.getO%s() != null){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch,
								this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
								this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName());
					}
					else{
						this.strClassFunctions = String.format("%s%selse if(this.o%s%s.getO%s() != null){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.strSelfRelationPatch,
								this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
								this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName());
					}
					this.strClassFunctions = String.format("%s%soRelativePath = String.format(\"multi%s/%s/%%d/%%s/%%d\", this.o%s%s.getO%s().get%s(), oApplicationUri.getPath().substring(0, oApplicationUri.getPath().lastIndexOf(\"/%s\")).replaceAll(\"multi%sManager/\", \"\"), this.o%s%s.get%s());%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(),
							this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.strSelfRelationPatch,
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getIncomingJavaModels().get(n).getPIMParentResourceModel().getModelPrimaryIdentifierName(),
							this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
							this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
							this.strSelfRelationPatch,
							this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
							this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getModelPrimaryIdentifierName());
					this.strClassFunctions = String.format("%s%s}%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
				}
			}

			//if it self relation
///			if(this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceId() ==
	//				this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceId()){
		//		this.strClassFunctions = String.format("%s%soSource%s = oHibernateController.get%s(oSource%s);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
//						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
	//					this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
		//				this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName());
//				this.strClassFunctions = String.format("%s%sif(oSource%s.getO%s() != null){%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
	//					this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
		//				this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName());
//				this.strClassFunctions = String.format("%s%soRelativePath = oRelativePath.replaceAll(String.format(\"%s/[0-9]*/%s\"), String.format(\"%s/%%d/%s/%%d/%s\", oSource%s.getO%s().get%s() ,oSource%s.get%s()));%n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(),
	//					this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
		//				this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
//						this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
	//					this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
		//				this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
//						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
	//					this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName(),
		//				this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName(),
//						this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName(),
	//					this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getPIMParentResourceModel().getModelPrimaryIdentifierName());
		//		this.strClassFunctions = String.format("%s%s}%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
	//		}

			this.strClassFunctions = String.format("%s%sint iLastSlashIndex = String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oRelativePath).lastIndexOf(\"/\");%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
			if(this.oParentHTTPHandler.getIncomingJavaModel().getAssociatedResourceController().hasDeleteActivity()){
				this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oRelativePath).substring(0, iLastSlashIndex), \"%s\", \"DELETE\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName(),
						String.format("Delete the parent %s", this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName()));
			}
			if(this.oParentHTTPHandler.getIncomingJavaModel().getAssociatedResourceController().hasGetActivity()){
				this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oRelativePath).substring(0, iLastSlashIndex), \"%s\", \"GET\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName(),
						String.format("Get the parent %s", this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName()));
			}
			if(this.oParentHTTPHandler.getIncomingJavaModel().getAssociatedResourceController().hasPutActivity()){
				this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oRelativePath).substring(0, iLastSlashIndex), \"%s\", \"PUT\", \"Parent\"));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName(),
						String.format("Update the %s", this.oParentHTTPHandler.getIncomingJavaModel().getJavaResourceModelName()));
			}
		}

		this.strClassFunctions = String.format("%s%sreturn o%s;%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName());
		this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation() );
	}

	private void addSiblingHypermediLink(String strHTTPVerb, String strLinkType, boolean isManagerLink){
		String strRelMessage = "";
		if(strHTTPVerb.equalsIgnoreCase("GET") && !isManagerLink){
			strRelMessage = String.format("Get the %s", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName());
		}
		else if(strHTTPVerb.equalsIgnoreCase("GET") && isManagerLink){
			strRelMessage = String.format("%s%s", String.format("Get all %ss", this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName()), (this.oParentHTTPHandler.getIncomingJavaModel() != null ? " of this " + this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceName() : ""));
		}
		else if(strHTTPVerb.equalsIgnoreCase("PUT")){
			strRelMessage = String.format("Update the %s", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName());
		}
		else if(strHTTPVerb.equalsIgnoreCase("DELETE")){
			strRelMessage = String.format("Delete the %s", this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName());
		}
		else if(strHTTPVerb.equalsIgnoreCase("POST")){
			strRelMessage = String.format("Create a new %s", this.oParentHTTPHandler.getParentJavaControllerManager().getPIMParentResourceControllerManager().getParentCIMResource().getResourceName());
		}

		this.strClassFunctions = String.format("%s%so%s.getLinkList().add(new HypermediaLink(String.format(\"%%s%%s\", oApplicationUri.getBaseUri(), oApplicationUri.getPath()), \"%s\", \"%s\", \"%s\"));%n",
				this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
				!isManagerLink ? this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName() : (this.oParentHTTPHandler.getHTTPActivityHandlerVerb().equalsIgnoreCase("GET") ? this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getJavaResourceModelManagerName() : this.oParentHTTPHandler.getParentJavaControllerManager().getRelatedJavaResourceModelManager().getRelatedJavaResourceModel().getJavaResourceModelName()),
				strRelMessage, strHTTPVerb, strLinkType);
	}

	private void calculateChildUri(PSMHypermediaLink oHypermdiaLink){
		//if both this resource and the target resource are related resource of at most one other resources
		if((!this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().isRelatedResource() ||
		   this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().hasUniqueIncomingRelation()) &&
		   oHypermdiaLink.getPIMParentHypermediaLink().getTargetResourceControllerManager().hasUniqueIncomingRelation()){
			if(!this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().isRelatedResource()){
				this.strClassFunctions = String.format("%s%soRelativePath = oApplicationUri.getPath();%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
			}
			else{
				this.strClassFunctions = String.format("%s%soRelativePath = oApplicationUri.getPath().substring(oApplicationUri.getPath().indexOf(\"/%s\") + 1);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName());
			}
		}//else if this resource is related of at most one other resource but the target resource is related of at least two
		else if((!this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().isRelatedResource() ||
			    this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().hasUniqueIncomingRelation()) &&
				!oHypermdiaLink.getPIMParentHypermediaLink().getTargetResourceControllerManager().hasUniqueIncomingRelation()){
			if(!this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().isRelatedResource()){
				this.strClassFunctions = String.format("%s%soRelativePath = String.format(\"%%s/%%s\", \"multi%sManager\", oApplicationUri.getPath());%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						oHypermdiaLink.getPIMParentHypermediaLink().getTargetResourceControllerManager().getParentCIMResource().getResourceName());
			}
			else{
				this.strClassFunctions = String.format("%s%soRelativePath = String.format(\"%%s/%%s\", \"multi%sManager\", oApplicationUri.getPath().substring(oApplicationUri.getPath().indexOf(\"/%s\") + 1));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
						oHypermdiaLink.getPIMParentHypermediaLink().getTargetResourceControllerManager().getParentCIMResource().getResourceName(),
						this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName());
			}

		}//else if this resource is related of at least two other resources while the target resource is related of at most one
		else if((this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().isRelatedResource() &&
			    !this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().hasUniqueIncomingRelation()) &&
			    oHypermdiaLink.getPIMParentHypermediaLink().getTargetResourceControllerManager().hasUniqueIncomingRelation()){
			this.strClassFunctions = String.format("%s%soRelativePath = oApplicationUri.getPath().replaceAll(\"multi%s/\",\"\").substring(oApplicationUri.getPath().indexOf(\"/%s\") + 1);%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getPIMParentResourceModel().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName());
		}//else if both this resource and the target resource are related of at least two resources
		else if((this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().isRelatedResource() &&
			    !this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().hasUniqueIncomingRelation()) &&
				!oHypermdiaLink.getPIMParentHypermediaLink().getTargetResourceControllerManager().hasUniqueIncomingRelation()){
			this.strClassFunctions = String.format("%s%soRelativePath = String.format(\"multi%sManager/%%s\", oApplicationUri.getPath().substring(oApplicationUri.getPath().indexOf(\"/%s/\") + 1));%n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
					oHypermdiaLink.getPIMParentHypermediaLink().getTargetResourceControllerManager().getParentCIMResource().getResourceName(),
					this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName());
		}

		//if it is self relation
		if(this.oParentHTTPHandler.getIncomingJavaModel() != null &&
		  (this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceId() ==
		   this.oParentHTTPHandler.getIncomingJavaModel().getPIMParentResourceModel().getParentCIMResource().getResourceId())){
			this.strClassFunctions = String.format("%s%soRelativePath = oRelativePath.replaceAll(String.format(\"%s/[0-9]*/%s/%%d\", o%s.get%s()), String.format(\"%s/%%d\", o%s.get%s())); %n",
													this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
													this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName(),
													this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName(),
													this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
													this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getModelPrimaryIdentifierName(),
													this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getParentCIMResource().getResourceName(),
													this.oParentHTTPHandler.getParentJavaController().getRelatedJavaResourceModel().getJavaResourceModelName(),
													this.oParentHTTPHandler.getParentJavaController().getPIMParentResourceController().getModelPrimaryIdentifierName());
		}
	}
}