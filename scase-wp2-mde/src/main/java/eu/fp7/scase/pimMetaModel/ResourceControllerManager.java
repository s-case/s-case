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

public class ResourceControllerManager
{

    private int iResourceControllerManagerId;
    private String strResourceControllerManagerName;
    private Resource oParentCIMResource;
    private ResourceModelManager oAssociatedResourceModelManager;
    private ArrayList<ResourceControllerCRUDActivity> listOfControllerCRUDActivities;
    private ArrayList<CRUDActivityHandler> listOfControllerCRUDActivitiyHandlers;
    private String strResourceControllerManagerURI;
    private ResourceController oAssociatedResourceController;

    public ResourceControllerManager() {
        this.iResourceControllerManagerId = UniqueIdProducer.getNewUniqueId();
        this.listOfControllerCRUDActivities = new ArrayList<ResourceControllerCRUDActivity>();
        this.listOfControllerCRUDActivitiyHandlers = new ArrayList<CRUDActivityHandler>();
    }

    public ResourceControllerManager(String strResourceControllerManagerName, Resource oParentCIMResource,
            ResourceModelManager oAssociatedResourceModelManager, ResourceController oAssociatedResourceController)
    {
        this.iResourceControllerManagerId = UniqueIdProducer.getNewUniqueId();
        this.strResourceControllerManagerName = strResourceControllerManagerName;
        this.oParentCIMResource = oParentCIMResource;
        this.oAssociatedResourceModelManager = oAssociatedResourceModelManager;
        this.listOfControllerCRUDActivities = new ArrayList<ResourceControllerCRUDActivity>();
        this.listOfControllerCRUDActivitiyHandlers = new ArrayList<CRUDActivityHandler>();
        this.oAssociatedResourceController = oAssociatedResourceController;
    }

    public int getResourceControllerManagerId()
    {
        return this.iResourceControllerManagerId;
    }

    public void setResourceControllerManagerName(String strResourceControllerManagerName)
    {
        this.strResourceControllerManagerName = strResourceControllerManagerName;
    }

    public String getResourceControllerManagerName()
    {
        return this.strResourceControllerManagerName;
    }

    public Resource getParentCIMResource()
    {
        return this.oParentCIMResource;
    }

    public ResourceModelManager getAssociatedResourceModelManager()
    {
        return this.oAssociatedResourceModelManager;
    }

    public ArrayList<ResourceControllerCRUDActivity> getControllerCRUDActivities()
    {
        return this.listOfControllerCRUDActivities;
    }

    public void addCRUDActivities()
    {
        // since there exists PIM meta-model constraint that states that every resource controller
        // manager must have exactly one CREATE CRUD activity and exactly one READ CRUD activity
        String strTargetIdentifier = "";
        if (!this.hasIncomingRelations()) {
            this.listOfControllerCRUDActivities.add(new ResourceControllerCRUDActivity("CREATE", "create"
                    + this.oParentCIMResource.getResourceName(), "/")); // one CREATE CRUD activity
                                                                        // is added
            this.listOfControllerCRUDActivities.add(new ResourceControllerCRUDActivity("READ", "read"
                    + this.oParentCIMResource.getResourceName(), "/"));// and one READ CRUD activity
                                                                       // is added to the PIM
            this.oAssociatedResourceController.addCRUDActivities("", "/", null);
        } else if (this.hasUniqueIncomingRelation()) {
            this.listOfControllerCRUDActivities.add(new ResourceControllerCRUDActivity("CREATE", "create"
                    + this.oParentCIMResource.getResourceName(), "/", this.getAssociatedResourceModelManager()
                    .getIncomingResourceModelRelations().get(0))); // one CREATE CRUD activity is
                                                                   // added
            this.listOfControllerCRUDActivities.add(new ResourceControllerCRUDActivity("READ", "read"
                    + this.oParentCIMResource.getResourceName(), "/", this.getAssociatedResourceModelManager()
                    .getIncomingResourceModelRelations().get(0)));// and one READ CRUD activity is
                                                                  // added to the PIM
            this.oAssociatedResourceController.addCRUDActivities("", "/", this.getAssociatedResourceModelManager()
                    .getIncomingResourceModelRelations().get(0));
        } else {
            for (int n = 0; n < this.getAssociatedResourceModelManager().getIncomingResourceModelRelations().size(); n++) {
                this.listOfControllerCRUDActivities.add(new ResourceControllerCRUDActivity("CREATE", "create"
                        + this.oAssociatedResourceModelManager.getIncomingResourceModelRelations().get(n)
                                .getParentCIMResource().getResourceName() + this.oParentCIMResource.getResourceName(),
                        String.format("/%s/{%s}/%s", this.oAssociatedResourceModelManager
                                .getIncomingResourceModelRelations().get(n).getParentCIMResource().getResourceName(),
                                this.oAssociatedResourceModelManager.getIncomingResourceModelRelations().get(n)
                                        .getModelPrimaryIdentifierName(), this.oParentCIMResource.getResourceName()),
                        this.getAssociatedResourceModelManager().getIncomingResourceModelRelations().get(n))); // one
                                                                                                               // CREATE
                                                                                                               // CRUD
                                                                                                               // activity
                                                                                                               // is
                                                                                                               // added
                this.listOfControllerCRUDActivities.add(new ResourceControllerCRUDActivity("READ", "read"
                        + this.oAssociatedResourceModelManager.getIncomingResourceModelRelations().get(n)
                                .getParentCIMResource().getResourceName() + this.oParentCIMResource.getResourceName(),
                        String.format("/%s/{%s}/%s", this.oAssociatedResourceModelManager
                                .getIncomingResourceModelRelations().get(n).getParentCIMResource().getResourceName(),
                                this.oAssociatedResourceModelManager.getIncomingResourceModelRelations().get(n)
                                        .getModelPrimaryIdentifierName(), this.oParentCIMResource.getResourceName()),
                        this.getAssociatedResourceModelManager().getIncomingResourceModelRelations().get(n)));// and
                                                                                                              // one
                                                                                                              // READ
                                                                                                              // CRUD
                                                                                                              // activity
                                                                                                              // is
                                                                                                              // added
                                                                                                              // to
                                                                                                              // the
                                                                                                              // PIM
                strTargetIdentifier = String
                        .format("%s%s",
                                (this.oAssociatedResourceModelManager.getRelatedResourceModel().getParentCIMResource()
                                        .getResourceId() == this.oAssociatedResourceModelManager
                                        .getIncomingResourceModelRelations().get(n).getParentCIMResource()
                                        .getResourceId() ? "target" : ""), this.oAssociatedResourceModelManager
                                        .getRelatedResourceModel().getModelPrimaryIdentifierName());
                this.oAssociatedResourceController.addCRUDActivities(this.oAssociatedResourceModelManager
                        .getIncomingResourceModelRelations().get(n).getParentCIMResource().getResourceName(), String
                        .format("/%s/{%s}/%s/{%s}", this.oAssociatedResourceModelManager
                                .getIncomingResourceModelRelations().get(n).getParentCIMResource().getResourceName(),
                                this.oAssociatedResourceModelManager.getIncomingResourceModelRelations().get(n)
                                        .getModelPrimaryIdentifierName(), this.oParentCIMResource.getResourceName(),
                                strTargetIdentifier), this.getAssociatedResourceModelManager()
                        .getIncomingResourceModelRelations().get(n));
            }
        }
    }

    public ArrayList<CRUDActivityHandler> getControllerCRUDActivityHandlers()
    {
        return this.listOfControllerCRUDActivitiyHandlers;
    }

    public void addCRUDActivityHandlers()
    {
        for (int n = 0; n < this.listOfControllerCRUDActivities.size(); n++) {
            if (!this.hasIncomingRelations()) {
                if (this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb()
                        .equalsIgnoreCase("READ")) {
                    this.listOfControllerCRUDActivitiyHandlers.add(new CRUDActivityHandler(String.format(
                            "%sListHandler", this.listOfControllerCRUDActivities.get(n).getCRUDActivityName()),
                            this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb(),
                            this.listOfControllerCRUDActivities.get(n), this));
                } else {
                    this.listOfControllerCRUDActivitiyHandlers.add(new CRUDActivityHandler(String.format("%sHandler",
                            this.listOfControllerCRUDActivities.get(n).getCRUDActivityName()),
                            this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb(),
                            this.listOfControllerCRUDActivities.get(n), this));
                }
            } else {
                if (this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb()
                        .equalsIgnoreCase("READ")) {
                    this.listOfControllerCRUDActivitiyHandlers.add(new CRUDActivityHandler(String.format(
                            "%sListHandler", this.listOfControllerCRUDActivities.get(n).getCRUDActivityName()),
                            this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb(),
                            this.listOfControllerCRUDActivities.get(n), this, this.listOfControllerCRUDActivities
                                    .get(n).getIncomingResourceModel()));
                } else {
                    this.listOfControllerCRUDActivitiyHandlers.add(new CRUDActivityHandler(String.format("%sHandler",
                            this.listOfControllerCRUDActivities.get(n).getCRUDActivityName()),
                            this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb(),
                            this.listOfControllerCRUDActivities.get(n), this, this.listOfControllerCRUDActivities
                                    .get(n).getIncomingResourceModel()));
                }
            }
        }
    }

    public void printResourceControllerManager()
    {
        System.out.println("Resource Controller Manager " + this.getResourceControllerManagerName() + " with id "
                + this.getResourceControllerManagerId() + " and associated resource model manager "
                + this.getAssociatedResourceModelManager().getResourceModelManagerName()
                + " is added to the PIM because " + this.getParentCIMResource().getResourceName() + " exists");
        System.out.println("URI: " + this.strResourceControllerManagerURI);
        printCRUDActivities();
        printCRUDActivityHandlers();
    }

    private void printCRUDActivities()
    {
        for (int n = 0; n < this.listOfControllerCRUDActivities.size(); n++) {
            System.out.println("CRUD Activity: " + this.listOfControllerCRUDActivities.get(n).getCRUDActivityName()
                    + " is added to PIM because of PIM meta-model constraint");
            System.out.println("CRUD Activity URI: " + this.listOfControllerCRUDActivities.get(n).getCRUDActivityURI());

        }
    }

    private void printCRUDActivityHandlers()
    {
        for (int n = 0; n < this.listOfControllerCRUDActivitiyHandlers.size(); n++) {
            this.listOfControllerCRUDActivitiyHandlers.get(n).printCRUDActivityHandler();
        }
    }

    public void addRDBMSActivities()
    {
        for (int n = 0; n < this.listOfControllerCRUDActivities.size(); n++) {
            if (this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb()
                    .equalsIgnoreCase("read")) {
                DatabaseController
                        .getDatabaseControllerHandle()
                        .getDatabaseControllerActivities()
                        .add(new RDBMSActivity(this.listOfControllerCRUDActivities.get(n).getCRUDActivityName()
                                .replace("read", "select").concat("List"), "SELECT",
                                this.listOfControllerCRUDActivities.get(n)));
            } else if (this.listOfControllerCRUDActivities.get(n).getResourceControllerCRUDActivityVerb()
                    .equalsIgnoreCase("create")) {
                DatabaseController
                        .getDatabaseControllerHandle()
                        .getDatabaseControllerActivities()
                        .add(new RDBMSActivity(this.listOfControllerCRUDActivities.get(n).getCRUDActivityName()
                                .replace("create", "insert"), "INSERT", this.listOfControllerCRUDActivities.get(n)));
            }
        }
    }

    public String getResourceControllerManagerURI()
    {
        return this.strResourceControllerManagerURI;
    }

    public void setResourceControllerManagerURI(String strResourceControllerManagerURI)
    {
        this.strResourceControllerManagerURI = strResourceControllerManagerURI;
    }

    public boolean hasIncomingRelations()
    {
        if (this.getAssociatedResourceModelManager().getIncomingResourceModelRelations().size() == 0) { // if
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

    public boolean hasUniqueIncomingRelation()
    {
        if (this.getAssociatedResourceModelManager().getIncomingResourceModelRelations().size() == 1) { // if
                                                                                                        // this
                                                                                                        // resource
                                                                                                        // is
                                                                                                        // related
                                                                                                        // of
                                                                                                        // exactly
                                                                                                        // one
                                                                                                        // other
                                                                                                        // resource
            return true;
        } else {
            return false;
        }
    }

    public ResourceController getAssociatedResourceController()
    {
        return this.oAssociatedResourceController;
    }

    public void createAssociatedResourceControllerURI()
    {
        this.oAssociatedResourceController.setResourceControllerURI(String.format("%s/{%s}",
                this.strResourceControllerManagerURI,
                this.oAssociatedResourceController.getModelPrimaryIdentifierName()));
    }

    public void createSiblingHypermediaLinks()
    {
        for (int n = 0; n < this.listOfControllerCRUDActivitiyHandlers.size(); n++) {
            // add a create hypermedia link from this resoruce controller manager to itself
            this.listOfControllerCRUDActivitiyHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                    .add(new PIMHypermediaLink("CREATE", "Sibling", this));
            // add a read hypermedia link from this resource controller manager to itself
            this.listOfControllerCRUDActivitiyHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                    .add(new PIMHypermediaLink("READ", "Sibling", this));
        }
    }

    public void createChildrenHypermediaLinks()
    {
        for (int n = 0; n < this.listOfControllerCRUDActivitiyHandlers.size(); n++) {
            if (this.listOfControllerCRUDActivitiyHandlers.get(n).getCRUDActivityHandlerVerb().equalsIgnoreCase("READ")) {
                this.listOfControllerCRUDActivitiyHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                        .add(new PIMHypermediaLink("READ", "Children", this.oAssociatedResourceController));
            } else { // else it is CREATE (since the PIM meta-model allows a resource controller
                     // manager to have either READ or CREATE activities
                     // add a read hypermedia link from this resource controller manager to the
                     // associated resource controller if that resource controller has a read
                     // activity
                if (this.oAssociatedResourceController.hasReadActivity()) {
                    this.listOfControllerCRUDActivitiyHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                            .add(new PIMHypermediaLink("READ", "Children", this.oAssociatedResourceController));
                }
                if (this.oAssociatedResourceController.hasUpdateActivity()) {
                    this.listOfControllerCRUDActivitiyHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                            .add(new PIMHypermediaLink("UPDATE", "Children", this.oAssociatedResourceController));
                }
                if (this.oAssociatedResourceController.hasDeleteActivity()) {
                    this.listOfControllerCRUDActivitiyHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                            .add(new PIMHypermediaLink("DELETE", "Children", this.oAssociatedResourceController));
                }
            }
        }
    }

    public void createSpecificParentHypermediaLinks(ResourceController oParentResourceController)
    {
        for (int n = 0; n < this.listOfControllerCRUDActivitiyHandlers.size(); n++) {
            if (oParentResourceController.hasReadActivity()
                    && (oParentResourceController.getAssociatedResourceModel().getResourceModelId() == this.listOfControllerCRUDActivitiyHandlers
                            .get(n).getIncomingResourceModel().getResourceModelId())) {
                this.listOfControllerCRUDActivitiyHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                        .add(new PIMHypermediaLink("READ", "Parent", oParentResourceController));
            }
            if (oParentResourceController.hasUpdateActivity()
                    && (oParentResourceController.getAssociatedResourceModel().getResourceModelId() == this.listOfControllerCRUDActivitiyHandlers
                            .get(n).getIncomingResourceModel().getResourceModelId())) {
                this.listOfControllerCRUDActivitiyHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                        .add(new PIMHypermediaLink("UPDATE", "Parent", oParentResourceController));
            }
            if (oParentResourceController.hasDeleteActivity()
                    && (oParentResourceController.getAssociatedResourceModel().getResourceModelId() == this.listOfControllerCRUDActivitiyHandlers
                            .get(n).getIncomingResourceModel().getResourceModelId())) {
                this.listOfControllerCRUDActivitiyHandlers.get(n).getHypermediaFunction().getHypermediaLinkList()
                        .add(new PIMHypermediaLink("DELETE", "Parent", oParentResourceController));
            }
        }
    }

    public void addAssociatedResourceControllerParentHypermediaLinks()
    {
        this.oAssociatedResourceController.createParentHypermediaLinks(this);
    }
}
