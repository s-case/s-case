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

import main.java.scase.psmMetaModel.JavaResourceModel;
import main.java.scase.psmMetaModel.JavaResourceModelProperty;

public class ResourceModelJavaFile extends AJavaFile{
	
	private JavaResourceModel oParentJavaModel;
	
	public ResourceModelJavaFile(JavaResourceModel oParentJavaModel, String strProjectName, String strPackageStamp){
		super(oParentJavaModel.getJavaResourceModelName(), strProjectName, strPackageStamp);
		this.oParentJavaModel = oParentJavaModel;
		this.strProjectName = strProjectName;
		this.strPackageStamp = strPackageStamp;
	}
	
	public JavaResourceModel getParentJavaModel(){
		return this.oParentJavaModel;
	}
	
	@Override
	public void printJavaFile(){
		System.out.println("The Java Resource Model File: " + this.getJavaFileName() + " is added to software project code because " + this.oParentJavaModel.getJavaResourceModelName() + " exists in PSM");
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
		this.strFileImports = String.format("%s%simport java.util.ArrayList;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport java.util.HashSet;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport java.util.Iterator;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport java.util.List;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport java.util.Set;\n\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.CollectionTable;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.Column;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.ElementCollection;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.Entity;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.FetchType;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.GeneratedValue;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.Id;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.JoinColumn;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.ManyToOne;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.OneToMany;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.Table;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.persistence.Transient;\n\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.xml.bind.annotation.XmlRootElement;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport javax.xml.bind.annotation.XmlTransient;\n\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport org.hibernate.Query;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport org.hibernate.Session;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport org.hibernate.annotations.ForeignKey;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport org.hibernate.annotations.OnDelete;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
		this.strFileImports = String.format("%s%simport org.hibernate.annotations.OnDeleteAction;\n\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());

		this.strFileImports = String.format("%s%simport src.main.java.%s.utilities.HypermediaLink;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation(), this.strProjectName.toLowerCase());
		for(int n = 0; n < this.oParentJavaModel.getIncomingJavaModels().size(); n++){
			this.strFileImports = String.format("%s%simport src.main.java.%s.%s.%s;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation(),
					this.strProjectName.toLowerCase(), this.oParentJavaModel.getIncomingJavaModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName().toLowerCase(),
					this.oParentJavaModel.getIncomingJavaModels().get(n).getJavaResourceModelName());
		}
		
		for(int n = 0; n < this.oParentJavaModel.getRelatedJavaResourceModelManagers().size(); n++){
			this.strFileImports = String.format("%s%simport src.main.java.%s.%s.%s;\n", this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation(),
					this.strProjectName.toLowerCase(),
					this.oParentJavaModel.getRelatedJavaResourceModelManagers().get(n).getPIMParentResourceModelManager().getParentCIMResource().getResourceName().toLowerCase(),
					this.oParentJavaModel.getRelatedJavaResourceModelManagers().get(n).getRelatedJavaResourceModel().getJavaResourceModelName());
		}
		
		return this.strFileImports;
	}

	@Override
	public String addClassHeader() {
		this.strClassHeader = String.format("%s", this.oParentJavaModel.getModelJAXBAnnotaiton().getJAXBAnnotationText());
		this.strClassHeader = String.format("%s\n%s", this.strClassHeader, this.oParentJavaModel.getEntityHibernateAnnotation().getHibernateAnnotationText());
		this.strClassHeader = String.format("%s\n%s", this.strClassHeader, this.oParentJavaModel.getTableHibernateAnnotation().getHibernateAnnotationText());
		this.strClassHeader = String.format("%s\npublic class %s{\n\n", this.strClassHeader, this.oParentJavaModel.getJavaResourceModelName());
		this.oJavaFileIdentation.increaseIdentation();
		return this.strClassHeader;
	}

	@Override
	public String addClassProperties() {
		for(int n = 0; n < this.oParentJavaModel.getJavaResourceModelProperties().size(); n++){
			if(this.oParentJavaModel.getJavaResourceModelProperties().get(n).getJavaModelPropertyIdentifyingAbility()){
				this.strClassProperties = String.format("%s%s%s\n",this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getIdHibernateAnnotation().getHibernateAnnotationText() );
				this.strClassProperties = String.format("%s%s%s\n",this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getGeneratedValueHibernateAnnotation().getHibernateAnnotationText());
			}
			
			if(this.oParentJavaModel.getJavaResourceModelProperties().get(n).getColumnHibernateAnnotation() != null){
				this.strClassProperties = String.format("%s%s%s\n",this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getColumnHibernateAnnotation().getHibernateAnnotationText());
			}
			
			if(!this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyUniqueness()){				
				if(this.oParentJavaModel.getJavaResourceModelProperties().get(n).getElementCollectionHibernateAnnotation() != null){
					this.strClassProperties = String.format("%s%s%s\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getElementCollectionHibernateAnnotation().getHibernateAnnotationText());
					this.strClassProperties = String.format("%s%s%s\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getCollectionTableHibernateAnnotation().getHibernateAnnotationText());
					this.strClassProperties = String.format("%s%s%s\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getForeignKeyHibernateAnnotation().getHibernateAnnotationText());
					this.strClassProperties = String.format("%s%sprivate Set<%s> %s = new HashSet<%s>();\n\n",this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyType(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyName(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyType());
				}
				else if(this.oParentJavaModel.getJavaResourceModelProperties().get(n).getOneToManyHibernateAnnotation() != null){
					this.strClassProperties = String.format("%s%s%s\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getOneToManyHibernateAnnotation().getHibernateAnnotationText());
					this.strClassProperties = String.format("%s%s%s\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getOnDeleteHibernateAnnotation().getHibernateAnnotationText());
					this.strClassProperties = String.format("%s%sprivate Set<%s> %s = new HashSet<%s>();\n\n",this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyType(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyName(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyType());
				}
				else{// else this is the link list property
					this.strClassProperties = String.format("%s%s%s\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getTransientHibernateAnnotation().getHibernateAnnotationText());
					this.strClassProperties = String.format("%s%sprivate List<%s> %s = new ArrayList<%s>();\n\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyType(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyName(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyType());
				}
			}
			else{
				if(this.oParentJavaModel.getJavaResourceModelProperties().get(n).getManyToOneHibernateAnnotation() != null){
					this.strClassProperties = String.format("%s%s%s\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getManyToOneHibernateAnnotation().getHibernateAnnotationText());
					this.strClassProperties = String.format("%s%s%s\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getJoinColumnHibernateAnnotation().getHibernateAnnotationText());
					this.strClassProperties = String.format("%s%s%s\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getForeignKeyHibernateAnnotation().getHibernateAnnotationText());
					this.strClassProperties = String.format("%s%sprivate %s %s;\n\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyType(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyName());
				}
				else{
					this.strClassProperties = String.format("%s%sprivate %s %s; \n\n", this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyType(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyName());
				}
			}
		}
		return this.strClassProperties;
	}
	
	@Override
	public String addClassFunctions() {
		for(int n = 0; n < this.oParentJavaModel.getModelJavaGetterFunctions().size(); n++){
			if(this.oParentJavaModel.getModelJavaGetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyUniqueness()){
				this.strClassFunctions = String.format("%s%spublic %s %s(){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getModelJavaGetterFunctions().get(n).getReturnParameter().getParameterType(), this.oParentJavaModel.getModelJavaGetterFunctions().get(n).getJavaFunctionName());
			}
			else{
				if(this.oParentJavaModel.getModelJavaGetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyName().equalsIgnoreCase("linklist")){
					this.strClassFunctions = String.format("%s%spublic List<%s> %s(){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getModelJavaGetterFunctions().get(n).getReturnParameter().getParameterType(), this.oParentJavaModel.getModelJavaGetterFunctions().get(n).getJavaFunctionName());
				}
				else{
					this.strClassFunctions = String.format("%s%spublic Set<%s> %s(){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getModelJavaGetterFunctions().get(n).getReturnParameter().getParameterType(), this.oParentJavaModel.getModelJavaGetterFunctions().get(n).getJavaFunctionName());
				}
			}
			this.strClassFunctions = String.format("%s%sreturn this.%s;\n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), this.oParentJavaModel.getModelJavaGetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyName());
			this.strClassFunctions = String.format("%s%s}\n\n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		}

		for(int n = 0; n < this.oParentJavaModel.getModelJavaSetterFunctions().size(); n++){
			if(this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyUniqueness()){
				this.strClassFunctions = String.format("%s%spublic void %s(%s %s){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getJavaFunctionName(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getFunctionParameters().get(0).getParameterType(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getFunctionParameters().get(0).getFunctionParameterName());
			}
			else{
				if(this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyName().equalsIgnoreCase("linklist")){
					this.strClassFunctions = String.format("%s%spublic void %s(List<%s> %s){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getJavaFunctionName(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getFunctionParameters().get(0).getParameterType(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getFunctionParameters().get(0).getFunctionParameterName());
				}
				else{
					if(((JavaResourceModelProperty)this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getAccessedProperty()).getElementCollectionHibernateAnnotation() == null){
						this.strClassFunctions = String.format("%s%s%s\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getXMLTransientJAXBAnnotation().getJAXBAnnotationText());
					}
					this.strClassFunctions = String.format("%s%spublic void %s(Set<%s> %s){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getJavaFunctionName(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getFunctionParameters().get(0).getParameterType(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getFunctionParameters().get(0).getFunctionParameterName());
				}
			}
			this.strClassFunctions = String.format("%s%sthis.%s = %s;\n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyName(), this.oParentJavaModel.getModelJavaSetterFunctions().get(n).getAccessedProperty().getPSMComponentPropertyName());
			this.strClassFunctions = String.format("%s%s}\n\n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		}
		
		addDeleteAllCollectionsFunction();
		
		return this.strClassFunctions;
	}

	@Override
	public String addClassTail() {
		this.strClassTail = "}";
		return this.strClassTail;
	}
	
	private void addDeleteAllCollectionsFunction(){
		this.strClassFunctions = String.format("%s%spublic void deleteAllCollections(Session hibernateSession){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		this.oJavaFileIdentation.increaseIdentation();
		if(hasCollectionTables()){
			this.strClassFunctions = String.format("%s%sQuery oQuery;\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		}
		for(int n = 0; n < this.oParentJavaModel.getJavaResourceModelProperties().size(); n++){
			if(this.oParentJavaModel.getJavaResourceModelProperties().get(n).getElementCollectionHibernateAnnotation() != null){
				this.strClassFunctions = String.format("%s%soQuery = hibernateSession.createSQLQuery(String.format(\"DELETE FROM %%s where %%sId = %%d\",\"%s%s\".toLowerCase(),\"%s\",this.get%sId()));\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getPIMParentResourceModel().getParentCIMResource().getResourceName(), this.oParentJavaModel.getJavaResourceModelProperties().get(n).getPSMComponentPropertyName(), this.oParentJavaModel.getPIMParentResourceModel().getParentCIMResource().getResourceName(), this.oParentJavaModel.getPIMParentResourceModel().getParentCIMResource().getResourceName());
				this.strClassFunctions = String.format("%s%soQuery.executeUpdate();\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
			}
		}
		this.strClassFunctions = String.format("%s%s\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
		for(int n = 0; n < this.oParentJavaModel.getRelatedJavaResourceModelManagers().size(); n++){
			this.strClassFunctions = String.format("%s%sIterator<%s> %sIterator = SetOf%s.iterator();\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getRelatedJavaResourceModelManagers().get(n).getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentJavaModel.getRelatedJavaResourceModelManagers().get(n).getRelatedJavaResourceModel().getJavaResourceModelName(), this.oParentJavaModel.getRelatedJavaResourceModelManagers().get(n).getRelatedJavaResourceModel().getJavaResourceModelName());
			this.strClassFunctions = String.format("%s%swhile(%sIterator.hasNext()){\n", this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaModel.getRelatedJavaResourceModelManagers().get(n).getRelatedJavaResourceModel().getJavaResourceModelName());
			this.strClassFunctions = String.format("%s%s%sIterator.next().deleteAllCollections(hibernateSession);\n", this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), this.oParentJavaModel.getRelatedJavaResourceModelManagers().get(n).getRelatedJavaResourceModel().getJavaResourceModelName());
			this.strClassFunctions = String.format("%s%s}\n\n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
		}
		this.strClassFunctions = String.format("%s%s}\n\n", this.strClassFunctions, this.oJavaFileIdentation.decreaseIdentation());
	}
	
	private boolean hasCollectionTables(){
		for(int n = 0; n < this.oParentJavaModel.getJavaResourceModelProperties().size(); n++){
			if(this.oParentJavaModel.getJavaResourceModelProperties().get(n).getElementCollectionHibernateAnnotation() != null){
				return true;
			}
		}
		return false;
	}
}