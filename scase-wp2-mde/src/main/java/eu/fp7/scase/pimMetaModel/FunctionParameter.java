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

public class FunctionParameter
{

    private int iFunctionParameterId;
    private String strFunctionParameterName;
    private String strFunctionParameterType;
    private boolean bIsUnique;
    private boolean bIsReturnParameter;

    public FunctionParameter(String strFunctionParameterName, String strFunctionParameterType, boolean bIsUnique,
            boolean bIsReturnParameter)
    {
        this.iFunctionParameterId = UniqueIdProducer.getNewUniqueId();
        this.strFunctionParameterName = strFunctionParameterName;
        this.strFunctionParameterType = strFunctionParameterType;
        this.bIsReturnParameter = bIsReturnParameter;
        this.bIsUnique = bIsUnique;
    }

    public int getFunctionParameterId()
    {
        return this.iFunctionParameterId;
    }

    public String getFunctionParameterName()
    {
        return this.strFunctionParameterName;
    }

    public String getParameterType()
    {
        return this.strFunctionParameterType;
    }

    public boolean getFunctionParameterUniqueness()
    {
        return this.bIsUnique;
    }

    public boolean getFunctionParameterType()
    {
        return this.bIsReturnParameter;
    }

    public void printFunctionParameter()
    {
        System.out.println("Function Parameter Name: " + this.strFunctionParameterName);
        System.out.println("Parameter Type: " + this.strFunctionParameterType);
        System.out.println("Function Parameter Uniqueness: " + (this.bIsUnique == true ? "Unique" : "Multiple"));
        System.out.println("Function Parameter Type: "
                + (this.bIsReturnParameter == true ? "Return Parameter" : "Input Parameter"));
    }

}
