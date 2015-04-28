/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.pimMetaModel;

public class ResourceRepresentationParseFunction
        extends APIMFunction
{

    private ResourceModel oRelatedResourceModel;
    private ResourceInputRepresentation oRelatedResourceInputRepresentation;
    private AlgoResourceModel oAlgoRelatedResourceModel;

    public ResourceRepresentationParseFunction(ResourceModel oRelatedResourceModel,
            ResourceInputRepresentation oRelatedResourceInputRepresentation)
    {
        super(String.format("unmarshall%s", oRelatedResourceModel.getResourceModelName()));
        this.setFunctionReturnParameter(new FunctionParameter("-", oRelatedResourceModel.getResourceModelName(), true,
                true));
        this.getFunctionParameters().add(
                new FunctionParameter(
                        String.format(
                                "o%s",
                                oRelatedResourceInputRepresentation.getResourceInputRepresentation().substring(
                                        oRelatedResourceInputRepresentation.getResourceInputRepresentation().indexOf(
                                                "/") + 1)), oRelatedResourceInputRepresentation
                                .getResourceInputRepresentation().substring(
                                        oRelatedResourceInputRepresentation.getResourceInputRepresentation().indexOf(
                                                "/") + 1), true, false));
        this.oRelatedResourceModel = oRelatedResourceModel;
        this.oRelatedResourceInputRepresentation = oRelatedResourceInputRepresentation;
    }

    public ResourceRepresentationParseFunction(AlgoResourceModel oAlgoRelatedResourceModel,
            ResourceInputRepresentation oRelatedResourceInputRepresentation)
    {
        super(String.format("unmarshall%s", oAlgoRelatedResourceModel.getAlgoResourceModelName()));
        this.setFunctionReturnParameter(new FunctionParameter("-",
                oAlgoRelatedResourceModel.getAlgoResourceModelName(), true, true));
        this.getFunctionParameters().add(
                new FunctionParameter(
                        String.format(
                                "o%s",
                                oRelatedResourceInputRepresentation.getResourceInputRepresentation().substring(
                                        oRelatedResourceInputRepresentation.getResourceInputRepresentation().indexOf(
                                                "/") + 1)), oRelatedResourceInputRepresentation
                                .getResourceInputRepresentation().substring(
                                        oRelatedResourceInputRepresentation.getResourceInputRepresentation().indexOf(
                                                "/") + 1), true, false));
        this.oAlgoRelatedResourceModel = oAlgoRelatedResourceModel;
        this.oRelatedResourceInputRepresentation = oRelatedResourceInputRepresentation;
    }

    public ResourceModel getRelatedResourceModel()
    {
        return this.oRelatedResourceModel;
    }

    public AlgoResourceModel getAlgoRelatedResourceModel()
    {
        return this.oAlgoRelatedResourceModel;
    }

    public ResourceInputRepresentation getRelatedResourceInputRepresentation()
    {
        return this.oRelatedResourceInputRepresentation;
    }

    @Override
    public void printPIMFunction()
    {
        System.out.println("The input representation unmarshalling function: "
                + this.getPIMFunctionName()
                + " is added to PIM to unmarshall incoming representations of "
                + this.oRelatedResourceInputRepresentation.getResourceInputRepresentation()
                + " to "
                + (this.oAlgoRelatedResourceModel == null ? this.oRelatedResourceModel.getResourceModelName()
                        : this.oAlgoRelatedResourceModel.getAlgoResourceModelName()));
        super.printPIMFunction();
    }
}
