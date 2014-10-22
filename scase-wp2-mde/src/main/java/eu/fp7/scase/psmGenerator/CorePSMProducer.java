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

package eu.fp7.scase.psmGenerator;

import eu.fp7.scase.pimMetaModel.CRUDActivityHandler;
import eu.fp7.scase.pimMetaModel.FunctionParameter;
import eu.fp7.scase.pimMetaModel.SystemPIM;
import eu.fp7.scase.psmMetaModel.HibernateController;
import eu.fp7.scase.psmMetaModel.JavaAlgoResourceController;
import eu.fp7.scase.psmMetaModel.JavaAlgoResourceModel;
import eu.fp7.scase.psmMetaModel.JavaResourceController;
import eu.fp7.scase.psmMetaModel.JavaResourceControllerManager;
import eu.fp7.scase.psmMetaModel.JavaResourceModel;
import eu.fp7.scase.psmMetaModel.JavaResourceModelManager;
import eu.fp7.scase.psmMetaModel.SystemPSM;

public class CorePSMProducer extends APSMProducer
{

	public CorePSMProducer( SystemPIM oSystemPIM){
		super(oSystemPIM);
	}

	@Override
    public SystemPSM producePSM(){

		createPSMResources();
		transformPIMResources();
		transformAlgoResources();
		createDatabaseController();
		this.oSystemPSM.printPSM();

		return this.oSystemPSM;
	}

	private void createPSMResources(){
		createResourceModels();
		createResourceModelManagers();
		createResourceControllerManagers();
		createResourceControllers();
		createAlgoModels();
		createAlgoControllers();
		coupleControllersWithModels();
		coupleManagersWithModels();
	}

	private void transformPIMResources(){
		addResourceModelProperties();
		addResourceModelManagerProperties();
		addResourceControllerHTTPActivities();
		addResourceControllerManagerHTTPActivities();
		addPSMResourceRelations();
		addHTTPActivityHandlers();
		addHibernateRelationProperties();
		addAccessFunctions();
		addJAXRSInputOutputAnnotations();
		transformHypermediaLinks();
	}

	private void transformAlgoResources(){
		addAlgoModelProperties();
		addAlgoControllerHTTPActivities();
		addAlgoResourceRelations();
		addAlgoHTTPActivityHandlers();
		addAlgoAccessFunctions();
		addAlgoJAXRSInputOutputAnnotations();
		transformAlgoHypermediaLinks();
	}

	private void createResourceModels(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModels().size(); n++){
			this.oSystemPSM.getPSMResourceModels().add(new JavaResourceModel(this.oSystemPIM.getPIMResourceModels().get(n)));
		}
	}

	private void createResourceModelManagers(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceModelManagers().size(); n++){
			this.oSystemPSM.getPSMResourceModelManagers().add(new JavaResourceModelManager(this.oSystemPIM.getPIMResourceModelManagers().get(n)));
		}
	}

	private void createResourceControllerManagers(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllerManagers().size(); n++){
			this.oSystemPSM.getPSMResourceControllerManagers().add(new JavaResourceControllerManager(this.oSystemPIM.getPIMResourceControllerManagers().get(n)));
		}
	}

	private void createResourceControllers(){
		for(int n = 0; n < this.oSystemPIM.getPIMResourceControllers().size(); n++){
			this.oSystemPSM.getPSMResourceControllers().add(new JavaResourceController(this.oSystemPIM.getPIMResourceControllers().get(n)));
		}
	}

	private void addResourceModelProperties(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModels().size(); n++){
			this.oSystemPSM.getPSMResourceModels().get(n).addJavaResourceModelProperties();
		}
	}

	private void addResourceModelManagerProperties(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModelManagers().size(); n++){
			this.oSystemPSM.getPSMResourceModelManagers().get(n).addJavaResourceModelManagerProperties();
		}
	}

	private void addResourceControllerHTTPActivities(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllers().size(); n++){
			this.oSystemPSM.getPSMResourceControllers().get(n).addHTTPActivities();
		}
	}

	private void addResourceControllerManagerHTTPActivities(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllerManagers().size(); n++){
			this.oSystemPSM.getPSMResourceControllerManagers().get(n).addHTTPActivities();
		}
	}

	private void createAlgoModels(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceModels().size(); n++){
			this.oSystemPSM.getPSMAlgoResourceModels().add(new JavaAlgoResourceModel(this.oSystemPIM.getPIMAlgoResourceModels().get(n)));
		}
	}

	private void createAlgoControllers(){
		for(int n = 0; n < this.oSystemPIM.getPIMAlgoResourceControllers().size(); n++){
			this.oSystemPSM.getPSMAlgoResourceControllers().add(new JavaAlgoResourceController(this.oSystemPIM.getPIMAlgoResourceControllers().get(n)));
		}
	}

	private void coupleControllersWithModels(){
		for(int n = 0; n < this.oSystemPSM.getPSMAlgoResourceControllers().size(); n++){
			for(int i = 0; i < this.oSystemPSM.getPSMAlgoResourceModels().size(); i++){
				if(this.oSystemPSM.getPSMAlgoResourceControllers().get(n).getParentPIMAlgoResourceController().getParentCIMResource().getResourceId() == this.oSystemPSM.getPSMAlgoResourceModels().get(i).getParentPIMAlgoResourceModel().getParentCIMResource().getResourceId()){
					this.oSystemPSM.getPSMAlgoResourceControllers().get(n).setRelatedAlgoResourceModel(this.oSystemPSM.getPSMAlgoResourceModels().get(i));
					break;
				}
			}
		}

		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllers().size(); n++){
			for(int i = 0; i < this.oSystemPSM.getPSMResourceModels().size(); i++){
				if(this.oSystemPSM.getPSMResourceControllers().get(n).getPIMParentResourceController().getParentCIMResource().getResourceId() == this.oSystemPSM.getPSMResourceModels().get(i).getPIMParentResourceModel().getParentCIMResource().getResourceId()){
					this.oSystemPSM.getPSMResourceControllers().get(n).setRelatedJavaResourceModel(this.oSystemPSM.getPSMResourceModels().get(i));
					break;
				}
			}
		}

		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllerManagers().size(); n++){
			for(int i = 0; i < this.oSystemPSM.getPSMResourceModelManagers().size(); i++){
				if(this.oSystemPSM.getPSMResourceControllerManagers().get(n).getPIMParentResourceControllerManager().getParentCIMResource().getResourceId() == this.oSystemPSM.getPSMResourceModelManagers().get(i).getPIMParentResourceModelManager().getParentCIMResource().getResourceId()){
					this.oSystemPSM.getPSMResourceControllerManagers().get(n).setRelatedJavaResourceModelManager(this.oSystemPSM.getPSMResourceModelManagers().get(i));
					break;
				}
			}
		}
	}

	private void coupleManagersWithModels(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModelManagers().size(); n++){
			for(int i = 0; i < this.oSystemPSM.getPSMResourceModels().size(); i++){
				if(this.oSystemPSM.getPSMResourceModelManagers().get(n).getPIMParentResourceModelManager().getParentCIMResource().getResourceId() == this.oSystemPSM.getPSMResourceModels().get(i).getPIMParentResourceModel().getParentCIMResource().getResourceId()){
					this.oSystemPSM.getPSMResourceModelManagers().get(n).setRelatedJavaResourceModel(this.oSystemPSM.getPSMResourceModels().get(i));
				}
			}
		}

		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllerManagers().size(); n++){
			for(int i = 0; i < this.oSystemPSM.getPSMResourceControllers().size(); i++){
				if(this.oSystemPSM.getPSMResourceControllerManagers().get(n).getPIMParentResourceControllerManager().getParentCIMResource().getResourceId() == this.oSystemPSM.getPSMResourceControllers().get(i).getPIMParentResourceController().getParentCIMResource().getResourceId()){
					this.oSystemPSM.getPSMResourceControllerManagers().get(n).setAssociatedJavaResourceController(this.oSystemPSM.getPSMResourceControllers().get(i));
				}
			}
		}
	}

	private void addPSMResourceRelations(){
		addJavaResourceModelOutgoingRelations();
		addJavaResourceModelManagerIncomingRelations();
	}

	private void addJavaResourceModelOutgoingRelations(){
		//add outgoing relations towards other java resource model managers
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModels().size(); n++){
			for(int i = 0; i < this.oSystemPSM.getPSMResourceModelManagers().size(); i++){
				if(this.oSystemPSM.getPSMResourceModels().get(n).hasRelatedJavaResourceModelManager(this.oSystemPSM.getPSMResourceModelManagers().get(i))){
					this.oSystemPSM.getPSMResourceModels().get(n).getRelatedJavaResourceModelManagers().add(this.oSystemPSM.getPSMResourceModelManagers().get(i));
				}
			}

			//add outgoing relations towards other java algorithmic resource models
			for(int i = 0; i < this.oSystemPSM.getPSMAlgoResourceModels().size(); i++){
				if(this.oSystemPSM.getPSMResourceModels().get(n).hasRelatedJavaAlgoResourceModel(this.oSystemPSM.getPSMAlgoResourceModels().get(i))){
					this.oSystemPSM.getPSMResourceModels().get(n).getRelatedJavaAlgoResourceModels().add(this.oSystemPSM.getPSMAlgoResourceModels().get(i));
				}
			}
		}
	}

	private void addJavaResourceModelManagerIncomingRelations(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModels().size(); n++){
			this.oSystemPSM.getPSMResourceModels().get(n).addIncomingRelationToRelatedJavaModelManager();
		}

		//add to Java Models the incoming relations of the resource too
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModelManagers().size(); n++){
			this.oSystemPSM.getPSMResourceModelManagers().get(n).getRelatedJavaResourceModel().setIncomingJavaModels(this.oSystemPSM.getPSMResourceModelManagers().get(n).getIncomingJavaModelRelations());
		}
	}

	private void addHTTPActivityHandlers(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllerManagers().size(); n++){
			this.oSystemPSM.getPSMResourceControllerManagers().get(n).addHTTPActivityHandlers();
		}

		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllers().size(); n++){
			this.oSystemPSM.getPSMResourceControllers().get(n).addHTTPActivityHandlers();
		}

		addHTTPActivityHandlerIncomingModels();
	}

	private void addHTTPActivityHandlerIncomingModels(){
		for(int m = 0; m < this.oSystemPSM.getPSMResourceControllerManagers().size(); m++){
			if(this.oSystemPSM.getPSMResourceControllerManagers().get(m).getRelatedJavaResourceModelManager().getIncomingJavaModelRelations().size() != 0){
				for(int j = 0; j < this.oSystemPSM.getPSMResourceControllerManagers().get(m).getJavaControllerManagerHTTPHandlers().size(); j++){
					this.oSystemPSM.getPSMResourceControllerManagers().get(m).getJavaControllerManagerHTTPHandlers().get(j).setIncomingJavaModel(findIncomingJavaModel(this.oSystemPSM.getPSMResourceControllerManagers().get(m).getJavaControllerManagerHTTPHandlers().get(j).getParentPIMCRUDActivityHandler()));
				}

				for(int n = 0; n < this.oSystemPSM.getPSMResourceControllers().size(); n++){
					if(this.oSystemPSM.getPSMResourceControllers().get(n).getPIMParentResourceController().getParentCIMResource().getResourceId() == this.oSystemPSM.getPSMResourceControllerManagers().get(m).getPIMParentResourceControllerManager().getParentCIMResource().getResourceId()){
						for(int i = 0; i < this.oSystemPSM.getPSMResourceControllers().get(n).getJavaControllerHTTPActivityHandlers().size(); i++){
							this.oSystemPSM.getPSMResourceControllers().get(n).getJavaControllerHTTPActivityHandlers().get(i).setIncomingJavaModel(findIncomingJavaModel(this.oSystemPSM.getPSMResourceControllers().get(n).getJavaControllerHTTPActivityHandlers().get(i).getParentPIMCRUDActivityHandler()));
						}
					}
				}
			}
		}
	}

	private JavaResourceModel findIncomingJavaModel(CRUDActivityHandler oCRUDActivityHandler){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModels().size(); n++){
			if(this.oSystemPSM.getPSMResourceModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceId() == oCRUDActivityHandler.getParentControllerCRUDActivity().getIncomingResourceModel().getParentCIMResource().getResourceId()){
				return this.oSystemPSM.getPSMResourceModels().get(n);
			}
		}
		try {
			throw new Exception("Corrupted PIM! Could not find corresponding incoming java model for HTTP activity handler!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; //this should never happen
	}

	private void addHibernateRelationProperties(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModels().size(); n++){
			this.oSystemPSM.getPSMResourceModels().get(n).addHibernateRelationProperties();
		}
	}

	private void createDatabaseController(){
		HibernateController.initializeHibernateController(this.oSystemPIM.getPIMDatabaseController());
		this.oSystemPSM.setHibernateController(HibernateController.getHibernateControllerHandle());

		createHibernateActivities();
	}

	private void createHibernateActivities(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllers().size(); n++){
			this.oSystemPSM.getPSMResourceControllers().get(n).createHibernateActivities();
		}

		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllerManagers().size(); n++){
			this.oSystemPSM.getPSMResourceControllerManagers().get(n).createHibernateActivities();
		}
	}

	private void addAccessFunctions(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModels().size(); n++){
			this.oSystemPSM.getPSMResourceModels().get(n).createAccessFunctions();
		}

		for(int n = 0; n < this.oSystemPSM.getPSMResourceModelManagers().size(); n++){
			this.oSystemPSM.getPSMResourceModelManagers().get(n).createAccessFunctions();;
		}
	}

	private void addJAXRSInputOutputAnnotations(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllers().size(); n++){
			this.oSystemPSM.getPSMResourceControllers().get(n).addJAXRSInputOutputAnnotations();
		}

		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllerManagers().size(); n++){
			this.oSystemPSM.getPSMResourceControllerManagers().get(n).addJAXRSInputOutputAnnotations();
		}
	}

	private void transformHypermediaLinks(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllers().size(); n++){
			for(int i = 0; i < this.oSystemPSM.getPSMResourceControllers().get(n).getJavaControllerHTTPActivityHandlers().size(); i++){
				this.oSystemPSM.getPSMResourceControllers().get(n).getJavaControllerHTTPActivityHandlers().get(i).getHTTPHandlerHypermediaFunction().setReturnParameter(new FunctionParameter("", findChildType(this.oSystemPSM.getPSMResourceControllers().get(n).getJavaControllerHTTPActivityHandlers().get(i).getHTTPHandlerHypermediaFunction().getReturnParameter().getParameterType()), true, true));
				for(int j = 0; j < this.oSystemPSM.getPSMResourceControllers().get(n).getJavaControllerHTTPActivityHandlers().get(i).getHTTPHandlerHypermediaFunction().getFunctionParameters().size(); j++){
					String strChildType = findChildType(this.oSystemPSM.getPSMResourceControllers().get(n).getJavaControllerHTTPActivityHandlers().get(i).getHTTPHandlerHypermediaFunction().getFunctionParameters().get(j).getParameterType());
					this.oSystemPSM.getPSMResourceControllers().get(n).getJavaControllerHTTPActivityHandlers().get(i).getHTTPHandlerHypermediaFunction().getFunctionParameters().set(j, new FunctionParameter(String.format("o%s", strChildType), strChildType, true, false));
				}
			}
		}

		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllerManagers().size(); n++){
			for(int i = 0; i < this.oSystemPSM.getPSMResourceControllerManagers().get(n).getJavaControllerManagerHTTPHandlers().size(); i++){
				this.oSystemPSM.getPSMResourceControllerManagers().get(n).getJavaControllerManagerHTTPHandlers().get(i).getHTTPHandlerHypermediaFunction().setReturnParameter(new FunctionParameter("", findChildType(this.oSystemPSM.getPSMResourceControllerManagers().get(n).getJavaControllerManagerHTTPHandlers().get(i).getHTTPHandlerHypermediaFunction().getReturnParameter().getParameterType()), true, true));
				for(int j = 0; j < this.oSystemPSM.getPSMResourceControllerManagers().get(n).getJavaControllerManagerHTTPHandlers().get(i).getHTTPHandlerHypermediaFunction().getFunctionParameters().size(); j++){
					String strChildType = findChildType(this.oSystemPSM.getPSMResourceControllerManagers().get(n).getJavaControllerManagerHTTPHandlers().get(i).getHTTPHandlerHypermediaFunction().getFunctionParameters().get(j).getParameterType());
					this.oSystemPSM.getPSMResourceControllerManagers().get(n).getJavaControllerManagerHTTPHandlers().get(i).getHTTPHandlerHypermediaFunction().getFunctionParameters().set(j, new FunctionParameter(String.format("o%s", strChildType), strChildType, true, false));
				}
			}
		}
	}

	private String findChildType(String strParentType){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModels().size(); n++){
			if(this.oSystemPSM.getPSMResourceModels().get(n).getPIMParentResourceModel().getResourceModelName().equalsIgnoreCase(strParentType)){
				return this.oSystemPSM.getPSMResourceModels().get(n).getJavaResourceModelName();
			}
		}

		for(int n = 0; n < this.oSystemPSM.getPSMAlgoResourceModels().size(); n++){
			if(this.oSystemPSM.getPSMAlgoResourceModels().get(n).getParentPIMAlgoResourceModel().getAlgoResourceModelName().equalsIgnoreCase(strParentType)){
				return this.oSystemPSM.getPSMAlgoResourceModels().get(n).getJavaAlgoResourceModelName();
			}
		}
		// this should never happen
		try {
			throw new Exception("Corrupted PIM! Could not find correspondng PSM type of " + strParentType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void addAlgoModelProperties(){
		for(int n = 0; n < this.oSystemPSM.getPSMAlgoResourceModels().size(); n++){
			this.oSystemPSM.getPSMAlgoResourceModels().get(n).addAlgoModelProperties();
		}
	}

	private void addAlgoControllerHTTPActivities(){
		for(int n = 0; n < this.oSystemPSM.getPSMAlgoResourceControllers().size(); n++){
			this.oSystemPSM.getPSMAlgoResourceControllers().get(n).addAlgoControllerHTTPActivities();
		}
	}

	private void addAlgoResourceRelations(){
		//probably not needed
	}

	private void addAlgoHTTPActivityHandlers(){
		for(int n = 0; n < this.oSystemPSM.getPSMAlgoResourceControllers().size(); n++){
			this.oSystemPSM.getPSMAlgoResourceControllers().get(n).addAlgoHTTPActivityHandlers();
		}
	}

	private void addAlgoAccessFunctions(){
		for(int n = 0; n < this.oSystemPSM.getPSMAlgoResourceModels().size(); n++){
			this.oSystemPSM.getPSMAlgoResourceModels().get(n).addAlgoModelAccessFunctions();;
		}
	}

	private void addAlgoJAXRSInputOutputAnnotations(){
		for(int n = 0; n < this.oSystemPSM.getPSMAlgoResourceControllers().size(); n++){
			this.oSystemPSM.getPSMAlgoResourceControllers().get(n).addJAXRSInputOutputAnnotations();
		}
	}

	private void transformAlgoHypermediaLinks(){
		for(int n = 0; n < this.oSystemPSM.getPSMAlgoResourceControllers().size(); n++){
			for(int i = 0; i < this.oSystemPSM.getPSMAlgoResourceControllers().get(n).getAlgoControllerActivityHandlers().size(); i++){
				this.oSystemPSM.getPSMAlgoResourceControllers().get(n).getAlgoControllerActivityHandlers().get(i).getHTTPHandlerHypermediaFunction().setReturnParameter(new FunctionParameter("", findChildType(this.oSystemPSM.getPSMAlgoResourceControllers().get(n).getAlgoControllerActivityHandlers().get(i).getHTTPHandlerHypermediaFunction().getReturnParameter().getParameterType()), true, true));
				for(int j = 0; j < this.oSystemPSM.getPSMAlgoResourceControllers().get(n).getAlgoControllerActivityHandlers().get(i).getHTTPHandlerHypermediaFunction().getFunctionParameters().size(); j++){
					String strChildType = findChildType(this.oSystemPSM.getPSMAlgoResourceControllers().get(n).getAlgoControllerActivityHandlers().get(i).getHTTPHandlerHypermediaFunction().getFunctionParameters().get(j).getParameterType());
					this.oSystemPSM.getPSMAlgoResourceControllers().get(n).getAlgoControllerActivityHandlers().get(i).getHTTPHandlerHypermediaFunction().getFunctionParameters().set(j, new FunctionParameter(String.format("o%s", strChildType), strChildType, true, false));
				}
			}
		}
	}
}