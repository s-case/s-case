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
import java.util.Iterator;

public class SystemPIM
{

    private ArrayList<ResourceModel> listOfPIMResourceModels;
    private ArrayList<ResourceModelManager> listOfPIMResourceModelManagers;
    private ArrayList<ResourceController> listOfPIMResourceControllers;
    private ArrayList<ResourceControllerManager> listOfPIMResourceControllerManagers;
    private ArrayList<AlgoResourceModel> listOfPIMAlgoResourceModels;
    private ArrayList<AlgoResourceController> listOfPIMAlgoResourceControllers;
    private ArrayList<RDBMSTable> listOfPIMRDBMSTables;
    private DatabaseController oPIMDatabaseController;

    public SystemPIM() {
        this.listOfPIMResourceModels = new ArrayList<ResourceModel>();
        this.listOfPIMResourceModelManagers = new ArrayList<ResourceModelManager>();
        this.listOfPIMResourceControllers = new ArrayList<ResourceController>();
        this.listOfPIMResourceControllerManagers = new ArrayList<ResourceControllerManager>();
        this.listOfPIMAlgoResourceModels = new ArrayList<AlgoResourceModel>();
        this.listOfPIMAlgoResourceControllers = new ArrayList<AlgoResourceController>();
        this.listOfPIMRDBMSTables = new ArrayList<RDBMSTable>();
        this.oPIMDatabaseController = DatabaseController.getDatabaseControllerHandle();
    }

    public ArrayList<ResourceModel> getPIMResourceModels()
    {
        return this.listOfPIMResourceModels;
    }

    public ArrayList<ResourceModelManager> getPIMResourceModelManagers()
    {
        return this.listOfPIMResourceModelManagers;
    }

    public ArrayList<ResourceController> getPIMResourceControllers()
    {
        return this.listOfPIMResourceControllers;
    }

    public ArrayList<ResourceControllerManager> getPIMResourceControllerManagers()
    {
        return this.listOfPIMResourceControllerManagers;
    }

    public ArrayList<AlgoResourceModel> getPIMAlgoResourceModels()
    {
        return this.listOfPIMAlgoResourceModels;
    }

    public ArrayList<AlgoResourceController> getPIMAlgoResourceControllers()
    {
        return this.listOfPIMAlgoResourceControllers;
    }

    public ArrayList<RDBMSTable> getPIMRDBMSTables()
    {
        return this.listOfPIMRDBMSTables;
    }

    public DatabaseController getPIMDatabaseController()
    {
        return this.oPIMDatabaseController;
    }

    public void printResourceModels()
    {
        Iterator<ResourceModel> iteratorOfPIMResourceModels = this.listOfPIMResourceModels.iterator();

        while (iteratorOfPIMResourceModels.hasNext()) {
            iteratorOfPIMResourceModels.next().printResourceModel();
        }
    }

    public void printResourceModelManagers()
    {
        Iterator<ResourceModelManager> iteratorOfPIMResourceModelManagers = this.listOfPIMResourceModelManagers
                .iterator();

        while (iteratorOfPIMResourceModelManagers.hasNext()) {
            iteratorOfPIMResourceModelManagers.next().printResourceModelManager();
        }
    }

    public void printResourceControllers()
    {
        Iterator<ResourceController> iteratorOfPIMResourceControllers = this.listOfPIMResourceControllers.iterator();

        while (iteratorOfPIMResourceControllers.hasNext()) {
            iteratorOfPIMResourceControllers.next().printResourceController();
        }
    }

    public void printResourceControllerManagers()
    {
        Iterator<ResourceControllerManager> iteratorOfPIMResourceControllerManagers = this.listOfPIMResourceControllerManagers
                .iterator();

        while (iteratorOfPIMResourceControllerManagers.hasNext()) {
            iteratorOfPIMResourceControllerManagers.next().printResourceControllerManager();
            ;
        }
    }

    public void printAlgoResourceModels()
    {
        Iterator<AlgoResourceModel> iteratorOfPIMAlgoResourceModels = this.listOfPIMAlgoResourceModels.iterator();

        while (iteratorOfPIMAlgoResourceModels.hasNext()) {
            iteratorOfPIMAlgoResourceModels.next().printAlgoResourceModel();
        }
    }

    public void printAlgoResourceControllers()
    {
        Iterator<AlgoResourceController> iteratorOfPIMAlgoResourceControllers = this.listOfPIMAlgoResourceControllers
                .iterator();

        while (iteratorOfPIMAlgoResourceControllers.hasNext()) {
            iteratorOfPIMAlgoResourceControllers.next().printAlgoResourceController();
        }
    }

    public void printRDBMSTables()
    {
        for (int n = 0; n < this.listOfPIMRDBMSTables.size(); n++) {
            this.listOfPIMRDBMSTables.get(n).printRDBMSTable();
        }
    }

    public void printDatabaseController()
    {
        this.oPIMDatabaseController.printDatabaseController();
    }
}
