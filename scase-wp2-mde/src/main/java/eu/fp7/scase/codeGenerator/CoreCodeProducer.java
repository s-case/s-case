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

package eu.fp7.scase.codeGenerator;

import eu.fp7.scase.cimMetaModel.Resource;
import eu.fp7.scase.codeMetaModel.AlgoResourceControllerJavaFile;
import eu.fp7.scase.codeMetaModel.AlgoResourceModelJavaFile;
import eu.fp7.scase.codeMetaModel.HTTPHandlerJavaFile;
import eu.fp7.scase.codeMetaModel.HibernateConfigurationFile;
import eu.fp7.scase.codeMetaModel.HibernateControllerJavaFile;
import eu.fp7.scase.codeMetaModel.HibernateUtilJavaFile;
import eu.fp7.scase.codeMetaModel.HypermediaLinkJavaFile;
import eu.fp7.scase.codeMetaModel.JAXRSPublisherJavaFile;
import eu.fp7.scase.codeMetaModel.JavaPackage;
import eu.fp7.scase.codeMetaModel.MavenConfigurationFile;
import eu.fp7.scase.codeMetaModel.ResourceControllerJavaFile;
import eu.fp7.scase.codeMetaModel.ResourceControllerManagerJavaFile;
import eu.fp7.scase.codeMetaModel.ResourceModelJavaFile;
import eu.fp7.scase.codeMetaModel.ResourceModelManagerJavaFile;
import eu.fp7.scase.codeMetaModel.SystemCode;
import eu.fp7.scase.codeMetaModel.WebXMLConfigurationFile;
import eu.fp7.scase.psmMetaModel.JavaAlgoResourceController;
import eu.fp7.scase.psmMetaModel.JavaAlgoResourceModel;
import eu.fp7.scase.psmMetaModel.JavaResourceController;
import eu.fp7.scase.psmMetaModel.JavaResourceControllerManager;
import eu.fp7.scase.psmMetaModel.JavaResourceModel;
import eu.fp7.scase.psmMetaModel.JavaResourceModelManager;
import eu.fp7.scase.psmMetaModel.SystemPSM;

public class CoreCodeProducer extends ACodeProducer{
	
	public CoreCodeProducer(SystemPSM oSystemPSM, String strProjectName, String strOutputPath, String strDatabaseIp, String strDatabasePort, String strDatabaseUsername, String strDatabasePassword){
		super(oSystemPSM, strProjectName, strOutputPath, strDatabaseIp, strDatabasePort, strDatabaseUsername, strDatabasePassword);
	}
	
	public SystemCode produceCode(){
		
		setUpProject();
		createProjectPackages();
		createPackageFiles();
		transformPSMToCode();
		createConfigurationFiles();
		
		this.oSystemCode.printSystemCode();
		this.oSystemCode.exportSystemCode(this.getOutputPath(), this.getProjectName());
		
		return this.oSystemCode;
	}
	
	private void setUpProject(){
		this.oSystemCode.setSystemCodeProjectName(this.getProjectName());
	}
	
	private void createProjectPackages(){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModels().size(); n++){
			this.oSystemCode.getJavaPackages().add(new JavaPackage(this.oSystemPSM.getPSMResourceModels().get(n).getPIMParentResourceModel().getParentCIMResource(), this.oSystemCode.getSystemCodeProjectName(), String.format("%s/%s/src/main/java/%s/%s", this.strOutputPath, this.strProjectName, this.strProjectName.toLowerCase(), this.oSystemPSM.getPSMResourceModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceName().toLowerCase())));
		}
		
		for(int n = 0; n < this.oSystemPSM.getPSMAlgoResourceModels().size(); n++){
			this.oSystemCode.getJavaPackages().add(new JavaPackage(this.oSystemPSM.getPSMAlgoResourceModels().get(n).getParentPIMAlgoResourceModel().getParentCIMResource(), this.oSystemCode.getSystemCodeProjectName(), String.format("%s/%s/src/main/java/%s/%s", this.strOutputPath, this.strProjectName, this.strProjectName.toLowerCase(), this.oSystemPSM.getPSMAlgoResourceModels().get(n).getParentPIMAlgoResourceModel().getParentCIMResource().getResourceName().toLowerCase())));
		}
		
		this.oSystemCode.getJavaPackages().add(new JavaPackage("Utilities", this.oSystemCode.getSystemCodeProjectName(), String.format("%s/%s/src/main/java/%s/utilities", this.strOutputPath, this.strProjectName, this.strProjectName.toLowerCase())));
	}
	
	private void createPackageFiles(){
		for(int n = 0; n < this.oSystemCode.getJavaPackages().size(); n++){
			if(this.oSystemCode.getJavaPackages().get(n).getParentResource() == null){//if it is a custom package such as the Utilities package
				createUtilityPackageFiles(this.oSystemCode.getJavaPackages().get(n));
			}
			else if(!this.oSystemCode.getJavaPackages().get(n).getParentResource().getResourceType()){ //else if it is a non algorithmic resource package
				createNonAlgorithmicPackageFiles(this.oSystemCode.getJavaPackages().get(n));
			}
			else{// else if it is an algorithmic resource package
				createAlgorithmicPackageFiles(this.oSystemCode.getJavaPackages().get(n));
			}
		}
	}
	
	private void createUtilityPackageFiles(JavaPackage oPackage){
		//add the HibernateUtil file
		oPackage.getPackageJavaFiles().add(new HibernateUtilJavaFile("HibernateUtil", this.strProjectName, oPackage.getJavaPackageStamp(), this.oSystemPSM.getPSMResourceModels()));
		//add the HypermediaLink file
		oPackage.getPackageJavaFiles().add(new HypermediaLinkJavaFile(this.strProjectName, oPackage.getJavaPackageStamp()));
		//add the JAXRSPublisher file
		oPackage.getPackageJavaFiles().add(new JAXRSPublisherJavaFile("JAXRSPublisher", this.strProjectName, oPackage.getJavaPackageStamp(), this.oSystemPSM.getPSMResourceControllers(), this.oSystemPSM.getPSMResourceControllerManagers(), this.oSystemPSM.getPSMAlgoResourceControllers()));
		//add the Hibernate Controller file
		oPackage.getPackageJavaFiles().add(new HibernateControllerJavaFile(this.oSystemPSM.getHibernateController(), this.strProjectName, oPackage.getJavaPackageStamp(), this.oSystemPSM.getPSMResourceModels()));
	}
	
	private void createNonAlgorithmicPackageFiles(JavaPackage oPackage){
		//add the resource controller manager file
		JavaResourceControllerManager oParentJavaControllerManager = findParentJavaControllerManager(oPackage.getParentResource());
		oPackage.getPackageJavaFiles().add(new ResourceControllerManagerJavaFile(oParentJavaControllerManager, this.strProjectName, oPackage.getJavaPackageStamp()));
		//add the resource controller file
		JavaResourceController oParentJavaController = findParentJavaController(oPackage.getParentResource());
		oPackage.getPackageJavaFiles().add(new ResourceControllerJavaFile(oParentJavaController, this.strProjectName, oPackage.getJavaPackageStamp()));
		//add the resource model manager file
		oPackage.getPackageJavaFiles().add(new ResourceModelManagerJavaFile(findParentJavaModelManager(oPackage.getParentResource()), this.strProjectName, oPackage.getJavaPackageStamp()));
		//add the resource model file
		oPackage.getPackageJavaFiles().add(new ResourceModelJavaFile(findParentJavaModel(oPackage.getParentResource()), this.strProjectName, oPackage.getJavaPackageStamp()));
		//add the resource controller manager HTTP Handler files
		for(int n = 0; n < oParentJavaControllerManager.getJavaControllerManagerHTTPHandlers().size(); n++){
			oPackage.getPackageJavaFiles().add(new HTTPHandlerJavaFile(oParentJavaControllerManager.getJavaControllerManagerHTTPHandlers().get(n), this.strProjectName, oPackage.getJavaPackageStamp()));
		}
		//add the resource controler HTTP Handler files
		for(int n = 0; n < oParentJavaController.getJavaControllerHTTPActivityHandlers().size(); n++){
			oPackage.getPackageJavaFiles().add(new HTTPHandlerJavaFile(oParentJavaController.getJavaControllerHTTPActivityHandlers().get(n), this.strProjectName, oPackage.getJavaPackageStamp()));
		}
	}
	
	private void createAlgorithmicPackageFiles(JavaPackage oPackage){
		//add the algorithmic resource controller file
		JavaAlgoResourceController oParentAlgoController = findParentJavaAlgoController(oPackage.getParentResource());
		oPackage.getPackageJavaFiles().add(new AlgoResourceControllerJavaFile(oParentAlgoController, this.strProjectName, oPackage.getJavaPackageStamp()));
		//add the algorithmic resource model file
		oPackage.getPackageJavaFiles().add(new AlgoResourceModelJavaFile(findParentJavaAlgoModel(oPackage.getParentResource()), this.strProjectName, oPackage.getJavaPackageStamp()));
		//add the algorithmic resource controller HTTP Handler files;
		for(int n = 0;  n < oParentAlgoController.getAlgoControllerActivityHandlers().size(); n++){
			oPackage.getPackageJavaFiles().add(new HTTPHandlerJavaFile(oParentAlgoController.getAlgoControllerActivityHandlers().get(n), this.strProjectName, oPackage.getJavaPackageStamp()));
		}
	}
	
	private JavaResourceControllerManager findParentJavaControllerManager(Resource oCIMResource){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllerManagers().size(); n++){
			if(this.oSystemPSM.getPSMResourceControllerManagers().get(n).getPIMParentResourceControllerManager().getParentCIMResource().getResourceId() == oCIMResource.getResourceId()){
				return this.oSystemPSM.getPSMResourceControllerManagers().get(n);
			}
		}
		
		//this should never happen
		try {
			throw new Exception("PSM is corrupted! Could not find parent java resource controller manager!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private JavaResourceController findParentJavaController(Resource oCIMResource){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceControllers().size(); n++){
			if(this.oSystemPSM.getPSMResourceControllers().get(n).getPIMParentResourceController().getParentCIMResource().getResourceId() == oCIMResource.getResourceId()){
				return this.oSystemPSM.getPSMResourceControllers().get(n);
			}
		}
		
		//this should never happen
		try {
			throw new Exception("PSM is corrupted! Could not find parent java resource controller!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private JavaResourceModelManager findParentJavaModelManager(Resource oCIMResource){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModelManagers().size(); n++){
			if(this.oSystemPSM.getPSMResourceModelManagers().get(n).getPIMParentResourceModelManager().getParentCIMResource().getResourceId() == oCIMResource.getResourceId()){
				return this.oSystemPSM.getPSMResourceModelManagers().get(n);
			}
		}
		
		//this should never happen
		try {
			throw new Exception("PSM is corrupted! Could not find parent java resource model manager!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private JavaResourceModel findParentJavaModel(Resource oCIMResource){
		for(int n = 0; n < this.oSystemPSM.getPSMResourceModels().size(); n++){
			if(this.oSystemPSM.getPSMResourceModels().get(n).getPIMParentResourceModel().getParentCIMResource().getResourceId() == oCIMResource.getResourceId()){
				return this.oSystemPSM.getPSMResourceModels().get(n);
			}
		}
		
		//this should never happen
		try {
			throw new Exception("PSM is corrupted! Could not find parent java resource model!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private JavaAlgoResourceController findParentJavaAlgoController(Resource oCIMResource){
		for(int n = 0; n < this.oSystemPSM.getPSMAlgoResourceControllers().size(); n++){
			if(this.oSystemPSM.getPSMAlgoResourceControllers().get(n).getParentPIMAlgoResourceController().getParentCIMResource().getResourceId() == oCIMResource.getResourceId()){
				return this.oSystemPSM.getPSMAlgoResourceControllers().get(n);
			}
		}
		
		//this should never happen
		try {
			throw new Exception("PSM is corrupted! Could not find parent java algorithmic resource controller!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private JavaAlgoResourceModel findParentJavaAlgoModel(Resource oCIMResource){
		for(int n = 0; n < this.oSystemPSM.getPSMAlgoResourceModels().size(); n++){
			if(this.oSystemPSM.getPSMAlgoResourceModels().get(n).getParentPIMAlgoResourceModel().getParentCIMResource().getResourceId() == oCIMResource.getResourceId()){
				return this.oSystemPSM.getPSMAlgoResourceModels().get(n);
			}
		}
		
		//this should never happen
		try {
			throw new Exception("PSM is corrupted! Could not find parent java algorithmic resource model!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void transformPSMToCode(){
		for(int n = 0; n < this.oSystemCode.getJavaPackages().size(); n++){
			this.oSystemCode.getJavaPackages().get(n).transfromPackageFiles();
		}
	}
	
	private void createConfigurationFiles(){
		this.oSystemCode.setHibernateConfigurationFile(new HibernateConfigurationFile(this.getDatabaseIp(), this.getDatabasePort(), this.getDatabaseUsername(), this.getDatabasePassword()));
		this.oSystemCode.setMavenConfigurationFile(new MavenConfigurationFile(this.getProjectName()));
		this.oSystemCode.setWebXMLConfigurationFile(new WebXMLConfigurationFile(this.strProjectName));
	}
}