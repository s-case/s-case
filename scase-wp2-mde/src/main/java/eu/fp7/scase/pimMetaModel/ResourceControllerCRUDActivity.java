/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.pimMetaModel;

import eu.fp7.scase.cimMetaModel.CRUDActivity;
import eu.fp7.scase.customUtilities.UniqueIdProducer;

public class ResourceControllerCRUDActivity
{

    private int iResourceControllerCRUDActivityId;
    private String strActivityCRUDVerb;
    private CRUDActivity oParentCRUDActivity;
    private String strCRUDActivityName;
    private String strCRUDActivityURI;
    private ResourceModel oIncomingResourceModel; // bad name, it refers to the resource model of
                                                  // which this one is related resource

    public ResourceControllerCRUDActivity(String strActivityCRUDVerb, String strCRUDActivityName,
            String strCRUDActivityURI)
    {
        this.iResourceControllerCRUDActivityId = UniqueIdProducer.getNewUniqueId();
        this.strActivityCRUDVerb = strActivityCRUDVerb;
        this.strCRUDActivityName = strCRUDActivityName;
        this.strCRUDActivityURI = strCRUDActivityURI;
    }

    public ResourceControllerCRUDActivity(String strActivityCRUDVerb, String strCRUDActivityName,
            String strCRUDActivityURI, ResourceModel oIncomingResourceModel)
    {
        this.iResourceControllerCRUDActivityId = UniqueIdProducer.getNewUniqueId();
        this.strActivityCRUDVerb = strActivityCRUDVerb;
        this.strCRUDActivityName = strCRUDActivityName;
        this.strCRUDActivityURI = strCRUDActivityURI;
        this.oIncomingResourceModel = oIncomingResourceModel;
    }

    public ResourceControllerCRUDActivity(String strActivityCRUDVerb, CRUDActivity oParentCRUDActivity,
            String strCRUDActivityName, String strCRUDActivityURI, ResourceModel oIncomingResourceModel)
    {
        this.iResourceControllerCRUDActivityId = UniqueIdProducer.getNewUniqueId();
        this.strActivityCRUDVerb = strActivityCRUDVerb;
        this.oParentCRUDActivity = oParentCRUDActivity;
        this.strCRUDActivityName = strCRUDActivityName;
        this.strCRUDActivityURI = strCRUDActivityURI;
        this.oIncomingResourceModel = oIncomingResourceModel;
    }

    public int getResourceControllerCRUDActivityId()
    {
        return this.iResourceControllerCRUDActivityId;
    }

    public String getResourceControllerCRUDActivityVerb()
    {
        return this.strActivityCRUDVerb;
    }

    public CRUDActivity getParentCRUDActivity()
    {
        return this.oParentCRUDActivity;
    }

    public String getCRUDActivityName()
    {
        return this.strCRUDActivityName;
    }

    public String getCRUDActivityURI()
    {
        return this.strCRUDActivityURI;
    }

    public ResourceModel getIncomingResourceModel()
    {
        return this.oIncomingResourceModel;
    }
}
