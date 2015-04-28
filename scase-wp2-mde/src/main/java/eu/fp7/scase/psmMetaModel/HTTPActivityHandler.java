/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.psmMetaModel;

import eu.fp7.scase.customUtilities.UniqueIdProducer;
import eu.fp7.scase.pimMetaModel.CRUDActivityHandler;

public class HTTPActivityHandler
{

    private int iHTTPActivityHandlerId;
    private String strHTTPActivityHandlerName;
    private String strHTTPActivityHandlerVerb;
    private CRUDActivityHandler oParentPIMCRUDActivityHandler;
    private JavaHypermediaFunction oCreateHypermediaFunction;
    private JavaResourceController oParentJavaController;
    private JavaResourceControllerManager oParentJavaControllerManager;
    private JavaAlgoResourceController oParentAlgoJavaController;
    private JavaResourceModel oIncomingJavaModel;// bad name, see explanation at
                                                 // ResourceControllerCRUDActivity.java

    public HTTPActivityHandler(CRUDActivityHandler oParentPIMCRUDActivityHandler, String strHTTPActivityHandlerVerb,
            String strHTTPActivityHandlerName, JavaResourceController oParentJavaController)
    {
        this.iHTTPActivityHandlerId = UniqueIdProducer.getNewUniqueId();
        this.oParentPIMCRUDActivityHandler = oParentPIMCRUDActivityHandler;
        this.strHTTPActivityHandlerName = strHTTPActivityHandlerName;
        this.strHTTPActivityHandlerVerb = strHTTPActivityHandlerVerb;
        this.oCreateHypermediaFunction = new JavaHypermediaFunction(
                this.oParentPIMCRUDActivityHandler.getHypermediaFunction());
        transformPIMHypermediaLinks();
        capitalizeFirstNameLetter();
        this.oParentJavaController = oParentJavaController;
    }

    public HTTPActivityHandler(CRUDActivityHandler oParentPIMCRUDActivityHandler, String strHTTPActivityHandlerVerb,
            String strHTTPActivityHandlerName, JavaResourceControllerManager oParentJavaControllerManager)
    {
        this.iHTTPActivityHandlerId = UniqueIdProducer.getNewUniqueId();
        this.oParentPIMCRUDActivityHandler = oParentPIMCRUDActivityHandler;
        this.strHTTPActivityHandlerName = strHTTPActivityHandlerName;
        this.strHTTPActivityHandlerVerb = strHTTPActivityHandlerVerb;
        this.oCreateHypermediaFunction = new JavaHypermediaFunction(
                this.oParentPIMCRUDActivityHandler.getHypermediaFunction());
        transformPIMHypermediaLinks();
        capitalizeFirstNameLetter();
        this.oParentJavaControllerManager = oParentJavaControllerManager;
    }

    public HTTPActivityHandler(CRUDActivityHandler oParentPIMCRUDActivityHandler, String strHTTPActivityHandlerVerb,
            String strHTTPActivityHandlerName, JavaAlgoResourceController oParentAlgoJavaController)
    {
        this.iHTTPActivityHandlerId = UniqueIdProducer.getNewUniqueId();
        this.oParentPIMCRUDActivityHandler = oParentPIMCRUDActivityHandler;
        this.strHTTPActivityHandlerName = strHTTPActivityHandlerName;
        this.strHTTPActivityHandlerVerb = strHTTPActivityHandlerVerb;
        this.oCreateHypermediaFunction = new JavaHypermediaFunction(
                this.oParentPIMCRUDActivityHandler.getHypermediaFunction());
        transformPIMHypermediaLinks();
        capitalizeFirstNameLetter();
        this.oParentAlgoJavaController = oParentAlgoJavaController;
    }

    private void capitalizeFirstNameLetter()
    {
        this.strHTTPActivityHandlerName = String.format("%s%s", this.strHTTPActivityHandlerName.substring(0, 1)
                .toUpperCase(), this.strHTTPActivityHandlerName.substring(1));
    }

    public JavaResourceController getParentJavaController()
    {
        return this.oParentJavaController;
    }

    public JavaResourceControllerManager getParentJavaControllerManager()
    {
        return this.oParentJavaControllerManager;
    }

    public void setIncomingJavaModel(JavaResourceModel oIncomingJavaModel)
    {
        this.oIncomingJavaModel = oIncomingJavaModel;
    }

    public JavaResourceModel getIncomingJavaModel()
    {
        return this.oIncomingJavaModel;
    }

    public JavaAlgoResourceController getJavaAlgoResourceController()
    {
        return this.oParentAlgoJavaController;
    }

    public int getHTTPActivityHandlerId()
    {
        return this.iHTTPActivityHandlerId;
    }

    public String getHTTPActivityHandlerName()
    {
        return this.strHTTPActivityHandlerName;
    }

    public String getHTTPActivityHandlerVerb()
    {
        return this.strHTTPActivityHandlerVerb;
    }

    public CRUDActivityHandler getParentPIMCRUDActivityHandler()
    {
        return this.oParentPIMCRUDActivityHandler;
    }

    public JavaHypermediaFunction getHTTPHandlerHypermediaFunction()
    {
        return this.oCreateHypermediaFunction;
    }

    private void transformPIMHypermediaLinks()
    {
        for (int n = 0; n < this.oParentPIMCRUDActivityHandler.getHypermediaFunction().getHypermediaLinkList().size(); n++) {
            this.oCreateHypermediaFunction.getHypermediaLinks().add(
                    new PSMHypermediaLink(this.oParentPIMCRUDActivityHandler.getHypermediaFunction()
                            .getHypermediaLinkList().get(n)));
        }
    }

    public void printHTTPActivityHandler()
    {
        System.out.println("The HTTP Activity Handler: " + this.strHTTPActivityHandlerName
                + " is added to PSM because " + this.oParentPIMCRUDActivityHandler.getCRUDActivityHandlerName()
                + " exists in PIM");
        this.oCreateHypermediaFunction.printJavaFunction();
    }
}
