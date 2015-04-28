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

public class JAXRSAnnotation
{

    private int iJAXRSAnnotationId;
    private String strJAXRSAnnotationText;

    public JAXRSAnnotation(String strJAXRSAnnotationText) {
        this.iJAXRSAnnotationId = UniqueIdProducer.getNewUniqueId();
        this.strJAXRSAnnotationText = strJAXRSAnnotationText;
    }

    public int getJAXRSAnnotationId()
    {
        return this.iJAXRSAnnotationId;
    }

    public String getJAXRSAnnotationText()
    {
        return this.strJAXRSAnnotationText;
    }
}
