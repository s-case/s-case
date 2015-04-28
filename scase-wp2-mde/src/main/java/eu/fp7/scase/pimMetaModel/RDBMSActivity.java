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

public class RDBMSActivity
{

    private int iRDBMSActivityId;
    private String strRDBMSActivityName;
    private String strRDBMSActivityVerb;
    private ResourceControllerCRUDActivity oParentCRUDActivity;

    public RDBMSActivity(String strRDBMSActivityName, String strRDBMSActivityVerb,
            ResourceControllerCRUDActivity oParentCRUDActivity)
    {
        this.iRDBMSActivityId = UniqueIdProducer.getNewUniqueId();
        this.strRDBMSActivityName = strRDBMSActivityName;
        this.strRDBMSActivityVerb = strRDBMSActivityVerb;
        this.oParentCRUDActivity = oParentCRUDActivity;
    }

    public int getRDBMSActivityId()
    {
        return this.iRDBMSActivityId;
    }

    public String getRDBMSActivityName()
    {
        return this.strRDBMSActivityName;
    }

    public String getRDBMSActivityVerb()
    {
        return this.strRDBMSActivityVerb;
    }

    public ResourceControllerCRUDActivity getParentCRUDActivity()
    {
        return this.oParentCRUDActivity;
    }

    public void printRDBMSActivity()
    {
        System.out.println("RDBMS Activity: " + this.strRDBMSActivityName + " is added to PIM because "
                + this.oParentCRUDActivity.getCRUDActivityName() + " exists");
    }
}
