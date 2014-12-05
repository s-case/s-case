/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.cimGenerator;

import eu.fp7.scase.cimMetaModel.SystemCIM;
import eu.fp7.scase.inputParsing.IInputParserImplementation;

public abstract class ACIMProducer
{
    protected SystemCIM oSystemCIM;
    private IInputParserImplementation ioInputParserImplementation;

    public ACIMProducer(IInputParserImplementation inputParserImplementation) {
        this.ioInputParserImplementation = inputParserImplementation;
        oSystemCIM = new SystemCIM();
    }

    public SystemCIM getSystemCIM()
    {
        return this.oSystemCIM;
    }

    public IInputParserImplementation getInputParserImplementation()
    {
        return this.ioInputParserImplementation;
    }

    public abstract SystemCIM produceCIM();
}
