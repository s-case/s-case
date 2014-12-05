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

import eu.fp7.scase.psmMetaModel.JavaAlgoResourceController;
import eu.fp7.scase.psmMetaModel.JavaResourceController;
import eu.fp7.scase.psmMetaModel.JavaResourceControllerManager;

public class JAXRSPublisherJavaFile
        extends AJavaFile
{

    private ArrayList<JavaResourceController> listOfSystemResourceControllers;
    private ArrayList<JavaResourceControllerManager> listOfSystemResourceControllerManagers;
    private ArrayList<JavaAlgoResourceController> listOfSystemAlgoResourceControllers;

    public JAXRSPublisherJavaFile(String strFileName, String strProjectName, String strPackageStamp,
            ArrayList<JavaResourceController> listControllers,
            ArrayList<JavaResourceControllerManager> listControllerManagers,
            ArrayList<JavaAlgoResourceController> listAlgoControllers)
    {
        super(strFileName, strProjectName, strPackageStamp);
        this.listOfSystemResourceControllers = listControllers;
        this.listOfSystemResourceControllerManagers = listControllerManagers;
        this.listOfSystemAlgoResourceControllers = listAlgoControllers;
    }

    @Override
    public void printJavaFile()
    {
        System.out
                .println("The JAXRS Publisher File: "
                        + this.getJavaFileName()
                        + " is added to the software code project because it set ups the configuration of the service during deployment");
    }

    @Override
    public void transformFile()
    {
        super.transformFile();
    }

    @Override
    public String addAuthorComment()
    {
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
    public String addFileImports()
    {
        this.strFileImports = String.format("%s%simport java.util.HashSet;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport java.util.Set;%n%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport javax.ws.rs.ApplicationPath;%n%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport javax.ws.rs.core.Application;%n%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        for (int n = 0; n < this.listOfSystemResourceControllers.size(); n++) {
            this.strFileImports = String.format("%s%simport src.main.java.%s.%s.%s;%n", this.strFileImports,
                    this.oJavaFileIdentation.getCurrentIdentation(), this.strProjectName.toLowerCase(),
                    this.listOfSystemResourceControllers.get(n).getPIMParentResourceController().getParentCIMResource()
                            .getResourceName().toLowerCase(), this.listOfSystemResourceControllers.get(n)
                            .getJavaResourceControllerName());
        }
        for (int n = 0; n < this.listOfSystemResourceControllerManagers.size(); n++) {
            this.strFileImports = String.format("%s%simport src.main.java.%s.%s.%s;%n", this.strFileImports,
                    this.oJavaFileIdentation.getCurrentIdentation(), this.strProjectName.toLowerCase(),
                    this.listOfSystemResourceControllerManagers.get(n).getPIMParentResourceControllerManager()
                            .getParentCIMResource().getResourceName().toLowerCase(),
                    this.listOfSystemResourceControllerManagers.get(n).getJavaResourceControllerManagerName());
        }
        for (int n = 0; n < this.listOfSystemAlgoResourceControllers.size(); n++) {
            this.strFileImports = String.format("%s%simport src.main.java.%s.%s.%s;%n", this.strFileImports,
                    this.oJavaFileIdentation.getCurrentIdentation(), this.strProjectName.toLowerCase(),
                    this.listOfSystemAlgoResourceControllers.get(n).getParentPIMAlgoResourceController()
                            .getParentCIMResource().getResourceName().toLowerCase(),
                    this.listOfSystemAlgoResourceControllers.get(n).getJavaAlgoResourceControllerName());
        }

        return this.strFileImports;
    }

    @Override
    public String addClassHeader()
    {
        this.strClassHeader = String.format("%s%s@ApplicationPath(\"/api/\")%n", this.strClassHeader,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strClassHeader = String.format("%s%spublic class JAXRSPublisher extends Application{%n%n",
                this.strClassHeader, this.oJavaFileIdentation.getCurrentIdentation());
        this.oJavaFileIdentation.increaseIdentation();
        return this.strClassHeader;
    }

    @Override
    public String addClassProperties()
    {
        return "";
    }

    @Override
    public String addClassFunctions()
    {
        this.strClassFunctions = String.format("%s%spublic JAXRSPublisher(){}%n%n", this.strClassFunctions,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strClassFunctions = String.format("%s%s@Override%n", this.strClassFunctions,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strClassFunctions = String.format("%s%spublic Set<Class<?>> getClasses(){%n", this.strClassFunctions,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strClassFunctions = String.format("%s%sHashSet<Class<?>> SetOfClasses = new HashSet<Class<?>>();%n",
                this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation());
        for (int n = 0; n < this.listOfSystemResourceControllers.size(); n++) {
            this.strClassFunctions = String.format("%s%sSetOfClasses.add(%s.class);%n", this.strClassFunctions,
                    this.oJavaFileIdentation.getCurrentIdentation(), this.listOfSystemResourceControllers.get(n)
                            .getJavaResourceControllerName());
        }
        for (int n = 0; n < this.listOfSystemResourceControllerManagers.size(); n++) {
            this.strClassFunctions = String.format("%s%sSetOfClasses.add(%s.class);%n", this.strClassFunctions,
                    this.oJavaFileIdentation.getCurrentIdentation(), this.listOfSystemResourceControllerManagers.get(n)
                            .getJavaResourceControllerManagerName());
        }
        for (int n = 0; n < this.listOfSystemAlgoResourceControllers.size(); n++) {
            this.strClassFunctions = String.format("%s%sSetOfClasses.add(%s.class);%n", this.strClassFunctions,
                    this.oJavaFileIdentation.getCurrentIdentation(), this.listOfSystemAlgoResourceControllers.get(n)
                            .getJavaAlgoResourceControllerName());
        }
        this.strClassFunctions = String.format("%s%n%sreturn SetOfClasses;%n", this.strClassFunctions,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions,
                this.oJavaFileIdentation.decreaseIdentation());
        this.strClassFunctions = String.format("%s%s@Override%n", this.strClassFunctions,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strClassFunctions = String.format("%s%spublic Set<Object> getSingletons(){%n", this.strClassFunctions,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strClassFunctions = String.format("%s%sreturn new HashSet<Object>();%n", this.strClassFunctions,
                this.oJavaFileIdentation.increaseIdentation());
        this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions,
                this.oJavaFileIdentation.decreaseIdentation());

        return this.strClassFunctions;
    }

    @Override
    public String addClassTail()
    {
        this.strClassTail = "}";
        return this.strClassTail;
    }
}
