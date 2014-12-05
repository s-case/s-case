/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.cimMetaModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eu.fp7.scase.cimMetaModel.Resource;

public class SystemCIM
{
    private List<Resource> listOfCIMResources = new ArrayList<Resource>();

    public SystemCIM() {
    }

    public void setListOfCIMResources(ArrayList<Resource> newListOfCIMResources)
    {
        this.listOfCIMResources = newListOfCIMResources;
    }

    public List<Resource> getListOfCIMResource()
    {
        return this.listOfCIMResources;
    }

    public void addResourceToCIM(Resource newResource)
    {
        listOfCIMResources.add(newResource);
    }

    public void printAllResources()
    {
        Iterator<Resource> oResourceListIterator = listOfCIMResources.iterator();

        while (oResourceListIterator.hasNext()) {
            Resource oCurrentResource = oResourceListIterator.next();
            oCurrentResource.printResourceDetails();
        }
    }
}
