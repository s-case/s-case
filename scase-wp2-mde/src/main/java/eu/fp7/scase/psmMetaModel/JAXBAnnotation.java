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

public class JAXBAnnotation
{

    private int iJAXBAnnotationId;
    private String strJAXBAnnotationText;

    public JAXBAnnotation(String strJAXBAnnotationText) {
        this.iJAXBAnnotationId = UniqueIdProducer.getNewUniqueId();
        this.strJAXBAnnotationText = strJAXBAnnotationText;
    }

    public int getJAXBAnnotationId()
    {
        return this.iJAXBAnnotationId;
    }

    public String getJAXBAnnotationText()
    {
        return this.strJAXBAnnotationText;
    }
}
