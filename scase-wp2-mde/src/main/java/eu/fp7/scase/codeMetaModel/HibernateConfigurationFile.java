/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.codeMetaModel;

public class HibernateConfigurationFile
{

    private String strDatabaseIp;
    private String strDatabasePort;
    private String strDatabaseUsername;
    private String strDatabasePassword;
    private String strHibernateFileText;
    private JavaFileIdentation oFileIdentation;

    public HibernateConfigurationFile(String strDatabaseIp, String strDatabasePort, String strDatabaseUsername,
            String strDatabasePassword)
    {
        this.strDatabaseIp = strDatabaseIp;
        this.strDatabasePort = strDatabasePort;
        this.strDatabaseUsername = strDatabaseUsername;
        this.strDatabasePassword = strDatabasePassword;
        this.strHibernateFileText = "";
        this.oFileIdentation = new JavaFileIdentation();
    }

    public String getDatabaseIp()
    {
        return this.strDatabaseIp;
    }

    public String getDatabasePort()
    {
        return this.strDatabasePort;
    }

    public String getDatabaseUsername()
    {
        return this.strDatabaseUsername;
    }

    public String getDatabasePassword()
    {
        return this.strDatabasePassword;
    }

    public String exportHibernateFileText()
    {
        this.strHibernateFileText = String.format("%s%s<?xml version=\"1.0\" encoding=\"utf-8\"?>%n",
                this.strHibernateFileText, this.oFileIdentation.getCurrentIdentation());
        this.strHibernateFileText = String.format("%s%s<!DOCTYPE hibernate-configuration SYSTEM%n",
                this.strHibernateFileText, this.oFileIdentation.getCurrentIdentation());
        this.strHibernateFileText = String.format(
                "%s%s\"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd\">%n%n", this.strHibernateFileText,
                this.oFileIdentation.getCurrentIdentation());
        this.strHibernateFileText = String.format("%s%s<hibernate-configuration>%n", this.strHibernateFileText,
                this.oFileIdentation.getCurrentIdentation());
        this.strHibernateFileText = String.format("%s%s<session-factory>%n", this.strHibernateFileText,
                this.oFileIdentation.increaseIdentation());
        this.strHibernateFileText = String.format("%s%s<property name=\"hibernate.dialect\">%n",
                this.strHibernateFileText, this.oFileIdentation.increaseIdentation());
        this.strHibernateFileText = String.format("%s%sorg.hibernate.dialect.PostgreSQLDialect%n",
                this.strHibernateFileText, this.oFileIdentation.increaseIdentation());
        this.strHibernateFileText = String.format("%s%s</property>%n", this.strHibernateFileText,
                this.oFileIdentation.decreaseIdentation());
        this.strHibernateFileText = String.format("%s%s<property name=\"hibernate.connection.driver_class\">%n",
                this.strHibernateFileText, this.oFileIdentation.getCurrentIdentation());
        this.strHibernateFileText = String.format("%s%sorg.postgresql.Driver%n", this.strHibernateFileText,
                this.oFileIdentation.increaseIdentation());
        this.strHibernateFileText = String.format("%s%s</property>%n", this.strHibernateFileText,
                this.oFileIdentation.decreaseIdentation());
        this.strHibernateFileText = String.format("%s%s<property name=\"hibernate.connection.url\">%n",
                this.strHibernateFileText, this.oFileIdentation.getCurrentIdentation());
        this.strHibernateFileText = String.format("%s%sjdbc:postgresql://%s:%s/%n", this.strHibernateFileText,
                this.oFileIdentation.increaseIdentation(), this.strDatabaseIp, this.strDatabasePort);
        this.strHibernateFileText = String.format("%s%s</property>%n", this.strHibernateFileText,
                this.oFileIdentation.decreaseIdentation());
        this.strHibernateFileText = String.format("%s%s<property name=\"hibernate.connection.username\">%n",
                this.strHibernateFileText, this.oFileIdentation.getCurrentIdentation());
        this.strHibernateFileText = String.format("%s%s%s%n", this.strHibernateFileText,
                this.oFileIdentation.increaseIdentation(), this.strDatabaseUsername);
        this.strHibernateFileText = String.format("%s%s</property>%n", this.strHibernateFileText,
                this.oFileIdentation.decreaseIdentation());
        this.strHibernateFileText = String.format("%s%s<property name=\"hibernate.connection.password\">%n",
                this.strHibernateFileText, this.oFileIdentation.getCurrentIdentation());
        this.strHibernateFileText = String.format("%s%s%s%n", this.strHibernateFileText,
                this.oFileIdentation.increaseIdentation(), this.strDatabasePassword);
        this.strHibernateFileText = String.format("%s%s</property>%n", this.strHibernateFileText,
                this.oFileIdentation.decreaseIdentation());
        this.strHibernateFileText = String.format("%s%s<property name=\"hibernate.hbm2ddl.auto\">update</property>%n",
                this.strHibernateFileText, this.oFileIdentation.getCurrentIdentation());
        this.strHibernateFileText = String.format(
                "%s%s<property name=\"hibernate.search.default.directory_provider\">filesystem</property>%n",
                this.strHibernateFileText, this.oFileIdentation.getCurrentIdentation());
        this.strHibernateFileText = String.format("%s%s</session-factory>%n", this.strHibernateFileText,
                this.oFileIdentation.decreaseIdentation());
        this.strHibernateFileText = String.format("%s%s</hibernate-configuration>%n", this.strHibernateFileText,
                this.oFileIdentation.decreaseIdentation());

        return this.strHibernateFileText;
    }
}
