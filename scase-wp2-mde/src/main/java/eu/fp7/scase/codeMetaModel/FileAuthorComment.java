/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.codeMetaModel;

import java.util.Calendar;

import eu.fp7.scase.customUtilities.UniqueIdProducer;

public class FileAuthorComment
{

    private int iFileAuthorCommentId;
    private static String strOrganizationStamp = String
            .format("/*%n * ARISTOSTLE UNIVERSITY OF THESSALONIKI%n * Copyright (C) %d%n * Aristotle University of Thessaloniki%n * Department of Electrical & Computer Engineering%n * Division of Electronics & Computer Engineering%n * Intelligent Systems & Software Engineering Lab%n *",
                    Calendar.getInstance().get(Calendar.YEAR));
    private String strProject = "";
    private String strWorkFile = "";
    private String strCompiler = "";
    private String strFileDescription = "";
    private String strDocumentDescription = "";
    private String strRelatedDocuments = "";
    private String strNote = "";
    private static String strProgrammer = String
            .format("%n* Programmer		   : RESTful MDE Engine created by Christoforos Zolotas");
    private static String strContact = String.format("%n* Contact			   : christopherzolotas@issel.ee.auth.gr%n*/");

    public FileAuthorComment() {
        this.iFileAuthorCommentId = UniqueIdProducer.getNewUniqueId();
    }

    public int getFileAuthorCommentId()
    {
        return this.iFileAuthorCommentId;
    }

    public void setProject(String strProject)
    {
        this.strProject = String.format("%n * Project             : %s", strProject);
    }

    public void setWorkFile(String strWorkFile)
    {
        this.strWorkFile = String.format("%n * WorkFile            : %s", strWorkFile);
    }

    public void setCompiler(String strCompiler)
    {
        this.strCompiler = String.format("%n * Compiler            : %s", strCompiler);
    }

    public void setFileDescription(String strFileDescription)
    {
        this.strFileDescription = String.format("%n * File Description    : %s", strFileDescription);
    }

    public void setDocumentDescription(String strDocumentDescription)
    {
        this.strDocumentDescription = String.format("%n * Document Description: %s", strDocumentDescription);
    }

    public void setRelatedDocuments(String strRelatedDocuments)
    {
        this.strRelatedDocuments = String.format("%n* Related Documents	   : %s", strRelatedDocuments);
    }

    public void setNote(String strNote)
    {
        this.strNote = String.format("%n* Note				   : %s", strNote);
    }

    public String exportFileAuthorComment()
    {
        return (strOrganizationStamp + this.strProject + this.strWorkFile + this.strCompiler + this.strFileDescription
                + this.strDocumentDescription + this.strRelatedDocuments + this.strNote + strProgrammer + strContact);
    }
}
