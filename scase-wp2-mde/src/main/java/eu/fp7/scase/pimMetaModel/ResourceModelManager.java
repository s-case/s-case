/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.pimMetaModel;

import java.util.ArrayList;

import eu.fp7.scase.cimMetaModel.Resource;
import eu.fp7.scase.customUtilities.UniqueIdProducer;

public class ResourceModelManager
{

    private int iResourceModelManagerId;
    private String strResourceModelManagerName;
    private Resource oParentCIMResource;
    private ResourceModel oRelatedResourceModel;
    private ArrayList<ResourceInputRepresentation> listOfResourceModelInputRepresentations;
    private ArrayList<ResourceOutputRepresentation> listOfResourceModelOutputRepresentations;
    private ArrayList<ResourceModel> listOfIncomingResourceModelRelations;
    private ArrayList<AlgoResourceModel> listOfIncomingAlgoResourceModelRelations;
    private ArrayList<PIMComponentProperty> listOfResourceModelManagerProperties;
    private ArrayList<APIMFunction> listOfResourceModelManagerFunctions;

    public ResourceModelManager() {
        this.iResourceModelManagerId = UniqueIdProducer.getNewUniqueId();
        listOfResourceModelInputRepresentations = new ArrayList<ResourceInputRepresentation>();
        this.listOfResourceModelOutputRepresentations = new ArrayList<ResourceOutputRepresentation>();
        this.listOfIncomingResourceModelRelations = new ArrayList<ResourceModel>();
        this.listOfIncomingAlgoResourceModelRelations = new ArrayList<AlgoResourceModel>();
        this.listOfResourceModelManagerProperties = new ArrayList<PIMComponentProperty>();
        this.listOfResourceModelManagerFunctions = new ArrayList<APIMFunction>();
    }

    public ResourceModelManager(String strResourceModelManagerName, Resource oParentCIMResource,
            ResourceModel oRelatedResourceModel)
    {
        this.iResourceModelManagerId = UniqueIdProducer.getNewUniqueId();
        this.strResourceModelManagerName = strResourceModelManagerName;
        this.oParentCIMResource = oParentCIMResource;
        this.oRelatedResourceModel = oRelatedResourceModel;
        listOfResourceModelInputRepresentations = new ArrayList<ResourceInputRepresentation>();
        this.listOfResourceModelOutputRepresentations = new ArrayList<ResourceOutputRepresentation>();
        this.listOfIncomingResourceModelRelations = new ArrayList<ResourceModel>();
        this.listOfIncomingAlgoResourceModelRelations = new ArrayList<AlgoResourceModel>();
        this.listOfResourceModelManagerProperties = new ArrayList<PIMComponentProperty>();
        this.listOfResourceModelManagerFunctions = new ArrayList<APIMFunction>();
    }

    public int getResourceModelManagerId()
    {
        return this.iResourceModelManagerId;
    }

    public void setResourceModelManagerName(String strResourceModelManagerName)
    {
        this.strResourceModelManagerName = strResourceModelManagerName;
    }

    public String getResourceModelManagerName()
    {
        return this.strResourceModelManagerName;
    }

    public Resource getParentCIMResource()
    {
        return this.oParentCIMResource;
    }

    public ResourceModel getRelatedResourceModel()
    {
        return this.oRelatedResourceModel;
    }

    public ArrayList<ResourceInputRepresentation> getResourceInputRepresentations()
    {
        return this.listOfResourceModelInputRepresentations;
    }

    public ArrayList<ResourceOutputRepresentation> getResourceOutputRepresentations()
    {
        return this.listOfResourceModelOutputRepresentations;
    }

    public void addResourceInputRepresentations()
    {
        for (int n = 0; n < oParentCIMResource.getResourceInputRepresentations().size(); n++) {
            this.listOfResourceModelInputRepresentations.add(new ResourceInputRepresentation(oParentCIMResource
                    .getResourceInputRepresentations().get(n)));
        }
        this.oRelatedResourceModel.addResourceInputRepresentations();
    }

    public void addResourceOutputRepresentations()
    {
        for (int n = 0; n < oParentCIMResource.getResourceOutputRepresentations().size(); n++) {
            this.listOfResourceModelOutputRepresentations.add(new ResourceOutputRepresentation(oParentCIMResource
                    .getResourceOutputRepresentations().get(n)));
        }
        this.oRelatedResourceModel.addResourceOutputRepresentations();
    }

    public ArrayList<ResourceModel> getIncomingResourceModelRelations()
    {
        return this.listOfIncomingResourceModelRelations;
    }

    public ArrayList<AlgoResourceModel> getIncomingAlgoResourceModelRelations()
    {
        return this.listOfIncomingAlgoResourceModelRelations;
    }

    public ArrayList<PIMComponentProperty> getResourceModelManagerProperties()
    {
        return this.listOfResourceModelManagerProperties;
    }

    public boolean isRelatedOfAlgoResourceModel(AlgoResourceModel oAlgoResourceModel)
    {
        for (int n = 0; n < oAlgoResourceModel.getParentCIMResource().getResourceOutgoingRelations().size(); n++) {
            if (this.getParentCIMResource().getResourceId() == oAlgoResourceModel.getParentCIMResource()
                    .getResourceOutgoingRelations().get(n)) {
                return true;
            }
        }

        return false;
    }

    public void printResourceModelManager()
    {
        System.out.println("Resource Model manager " + this.getResourceModelManagerName() + " with id "
                + this.getResourceModelManagerId() + " and related resource model "
                + this.getRelatedResourceModel().getResourceModelName() + " is added to the PIM because "
                + this.getParentCIMResource().getResourceName() + " exists");
        printResourceModelManagerProperties();
        printResourceModelManagerFunctions();
        printResourceInputRepresentations();
        printResourceOutputRepresentations();
        printIncomingRelations();
    }

    private void printResourceInputRepresentations()
    {
        for (int n = 0; n < this.listOfResourceModelInputRepresentations.size(); n++) {
            System.out.println("Input Representation: "
                    + this.listOfResourceModelInputRepresentations.get(n).getResourceInputRepresentation());
        }
    }

    private void printResourceOutputRepresentations()
    {
        for (int n = 0; n < this.listOfResourceModelOutputRepresentations.size(); n++) {
            System.out.println("Output Representation: "
                    + this.listOfResourceModelOutputRepresentations.get(n).getResourceOutputRepresentation());
        }
    }

    private void printIncomingRelations()
    {
        printIncomingResourceModelRelations();
        printIncomingAlgoResourceModelRelations();
    }

    private void printIncomingResourceModelRelations()
    {
        for (int n = 0; n < this.listOfIncomingResourceModelRelations.size(); n++) {
            System.out.println("Incoming Resource Model Relation from: "
                    + this.listOfIncomingResourceModelRelations.get(n).getResourceModelName());
        }
    }

    private void printIncomingAlgoResourceModelRelations()
    {
        for (int n = 0; n < this.listOfIncomingAlgoResourceModelRelations.size(); n++) {
            System.out.println("Incoming Algorithmic Model Relation from: "
                    + this.listOfIncomingAlgoResourceModelRelations.get(n).getAlgoResourceModelName());
        }
    }

    private void printResourceModelManagerProperties()
    {
        for (int n = 0; n < this.listOfResourceModelManagerProperties.size(); n++) {
            this.listOfResourceModelManagerProperties.get(n).printPIMComponentProperty();
        }
    }

    public void addLinkListProperty()
    {
        this.listOfResourceModelManagerProperties.add(new PIMComponentProperty("linkList", "HypermediaLink", false));
    }

    public void addPropertyAccessFunctions()
    {
        for (int n = 0; n < this.listOfResourceModelManagerProperties.size(); n++) {
            this.listOfResourceModelManagerFunctions.add(new SetterFunction(this.listOfResourceModelManagerProperties
                    .get(n)));
            this.listOfResourceModelManagerFunctions.add(new GetterFunction(this.listOfResourceModelManagerProperties
                    .get(n)));
        }
    }

    public ArrayList<APIMFunction> getResourceModelManagerFunctions()
    {
        return this.listOfResourceModelManagerFunctions;
    }

    private void printResourceModelManagerFunctions()
    {
        for (int n = 0; n < this.listOfResourceModelManagerFunctions.size(); n++) {
            this.listOfResourceModelManagerFunctions.get(n).printPIMFunction();
        }
    }

    public void addRepresentationUnmarshallingFunctions()
    {
        for (int n = 0; n < this.listOfResourceModelInputRepresentations.size(); n++) {
            this.listOfResourceModelManagerFunctions.add(new ResourceRepresentationParseFunction(
                    this.oRelatedResourceModel, this.listOfResourceModelInputRepresentations.get(n)));
        }
    }

    public void addRepresentationMarshallingFunctions()
    {
        for (int n = 0; n < this.listOfResourceModelOutputRepresentations.size(); n++) {
            this.listOfResourceModelManagerFunctions.add(new ResourceRepresentationMarshallingFunction(
                    this.oRelatedResourceModel, this.listOfResourceModelOutputRepresentations.get(n)));
        }
    }
}
