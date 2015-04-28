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

public class AlgoResourceController
{

    private int iAlgoResourceControllerId;
    private String strAlgoResourceControllerName;
    private Resource oParentCIMResource;
    private AlgoResourceModel oAssociatedAlgoResourceModel;
    private ArrayList<ResourceControllerCRUDActivity> listOfControllerCRUDActivities;
    private ArrayList<CRUDActivityHandler> listOfControllerCRUDActivityHandlers;
    private String strAlgoResourceControllerURI;

    public AlgoResourceController() {
        this.iAlgoResourceControllerId = UniqueIdProducer.getNewUniqueId();
        this.listOfControllerCRUDActivities = new ArrayList<ResourceControllerCRUDActivity>();
        this.listOfControllerCRUDActivityHandlers = new ArrayList<CRUDActivityHandler>();
    }

    public AlgoResourceController(String strAlgoResourceControllerName, Resource oParentCIMResource,
            AlgoResourceModel oAssociatedAlgoResourceModel)
    {
        this.iAlgoResourceControllerId = UniqueIdProducer.getNewUniqueId();
        this.strAlgoResourceControllerName = strAlgoResourceControllerName;
        this.oParentCIMResource = oParentCIMResource;
        this.oAssociatedAlgoResourceModel = oAssociatedAlgoResourceModel;
        this.listOfControllerCRUDActivities = new ArrayList<ResourceControllerCRUDActivity>();
        this.listOfControllerCRUDActivityHandlers = new ArrayList<CRUDActivityHandler>();
    }

    public int getAlgoResourceControllerId()
    {
        return this.iAlgoResourceControllerId;
    }

    public void setAlgoResourceControllerName(String strAlgoResourceControllerName)
    {
        this.strAlgoResourceControllerName = strAlgoResourceControllerName;
    }

    public String getAlgoResourceControllerName()
    {
        return this.strAlgoResourceControllerName;
    }

    public Resource getParentCIMResource()
    {
        return this.oParentCIMResource;
    }

    public AlgoResourceModel getAssociatedAlgoResourceModel()
    {
        return this.oAssociatedAlgoResourceModel;
    }

    public void printAlgoResourceController()
    {
        System.out.println("Resource Algorithmic Controller " + this.getAlgoResourceControllerName() + " with id "
                + this.getAlgoResourceControllerId() + " and associated algorithmic resource model "
                + this.getAssociatedAlgoResourceModel().getAlgoResourceModelName() + " is added to the PIM because "
                + this.getParentCIMResource().getResourceName() + " exists");
        System.out.println("URI: " + this.strAlgoResourceControllerURI);
        printAlgoControllerCRUDActivities();
        printAlgoControllerCRUDActivityHandlers();
    }

    public void addCRUDActivities()
    {
        for (int n = 0; n < this.oParentCIMResource.getResourceCRUDActivities().size(); n++) {
            // since only CREATE OR READ activities are allowed for PIM algorithmic resource
            // controllers from the meta-model
            if (this.oParentCIMResource.getResourceCRUDActivities().get(n).getActivityCRUDVerb()
                    .equalsIgnoreCase("READ")) { // check if the CRUD verb is the READ one
                this.listOfControllerCRUDActivities.add(new ResourceControllerCRUDActivity("READ",
                        this.oParentCIMResource.getResourceCRUDActivities().get(n), "read"
                                + this.oParentCIMResource.getResourceName(), "/", null)); // and add
                                                                                          // one
                                                                                          // READ
                                                                                          // CRUD
                                                                                          // activity
                                                                                          // to PIM
            } else if (this.oParentCIMResource.getResourceCRUDActivities().get(n).getActivityCRUDVerb()
                    .equalsIgnoreCase("CREATE")) { // check if the CRUD verb is the CREATE one
                this.listOfControllerCRUDActivities.add(new ResourceControllerCRUDActivity("CREATE",
                        this.oParentCIMResource.getResourceCRUDActivities().get(n), "create"
                                + this.oParentCIMResource.getResourceName(), "/", null)); // and add
                                                                                          // one
                                                                                          // CREATE
                                                                                          // CRUD
                                                                                          // activity
                                                                                          // to PIM
            }
        }
        // check if the PIM meta-model constraint that there exists at least one CRUD activity per
        // algorithmic resource controller is satisfied
        if (this.listOfControllerCRUDActivities.size() == 0) { // if no CRUD activities where added
            try {
                throw new Exception(
                        "Error! Disatisfied PIM meta-model constraint: The algorithmic resource controller "
                                + this.strAlgoResourceControllerName + " does not have any CRUD activites!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<ResourceControllerCRUDActivity> getAlgoControllerCRUDActivities()
    {
        return this.listOfControllerCRUDActivities;
    }

    public void addCRUDActivityHandlers()
    {
        for (int n = 0; n < this.listOfControllerCRUDActivities.size(); n++) {
            this.listOfControllerCRUDActivityHandlers.add(new CRUDActivityHandler(String.format("%sActivityHandler",
                    this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb()),
                    this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb(),
                    this.listOfControllerCRUDActivities.get(n), this));
        }
    }

    public ArrayList<CRUDActivityHandler> getAlgoControllerCRUDActivityHandlers()
    {
        return this.listOfControllerCRUDActivityHandlers;
    }

    private void printAlgoControllerCRUDActivities()
    {
        for (int n = 0; n < this.listOfControllerCRUDActivities.size(); n++) {
            System.out.println("CRUD Activity: " + this.listOfControllerCRUDActivities.get(n).getCRUDActivityName()
                    + " is added to the PIM because "
                    + this.listOfControllerCRUDActivities.get(n).getParentCRUDActivity().getActivityCRUDVerb()
                    + " Activity exists in CIM");
        }
    }

    private void printAlgoControllerCRUDActivityHandlers()
    {
        for (int n = 0; n < this.listOfControllerCRUDActivityHandlers.size(); n++) {
            this.listOfControllerCRUDActivityHandlers.get(n).printCRUDActivityHandler();
        }
    }

    public String getAlgoResourceControllerURI()
    {
        return this.strAlgoResourceControllerURI;
    }

    public void setAlgoResourceControllerURI(String strAlgoResourceControllerURI)
    {
        this.strAlgoResourceControllerURI = strAlgoResourceControllerURI;
    }

    public void createSiblingHypermediaLinks()
    {
        for (int n = 0; n < this.listOfControllerCRUDActivityHandlers.size(); n++) {
            this.listOfControllerCRUDActivityHandlers
                    .get(n)
                    .getHypermediaFunction()
                    .getHypermediaLinkList()
                    .add(new PIMHypermediaLink(this.listOfControllerCRUDActivityHandlers.get(n)
                            .getCRUDActivityHandlerVerb(), "Sibling", this));
        }
    }

    public void createSpecificParentHypermediaLinks(ResourceController oParentResourceController)
    {
        for (int n = 0; n < this.listOfControllerCRUDActivityHandlers.size(); n++) {
            if (oParentResourceController.hasReadActivity()/*
                                                            * && (oParentResourceController.
                                                            * getAssociatedResourceModel
                                                            * ().getResourceModelId() ==
                                                            * this.listOfControllerCRUDActivityHandlers
                                                            * .get(n).getIncomingResourceModel().
                                                            * getResourceModelId())
                                                            */) {
                this.listOfControllerCRUDActivityHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                        .add(new PIMHypermediaLink("READ", "Parent", oParentResourceController));
            }
            if (oParentResourceController.hasUpdateActivity()/*
                                                              * && (oParentResourceController.
                                                              * getAssociatedResourceModel
                                                              * ().getResourceModelId() == this.
                                                              * listOfControllerCRUDActivityHandlers
                                                              * .get(n).getIncomingResourceModel().
                                                              * getResourceModelId())
                                                              */) {
                this.listOfControllerCRUDActivityHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                        .add(new PIMHypermediaLink("UPDATE", "Parent", oParentResourceController));
            }
            if (oParentResourceController.hasDeleteActivity()/*
                                                              * && (oParentResourceController.
                                                              * getAssociatedResourceModel
                                                              * ().getResourceModelId() == this.
                                                              * listOfControllerCRUDActivityHandlers
                                                              * .get(n).getIncomingResourceModel().
                                                              * getResourceModelId())
                                                              */) {
                this.listOfControllerCRUDActivityHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                        .add(new PIMHypermediaLink("DELETE", "Parent", oParentResourceController));
            }
        }
    }

    public boolean hasIncomingRelations()
    {
        if (this.getAssociatedAlgoResourceModel().getIncomingResourceModelRelations().size() == 0) { // if
                                                                                                     // this
                                                                                                     // resource
                                                                                                     // is
                                                                                                     // not
                                                                                                     // related
                                                                                                     // resource
                                                                                                     // of
                                                                                                     // any
                                                                                                     // other
            return false;
        } else {
            return true;
        }
    }
}
