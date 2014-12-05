/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.psmMetaModel;

import java.util.ArrayList;

import eu.fp7.scase.customUtilities.UniqueIdProducer;
import eu.fp7.scase.pimMetaModel.FunctionParameter;

public abstract class JavaFunction
{

    private int iJavaFunctionId;
    private String strJavaFunctionName;
    private ArrayList<FunctionParameter> listOfJavaFunctionParamters;
    private FunctionParameter oJavaFunctionReturnParameter;

    public JavaFunction(String strFunctionName) {
        this.iJavaFunctionId = UniqueIdProducer.getNewUniqueId();
        this.strJavaFunctionName = strFunctionName;
        this.listOfJavaFunctionParamters = new ArrayList<FunctionParameter>();
    }

    public int getJavaFunctionId()
    {
        return this.iJavaFunctionId;
    }

    public String getJavaFunctionName()
    {
        return this.strJavaFunctionName;
    }

    public ArrayList<FunctionParameter> getFunctionParameters()
    {
        return this.listOfJavaFunctionParamters;
    }

    public FunctionParameter getReturnParameter()
    {
        return this.oJavaFunctionReturnParameter;
    }

    public void setReturnParameter(FunctionParameter oReturnParameter)
    {
        this.oJavaFunctionReturnParameter = oReturnParameter;
    }

    public void printJavaFunction()
    {
        System.out.println("Java Function return type: "
                + (this.oJavaFunctionReturnParameter == null ? "void" : this.oJavaFunctionReturnParameter
                        .getParameterType()));
        for (int n = 0; n < this.listOfJavaFunctionParamters.size(); n++) {
            this.listOfJavaFunctionParamters.get(n).printFunctionParameter();
        }
    }
}
