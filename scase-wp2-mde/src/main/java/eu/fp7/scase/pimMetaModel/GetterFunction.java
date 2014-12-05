/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.pimMetaModel;

public class GetterFunction
        extends APIMFunction
{

    private PIMComponentProperty oAccessedProperty;

    public GetterFunction(PIMComponentProperty oAccessedProperty) {
        super(String.format("get%s", oAccessedProperty.getPIMComponentProeprtyName()));
        this.setFunctionReturnParameter(new FunctionParameter("", oAccessedProperty.getPIMComponentPropertyType(),
                oAccessedProperty.getPIMComponentPropertyUniqueness(), true));
        this.oAccessedProperty = oAccessedProperty;
    }

    public PIMComponentProperty getAccessedProperty()
    {
        return this.oAccessedProperty;
    }

    @Override
    public void printPIMFunction()
    {
        System.out.println("The getter function: " + this.getPIMFunctionName()
                + " is added to PIM to get the value of " + this.oAccessedProperty.getPIMComponentProeprtyName());
        super.printPIMFunction();
    }
}
