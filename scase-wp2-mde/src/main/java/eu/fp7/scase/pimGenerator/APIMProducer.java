/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.pimGenerator;

import eu.fp7.scase.cimMetaModel.SystemCIM;
import eu.fp7.scase.pimMetaModel.SystemPIM;

public abstract class APIMProducer
{

    protected SystemCIM oSystemCIM;
    protected SystemPIM oSystemPIM;

    public APIMProducer(SystemCIM oSystemCIM) {
        this.oSystemPIM = new SystemPIM();
        this.oSystemCIM = oSystemCIM;
    }

    public abstract SystemPIM producePIM();

}
