/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.codeMetaModel;

import eu.fp7.scase.psmMetaModel.HTTPActivity;
import eu.fp7.scase.psmMetaModel.HTTPActivityHandler;
import eu.fp7.scase.psmMetaModel.JavaResourceController;

public class ResourceControllerJavaFile
        extends AJavaFile
{

    private JavaResourceController oParentJavaController;

    public ResourceControllerJavaFile(JavaResourceController oParentJavaController, String strProjectName,
            String strPackageStamp)
    {
        super(oParentJavaController.getJavaResourceControllerName(), strProjectName, strPackageStamp);
        this.oParentJavaController = oParentJavaController;
    }

    public JavaResourceController getParentJavaController()
    {
        return this.oParentJavaController;
    }

    @Override
    public void printJavaFile()
    {
        System.out.println("The Java Resource Controller File: " + this.getJavaFileName()
                + " is added to software code project because "
                + this.oParentJavaController.getJavaResourceControllerName() + " exists in PSM");
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
        this.strFileImports = String.format("%s%simport javax.ws.rs.Consumes;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport javax.ws.rs.DELETE;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport javax.ws.rs.GET;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport javax.ws.rs.POST;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport javax.ws.rs.PUT;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport javax.ws.rs.Path;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport javax.ws.rs.PathParam;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport javax.ws.rs.Produces;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport javax.ws.rs.core.Context;%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());
        this.strFileImports = String.format("%s%simport javax.ws.rs.core.UriInfo;%n%n", this.strFileImports,
                this.oJavaFileIdentation.getCurrentIdentation());

        return this.strFileImports;
    }

    @Override
    public String addClassHeader()
    {
        this.strClassHeader = String.format("%s%s%n", this.oJavaFileIdentation.getCurrentIdentation(),
                this.oParentJavaController.getControllerPathAnnotation().getJAXRSAnnotationText());
        this.strClassHeader = String.format("%s%spublic class %s{%n%n", this.strClassHeader,
                this.oJavaFileIdentation.getCurrentIdentation(),
                this.oParentJavaController.getJavaResourceControllerName());
        this.oJavaFileIdentation.increaseIdentation();
        return this.strClassHeader;
    }

    @Override
    public String addClassProperties()
    {
        this.strClassProperties = String.format("%s@Context%n", this.oJavaFileIdentation.getCurrentIdentation());
        this.strClassProperties = String.format("%s%sprivate UriInfo oApplicationUri;%n%n", this.strClassProperties,
                this.oJavaFileIdentation.getCurrentIdentation());
        return this.strClassProperties;
    }

    @Override
    public String addClassFunctions()
    {
        for (int n = 0; n < this.oParentJavaController.getJavaControllerHTTPActivities().size(); n++) {
            HTTPActivityHandler oHTTPActivityHandler = findHTTPHandlerOfHTTPActivity(this.oParentJavaController
                    .getJavaControllerHTTPActivities().get(n));

            this.strClassFunctions = String.format("%s%s%s%n", this.strClassFunctions,
                    this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaController
                            .getJavaControllerHTTPActivities().get(n).getHTTPActivityJAXRSPathAnnotation()
                            .getJAXRSAnnotationText());
            this.strClassFunctions = String.format("%s%s%s%n", this.strClassFunctions,
                    this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaController
                            .getJavaControllerHTTPActivities().get(n).getHTTPActivityJAXRSVerbAnnotation()
                            .getJAXRSAnnotationText());
            this.strClassFunctions = String.format("%s%s%s%n", this.strClassFunctions,
                    this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaController
                            .getJavaControllerHTTPActivities().get(n).getJAXRSProduceAnnotation()
                            .getJAXRSAnnotationText());

            String strTargetIdentifier;
            if (this.oParentJavaController.getJavaControllerHTTPActivities().get(n).getParentPIMCRUDActivity()
                    .getIncomingResourceModel() != null) {
                strTargetIdentifier = String.format("%s%s",
                        (this.oParentJavaController.getPIMParentResourceController().getParentCIMResource()
                                .getResourceId() == this.oParentJavaController.getJavaControllerHTTPActivities().get(n)
                                .getParentPIMCRUDActivity().getIncomingResourceModel().getParentCIMResource()
                                .getResourceId() ? "target" : ""), this.oParentJavaController
                                .getRelatedJavaResourceModel().getPIMParentResourceModel()
                                .getModelPrimaryIdentifierName());
            } else {
                strTargetIdentifier = String.format("%s", this.oParentJavaController.getRelatedJavaResourceModel()
                        .getPIMParentResourceModel().getModelPrimaryIdentifierName());
            }

            if (this.oParentJavaController.getJavaControllerHTTPActivities().get(n).getHTTPActivityVerb()
                    .equalsIgnoreCase("GET")) {
                this.strClassFunctions = String.format("%s%spublic %s %s(@PathParam(\"%s\") int %s){%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
                        this.oParentJavaController.getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentJavaController.getJavaControllerHTTPActivities().get(n).getHTTPActivityName(),
                        strTargetIdentifier, strTargetIdentifier);
                this.strClassFunctions = String.format("%s%s%s o%s = new %s(%s, oApplicationUri);%n",
                        this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(),
                        oHTTPActivityHandler.getHTTPActivityHandlerName(),
                        oHTTPActivityHandler.getHTTPActivityHandlerName(),
                        oHTTPActivityHandler.getHTTPActivityHandlerName(), strTargetIdentifier);
                this.strClassFunctions = String.format("%s%sreturn o%s.get%s();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), oHTTPActivityHandler
                                .getHTTPActivityHandlerName(), this.oParentJavaController.getRelatedJavaResourceModel()
                                .getJavaResourceModelName());
            } else if (this.oParentJavaController.getJavaControllerHTTPActivities().get(n).getHTTPActivityVerb()
                    .equalsIgnoreCase("PUT")) {
                String strSourceIdentifier = "";
                String strSourceIdentifierFunctionSignature = "";

                if (this.oParentJavaController.getJavaControllerHTTPActivities().get(n).getParentPIMCRUDActivity()
                        .getIncomingResourceModel() != null) {// if this resource is related of some
                                                              // other
                    strSourceIdentifier = this.oParentJavaController.getJavaControllerHTTPActivities().get(n)
                            .getParentPIMCRUDActivity().getIncomingResourceModel().getModelPrimaryIdentifierName();
                    strSourceIdentifierFunctionSignature = String.format("@PathParam(\"%s\") int %s, ",
                            strSourceIdentifier, strSourceIdentifier);
                }

                this.strClassFunctions = String.format("%s%s%s%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), this.oParentJavaController
                                .getJavaControllerHTTPActivities().get(n).getJAXRSConsumeAnnotation()
                                .getJAXRSAnnotationText());
                this.strClassFunctions = String.format("%s%spublic %s %s(%s@PathParam(\"%s\") int %s, %s o%s){%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
                        this.oParentJavaController.getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentJavaController.getJavaControllerHTTPActivities().get(n).getHTTPActivityName(),
                        strSourceIdentifierFunctionSignature, strTargetIdentifier, strTargetIdentifier,
                        this.oParentJavaController.getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentJavaController.getRelatedJavaResourceModel().getJavaResourceModelName());
                this.strClassFunctions = String.format("%s%s%s o%s = new %s(%s%s, o%s, oApplicationUri);%n",
                        this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(), oHTTPActivityHandler
                                .getHTTPActivityHandlerName(), oHTTPActivityHandler.getHTTPActivityHandlerName(),
                        oHTTPActivityHandler.getHTTPActivityHandlerName(),
                        (strSourceIdentifier.equalsIgnoreCase("") ? "" : strSourceIdentifier + ", "),
                        strTargetIdentifier, this.oParentJavaController.getRelatedJavaResourceModel()
                                .getJavaResourceModelName());
                this.strClassFunctions = String.format("%s%sreturn o%s.put%s();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), oHTTPActivityHandler
                                .getHTTPActivityHandlerName(), this.oParentJavaController.getRelatedJavaResourceModel()
                                .getJavaResourceModelName());
            } else if (this.oParentJavaController.getJavaControllerHTTPActivities().get(n).getHTTPActivityVerb()
                    .equalsIgnoreCase("DELETE")) {
                this.strClassFunctions = String.format("%s%spublic %s %s(@PathParam(\"%s\") int %s){%n",
                        this.strClassFunctions, this.oJavaFileIdentation.getCurrentIdentation(),
                        this.oParentJavaController.getRelatedJavaResourceModel().getJavaResourceModelName(),
                        this.oParentJavaController.getJavaControllerHTTPActivities().get(n).getHTTPActivityName(),
                        strTargetIdentifier, strTargetIdentifier);
                this.strClassFunctions = String.format("%s%s%s o%s = new %s(%s, oApplicationUri);%n",
                        this.strClassFunctions, this.oJavaFileIdentation.increaseIdentation(),
                        oHTTPActivityHandler.getHTTPActivityHandlerName(),
                        oHTTPActivityHandler.getHTTPActivityHandlerName(),
                        oHTTPActivityHandler.getHTTPActivityHandlerName(), strTargetIdentifier);
                this.strClassFunctions = String.format("%s%sreturn o%s.delete%s();%n", this.strClassFunctions,
                        this.oJavaFileIdentation.getCurrentIdentation(), oHTTPActivityHandler
                                .getHTTPActivityHandlerName(), this.oParentJavaController.getRelatedJavaResourceModel()
                                .getJavaResourceModelName());
            }

            this.strClassFunctions = String.format("%s%s}%n%n", this.strClassFunctions,
                    this.oJavaFileIdentation.decreaseIdentation());
        }
        return this.strClassFunctions;
    }

    @Override
    public String addClassTail()
    {
        this.strClassTail = "}";
        return this.strClassTail;
    }

    private HTTPActivityHandler findHTTPHandlerOfHTTPActivity(HTTPActivity oHTTPActivity)
    {
        for (int n = 0; n < this.oParentJavaController.getJavaControllerHTTPActivityHandlers().size(); n++) {
            if (this.oParentJavaController.getJavaControllerHTTPActivityHandlers().get(n)
                    .getParentPIMCRUDActivityHandler().getParentControllerCRUDActivity()
                    .getResourceControllerCRUDActivityId() == oHTTPActivity.getParentPIMCRUDActivity()
                    .getResourceControllerCRUDActivityId()) {
                return this.oParentJavaController.getJavaControllerHTTPActivityHandlers().get(n);
            }
        }
        try {
            throw new Exception(
                    "Corrupted PSM! Could not find corresponding HTTP Activity Handler of the HTTP Activity: "
                            + oHTTPActivity.getHTTPActivityName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // this should never happen
    }
}
