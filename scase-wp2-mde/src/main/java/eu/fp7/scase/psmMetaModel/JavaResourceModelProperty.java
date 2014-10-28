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

package main.java.scase.psmMetaModel;

import main.java.scase.pimMetaModel.ResourceModelProperty;

public class JavaResourceModelProperty extends PSMComponentProperty{
	
	private boolean bIsNamingProperty;
	private boolean bIsPrimaryIdentifier;
	private ResourceModelProperty oParentPIMResourceModelProperty;
	private HibernateAnnotation oIdHibernateAnnotation;
	private HibernateAnnotation oGeneratedValueHibernateAnnotation;
	private HibernateAnnotation oColumnHibernateAnnotation;
	private HibernateAnnotation oOneToManyHibernateAnnotation;
	private HibernateAnnotation oOnDeleteHibernateAnnotation;
	private HibernateAnnotation oManyToOneHibernateAnnotation;
	private HibernateAnnotation oJoinColumnHibernateAnnotation;
	private HibernateAnnotation oForeignKeyHibernateAnnotation;
	private HibernateAnnotation oTransientHibernateAnnotation;
	private HibernateAnnotation oElementCollectionHibernateAnnotation;
	private HibernateAnnotation oCollectionTableHibernateAnnotation;
	private JavaResourceModel oParentResourceModel;
	
	public JavaResourceModelProperty(ResourceModelProperty oResourceModelProperty, JavaResourceModel oParentResourceModel){
		super(oResourceModelProperty);
		this.oParentPIMResourceModelProperty = oResourceModelProperty;
		this.bIsNamingProperty = oResourceModelProperty.getResourceModelPropertyNamingAbility();
		this.bIsPrimaryIdentifier = oResourceModelProperty.getResourceModelIdentifyingAbility();
		this.oParentResourceModel = oParentResourceModel;
		addHibernatePropertyAnnotations();
	}
	
	public JavaResourceModelProperty(String strJavaPropertyName, String strJavaPropertyType, String strHibernateRelationPropertyName){
		super(strJavaPropertyName, strJavaPropertyType, false);
		this.bIsNamingProperty = false;
		this.bIsPrimaryIdentifier = false;
		this.oOneToManyHibernateAnnotation = new HibernateAnnotation(String.format("@OneToMany(fetch = FetchType.EAGER, mappedBy=\"O%s\",orphanRemoval=true)", strHibernateRelationPropertyName));
		this.oOnDeleteHibernateAnnotation = new HibernateAnnotation("@OnDelete(action=OnDeleteAction.CASCADE)");
	}
	
	public JavaResourceModelProperty(String strJavaPropertyName, String strJavaPropertyType, String strIncomingJavaModelPrimardyIdentifierName, String strForeignKeyName){
		super(strJavaPropertyName, strJavaPropertyType, true);
		this.bIsNamingProperty = false;
		this.bIsPrimaryIdentifier = false;
		this.oManyToOneHibernateAnnotation = new HibernateAnnotation("@ManyToOne(fetch = FetchType.EAGER)");
		this.oJoinColumnHibernateAnnotation = new HibernateAnnotation(String.format("@JoinColumn(name=\"%s\")", strIncomingJavaModelPrimardyIdentifierName));
		this.oForeignKeyHibernateAnnotation = new HibernateAnnotation(String.format("@ForeignKey(name = \"%s\")", strForeignKeyName));
	}
	
	public boolean getJavaModelPropertyNamingAbility(){
		return this.bIsNamingProperty;
	}
	
	public boolean getJavaModelPropertyIdentifyingAbility(){
		return this.bIsPrimaryIdentifier;
	}
	
	public ResourceModelProperty getParentPIMResourceModelProeprty(){
		return this.oParentPIMResourceModelProperty;
	}
	
	public HibernateAnnotation getIdHibernateAnnotation(){
		return this.oIdHibernateAnnotation;
	}
	
	public HibernateAnnotation getGeneratedValueHibernateAnnotation(){
		return this.oGeneratedValueHibernateAnnotation;
	}
	
	public HibernateAnnotation getColumnHibernateAnnotation(){
		return this.oColumnHibernateAnnotation;
	}
	
	public HibernateAnnotation getOnDeleteHibernateAnnotation(){
		return this.oOnDeleteHibernateAnnotation;
	}
	
	public HibernateAnnotation getOneToManyHibernateAnnotation(){
		return this.oOneToManyHibernateAnnotation;
	}
	
	public HibernateAnnotation getManyToOneHibernateAnnotation(){
		return this.oManyToOneHibernateAnnotation;
	}
	
	public HibernateAnnotation getJoinColumnHibernateAnnotation(){
		return this.oJoinColumnHibernateAnnotation;
	}
	
	public HibernateAnnotation getForeignKeyHibernateAnnotation(){
		return this.oForeignKeyHibernateAnnotation;
	}
	
	public HibernateAnnotation getTransientHibernateAnnotation(){
		return this.oTransientHibernateAnnotation;
	}
	
	public HibernateAnnotation getElementCollectionHibernateAnnotation(){
		return this.oElementCollectionHibernateAnnotation;
	}
	
	public HibernateAnnotation getCollectionTableHibernateAnnotation(){
		return this.oCollectionTableHibernateAnnotation;
	}
	
	private void addHibernatePropertyAnnotations(){
		if(this.bIsPrimaryIdentifier){
			this.oIdHibernateAnnotation = new HibernateAnnotation("@Id");
			this.oGeneratedValueHibernateAnnotation = new HibernateAnnotation("@GeneratedValue");
		}
		
		if(isEligibleForDBColumn()){
			this.oColumnHibernateAnnotation = new HibernateAnnotation(String.format("@Column(name = \"%s\")", this.getPSMComponentPropertyName().toLowerCase()));
			if( !this.getPSMComponentPropertyUniqueness() ){
				this.oElementCollectionHibernateAnnotation = new HibernateAnnotation("@ElementCollection(fetch = FetchType.EAGER)");
				this.oCollectionTableHibernateAnnotation = new HibernateAnnotation(String.format("@CollectionTable(name=\"%s%s\", joinColumns=@JoinColumn(name=\"%s\"))", this.oParentResourceModel.getPIMParentResourceModel().getParentCIMResource().getResourceName().toLowerCase() ,this.oParentPIMResourceModelProperty.getParentProperty().getPropertyName().toLowerCase(), this.oParentResourceModel.getPIMParentResourceModel().getModelPrimaryIdentifierName().toLowerCase()));
				this.oForeignKeyHibernateAnnotation = new HibernateAnnotation(String.format("@ForeignKey(name = \"fk_%s_%s\")", this.oParentResourceModel.getPIMParentResourceModel().getParentCIMResource().getResourceName().toLowerCase(), this.oParentPIMResourceModelProperty.getPIMComponentProeprtyName().toLowerCase()));
			}
		}
		
		if(this.getPSMComponentPropertyName().equalsIgnoreCase("linkList")){
			this.oTransientHibernateAnnotation = new HibernateAnnotation("@Transient");
		}
	}
		
	private boolean isHibernateRelationColumn(){
		if(this.oParentPIMResourceModelProperty == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	private boolean isEligibleForDBColumn(){
		if(this.bIsPrimaryIdentifier){
			return true;
		}
		else if(this.getParentPIMResourceModelProeprty() != null){
			if(this.getParentPIMResourceModelProeprty().getParentProperty() != null){
				return true;
			}
		}
		
		return false;
	}
	
	public void printJavaResourceModelProperty(){
		super.printPSMComponentProperty();
		System.out.println("Java Property naming ability: " + (this.bIsNamingProperty == true ? "It is naming property" : "It is not naming property"));
		System.out.println("Java Property identifying ability: " + (this.bIsPrimaryIdentifier == true? "It is primary identifier" : "It is not primary identifier"));
		printHibernatePropertyAnnotations();
	}
	
	private void printHibernatePropertyAnnotations(){
		if(this.bIsPrimaryIdentifier){
			System.out.println("Hibernate Annotation: " + this.oIdHibernateAnnotation.getHibernateAnnotationText());
			System.out.println("Hibernate Annotation: " + this.oGeneratedValueHibernateAnnotation.getHibernateAnnotationText());
		}
		
		if(this.isEligibleForDBColumn()){
			System.out.println("Hibernate Annotation: " + this.oColumnHibernateAnnotation.getHibernateAnnotationText());
		}
		
		if(this.isHibernateRelationColumn()){
			if( oOneToManyHibernateAnnotation != null){
				System.out.println("Hibernate Annotation: " + this.oOneToManyHibernateAnnotation.getHibernateAnnotationText());
			}
			if( oOnDeleteHibernateAnnotation != null){
				System.out.println("Hibernate Annotation: " + this.oOnDeleteHibernateAnnotation.getHibernateAnnotationText());
			}
			if( oManyToOneHibernateAnnotation != null ){
				System.out.println("Hibernate Annotation: " + this.oManyToOneHibernateAnnotation.getHibernateAnnotationText());
			}
			if( oJoinColumnHibernateAnnotation != null){
				System.out.println("Hibernate Annotation: " + this.oJoinColumnHibernateAnnotation.getHibernateAnnotationText());
			}
		}
		
		if( this.oElementCollectionHibernateAnnotation != null){
			System.out.println("Hibernate Annotation: " + this.oElementCollectionHibernateAnnotation.getHibernateAnnotationText());
		}
		
		if( this.oCollectionTableHibernateAnnotation != null){
			System.out.println("Hibernate Annotation: " + this.oCollectionTableHibernateAnnotation.getHibernateAnnotationText());
		}
		
		if( oForeignKeyHibernateAnnotation != null){
			System.out.println("Hibernate Annotation: " + this.oForeignKeyHibernateAnnotation.getHibernateAnnotationText());
		}
		
		if(this.oTransientHibernateAnnotation != null){
			System.out.println("Hibenate Annotation: " + this.oTransientHibernateAnnotation.getHibernateAnnotationText());
		}
	}
}