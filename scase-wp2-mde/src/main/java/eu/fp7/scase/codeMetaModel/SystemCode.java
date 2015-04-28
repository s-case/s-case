/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.codeMetaModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SystemCode
{

    private String strSystemCodeProjectName;
    private ArrayList<JavaPackage> listOfJavaPackages;
    private ArrayList<JarFile> listOfNeededJarFiles;
    private HibernateConfigurationFile oHibernateConfigurationFile;
    private MavenConfigurationFile oMavenConfigurationFile;
    private WebXMLConfigurationFile oWebXMLConfigurationFile;

    public SystemCode() {
        this.listOfJavaPackages = new ArrayList<JavaPackage>();
        this.listOfNeededJarFiles = new ArrayList<JarFile>();
    }

    public String getSystemCodeProjectName()
    {
        return this.strSystemCodeProjectName;
    }

    public ArrayList<JavaPackage> getJavaPackages()
    {
        return this.listOfJavaPackages;
    }

    public ArrayList<JarFile> getJarFiles()
    {
        return this.listOfNeededJarFiles;
    }

    public void setHibernateConfigurationFile(HibernateConfigurationFile oHibernateConfigurationFile)
    {
        this.oHibernateConfigurationFile = oHibernateConfigurationFile;
    }

    public void setMavenConfigurationFile(MavenConfigurationFile oMavenConfigurationFile)
    {
        this.oMavenConfigurationFile = oMavenConfigurationFile;
    }

    public void setWebXMLConfigurationFile(WebXMLConfigurationFile oWebXMLConfigurationFile)
    {
        this.oWebXMLConfigurationFile = oWebXMLConfigurationFile;
    }

    public HibernateConfigurationFile getHibernateConfigurationFile()
    {
        return this.oHibernateConfigurationFile;
    }

    public MavenConfigurationFile getMavenConfigurationFile()
    {
        return this.oMavenConfigurationFile;
    }

    public void setSystemCodeProjectName(String strSystemCodeProjectName)
    {
        this.strSystemCodeProjectName = strSystemCodeProjectName;
    }

    public void printSystemCode()
    {
        System.out.println("System Code Project Name: " + this.strSystemCodeProjectName);
        printJavaPackages();
        printJarFiles();
        printHibernateConfigurationFile();
        printMavenConfigurationFile();
    }

    private void printJavaPackages()
    {
        for (int n = 0; n < this.listOfJavaPackages.size(); n++) {
            this.listOfJavaPackages.get(n).printJavaPackage();
        }
    }

    private void printJarFiles()
    {

    }

    private void printHibernateConfigurationFile()
    {

    }

    private void printMavenConfigurationFile()
    {

    }

    public void exportSystemCode(String strOutputPath, String strProjectName)
    {
        createParentFolders(strOutputPath, strProjectName);
        createPackageFolders();
        createPackageFiles();
        createHibernateConfigurationFile(strOutputPath, strProjectName);
        createMavenConfigurationFile(strOutputPath, strProjectName);
        createWebXMLConfigurationFile(strOutputPath, strProjectName);
        writeJavaFiles(strOutputPath, strProjectName);
    }

    private void createParentFolders(String strOutputPath, String strProjectName)
    {
        // create the parent folder named by the project name
        File oParentDirectory = new File(String.format("%s/%s", strOutputPath, strProjectName));
        if (!oParentDirectory.exists()) {
            if (!oParentDirectory.mkdir()) {
                try {
                    throw new Exception("Could not create parent directory! " + strOutputPath + "/" + strProjectName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // create the main, java, project_name folders within the parent folder
        File oStructureDirectories = new File(String.format("%s/%s/src/main/java/%s", strOutputPath, strProjectName,
                strProjectName.toLowerCase()));
        if (!oStructureDirectories.exists()) {
            if (!oStructureDirectories.mkdirs()) {
                try {
                    throw new Exception("Could not create structure directories!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        File oWebAppDirectory = new File(String.format("%s/%s/src/main/webapp/WEB-INF/classes", strOutputPath,
                strProjectName));
        if (!oWebAppDirectory.exists()) {
            if (!oWebAppDirectory.mkdirs()) {
                try {
                    throw new Exception("Could not create Web Application directory!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createPackageFolders()
    {
        for (int n = 0; n < this.getJavaPackages().size(); n++) {
            File oPackageDirectory = new File(this.getJavaPackages().get(n).getFileSystemPath());
            if (!oPackageDirectory.exists()) {
                if (!oPackageDirectory.mkdir()) {
                    try {
                        throw new Exception("Could not create the package directory: "
                                + this.getJavaPackages().get(n).getFileSystemPath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void createPackageFiles()
    {
        for (int n = 0; n < this.getJavaPackages().size(); n++) {
            for (int i = 0; i < this.getJavaPackages().get(n).getPackageJavaFiles().size(); i++) {
                File oPackageJavaFile = new File(String.format("%s/%s.java", this.getJavaPackages().get(n)
                        .getFileSystemPath(), this.getJavaPackages().get(n).getPackageJavaFiles().get(i)
                        .getJavaFileName()));
                try {
                    oPackageJavaFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createHibernateConfigurationFile(String strOutputPath, String strProjectName)
    {
        File oHibernateConfigurationFile = new File(String.format(
                "%s/%s/src/main/webapp/WEB-INF/classes/hibernate.cfg.xml", strOutputPath, strProjectName));
        try {
            oHibernateConfigurationFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createMavenConfigurationFile(String strOutputPath, String strProjectName)
    {
        File oMavenConfigurationFile = new File(String.format("%s/%s/pom.xml", strOutputPath, strProjectName));
        try {
            oMavenConfigurationFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createWebXMLConfigurationFile(String strOutputPath, String strProjectName)
    {
        File oWebXMLConfigurationFile = new File(String.format("%s/%s/src/main/webapp/WEB-INF/web.xml", strOutputPath,
                strProjectName));
        try {
            oWebXMLConfigurationFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeJavaFiles(String strOutputPath, String strProjectName)
    {
        for (int n = 0; n < this.getJavaPackages().size(); n++) {
            for (int i = 0; i < this.getJavaPackages().get(n).getPackageJavaFiles().size(); i++) {
                File oPackageJavaFile = new File(String.format("%s/%s.java", this.getJavaPackages().get(n)
                        .getFileSystemPath(), this.getJavaPackages().get(n).getPackageJavaFiles().get(i)
                        .getJavaFileName()));
                try {
                    BufferedWriter oBufferedWriter = new BufferedWriter(new FileWriter(
                            oPackageJavaFile.getAbsoluteFile()));
                    oBufferedWriter.write(this.getJavaPackages().get(n).getPackageJavaFiles().get(i).exportJavaFile());
                    oBufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        File oHibernateConfigurationFile = new File(String.format(
                "%s/%s/src/main/webapp/WEB-INF/classes/hibernate.cfg.xml", strOutputPath, strProjectName));
        try {
            BufferedWriter oBufferedWriter = new BufferedWriter(new FileWriter(
                    oHibernateConfigurationFile.getAbsoluteFile()));
            oBufferedWriter.write(this.getHibernateConfigurationFile().exportHibernateFileText());
            oBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File oMavenConfigurationFile = new File(String.format("%s/%s/pom.xml", strOutputPath, strProjectName));
        try {
            BufferedWriter oBufferedWriter = new BufferedWriter(new FileWriter(
                    oMavenConfigurationFile.getAbsoluteFile()));
            oBufferedWriter.write(this.getMavenConfigurationFile().exportMavenText());
            oBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File oWebXMLConfigurationFile = new File(String.format("%s/%s/src/main/webapp/WEB-INF/web.xml", strOutputPath,
                strProjectName));
        try {
            BufferedWriter oBufferedWriter = new BufferedWriter(new FileWriter(
                    oWebXMLConfigurationFile.getAbsoluteFile()));
            oBufferedWriter.write(this.oWebXMLConfigurationFile.exportWebXMLText());
            oBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
