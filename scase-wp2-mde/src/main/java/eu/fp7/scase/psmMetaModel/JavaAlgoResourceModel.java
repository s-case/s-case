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
import eu.fp7.scase.pimMetaModel.AlgoResourceModel;

public class JavaAlgoResourceModel
{

    private int iJavaAlgoResourceModelId;
    private String strJavaAlgoResourceModelName;
    private AlgoResourceModel oParentPIMAlgoResourceModel;
    private ArrayList<PSMComponentProperty> listOfAlgoModelProperties;
    private ArrayList<JavaSetterFunction> listOfAlgoModelSetterFunctions;
    private ArrayList<JavaGetterFunction> listOfAlgoModelGetterFunctions;

    public JavaAlgoResourceModel(AlgoResourceModel oAlgoResourceModel) {
        this.iJavaAlgoResourceModelId = UniqueIdProducer.getNewUniqueId();
        this.oParentPIMAlgoResourceModel = oAlgoResourceModel;
        this.strJavaAlgoResourceModelName = oAlgoResourceModel.getAlgoResourceModelName();
        this.listOfAlgoModelProperties = new ArrayList<PSMComponentProperty>();
        this.listOfAlgoModelSetterFunctions = new ArrayList<JavaSetterFunction>();
        this.listOfAlgoModelGetterFunctions = new ArrayList<JavaGetterFunction>();
    }

    public int getJavaAlgoResourceModelId()
    {
        return this.iJavaAlgoResourceModelId;
    }

    public String getJavaAlgoResourceModelName()
    {
        return this.strJavaAlgoResourceModelName;
    }

    public AlgoResourceModel getParentPIMAlgoResourceModel()
    {
        return this.oParentPIMAlgoResourceModel;
    }

    public ArrayList<PSMComponentProperty> getAlgoModelProperties()
    {
        return this.listOfAlgoModelProperties;
    }

    public ArrayList<JavaSetterFunction> getAlgoModelSetterFunctions()
    {
        return this.listOfAlgoModelSetterFunctions;
    }

    public ArrayList<JavaGetterFunction> getAlgoModelGetterFunctions()
    {
        return this.listOfAlgoModelGetterFunctions;
    }

    public void printJavaAlgoResourceModel()
    {
        System.out.println("The Java Algorithmic Resource Model: " + this.strJavaAlgoResourceModelName
                + " is added to PSM because " + this.oParentPIMAlgoResourceModel.getAlgoResourceModelName()
                + " exists in PIM");
        printAlgoModelProperties();
        printAlgoModelAccessFunctions();
    }

    private void printAlgoModelAccessFunctions()
    {
        for (int n = 0; n < this.listOfAlgoModelProperties.size(); n++) {
            this.listOfAlgoModelGetterFunctions.get(n).printJavaFunction();
            this.listOfAlgoModelSetterFunctions.get(n).printJavaFunction();
        }
    }

    public void addAlgoModelProperties()
    {
        for (int n = 0; n < this.oParentPIMAlgoResourceModel.getAlgoResourceModelProperties().size(); n++) {
            this.listOfAlgoModelProperties.add(new PSMComponentProperty(this.oParentPIMAlgoResourceModel
                    .getAlgoResourceModelProperties().get(n)));
        }
    }

    private void printAlgoModelProperties()
    {
        for (int n = 0; n < this.listOfAlgoModelProperties.size(); n++) {
            this.listOfAlgoModelProperties.get(n).printPSMComponentProperty();
        }
    }

    public void addAlgoModelAccessFunctions()
    {
        for (int n = 0; n < this.listOfAlgoModelProperties.size(); n++) {
            this.listOfAlgoModelSetterFunctions
                    .add(new JavaSetterFunction(this.listOfAlgoModelProperties.get(n), false));
            this.listOfAlgoModelGetterFunctions.add(new JavaGetterFunction(this.listOfAlgoModelProperties.get(n)));
        }
    }
}
