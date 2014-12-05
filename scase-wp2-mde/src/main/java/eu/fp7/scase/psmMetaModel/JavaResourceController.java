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
import eu.fp7.scase.pimMetaModel.ResourceController;

public class JavaResourceController
{

    private int iJavaResourceControllerId;
    private ResourceController oPIMParentResourceController;
    private String strJavaResourceControllerName;
    private String strJavaResourceControllerURI;
    private JAXRSAnnotation oControllerPathAnnotation;
    private ArrayList<HTTPActivity> listOfJavaControllerHTTPActivities;
    private JavaResourceModel oRelatedJavaResourceModel;
    private ArrayList<HTTPActivityHandler> listOfJavaControllerHTTPActivityHandlers;

    public JavaResourceController(ResourceController oResourceController) {
        this.iJavaResourceControllerId = UniqueIdProducer.getNewUniqueId();
        this.oPIMParentResourceController = oResourceController;
        this.strJavaResourceControllerName = String.format("Java%s", oResourceController.getResourceControllerName());
        this.strJavaResourceControllerURI = oResourceController.getResourceControllerURI();
        this.oControllerPathAnnotation = new JAXRSAnnotation(String.format("@Path(\"%s\")",
                this.strJavaResourceControllerURI));
        this.listOfJavaControllerHTTPActivities = new ArrayList<HTTPActivity>();
        this.listOfJavaControllerHTTPActivityHandlers = new ArrayList<HTTPActivityHandler>();
    }

    public int getJavaResourceControllerId()
    {
        return this.iJavaResourceControllerId;
    }

    public ResourceController getPIMParentResourceController()
    {
        return this.oPIMParentResourceController;
    }

    public String getJavaResourceControllerName()
    {
        return this.strJavaResourceControllerName;
    }

    public String getJavaResourceControllerURI()
    {
        return this.strJavaResourceControllerURI;
    }

    public JAXRSAnnotation getControllerPathAnnotation()
    {
        return this.oControllerPathAnnotation;
    }

    public ArrayList<HTTPActivity> getJavaControllerHTTPActivities()
    {
        return this.listOfJavaControllerHTTPActivities;
    }

    public ArrayList<HTTPActivityHandler> getJavaControllerHTTPActivityHandlers()
    {
        return this.listOfJavaControllerHTTPActivityHandlers;
    }

    public void setRelatedJavaResourceModel(JavaResourceModel oRelatedJavaResourceModel)
    {
        this.oRelatedJavaResourceModel = oRelatedJavaResourceModel;
        this.oRelatedJavaResourceModel.setAssociatedJavaResourceController(this);
    }

    public JavaResourceModel getRelatedJavaResourceModel()
    {
        return this.oRelatedJavaResourceModel;
    }

    public void addHTTPActivities()
    {
        for (int n = 0; n < this.oPIMParentResourceController.getResourceControllerCRUDActivities().size(); n++) {
            this.listOfJavaControllerHTTPActivities.add(new HTTPActivity(this.oPIMParentResourceController
                    .getResourceControllerCRUDActivities().get(n), transformCRUDVerb(this.oPIMParentResourceController
                    .getResourceControllerCRUDActivities().get(n).getResourceControllerCRUDActivityVerb()),
                    transformCRUDActivityName(this.oPIMParentResourceController.getResourceControllerCRUDActivities()
                            .get(n).getCRUDActivityName(), this.oPIMParentResourceController
                            .getResourceControllerCRUDActivities().get(n).getResourceControllerCRUDActivityVerb())));
        }
    }

    public void addHTTPActivityHandlers()
    {
        for (int n = 0; n < this.oPIMParentResourceController.getControllerCRUDActivityHandlers().size(); n++) {
            this.listOfJavaControllerHTTPActivityHandlers.add(new HTTPActivityHandler(this.oPIMParentResourceController
                    .getControllerCRUDActivityHandlers().get(n), transformCRUDVerb(this.oPIMParentResourceController
                    .getControllerCRUDActivityHandlers().get(n).getCRUDActivityHandlerVerb()),
                    transformCRUDActivityHandlerName(this.oPIMParentResourceController
                            .getControllerCRUDActivityHandlers().get(n).getCRUDActivityHandlerName(),
                            this.oPIMParentResourceController.getControllerCRUDActivityHandlers().get(n)
                                    .getCRUDActivityHandlerVerb()), this));
        }
    }

    private String transformCRUDVerb(String strCRUDVerb)
    {
        if (strCRUDVerb.equalsIgnoreCase("CREATE")) {
            return "POST";
        } else if (strCRUDVerb.equalsIgnoreCase("READ")) {
            return "GET";
        } else if (strCRUDVerb.equalsIgnoreCase("UPDATE")) {
            return "PUT";
        } else if (strCRUDVerb.equalsIgnoreCase("DELETE")) {
            return "DELETE";
        } else {

            try {
                throw new Exception("Unkown CRUD verb! Possibly the PIM is corrupted!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private String transformCRUDActivityName(String strCRUDActivityName, String strCRUDVerb)
    {
        if (strCRUDVerb.equalsIgnoreCase("CREATE")) {
            return String.format("post%s", strCRUDActivityName.substring(6));
        } else if (strCRUDVerb.equalsIgnoreCase("READ")) {
            return String.format("get%s", strCRUDActivityName.substring(4));
        } else if (strCRUDVerb.equalsIgnoreCase("UPDATE")) {
            return String.format("put%s", strCRUDActivityName.substring(6));
        } else if (strCRUDVerb.equalsIgnoreCase("DELETE")) {
            return strCRUDActivityName;
        } else {

            try {
                throw new Exception("Unkown CRUD verb! Possibly the PIM is corrupted!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private String transformCRUDActivityHandlerName(String strCRUDActivityHandlerName, String strCRUDVerb)
    {
        return transformCRUDActivityName(strCRUDActivityHandlerName, strCRUDVerb);
    }

    public void createHibernateActivities()
    {
        if (hasPutHTTPActivities()) {
            HibernateController
                    .getHibernateControllerHandle()
                    .getControllerHibernateActivities()
                    .add(new HibernateActivity(String.format("put%s", this.oRelatedJavaResourceModel
                            .getPIMParentResourceModel().getParentCIMResource().getResourceName()), "PUT", this));
        }
        if (hasGetHTTPActivities()) {
            HibernateController
                    .getHibernateControllerHandle()
                    .getControllerHibernateActivities()
                    .add(new HibernateActivity(String.format("get%s", this.oRelatedJavaResourceModel
                            .getPIMParentResourceModel().getParentCIMResource().getResourceName()), "GET", this));
        }
        if (hasDeleteHTTPActivities()) {
            HibernateController
                    .getHibernateControllerHandle()
                    .getControllerHibernateActivities()
                    .add(new HibernateActivity(String.format("delete%s", this.oRelatedJavaResourceModel
                            .getPIMParentResourceModel().getParentCIMResource().getResourceName()), "DELETE", this));
        }
    }

    private boolean hasPutHTTPActivities()
    {
        for (int n = 0; n < this.listOfJavaControllerHTTPActivities.size(); n++) {
            if (this.listOfJavaControllerHTTPActivities.get(n).getHTTPActivityVerb().equalsIgnoreCase("PUT")) {
                return true;
            }
        }
        return false;
    }

    private boolean hasGetHTTPActivities()
    {
        for (int n = 0; n < this.listOfJavaControllerHTTPActivities.size(); n++) {
            if (this.listOfJavaControllerHTTPActivities.get(n).getHTTPActivityVerb().equalsIgnoreCase("GET")) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDeleteHTTPActivities()
    {
        for (int n = 0; n < this.listOfJavaControllerHTTPActivities.size(); n++) {
            if (this.listOfJavaControllerHTTPActivities.get(n).getHTTPActivityVerb().equalsIgnoreCase("DELETE")) {
                return true;
            }
        }
        return false;
    }

    public void printJavaResourceController()
    {
        System.out.println("The Java Resource Controller " + this.strJavaResourceControllerName
                + " with related java resource model " + this.oRelatedJavaResourceModel.getJavaResourceModelName()
                + " is added to PSM because " + this.oPIMParentResourceController.getResourceControllerName()
                + " exists in PIM");
        System.out.println("URI: " + this.strJavaResourceControllerURI);
        System.out.println("JAXRS controller @PATH annotation: "
                + this.oControllerPathAnnotation.getJAXRSAnnotationText());
        printHTTPActivities();
        printHTTPActivityHandlers();
    }

    private void printHTTPActivities()
    {
        for (int n = 0; n < this.listOfJavaControllerHTTPActivities.size(); n++) {
            this.listOfJavaControllerHTTPActivities.get(n).printHTTPActivity();
        }
    }

    private void printHTTPActivityHandlers()
    {
        for (int n = 0; n < this.listOfJavaControllerHTTPActivityHandlers.size(); n++) {
            this.listOfJavaControllerHTTPActivityHandlers.get(n).printHTTPActivityHandler();
        }
    }

    public void addJAXRSInputOutputAnnotations()
    {
        for (int n = 0; n < this.listOfJavaControllerHTTPActivities.size(); n++) {
            if (this.listOfJavaControllerHTTPActivities.get(n).getHTTPActivityVerb().equalsIgnoreCase("POST")
                    || this.listOfJavaControllerHTTPActivities.get(n).getHTTPActivityVerb().equalsIgnoreCase("PUT")) {
                this.listOfJavaControllerHTTPActivities.get(n).createJAXRSConsumeAnnotation(
                        this.oRelatedJavaResourceModel.getPIMParentResourceModel().getResourceInputRepresentations());
            }
            this.listOfJavaControllerHTTPActivities.get(n).createJAXRSProduceAnnotaiton(
                    this.oRelatedJavaResourceModel.getPIMParentResourceModel().getResourceOutputRepresentations());
        }
    }

    public boolean hasGetActivity()
    {
        for (int n = 0; n < this.listOfJavaControllerHTTPActivities.size(); n++) {
            if (this.listOfJavaControllerHTTPActivities.get(n).getHTTPActivityVerb().equalsIgnoreCase("GET")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDeleteActivity()
    {
        for (int n = 0; n < this.listOfJavaControllerHTTPActivities.size(); n++) {
            if (this.listOfJavaControllerHTTPActivities.get(n).getHTTPActivityVerb().equalsIgnoreCase("DELETE")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPutActivity()
    {
        for (int n = 0; n < this.listOfJavaControllerHTTPActivities.size(); n++) {
            if (this.listOfJavaControllerHTTPActivities.get(n).getHTTPActivityVerb().equalsIgnoreCase("PUT")) {
                return true;
            }
        }
        return false;
    }
}
