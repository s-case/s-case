/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.pimMetaModel;

import eu.fp7.scase.customUtilities.UniqueIdProducer;

public class PIMHypermediaLink
{

    private int iPIMHypermediaLinkId;
    private ResourceController oTargetResourceController;
    private ResourceControllerManager oTargetResourceControllerManager;
    private AlgoResourceController oTargetAlgoResourceController;
    private String strLinkCRUDVerb;
    private String strLinkType;

    public PIMHypermediaLink(String strLinkCRUDVerb, String strLinkType, ResourceController oTargetResourceController) {
        this.iPIMHypermediaLinkId = UniqueIdProducer.getNewUniqueId();
        this.strLinkCRUDVerb = strLinkCRUDVerb;
        this.strLinkType = strLinkType;
        this.oTargetResourceController = oTargetResourceController;
    }

    public PIMHypermediaLink(String strLinkCRUDVerb, String strLinkType,
            ResourceControllerManager oTargetResourceControllerManager)
    {
        this.iPIMHypermediaLinkId = UniqueIdProducer.getNewUniqueId();
        this.strLinkCRUDVerb = strLinkCRUDVerb;
        this.strLinkType = strLinkType;
        this.oTargetResourceControllerManager = oTargetResourceControllerManager;
    }

    public PIMHypermediaLink(String strLinkCRUDVerb, String strLinkType,
            AlgoResourceController oTargetAlgoResourceController)
    {
        this.iPIMHypermediaLinkId = UniqueIdProducer.getNewUniqueId();
        this.strLinkCRUDVerb = strLinkCRUDVerb;
        this.strLinkType = strLinkType;
        this.oTargetAlgoResourceController = oTargetAlgoResourceController;
    }

    public int getPIMHypermediaLinkId()
    {
        return this.iPIMHypermediaLinkId;
    }

    public String getLinkCRUDVerb()
    {
        return this.strLinkCRUDVerb;
    }

    public String getLinkType()
    {
        return this.strLinkType;
    }

    public ResourceController getTargetResourceController()
    {
        return this.oTargetResourceController;
    }

    public ResourceControllerManager getTargetResourceControllerManager()
    {
        return this.oTargetResourceControllerManager;
    }

    public AlgoResourceController getTargetAlgoResourceController()
    {
        return this.oTargetAlgoResourceController;
    }
}
