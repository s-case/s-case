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

package main.java.scase.pimGenerator;

import java.util.Iterator;

import main.java.scase.cimMetaModel.Resource;
import main.java.scase.cimMetaModel.SystemCIM;
import main.java.scase.pimMetaModel.AlgoResourceController;
import main.java.scase.pimMetaModel.AlgoResourceModel;
import main.java.scase.pimMetaModel.RDBMSTable;
import main.java.scase.pimMetaModel.RDBMSTableColumn;
import main.java.scase.pimMetaModel.ResourceController;
import main.java.scase.pimMetaModel.ResourceControllerManager;
import main.java.scase.pimMetaModel.ResourceModel;
import main.java.scase.pimMetaModel.ResourceModelManager;
import main.java.scase.pimMetaModel.SystemPIM;

public class CorePIMProducer extends APIMProducer{
	
	
	public CorePIMProducer(SystemCIM oSystemCIM){
		super(oSystemCIM);
	}
	
	public SystemPIM producePIM(){
		createPIMResources();
		transformCIMResources();
		createDatabase();
		createDatabaseController();
		createHypermedia();
		printPIM();
		
		return this.oSystemPIM;
	}
	
	private void createPIMResources(){
		createCRUDPIMResources();
		createAlgorithmicPIMResources();
	}
	
	private void createCRUDPIMResources(){
		Iterator<Resource> iteratorOfCIMResources = this.oSystemCIM.getListOfCIMResource().iterator();
		
		while(iteratorOfCIMResources.hasNext()){
			Resource oCurrentCIMResource = iteratorOfCIMResources.next();

			if(!oCurrentCIMResource.getResourceType()){//if this CIM resource is not algorithmic
				ResourceModel oNewResourceModel = new ResourceModel(String.format("%sModel",oCurrentCIMResource.getResourceName()), oCurrentCIMResource);
				oSystemPIM.getPIMResourceModels().add(oNewResourceModel); //then add one resource model to the PIM
				ResourceModelManager oNewResourceModelManager = new ResourceModelManager(String.format("%sModelManager",oCurrentCIMResource.getResourceName()), oCurrentCIMResource, oNewResourceModel); 
				oSystemPIM.getPIMResourceModelManagers().add(oNewResourceModelManager); //and one resource model manager
				ResourceController oNewResourceController = new ResourceController(String.format("%sController", oCurrentCIMResource.getResourceName()), oCurrentCIMResource, oNewResourceModel); 
				oSystemPIM.getPIMResourceControllers().add(oNewResourceController); //and one resource controller
				ResourceControllerManager oNewResourceControllerManager = new ResourceControllerManager(String.format("%sControllerManager", oCurrentCIMResource.getResourceName()), oCurrentCIMResource, oNewResourceModelManager, oNewResourceController);
				oSystemPIM.getPIMResourceControllerManagers().add(oNewResourceControllerManager); //and one resource controller manager
			}
		}
	}
	
	private void createAlgorithmicPIMResources(){
		Iterator<Resource> iteratorOfCIMResource = this.oSystemCIM.getListOfCIMResource().iterator();
		
		while(iteratorOfCIMResource.hasNext()){
			Resource oCurrentCIMResource = iteratorOfCIMResource.next();
			
			if(oCurrentCIMResource.getResourceType()){//if this resource is algorithmic
				AlgoResourceModel oNewAlgoResourceModel = new AlgoResourceModel(String.format("%sAlgoModel", oCurrentCIMResource.getResourceName()), oCurrentCIMResource);
				oSystemPIM.getPIMAlgoResourceModels().add(oNewAlgoResourceModel);
				AlgoResourceController oNewAlgoResourceController = new AlgoResourceController(String.format("%sAlgoController", oCurrentCIMResource.getResourceName()), oCurrentCIMResource, oNewAlgoResourceModel);
				oSystemPIM.getPIMAlgoResourceControllers().add(oNewAlgoResourceController);	
			}
		}
	}
	
	private void transformCIMResources(){
		transformCRUDCIMResources();
		transformAlgorithmicCIMResources();
	}
	
	private void transformCRUDCIMResources(){
		addResourceRelations();
		addCRUDResourceRepresentations();
		addResourceModelProperties();
		addResourceModelPrimaryIdentifier();
		createControllerURIs();
		addResourceControllerCRUDActivities();
		addCRUDActivityHandlers();
		addResourceModelLinkLists();
		addResourceModelPropertyAccessFunctions();
		addRepresentationUnmarshallingFunctions();
		addRepresentationMarshallingFunctions();
	}
	
	private void transformAlgorithmicCIMResources(){
		addAlgoResourceRelations();
		addAlgoResourceRepresentations();
		createAlgoControllerURIs();
		addAlgoResourceControllerCRUDActivities();
		addAlgoCRUDActivityHandlers();
		addAlgoResourceModelLinkLists();
		addAlgoResourceModelPropertyAccessFunctions();
		addAlgoRepresentationUnmarshallingFunctions();
		addAlgoRepresentationMarshallingFunctions();
	}
	
	private void printPIM(){
		oSystemPIM.printResourceModels();
		oSystemPIM.printResourceModelManagers();
		oSystemPIM.printResourceControllers();
		oSystemPIM.printResourceControllerManagers();
		oSystemPIM.printAlgoResourceModels();
		oSystemPIM.printAlgoResourceControllers();
		oSystemPIM.printRDBMSTables();
		oSystemPIM.printDatabaseController();
	}
	
	private void addCRUDResourceRepresentations(){
		addCRUDResourceInputRepresentations();
		addCRUDResourceOutputRepresentations();
	}
	
	private void addAlgoResourceRepresentations(){
		addAlgoResourceInputRepresentations();
		addAlgoResourceOutputRepresentations();
	}
	
	private void addCRUDResourceInputRepresentations(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModelManagers().size(); n++){
			this.oSystemPIM.getPIMResourceModelManagers().get(n).addResourceInputRepresentations();
		}
	}
	
	private void addCRUDResourceOutputRepresentations(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModelManagers().size(); n++){
			this.oSystemPIM.getPIMResourceModelManagers().get(n).addResourceOutputRepresentations();
		}
	}
	
	private void addAlgoResourceInputRepresentations(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceModels().size(); n++){
			this.oSystemPIM.getPIMAlgoResourceModels().get(n).addResourceInputRepresentations();
		}
	}
	
	private void addAlgoResourceOutputRepresentations(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceModels().size(); n++){
			this.oSystemPIM.getPIMAlgoResourceModels().get(n).addResourceOutputRepresentations();
		}
	}
	
	private void addResourceModelProperties(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModels().size(); n++){
			this.oSystemPIM.getPIMResourceModels().get(n).addResourceModelProperties();
		}
	}
	
	private void addResourceControllerCRUDActivities(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllerManagers().size(); n++){
			this.oSystemPIM.getPIMResourceControllerManagers().get(n).addCRUDActivities();
		}
	}
	
	private void addResourceRelations(){
		addResourceModelOutgoingRelations();
		addResourceModelManagerIncomingRelations();
	}
	
	private void addResourceModelOutgoingRelations(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModels().size(); n++){
			for(int i = 0; i < this.oSystemPIM.getPIMResourceModelManagers().size(); i++){ //add any related Resource Model Managers
				if(this.oSystemPIM.getPIMResourceModels().get(n).hasRelatedModelManager(this.oSystemPIM.getPIMResourceModelManagers().get(i))){
					this.oSystemPIM.getPIMResourceModels().get(n).getResourceModelRelatedResourceModelManagers().add(this.oSystemPIM.getPIMResourceModelManagers().get(i));
				}
			}
			
			for(int i = 0; i < this.oSystemPIM.getPIMAlgoResourceModels().size(); i++){ //add any related Algorithmic Models
				if(this.oSystemPIM.getPIMResourceModels().get(n).hasRelatedAlgoResourceModel(this.oSystemPIM.getPIMAlgoResourceModels().get(i))){
					this.oSystemPIM.getPIMResourceModels().get(n).getRelatedAlgoResourceModels().add(this.oSystemPIM.getPIMAlgoResourceModels().get(i));
				}
			}
		}
	}
	
	private void addResourceModelManagerIncomingRelations(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModels().size(); n++){ //add any incoming relations from Resource Models
			this.oSystemPIM.getPIMResourceModels().get(n).addIncomingRelationsToRelatedResourceModelManagers();
		}
		
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModelManagers().size(); n++){ //add any incoming relations from Algorithmic Resource Models
			for(int i = 0; i < this.oSystemPIM.getPIMAlgoResourceModels().size(); i++){
				if(this.oSystemPIM.getPIMResourceModelManagers().get(n).isRelatedOfAlgoResourceModel(this.oSystemPIM.getPIMAlgoResourceModels().get(i))){
					this.oSystemPIM.getPIMResourceModelManagers().get(n).getIncomingAlgoResourceModelRelations().add(this.oSystemPIM.getPIMAlgoResourceModels().get(i));
				}
			}
		}
	}
	
	private void addCRUDActivityHandlers(){
		addResourceControllerCRUDActivityHandlers();
		addResourceControllerManagersCRUDActivityHandlers();
	}
	
	private void addResourceControllerCRUDActivityHandlers(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllers().size(); n++){
			this.oSystemPIM.getPIMResourceControllers().get(n).addCRUDActivityHandlers();
		}
	}
	
	private void addResourceControllerManagersCRUDActivityHandlers(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllerManagers().size(); n++){
			this.oSystemPIM.getPIMResourceControllerManagers().get(n).addCRUDActivityHandlers();
		}
	}
	
	private void addResourceModelPrimaryIdentifier(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModels().size(); n++){
			this.oSystemPIM.getPIMResourceModels().get(n).addPrimaryIdentifier();
		}
	}
	
	private void createDatabase(){
		createRDBMSTables();
		addTableColumns();
		addTablePrimaryKeys();
		addTableForeignKeys();
	}
	
	private void createRDBMSTables(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModels().size(); n++){
			this.oSystemPIM.getPIMRDBMSTables().add(new RDBMSTable(String.format("%sTable", this.oSystemPIM.getPIMResourceModels().get(n).getParentCIMResource().getResourceName()), this.oSystemPIM.getPIMResourceModels().get(n)));
		}
	}
	
	private void addTableColumns(){
		for(int n = 0; n < this.oSystemPIM.getPIMRDBMSTables().size(); n++){
			for(int i = 0; i < this.oSystemPIM.getPIMRDBMSTables().get(n).getRDBMSParentResource().getResourceModelProperties().size(); i++){
				if(this.oSystemPIM.getPIMRDBMSTables().get(n).getRDBMSParentResource().getResourceModelProperties().get(i).getParentProperty() != null){ //if this property has its roots at CIM
					this.oSystemPIM.getPIMRDBMSTables().get(n).getTableColumns().add(new RDBMSTableColumn(this.oSystemPIM.getPIMRDBMSTables().get(n).getRDBMSParentResource().getResourceModelProperties().get(i),false));
				}
			}
		}
	}
	
	private void addTablePrimaryKeys(){
		for(int n = 0; n < this.oSystemPIM.getPIMRDBMSTables().size(); n++){
			for(int i = 0; i < this.oSystemPIM.getPIMRDBMSTables().get(n).getRDBMSParentResource().getResourceModelProperties().size(); i++){
				if(this.oSystemPIM.getPIMRDBMSTables().get(n).getRDBMSParentResource().getResourceModelProperties().get(i).getResourceModelIdentifyingAbility() == true){ //if this property is primary identifier
					this.oSystemPIM.getPIMRDBMSTables().get(n).getTableColumns().add(new RDBMSTableColumn(this.oSystemPIM.getPIMRDBMSTables().get(n).getRDBMSParentResource().getResourceModelProperties().get(i),false));
				}
			}
		}
	}
	
	private void addTableForeignKeys(){
		for(int n = 0; n < this.oSystemPIM.getPIMRDBMSTables().size(); n++){
			for(int i = 0; i < this.oSystemPIM.getPIMRDBMSTables().get(n).getRDBMSParentResource().getResourceModelRelatedResourceModelManagers().size(); i++){
				this.oSystemPIM.getPIMRDBMSTables().get(findRelatedTable(this.oSystemPIM.getPIMRDBMSTables().get(n).getRDBMSParentResource(),i)).getTableColumns().add(new RDBMSTableColumn(this.oSystemPIM.getPIMRDBMSTables().get(n).getPrimaryKeyColumn()));
			}
		}
		normalizeForeignKeyNames();
	}
	
	private void normalizeForeignKeyNames(){
		for(int n = 0; n < this.oSystemPIM.getPIMRDBMSTables().size(); n++){
			this.oSystemPIM.getPIMRDBMSTables().get(n).normalizeForeignKeyName();
		}
	}
	
	private int findRelatedTable(ResourceModel oResourceModel, int iRelatedResourceModelManagerIndex){
		for(int n = 0; n < this.oSystemPIM.getPIMRDBMSTables().size(); n++){
			if(this.oSystemPIM.getPIMRDBMSTables().get(n).getRDBMSParentResource().getResourceModelId() == oResourceModel.getResourceModelRelatedResourceModelManagers().get(iRelatedResourceModelManagerIndex).getRelatedResourceModel().getResourceModelId()){
				return n;
			}
		}
		
		try {
			throw new Exception("Failed to find related table!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private void createDatabaseController(){
		addResourceControllerRDBMSActivities();
		addResourceControllerManagerRDBMSActivities();
	}
	
	private void addResourceControllerRDBMSActivities(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllers().size(); n++){
			this.oSystemPIM.getPIMResourceControllers().get(n).addRDBMSActivities();
		}
	}
	
	private void addResourceControllerManagerRDBMSActivities(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllerManagers().size(); n++){
			this.oSystemPIM.getPIMResourceControllerManagers().get(n).addRDBMSActivities();
		}
	}
	
	private void addResourceModelLinkLists(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModelManagers().size(); n++){
			this.oSystemPIM.getPIMResourceModelManagers().get(n).addLinkListProperty();
		}
		
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModels().size(); n++){
			this.oSystemPIM.getPIMResourceModels().get(n).addLinkListProperty();
		}
	}
	
	private void addResourceModelPropertyAccessFunctions(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModelManagers().size(); n++){
			this.oSystemPIM.getPIMResourceModelManagers().get(n).addPropertyAccessFunctions();
		}
		
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModels().size(); n++){
			this.oSystemPIM.getPIMResourceModels().get(n).addPropertyAccessFunctions();
		}
	}
	
	private void addRepresentationUnmarshallingFunctions(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModelManagers().size(); n++){
			this.oSystemPIM.getPIMResourceModelManagers().get(n).addRepresentationUnmarshallingFunctions();
		}
		
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModels().size(); n++){
			this.oSystemPIM.getPIMResourceModels().get(n).addRepresentationUnmarshallingFunctions();
		}
	}
	
	private void addRepresentationMarshallingFunctions(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModelManagers().size(); n++){
			this.oSystemPIM.getPIMResourceModelManagers().get(n).addRepresentationMarshallingFunctions();
		}
		
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModels().size(); n++){
			this.oSystemPIM.getPIMResourceModels().get(n).addRepresentationMarshallingFunctions();
		}
	}
	
	private void addAlgoResourceControllerCRUDActivities(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceControllers().size(); n++){
			this.oSystemPIM.getPIMAlgoResourceControllers().get(n).addCRUDActivities();
		}
	}
	
	private void addAlgoResourceRelations(){
		addAlgoResourceIncomingRelations();
		addAlgoResourceOutgoingRelations();
	}
	
	private void addAlgoResourceIncomingRelations(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModels().size(); n++){ //add any incoming relations from Resource Models
			this.oSystemPIM.getPIMResourceModels().get(n).addIncomingRelationsToRelatedAlgoResourceModel();
		}
		
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceModels().size(); n++){ //add any incoming relations from Algorithmic Resource Models
			for(int i = 0; i < this.oSystemPIM.getPIMAlgoResourceModels().size(); i++){
				if(this.oSystemPIM.getPIMAlgoResourceModels().get(n).isRelatedOfAlgoResourceModel(this.oSystemPIM.getPIMAlgoResourceModels().get(i))){
					this.oSystemPIM.getPIMAlgoResourceModels().get(n).getIncomingAlgoResourceModelRelations().add(this.oSystemPIM.getPIMAlgoResourceModels().get(i));
				}
			}
		}
	}
	
	private void addAlgoResourceOutgoingRelations(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceModels().size(); n++){
			for(int i = 0; i < this.oSystemPIM.getPIMResourceModelManagers().size(); i++){ //add any related Resource Model Managers
				if(this.oSystemPIM.getPIMAlgoResourceModels().get(n).hasRelatedModelManager(this.oSystemPIM.getPIMResourceModelManagers().get(i))){
					this.oSystemPIM.getPIMAlgoResourceModels().get(n).getAlgoResourceModelRelatedResourceModelManagers().add(this.oSystemPIM.getPIMResourceModelManagers().get(i));
				}
			}
			
			for(int i = 0; i < this.oSystemPIM.getPIMAlgoResourceModels().size(); i++){ //add any related Algorithmic Models
				if(this.oSystemPIM.getPIMAlgoResourceModels().get(n).hasRelatedAlgoResourceModel(this.oSystemPIM.getPIMAlgoResourceModels().get(i))){
					this.oSystemPIM.getPIMAlgoResourceModels().get(n).getRelatedAlgoResourceModels().add(this.oSystemPIM.getPIMAlgoResourceModels().get(i));
				}
			}
		}
	}
	
	private void addAlgoCRUDActivityHandlers(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceControllers().size(); n++){
			this.oSystemPIM.getPIMAlgoResourceControllers().get(n).addCRUDActivityHandlers();
		}
	}
	
	private void addAlgoResourceModelLinkLists(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceModels().size(); n++){
			this.oSystemPIM.getPIMAlgoResourceModels().get(n).addLinkListProperty();
		}
	}
	
	private void addAlgoResourceModelPropertyAccessFunctions(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceModels().size(); n++){
			this.oSystemPIM.getPIMAlgoResourceModels().get(n).addPropertyAccessFunctions();
		}
	}
	
	private void addAlgoRepresentationUnmarshallingFunctions(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceModels().size(); n++){
			this.oSystemPIM.getPIMAlgoResourceModels().get(n).addRepresentationUnmarshallingFunctions();
		}
	}
	
	private void addAlgoRepresentationMarshallingFunctions(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceModels().size(); n++){
			this.oSystemPIM.getPIMAlgoResourceModels().get(n).addRepresentationMarshallingFunctions();
		}
	}
	
	private void createControllerURIs(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllerManagers().size(); n++){
			if(!this.oSystemPIM.getPIMResourceControllerManagers().get(n).hasIncomingRelations()){ //check if this controller manager belongs to a resource that is not related resource of any other
				this.oSystemPIM.getPIMResourceControllerManagers().get(n).setResourceControllerManagerURI(String.format("/%s", this.oSystemPIM.getPIMResourceControllerManagers().get(n).getParentCIMResource().getResourceName())); //create the URI of the resource controller manager
				this.oSystemPIM.getPIMResourceControllerManagers().get(n).createAssociatedResourceControllerURI(); // create the URI of the associated resource controller
				
			}
			else if(this.oSystemPIM.getPIMResourceControllerManagers().get(n).hasUniqueIncomingRelation()){ //else if it is related resource of only one other resource
				this.oSystemPIM.getPIMResourceControllerManagers().get(n).setResourceControllerManagerURI(String.format("/%s/{%s}/%s", this.oSystemPIM.getPIMResourceControllerManagers().get(n).getAssociatedResourceModelManager().getIncomingResourceModelRelations().get(0).getParentCIMResource().getResourceName(), this.oSystemPIM.getPIMResourceControllerManagers().get(n).getAssociatedResourceModelManager().getIncomingResourceModelRelations().get(0).getModelPrimaryIdentifierName(), this.oSystemPIM.getPIMResourceControllerManagers().get(n).getParentCIMResource().getResourceName()));
				this.oSystemPIM.getPIMResourceControllerManagers().get(n).createAssociatedResourceControllerURI();
			}
			else{ //else it is related of more than one other resources
				this.oSystemPIM.getPIMResourceControllerManagers().get(n).setResourceControllerManagerURI(String.format("/multi%sManager", this.oSystemPIM.getPIMResourceControllerManagers().get(n).getParentCIMResource().getResourceName()));
				this.oSystemPIM.getPIMResourceControllerManagers().get(n).getAssociatedResourceController().setResourceControllerURI(String.format("/multi%s", this.oSystemPIM.getPIMResourceControllerManagers().get(n).getParentCIMResource().getResourceName()));
			}
			
		}
	}
	
	private void createAlgoControllerURIs(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceControllers().size(); n++){
			this.oSystemPIM.getPIMAlgoResourceControllers().get(n).setAlgoResourceControllerURI(String.format("/algo%s", this.oSystemPIM.getPIMAlgoResourceControllers().get(n).getParentCIMResource().getResourceName()));
		}
	}
	
	private void createHypermedia(){
		createParentHypermediaLinks();
		createSiblingHypermediaLinks();
		createChildrenHypermediaLinks();
	}
	
	private void createParentHypermediaLinks(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllerManagers().size(); n++){
			if( this.oSystemPIM.getPIMResourceControllerManagers().get(n).hasIncomingRelations()){
				for(int i = 0; i < this.oSystemPIM.getPIMResourceControllerManagers().get(n).getAssociatedResourceModelManager().getIncomingResourceModelRelations().size(); i++){
					this.oSystemPIM.getPIMResourceControllerManagers().get(n).createSpecificParentHypermediaLinks(this.oSystemPIM.getPIMResourceControllers().get(findRelatedControllerOf(this.oSystemPIM.getPIMResourceControllerManagers().get(n).getAssociatedResourceModelManager().getIncomingResourceModelRelations().get(i))));
				}
			}
			
			//also create the parent hypermedia links of the associated resource controller
			
			this.oSystemPIM.getPIMResourceControllerManagers().get(n).addAssociatedResourceControllerParentHypermediaLinks();
		}
		
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceControllers().size(); n++){
			if( this.oSystemPIM.getPIMAlgoResourceControllers().get(n).hasIncomingRelations()){
				for(int i = 0; i < this.oSystemPIM.getPIMAlgoResourceControllers().get(n).getAssociatedAlgoResourceModel().getIncomingResourceModelRelations().size(); i++){
					this.oSystemPIM.getPIMAlgoResourceControllers().get(n).createSpecificParentHypermediaLinks(this.oSystemPIM.getPIMResourceControllers().get(findRelatedControllerOf(this.oSystemPIM.getPIMAlgoResourceControllers().get(n).getAssociatedAlgoResourceModel().getIncomingResourceModelRelations().get(i))));
				}
			}
		}
	}
	
	private void createSiblingHypermediaLinks(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllers().size(); n++){
			this.oSystemPIM.getPIMResourceControllers().get(n).createSiblingHypermediaLinks();
		}
		
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllerManagers().size(); n++){
			this.oSystemPIM.getPIMResourceControllerManagers().get(n).createSiblingHypermediaLinks();
		}
		
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceControllers().size(); n++){
			this.oSystemPIM.getPIMAlgoResourceControllers().get(n).createSiblingHypermediaLinks();
		}
	}
	
	private void createChildrenHypermediaLinks(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllers().size(); n++){
			//add any hypermedia links to related resource controller managers of this resource controller
			for(int i = 0; i < this.oSystemPIM.getPIMResourceControllers().get(n).getAssociatedResourceModel().getResourceModelRelatedResourceModelManagers().size(); i++){
				this.oSystemPIM.getPIMResourceControllers().get(n).createSpecificChildHypermediaLinks(this.oSystemPIM.getPIMResourceControllerManagers().get(findRelatedControllerOf(this.oSystemPIM.getPIMResourceControllers().get(n).getAssociatedResourceModel().getResourceModelRelatedResourceModelManagers().get(i))));
			}
			
			//add any hypermedia links to related algorithmic resource controllers of this resource controller
			for(int i = 0; i < this.oSystemPIM.getPIMResourceControllers().get(n).getAssociatedResourceModel().getRelatedAlgoResourceModels().size(); i++){
				this.oSystemPIM.getPIMResourceControllers().get(n).createSpecificChildHypermediaLinks(this.oSystemPIM.getPIMAlgoResourceControllers().get(findRelatedControllerOf(this.oSystemPIM.getPIMResourceControllers().get(n).getAssociatedResourceModel().getRelatedAlgoResourceModels().get(i))));
			}
		}
		
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllerManagers().size(); n++){
			this.oSystemPIM.getPIMResourceControllerManagers().get(n).createChildrenHypermediaLinks();
		}
	}
	
	private int findRelatedControllerOf(ResourceModel oResourceModel){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllers().size(); n++){
			if(this.oSystemPIM.getPIMResourceControllers().get(n).getAssociatedResourceModel().getResourceModelId() == oResourceModel.getResourceModelId()){
				return n;
			}
		}
		
		try {
			throw new Exception("PIM is corrupted! Could not found the resource controller of a resource model!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private int findRelatedControllerOf(ResourceModelManager oResourceModelManager){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllerManagers().size(); n++){
			if(this.oSystemPIM.getPIMResourceControllerManagers().get(n).getAssociatedResourceModelManager().getResourceModelManagerId() == oResourceModelManager.getResourceModelManagerId()){
				return n;
			}
		}
		
		try {
			throw new Exception("PIM is corrupted! Could not found the resource controller manager of a resource model manager!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private int findRelatedControllerOf(AlgoResourceModel oAlgoResourceModel){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceControllers().size(); n++){
			if(this.oSystemPIM.getPIMAlgoResourceControllers().get(n).getAssociatedAlgoResourceModel().getAlgoResourceModelId() == oAlgoResourceModel.getAlgoResourceModelId()){
				return n;
			}
		}
		
		try {
			throw new Exception("PIM is corrupted! Could not found the algorithmic resource controller of an algorithmic resource model manager!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}