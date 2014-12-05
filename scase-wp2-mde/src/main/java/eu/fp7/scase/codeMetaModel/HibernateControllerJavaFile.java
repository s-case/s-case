/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 *
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.codeMetaModel;

import java.util.ArrayList;

import eu.fp7.scase.psmMetaModel.HibernateController;
import eu.fp7.scase.psmMetaModel.JavaResourceModel;

public class HibernateControllerJavaFile
        extends AJavaFile
{

    private final HibernateController oParentHibernateController;
    private final ArrayList<JavaResourceModel> listOfSystemJavaModels;

    public HibernateControllerJavaFile(HibernateController oParentHibernateController, String strProjectName,
            String strPackageStamp, ArrayList<JavaResourceModel> listOfSystemJavaModels)
    {
        super(oParentHibernateController.getHibernateControllerName(), strProjectName, strPackageStamp);
        this.oParentHibernateController = oParentHibernateController;
        this.listOfSystemJavaModels = listOfSystemJavaModels;
    }

    @Override
    public void printJavaFile()
    {
        System.out.println("The Hibernate Controller File: " + getJavaFileName()
                + " is added to software code project because "
                + this.oParentHibernateController.getHibernateControllerName() + " exists in PSM");
    }

    @Override
    public void transformFile()
    {
        super.transformFile();
    }

    @Override
    public String addAuthorComment()
    {
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
    public String addFileImports()
    {
        this.strFileImports = String.format("%s%simport org.hibernate.HibernateException;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport org.hibernate.Query;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport org.hibernate.Session;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport org.hibernate.Transaction;%n%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport com.sun.jersey.core.spi.factory.ResponseBuilderImpl;%n%n",
                this.strFileImports, this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String
                .format("%s%simport src.main.java.%s.utilities.HibernateUtil;%n", this.strFileImports,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.strProjectName.toLowerCase());
        for (int n = 0; n < this.listOfSystemJavaModels.size(); n++) {
            this.strFileImports = String.format("%s%simport src.main.java.%s.%s.%s;%n", this.strFileImports,
                    this.oJavaFileIdentation.getCurrentIdentation(), this.strProjectName.toLowerCase(),
                    this.listOfSystemJavaModels.get(n).getPIMParentResourceModel().getParentCIMResource()
                            .getResourceName().toLowerCase(), this.listOfSystemJavaModels.get(n)
                            .getJavaResourceModelName());
        }
        return this.strFileImports;
    }

    @Override
    public String addClassHeader()
    {
        this.strClassHeader = String.format("%s%spublic class HibernateController{%n%n", this.strClassHeader,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.oJavaFileIdentation.increaseIdentation();
        return this.strClassHeader;
    }

    @Override
    public String addClassProperties()
    {
        this.strClassProperties = String.format(
                "%s%sprivate static HibernateController oHibernateController = new HibernateController();%n",
                this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
        return this.strClassProperties;
    }

    @Override
    public String addClassFunctions()
    {
        addConstructor();
        addSingletonAccessorFunction();
        addInsertActivityFunctions();
        addUpdateActivityFunctions();
        addSelectActivityFunctions();
        addDeleteActivityFunctions();
        addSelectListActivityFunctions();
        return this.strClassFunctions;
    }

    @Override
    public String addClassTail()
    {
        this.strClassTail = "}";
        return this.strClassTail;
    }

    private void addConstructor()
    {
        this.strClassFunctions = String.format("%s%sprivate HibernateController(){}%n%n", this.strClassFunctions,
                this.oJavaFileIdentation.getCurrentIdentation());
    }

    private void addSingletonAccessorFunction()
    {
        this.strClassFunctions = String.format(
                "%s%spublic static HibernateController getHibernateControllerHandle(){%n", this.strClassFunctions,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strClassFunctions = String.format("%s%sreturn oHibernateController;%n", this.strClassFunctions,
                this.oJavaFileIdentation.increaseIdentation());
        this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions,
                this.oJavaFileIdentation.decreaseIdentation());
    }

    private void addInsertActivityFunctions()
    {
        for (int n = 0; n < this.oParentHibernateController.getControllerHibernateActivities().size(); n++) {
            if (this.oParentHibernateController.getControllerHibernateActivities().get(n).getHibernateActivityVerb()
                    .equalsIgnoreCase("POST")) {
                this.strClassFunctions = String.format("%s%spublic %s %s(%s o%s){%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaControllerManager()
                                .getRelatedJavaResourceModelManager().getRelatedJavaResourceModel()
                                .getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getHibernateActivityName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaControllerManager().getRelatedJavaResourceModelManager()
                                .getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaControllerManager().getRelatedJavaResourceModelManager()
                                .getRelatedJavaResourceModel().getJavaResourceModelName());
                this.strClassFunctions = String.format(
                        "%s%sSession hibernateSession = HibernateUtil.getSessionFactory().openSession();%n",
                        this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
                this.strClassFunctions = String.format(
                        "%s%sTransaction hibernateTransaction = hibernateSession.beginTransaction();%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%sint %s = (Integer) hibernateSession.save(o%s);%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaControllerManager().getRelatedJavaResourceModelManager()
                                .getRelatedJavaResourceModel().getPIMParentResourceModel()
                                .getModelPrimaryIdentifierName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaControllerManager()
                                .getRelatedJavaResourceModelManager().getRelatedJavaResourceModel()
                                .getJavaResourceModelName());
                this.strClassFunctions = String.format("%s%shibernateTransaction.commit();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%shibernateSession.close();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%so%s.set%s(%s);%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaControllerManager()
                                .getRelatedJavaResourceModelManager().getRelatedJavaResourceModel()
                                .getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaControllerManager()
                                .getRelatedJavaResourceModelManager().getRelatedJavaResourceModel()
                                .getPIMParentResourceModel().getModelPrimaryIdentifierName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaControllerManager().getRelatedJavaResourceModelManager()
                                .getRelatedJavaResourceModel().getPIMParentResourceModel()
                                .getModelPrimaryIdentifierName());
                this.strClassFunctions = String.format("%s%sreturn o%s;%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaControllerManager()
                                .getRelatedJavaResourceModelManager().getRelatedJavaResourceModel()
                                .getJavaResourceModelName());
                this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions,
                        this.oJavaFileIdentation.decreaseIdentation());
            }
        }
    }

    private void addUpdateActivityFunctions()
    {
        for (int n = 0; n < this.oParentHibernateController.getControllerHibernateActivities().size(); n++) {
            if (this.oParentHibernateController.getControllerHibernateActivities().get(n).getHibernateActivityVerb()
                    .equalsIgnoreCase("PUT")) {
                this.strClassFunctions = String.format("%s%spublic %s %s(%s o%s){%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getHibernateActivityName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaResourceController().getRelatedJavaResourceModel()
                                .getJavaResourceModelName());
                this.strClassFunctions = String.format(
                        "%s%sSession hibernateSession = HibernateUtil.getSessionFactory().openSession();%n",
                        this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
                this.strClassFunctions = String.format(
                        "%s%sTransaction hibernateTransaction = hibernateSession.beginTransaction();%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%shibernateSession.update(o%s);%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName());
                this.strClassFunctions = String.format("%s%shibernateTransaction.commit();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%shibernateSession.close();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%sreturn o%s;%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName());
                this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions,
                        this.oJavaFileIdentation.decreaseIdentation());
            }
        }
    }

    private void addSelectActivityFunctions()
    {
        for (int n = 0; n < this.oParentHibernateController.getControllerHibernateActivities().size(); n++) {
            if (this.oParentHibernateController.getControllerHibernateActivities().get(n).getHibernateActivityVerb()
                    .equalsIgnoreCase("GET")
                    && this.oParentHibernateController.getControllerHibernateActivities().get(n)
                            .getParentJavaResourceController() != null) {
                this.strClassFunctions = String.format("%s%spublic %s %s(%s o%s){%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getHibernateActivityName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaResourceController().getRelatedJavaResourceModel()
                                .getJavaResourceModelName());
                this.strClassFunctions = String.format(
                        "%s%sSession hibernateSession = HibernateUtil.getSessionFactory().openSession();%n",
                        this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
                this.strClassFunctions = String.format(
                        "%s%sTransaction hibernateTransaction = hibernateSession.beginTransaction();%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%so%s = (%s) hibernateSession.get(%s.class, o%s.get%s());%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaResourceController().getRelatedJavaResourceModel()
                                .getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaResourceController().getRelatedJavaResourceModel()
                                .getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaResourceController().getRelatedJavaResourceModel()
                                .getPIMParentResourceModel().getModelPrimaryIdentifierName());
                this.strClassFunctions = String.format("%s%shibernateTransaction.commit();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%shibernateSession.close();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%sreturn o%s;%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName());
                this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions,
                        this.oJavaFileIdentation.decreaseIdentation());
            }
        }
    }

    private void addDeleteActivityFunctions()
    {
        for (int n = 0; n < this.oParentHibernateController.getControllerHibernateActivities().size(); n++) {
            if (this.oParentHibernateController.getControllerHibernateActivities().get(n).getHibernateActivityVerb()
                    .equalsIgnoreCase("DELETE")
                    && this.oParentHibernateController.getControllerHibernateActivities().get(n)
                            .getParentJavaResourceController() != null) {
                this.strClassFunctions = String.format("%s%spublic %s %s(%s o%s){%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getHibernateActivityName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaResourceController().getRelatedJavaResourceModel()
                                .getJavaResourceModelName());
                this.strClassFunctions = String.format(
                        "%s%sSession hibernateSession = HibernateUtil.getSessionFactory().openSession();%n",
                        this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
                this.strClassFunctions = String.format(
                        "%s%sTransaction hibernateTransaction = hibernateSession.beginTransaction();%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%so%s = (%s) hibernateSession.get(%s.class, o%s.get%s());%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaResourceController().getRelatedJavaResourceModel()
                                .getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaResourceController().getRelatedJavaResourceModel()
                                .getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getParentJavaResourceController().getRelatedJavaResourceModel()
                                .getPIMParentResourceModel().getModelPrimaryIdentifierName());
                if (this.oParentHibernateController.getControllerHibernateActivities().get(n)
                        .getParentJavaResourceController().getRelatedJavaResourceModel()
                        .getRelatedJavaResourceModelManagers().size() != 0) {
                    this.strClassFunctions = String.format("%s%so%s.deleteAllCollections(hibernateSession);%n",
                            this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
                            this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                    .getParentJavaResourceController().getRelatedJavaResourceModel()
                                    .getJavaResourceModelName());
                }
                this.strClassFunctions = String.format("%s%shibernateSession.delete(o%s);%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName());
                this.strClassFunctions = String.format("%s%shibernateTransaction.commit();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%shibernateSession.close();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%sreturn o%s;%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getParentJavaResourceController()
                                .getRelatedJavaResourceModel().getJavaResourceModelName());
                this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions,
                        this.oJavaFileIdentation.decreaseIdentation());
            }
        }
    }

    private void addSelectListActivityFunctions()
    {
        for (int n = 0; n < this.oParentHibernateController.getControllerHibernateActivities().size(); n++) {
            if (this.oParentHibernateController.getControllerHibernateActivities().get(n).getHibernateActivityVerb()
                    .equalsIgnoreCase("GET")
                    && this.oParentHibernateController.getControllerHibernateActivities().get(n)
                            .getParentJavaControllerManager() != null
                    && this.oParentHibernateController.getControllerHibernateActivities().get(n).getIncomingJavaModel() != null) {
                this.strClassFunctions = String.format("%s%spublic %s %s(%s o%s){%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getIncomingJavaModel()
                                .getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getHibernateActivityName(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getIncomingJavaModel().getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getIncomingJavaModel()
                                .getJavaResourceModelName());
                this.strClassFunctions = String.format(
                        "%s%sSession hibernateSession = HibernateUtil.getSessionFactory().openSession();%n",
                        this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
                this.strClassFunctions = String.format(
                        "%s%sTransaction hibernateTransaction = hibernateSession.beginTransaction();%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%so%s = (%s) hibernateSession.get(%s.class, o%s.get%s());%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
                        this.oParentHibernateController.getControllerHibernateActivities().get(n)
                                .getIncomingJavaModel().getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getIncomingJavaModel()
                                .getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getIncomingJavaModel()
                                .getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getIncomingJavaModel()
                                .getJavaResourceModelName(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getIncomingJavaModel()
                                .getPIMParentResourceModel().getModelPrimaryIdentifierName());
                this.strClassFunctions = String.format("%s%shibernateTransaction.commit();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%shibernateSession.close();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation());
                this.strClassFunctions = String.format("%s%sreturn o%s;%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentHibernateController
                                .getControllerHibernateActivities().get(n).getIncomingJavaModel()
                                .getJavaResourceModelName());
                this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions,
                        this.oJavaFileIdentation.decreaseIdentation());
            }
        }
    }
}
