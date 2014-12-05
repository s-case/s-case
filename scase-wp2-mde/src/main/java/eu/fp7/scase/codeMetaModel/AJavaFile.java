/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.codeMetaModel;

import java.util.ArrayList;

import eu.fp7.scase.customUtilities.UniqueIdProducer;

public abstract class AJavaFile
{

    protected int iJavaFileId;
    protected String strJavaFileName;
    protected ArrayList<AJavaClassFunction> listOfJavaClassFunctions;
    protected ArrayList<AJavaClassProperty> listOfJavaClassProperties;
    protected ArrayList<JavaFileImport> listOfJavaFileImports;
    protected JavaFileIdentation oJavaFileIdentation;
    protected FileAuthorComment oFileAuthorComment;
    protected String strAuthorComment = "";
    protected String strPackageStamp;
    protected String strFileImports = "";
    protected String strClassHeader = "";
    protected String strClassProperties = "";
    protected String strClassFunctions = "";
    protected String strClassTail = "";
    protected String strProjectName;

    public AJavaFile(String strJavaFileName, String strProjectName, String strPackageStamp) {
        this.iJavaFileId = UniqueIdProducer.getNewUniqueId();
        this.strJavaFileName = strJavaFileName;
        this.listOfJavaClassFunctions = new ArrayList<AJavaClassFunction>();
        this.listOfJavaClassProperties = new ArrayList<AJavaClassProperty>();
        this.listOfJavaFileImports = new ArrayList<JavaFileImport>();
        this.oJavaFileIdentation = new JavaFileIdentation();
        this.strPackageStamp = strPackageStamp;
        this.strProjectName = strProjectName;
        capitalizeFirstNameLetter();
    }

    public int getJavaFileId()
    {
        return this.iJavaFileId;
    }

    public String getJavaFileName()
    {
        return this.strJavaFileName;
    }

    public ArrayList<AJavaClassFunction> getJavaClassFunctions()
    {
        return this.listOfJavaClassFunctions;
    }

    public ArrayList<AJavaClassProperty> getJavaClassProeprties()
    {
        return this.listOfJavaClassProperties;
    }

    public ArrayList<JavaFileImport> getJavaFileImports()
    {
        return this.listOfJavaFileImports;
    }

    public JavaFileIdentation getJavaFileIdentation()
    {
        return this.oJavaFileIdentation;
    }

    private void capitalizeFirstNameLetter()
    {
        this.strJavaFileName = String.format("%s%s", this.strJavaFileName.substring(0, 1).toUpperCase(),
                this.strJavaFileName.substring(1));
    }

    public void printJavaFile()
    {
    }

    public String exportJavaFile()
    {
        return String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n", this.strAuthorComment, this.strPackageStamp,
                this.strFileImports, this.strClassHeader, this.strClassProperties, this.strClassFunctions,
                this.strClassTail);
    }

    public void transformFile()
    {
        this.strAuthorComment = addAuthorComment();
        this.strPackageStamp = addPackageStamp();
        this.strFileImports = addFileImports();
        this.strClassHeader = addClassHeader();
        this.strClassProperties = addClassProperties();
        this.strClassFunctions = addClassFunctions();
        this.strClassTail = addClassTail();
    }

    public abstract String addAuthorComment();

    public String addPackageStamp()
    {
        this.strPackageStamp = String.format("%n%n%s%n%n", this.strPackageStamp);
        return this.strPackageStamp;
    }

    public abstract String addFileImports();

    public abstract String addClassHeader();

    public abstract String addClassProperties();

    public abstract String addClassFunctions();

    public abstract String addClassTail();
}
