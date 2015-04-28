/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.pimMetaModel;

import java.util.ArrayList;

public class CreateHypermediaPIMFunction
        extends APIMFunction
{

    private CRUDActivityHandler oCRUDActivityHandler;
    private ResourceController oAssociatedResourceController;
    private ResourceControllerManager oAssociatedResourceControllerManager;
    private AlgoResourceController oAssociatedAlgoResourceController;
    private ArrayList<PIMHypermediaLink> listOfHypermediaLinks;
    private ResourceControllerCRUDActivity oParentCRUDActivity;

    public CreateHypermediaPIMFunction(CRUDActivityHandler oCRUDActivityHandler,
            ResourceController oAssociatedResourceController, ResourceControllerCRUDActivity oParentCRUDActivity)
    {
        super("createHypermedia");
        this.oCRUDActivityHandler = oCRUDActivityHandler;
        this.oAssociatedResourceController = oAssociatedResourceController;
        this.setFunctionReturnParameter(new FunctionParameter("", this.oAssociatedResourceController
                .getAssociatedResourceModel().getResourceModelName(), true, true));
        this.getFunctionParameters().add(
                new FunctionParameter(String.format("oRetrieved%s", this.oAssociatedResourceController
                        .getAssociatedResourceModel().getResourceModelName()), this.oAssociatedResourceController
                        .getAssociatedResourceModel().getResourceModelName(), true, false));
        this.listOfHypermediaLinks = new ArrayList<PIMHypermediaLink>();
        this.oParentCRUDActivity = oParentCRUDActivity;
    }

    public CreateHypermediaPIMFunction(CRUDActivityHandler oCRUDActivityHandler,
            ResourceControllerManager oAssociatedResourceControllerManager,
            ResourceControllerCRUDActivity oParentCRUDActivity)
    {
        super("createHypermedia");
        this.oCRUDActivityHandler = oCRUDActivityHandler;
        this.oAssociatedResourceControllerManager = oAssociatedResourceControllerManager;
        this.setFunctionReturnParameter(new FunctionParameter("", this.oAssociatedResourceControllerManager
                .getAssociatedResourceModelManager().getRelatedResourceModel().getResourceModelName(), true, true));
        this.getFunctionParameters().add(
                new FunctionParameter(String.format("oRetrieved%s", this.oAssociatedResourceControllerManager
                        .getAssociatedResourceModelManager().getRelatedResourceModel().getResourceModelName()),
                        this.oAssociatedResourceControllerManager.getAssociatedResourceModelManager()
                                .getRelatedResourceModel().getResourceModelName(), true, false));
        this.listOfHypermediaLinks = new ArrayList<PIMHypermediaLink>();
        this.oParentCRUDActivity = oParentCRUDActivity;
    }

    public CreateHypermediaPIMFunction(CRUDActivityHandler oCRUDActivityHandler,
            ResourceControllerManager oAssociatedResourceControllerManager,
            ResourceControllerCRUDActivity oParentCRUDActivity, ResourceModel oIncomingResourceModel)
    {
        super("createHypermedia");
        this.oCRUDActivityHandler = oCRUDActivityHandler;
        this.oAssociatedResourceControllerManager = oAssociatedResourceControllerManager;
        this.setFunctionReturnParameter(new FunctionParameter("", this.oAssociatedResourceControllerManager
                .getAssociatedResourceModelManager().getRelatedResourceModel().getResourceModelName(), true, true));
        if (!this.oCRUDActivityHandler.getCRUDActivityHandlerVerb().equalsIgnoreCase("READ")) {
            this.getFunctionParameters().add(
                    new FunctionParameter(String.format("oRetrieved%s", this.oAssociatedResourceControllerManager
                            .getAssociatedResourceModelManager().getRelatedResourceModel().getResourceModelName()),
                            this.oAssociatedResourceControllerManager.getAssociatedResourceModelManager()
                                    .getRelatedResourceModel().getResourceModelName(), true, false));
        } else {
            this.getFunctionParameters().add(
                    new FunctionParameter(String.format("oRetrieved%s", oIncomingResourceModel.getResourceModelName()),
                            oIncomingResourceModel.getResourceModelName(), true, false));
        }
        this.listOfHypermediaLinks = new ArrayList<PIMHypermediaLink>();
        this.oParentCRUDActivity = oParentCRUDActivity;
    }

    public CreateHypermediaPIMFunction(CRUDActivityHandler oCRUDActivityHandler,
            AlgoResourceController oAssociatedAlgoResourceController, ResourceControllerCRUDActivity oParentCRUDActivity)
    {
        super("createHypermedia");
        this.oCRUDActivityHandler = oCRUDActivityHandler;
        this.oAssociatedAlgoResourceController = oAssociatedAlgoResourceController;
        this.setFunctionReturnParameter(new FunctionParameter("", this.oAssociatedAlgoResourceController
                .getAssociatedAlgoResourceModel().getAlgoResourceModelName(), true, true));
        this.listOfHypermediaLinks = new ArrayList<PIMHypermediaLink>();
        this.oParentCRUDActivity = oParentCRUDActivity;
    }

    @Override
    public void printPIMFunction()
    {
        System.out.println("The hypermedia function: " + this.getPIMFunctionName() + " is added to PIM because "
                + this.oCRUDActivityHandler.getCRUDActivityHandlerName()
                + " must have exactly one such function according to PIM meta-model.");
        super.printPIMFunction();
        printHypermediaLinks();
    }

    public ResourceController getAssociatedResourceController()
    {
        return this.oAssociatedResourceController;
    }

    public ResourceControllerManager getAssociatedResourceControllerManager()
    {
        return this.oAssociatedResourceControllerManager;
    }

    public AlgoResourceController getAssociatedAlgoResourceController()
    {
        return this.oAssociatedAlgoResourceController;
    }

    public ArrayList<PIMHypermediaLink> getHypermediaLinkList()
    {
        return this.listOfHypermediaLinks;
    }

    public ResourceControllerCRUDActivity getParentCRUDActivity()
    {
        return this.oParentCRUDActivity;
    }

    private void printHypermediaLinks()
    {
        for (int n = 0; n < this.listOfHypermediaLinks.size(); n++) {
            System.out.println("Hypermedia Link: " + this.getHypermediaLinkList().get(n).getPIMHypermediaLinkId());
            System.out.println("CRUD Verb: " + this.getHypermediaLinkList().get(n).getLinkCRUDVerb());
            System.out.println("Type: " + this.getHypermediaLinkList().get(n).getLinkType());
            System.out.println("Links to: "
                    + (this.getHypermediaLinkList().get(n).getTargetResourceController() == null ? (this
                            .getHypermediaLinkList().get(n).getTargetResourceControllerManager() == null ? this
                            .getHypermediaLinkList().get(n).getTargetAlgoResourceController()
                            .getAlgoResourceControllerName() : this.getHypermediaLinkList().get(n)
                            .getTargetResourceControllerManager().getResourceControllerManagerName())
                            : this.listOfHypermediaLinks.get(n).getTargetResourceController()
                                    .getResourceControllerName()));
        }
    }
}
