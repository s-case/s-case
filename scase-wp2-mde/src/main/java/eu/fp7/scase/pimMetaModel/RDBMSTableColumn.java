/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.pimMetaModel;

import eu.fp7.scase.customUtilities.UniqueIdProducer;

public class RDBMSTableColumn
{

    private int iRDBMSTableColumnId;
    private String strTableColumnName;
    private String strTableColumnType;
    private ResourceModelProperty oParentProperty;
    private boolean bIsPrimaryKey;
    private boolean bIsForeignKey;
    private RDBMSTableColumn oReferencingTableColumn;

    public RDBMSTableColumn(ResourceModelProperty oParentProperty, boolean bIsForeignKey) {
        this.iRDBMSTableColumnId = UniqueIdProducer.getNewUniqueId();
        this.oParentProperty = oParentProperty;
        this.strTableColumnName = oParentProperty.getPIMComponentProeprtyName();
        this.strTableColumnType = oParentProperty.getPIMComponentPropertyType();
        this.bIsForeignKey = bIsForeignKey;
        this.bIsPrimaryKey = oParentProperty.getResourceModelIdentifyingAbility();
        this.oReferencingTableColumn = null;
    }

    public RDBMSTableColumn(RDBMSTableColumn oRDBMSTableColumn) {
        this.iRDBMSTableColumnId = UniqueIdProducer.getNewUniqueId();
        this.oParentProperty = oRDBMSTableColumn.getParentProperty();
        this.strTableColumnName = oRDBMSTableColumn.getTableColumnName();
        this.strTableColumnType = oRDBMSTableColumn.getTableColumnType();
        this.bIsForeignKey = true;
        this.bIsPrimaryKey = false;
        this.oReferencingTableColumn = oRDBMSTableColumn;
    }

    public int getRDBMSTableColumnId()
    {
        return this.iRDBMSTableColumnId;
    }

    public String getTableColumnName()
    {
        return this.strTableColumnName;
    }

    public String getTableColumnType()
    {
        return this.strTableColumnType;
    }

    public ResourceModelProperty getParentProperty()
    {
        return this.oParentProperty;
    }

    public boolean getTableColumnReferentialAbility()
    {
        return this.bIsForeignKey;
    }

    public boolean getTableColumnIdentifyingAbility()
    {
        return this.bIsPrimaryKey;
    }

    public RDBMSTableColumn getReferencingTableColumn()
    {
        return this.oReferencingTableColumn;
    }

    public void setTableColumnName(String strTableColumnName)
    {
        this.strTableColumnName = strTableColumnName;
    }

    public void printTableColumn()
    {
        if (!this.bIsForeignKey) {
            System.out.println("Column: " + this.strTableColumnName + " is added to PIM because "
                    + this.oParentProperty.getPIMComponentProeprtyName() + " exists");
        } else {
            System.out.println("Column: " + this.strTableColumnName
                    + " is added to PIM because it is foreignkey to column "
                    + this.oReferencingTableColumn.getTableColumnName() + " that corresponds to "
                    + this.oReferencingTableColumn.getParentProperty().getPIMComponentProeprtyName() + " property.");
        }
    }
}
