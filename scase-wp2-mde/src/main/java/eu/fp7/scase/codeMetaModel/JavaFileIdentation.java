/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.codeMetaModel;

public class JavaFileIdentation
{

    private String strTabSize = "    ";
    private int iNumberOfTabsUsed;

    public JavaFileIdentation() {
        this.iNumberOfTabsUsed = 0;
    }

    public String getCurrentIdentation()
    {
        return calculateIdentation();
    }

    private String calculateIdentation()
    {
        String strIdentation = "";
        for (int n = 0; n < this.iNumberOfTabsUsed; n++) {
            strIdentation = String.format("%s%s", strIdentation, this.strTabSize);
        }
        return strIdentation;
    }

    public String increaseIdentation()
    {
        this.iNumberOfTabsUsed++;
        return this.getCurrentIdentation();
    }

    public String decreaseIdentation()
    {
        this.iNumberOfTabsUsed--;
        if (this.iNumberOfTabsUsed < 0) {
            this.iNumberOfTabsUsed = 0;
        }
        return this.getCurrentIdentation();
    }

    public void changeTabSize(int iIdentationStep)
    {
        this.strTabSize = "";
        for (int n = 0; n < iIdentationStep; n++) {
            this.strTabSize = String.format("%s ", this.strTabSize);
        }
    }
}
