/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.codeMetaModel;

import eu.fp7.scase.psmMetaModel.JavaAlgoResourceModel;

public class AlgoResourceModelJavaFile
        extends AJavaFile
{

    private JavaAlgoResourceModel oParentAlgoJavaModel;

    public AlgoResourceModelJavaFile(JavaAlgoResourceModel oParentAlgoJavaModel, String strProjectName,
            String strPackageStamp)
    {
        super(oParentAlgoJavaModel.getJavaAlgoResourceModelName(), strProjectName, strPackageStamp);
        this.oParentAlgoJavaModel = oParentAlgoJavaModel;
    }

    public JavaAlgoResourceModel getParentAlgoJavaModel()
    {
        return this.oParentAlgoJavaModel;
    }

    @Override
    public void printJavaFile()
    {
        System.out.println("The Java Algorithmic Model File: " + this.getJavaFileName()
                + " is added to the software code project because "
                + this.oParentAlgoJavaModel.getJavaAlgoResourceModelName() + " exists in PSM");
    }

    @Override
    public void transformFile()
    {
        super.transformFile();
    }

    @Override
    public String addAuthorComment()
    {
        oFileAuthorComment = new FileAuthorComment();
        oFileAuthorComment.setProject(this.strProjectName);
        oFileAuthorComment.setWorkFile("");
        oFileAuthorComment.setCompiler("");
        oFileAuthorComment.setFileDescription("");
        oFileAuthorComment.setDocumentDescription("");
        oFileAuthorComment.setRelatedDocuments("");
        oFileAuthorComment.setNote("");
        return oFileAuthorComment.exportFileAuthorComment();
    }

    @Override
    public String addFileImports()
    {
        this.strFileImports = String.format("//Please add any needed imports here.");
        return this.strFileImports;
    }

    @Override
    public String addClassHeader()
    {
        this.strClassHeader = String.format("%s%spublic class %s{%n", this.strClassHeader,
                this.oJavaFileIdentation.getCurrentIdentation(),
                this.oParentAlgoJavaModel.getJavaAlgoResourceModelName());
        this.oJavaFileIdentation.increaseIdentation();
        return this.strClassHeader;
    }

    @Override
    public String addClassProperties()
    {
        this.strClassProperties = String.format("%s%s//Please add any properties of this model here.%n%n",
                this.strClassProperties, this.oJavaFileIdentation.getCurrentIdentation());
        return this.strClassProperties;
    }

    @Override
    public String addClassFunctions()
    {
        this.strClassFunctions = String.format(
                "%s%s//Please add the constructors and any functions of this model here.%n%n", this.strClassProperties,
                this.oJavaFileIdentation.getCurrentIdentation());
        return this.strClassFunctions;
    }

    @Override
    public String addClassTail()
    {
        this.strClassTail = "}";
        return this.strClassTail;
    }
}
