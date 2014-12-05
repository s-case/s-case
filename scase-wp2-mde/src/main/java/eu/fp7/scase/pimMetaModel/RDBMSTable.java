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

public class RDBMSTable
{

    private int iRDBMSTableId;
    private String strRDBMSTableName;
    private ResourceModel oParentResourceModel;
    private ArrayList<RDBMSTableColumn> listOfTableColumns;

    public RDBMSTable(String strRDBMSTableName, ResourceModel oParentResourceModel) {
        this.iRDBMSTableId = UniqueIdProducer.getNewUniqueId();
        this.strRDBMSTableName = strRDBMSTableName;
        this.oParentResourceModel = oParentResourceModel;
        this.listOfTableColumns = new ArrayList<RDBMSTableColumn>();
    };

    public int getRDBMSTableId()
    {
        return this.iRDBMSTableId;
    }

    public String getRDBMSTableName()
    {
        return this.strRDBMSTableName;
    }

    public ResourceModel getRDBMSParentResource()
    {
        return this.oParentResourceModel;
    }

    public ArrayList<RDBMSTableColumn> getTableColumns()
    {
        return this.listOfTableColumns;
    }

    public void printRDBMSTable()
    {
        System.out.println("RDBMS table: " + this.strRDBMSTableName + " is added to PIM because "
                + this.oParentResourceModel.getResourceModelName() + " exists");
        printTableColumns();
    }

    private void printTableColumns()
    {
        for (int n = 0; n < this.listOfTableColumns.size(); n++) {
            this.listOfTableColumns.get(n).printTableColumn();
        }
    }

    public RDBMSTableColumn getPrimaryKeyColumn()
    {
        for (int n = 0; n < this.listOfTableColumns.size(); n++) {
            if (this.listOfTableColumns.get(n).getTableColumnIdentifyingAbility() == true) {
                return this.listOfTableColumns.get(n);
            }
        }

        try {
            throw new Exception("Error! RDBMS table without primary key detected! " + this.strRDBMSTableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void normalizeForeignKeyName()
    {
        for (int n = 0; n < this.listOfTableColumns.size(); n++) {
            if ((this.listOfTableColumns.get(n).getTableColumnReferentialAbility() == true)
                    && this.listOfTableColumns.get(n).getTableColumnName()
                            .equalsIgnoreCase(this.getPrimaryKeyColumn().getTableColumnName())) {
                this.listOfTableColumns.get(n).setTableColumnName(
                        String.format("%sForeingKey", this.listOfTableColumns.get(n).getTableColumnName()));
            }
        }
    }
}
