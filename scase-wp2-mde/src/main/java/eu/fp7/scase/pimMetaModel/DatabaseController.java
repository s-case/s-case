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

import eu.fp7.scase.customUtilities.UniqueIdProducer;

public class DatabaseController
{

    private static DatabaseController oDatabaseController = new DatabaseController();
    private int iDatabaseControllerId;
    private String strDatabaseControllerName;
    private ArrayList<RDBMSActivity> listOfDatabaseControllerActivities;

    private DatabaseController() {
        this.iDatabaseControllerId = UniqueIdProducer.getNewUniqueId();
        this.strDatabaseControllerName = "RDBMSController";
        this.listOfDatabaseControllerActivities = new ArrayList<RDBMSActivity>();
    }

    public static DatabaseController getDatabaseControllerHandle()
    {
        return oDatabaseController;
    }

    public int getDatabaseControllerId()
    {
        return this.iDatabaseControllerId;
    }

    public String getDatabaseControllerName()
    {
        return this.strDatabaseControllerName;
    }

    public ArrayList<RDBMSActivity> getDatabaseControllerActivities()
    {
        return this.listOfDatabaseControllerActivities;
    }

    public void printDatabaseController()
    {
        System.out.println("The " + this.strDatabaseControllerName
                + " is added to PIM because it is PIM meta-model constraint");
        printDatabaseControllerActivities();
    }

    private void printDatabaseControllerActivities()
    {
        for (int n = 0; n < this.listOfDatabaseControllerActivities.size(); n++) {
            this.listOfDatabaseControllerActivities.get(n).printRDBMSActivity();
        }
    }
}
