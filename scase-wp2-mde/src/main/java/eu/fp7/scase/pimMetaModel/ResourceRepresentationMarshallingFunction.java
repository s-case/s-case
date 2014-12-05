/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.pimMetaModel;

public class ResourceRepresentationMarshallingFunction
        extends APIMFunction
{

    private ResourceModel oRelatedResourceModel;
    private ResourceOutputRepresentation oRelatedResourceOutputRepresentation;
    private AlgoResourceModel oAlgoRelatedResourceModel;

    public ResourceRepresentationMarshallingFunction(ResourceModel oRelatedResourceModel,
            ResourceOutputRepresentation oRelatedResourceOutputRepresentation)
    {
        super(String.format("marshall%s", oRelatedResourceModel.getResourceModelName()));
        this.setFunctionReturnParameter(new FunctionParameter("", oRelatedResourceOutputRepresentation
                .getResourceOutputRepresentation().substring(
                        oRelatedResourceOutputRepresentation.getResourceOutputRepresentation().indexOf("/") + 1), true,
                true));
        this.getFunctionParameters().add(
                new FunctionParameter(String.format("o%s", oRelatedResourceModel.getResourceModelName()),
                        oRelatedResourceModel.getResourceModelName(), true, false));
        this.oRelatedResourceModel = oRelatedResourceModel;
        this.oRelatedResourceOutputRepresentation = oRelatedResourceOutputRepresentation;
    }

    public ResourceRepresentationMarshallingFunction(AlgoResourceModel oAlgoRelatedResourceModel,
            ResourceOutputRepresentation oRelatedResourceOutputRepresentation)
    {
        super(String.format("marshall%s", oAlgoRelatedResourceModel.getAlgoResourceModelName()));
        this.setFunctionReturnParameter(new FunctionParameter("", oRelatedResourceOutputRepresentation
                .getResourceOutputRepresentation().substring(
                        oRelatedResourceOutputRepresentation.getResourceOutputRepresentation().indexOf("/") + 1), true,
                true));
        this.getFunctionParameters().add(
                new FunctionParameter(String.format("o%s", oAlgoRelatedResourceModel.getAlgoResourceModelName()),
                        oAlgoRelatedResourceModel.getAlgoResourceModelName(), true, false));
        this.oAlgoRelatedResourceModel = oAlgoRelatedResourceModel;
        this.oRelatedResourceOutputRepresentation = oRelatedResourceOutputRepresentation;
    }

    public ResourceModel getRelatedResourceModel()
    {
        return this.oRelatedResourceModel;
    }

    public AlgoResourceModel getAlgoRelatedResourceModel()
    {
        return this.oAlgoRelatedResourceModel;
    }

    public ResourceOutputRepresentation getRelatedResourceOutputRepresentation()
    {
        return this.oRelatedResourceOutputRepresentation;
    }

    @Override
    public void printPIMFunction()
    {
        System.out.println("The input representation marshalling function: "
                + this.getPIMFunctionName()
                + " is added to PIM to marshall outgoing resource models of type "
                + (this.oAlgoRelatedResourceModel == null ? this.oRelatedResourceModel.getResourceModelName()
                        : this.oAlgoRelatedResourceModel.getAlgoResourceModelName()) + " to "
                + this.oRelatedResourceOutputRepresentation.getResourceOutputRepresentation()
                + " output representations");
        super.printPIMFunction();
    }
}
