/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.cimGenerator;

import java.util.ArrayList;
import java.util.Iterator;

import eu.fp7.scase.cimMetaModel.Resource;
import eu.fp7.scase.cimMetaModel.SystemCIM;
import eu.fp7.scase.inputParsing.IInputParserImplementation;

public class CoreCIMProducer
        extends ACIMProducer
{

    public CoreCIMProducer(IInputParserImplementation ioInputParserImplementation) {
        super(ioInputParserImplementation);
    }

    @Override
    public SystemCIM produceCIM()
    {

        createAllCIMResources();
        populateCIMResources();
        createCIMResourcesRelations();
        createResourceURIs();

        oSystemCIM.printAllResources();

        return oSystemCIM;
    }

    private void createAllCIMResources()
    {
        ArrayList<String> listOfCIMResources = getInputParserImplementation().parseResourceList();
        Iterator<String> iteratorOfCIMResources = listOfCIMResources.iterator();

        while (iteratorOfCIMResources.hasNext()) {
            Resource oResource = new Resource((String) iteratorOfCIMResources.next());
            oSystemCIM.addResourceToCIM(oResource);
        }
    }

    private void populateCIMResources()
    {

        for (int n = 0; n < oSystemCIM.getListOfCIMResource().size(); n++) {
            oSystemCIM.getListOfCIMResource().set(n,
                    getInputParserImplementation().parseResourceByName(oSystemCIM.getListOfCIMResource().get(n)));
        }
    }

    private void createCIMResourcesRelations()
    {
        createCIMOutgoingResourceRelations();
        createCIMIncomingResourceRelations();
    }

    private void createCIMOutgoingResourceRelations()
    {

        for (int n = 0; n < oSystemCIM.getListOfCIMResource().size(); n++) {

            ArrayList<String> listOfCIMResourceOutgoingRelations = getInputParserImplementation()
                    .parseResourceOutgoingRelations(oSystemCIM.getListOfCIMResource().get(n));
            Iterator<String> iteratorOfCIMResourceOutgoingRelations = listOfCIMResourceOutgoingRelations.iterator();

            while (iteratorOfCIMResourceOutgoingRelations.hasNext()) {
                oSystemCIM.getListOfCIMResource()
                        .set(n,
                                oSystemCIM
                                        .getListOfCIMResource()
                                        .get(n)
                                        .addOutgoingRelation(
                                                getResourceIdByName(iteratorOfCIMResourceOutgoingRelations.next())));
            }
        }
    }

    private void createCIMIncomingResourceRelations()
    {

        for (int n = 0; n < oSystemCIM.getListOfCIMResource().size(); n++) {
            ArrayList<String> listOfCIMResourceIncomingRelations = getInputParserImplementation()
                    .parseResourceIncomingRelations(oSystemCIM.getListOfCIMResource().get(n));
            Iterator<String> iteratorOfCIMResourceIncomingRelations = listOfCIMResourceIncomingRelations.iterator();

            while (iteratorOfCIMResourceIncomingRelations.hasNext()) {
                oSystemCIM.getListOfCIMResource()
                        .set(n,
                                oSystemCIM
                                        .getListOfCIMResource()
                                        .get(n)
                                        .addIncomingRelation(
                                                getResourceIdByName(iteratorOfCIMResourceIncomingRelations.next())));
            }
        }
    }

    private int getResourceIdByName(String strResourceName)
    {
        Iterator<Resource> iteratorOfCIMResources = oSystemCIM.getListOfCIMResource().iterator();

        while (iteratorOfCIMResources.hasNext()) {
            Resource oCurrentResource = iteratorOfCIMResources.next();
            if (oCurrentResource.getResourceName().equalsIgnoreCase(strResourceName)) {// if this
                                                                                       // resource
                                                                                       // has the
                                                                                       // same name
                                                                                       // as the one
                                                                                       // we are
                                                                                       // looking
                                                                                       // for
                return oCurrentResource.getResourceId(); // return the id
            }
        }

        return -1;
    }

    private void createResourceURIs()
    {
        for (int n = 0; n < this.oSystemCIM.getListOfCIMResource().size(); n++) {
            this.oSystemCIM
                    .getListOfCIMResource()
                    .get(n)
                    .setResourceURI(
                            String.format("/%s", this.oSystemCIM.getListOfCIMResource().get(n).getResourceName()));
        }
    }
}
