/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI Copyright (C) 2014 Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 * 
 * Project : S-CASE WorkFile : Compiler : File Description : Document Description: Related Documents
 * : Note : Programmer : Christoforos Zolotas Contact : christopherzolotas@issel.ee.auth.gr
 */

package eu.fp7.scase.inputParsing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import eu.fp7.scase.cimMetaModel.CRUDActivity;
import eu.fp7.scase.cimMetaModel.Property;
import eu.fp7.scase.cimMetaModel.Resource;

public class TextFileInputParserImplementation
        implements IInputParserImplementation
{

    private final String strCIMInputFile;
    private BufferedReader oBufferedReader;

    public TextFileInputParserImplementation(String CIMInputFile) {
        this.strCIMInputFile = CIMInputFile;
        openCIMFile();
    }

    @Override
    public ArrayList<String> parseResourceList()
    {
        ArrayList<String> listOfResources = new ArrayList<String>();
        parseResourcesTag();
        listOfResources = parseResources(listOfResources);
        return listOfResources;
    }

    @Override
    public Resource parseResourceByName(Resource oResource)
    {
        if (findResource(oResource)) {
            oResource = parseResource(oResource);
        }
        return oResource;
    }

    @Override
    public ArrayList<String> parseResourceOutgoingRelations(Resource oResource)
    {
        if (findResource(oResource)) {
            return parseResourceOutgoingRelations();
        }

        return null;
    }

    @Override
    public ArrayList<String> parseResourceIncomingRelations(Resource oResource)
    {
        if (findResource(oResource)) {
            return parseResourceIncomingRelations();
        }

        return null;
    }

    private void openCIMFile()
    {
        try {
            this.oBufferedReader = new BufferedReader(new FileReader(this.strCIMInputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parseResourcesTag()
    {
        try {
            // it must be the first line, otherwise the input file is not in the correct format
            String strInputTextLine = this.oBufferedReader.readLine();
            if (strInputTextLine.equalsIgnoreCase("Resources:")) {
                System.out.println("Resources Tag found!");
            } else {
                try {
                    throw new Exception("Input file is not in the correct format!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> parseResources(ArrayList<String> listOfResources)
    {
        String strInputTextLine;
        try {
            while ((strInputTextLine = this.oBufferedReader.readLine()) != null) {
                // check if the line is empty
                if (strInputTextLine.isEmpty()) {
                    // then all the resource list is read
                    System.out.println("End of resources list detected");
                    break;
                } else {
                    listOfResources.add(strInputTextLine);
                    System.out.println("Found Resource: " + strInputTextLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfResources;
    }

    private boolean findResource(Resource oResource)
    {
        reOpenCIMFile(); // start searching in the file from the beginning
        String strInputTextLine;

        try {
            while ((strInputTextLine = this.oBufferedReader.readLine()) != null) {
                if (strInputTextLine.equalsIgnoreCase("Resource:")) { // should a resource tag is
                                                                      // encountered
                    if ((strInputTextLine = this.oBufferedReader.readLine()) != null) {// and the
                                                                                       // next
                                                                                       // statement
                                                                                       // is not
                                                                                       // null
                        if (strInputTextLine.contains(oResource.getResourceName())) {// and it
                                                                                     // contains the
                                                                                     // name of the
                                                                                     // resource we
                                                                                     // are looking
                                                                                     // for
                            return true; // then the resource is found
                        }
                    } else { // if after the Resource tag the next line is null then the file is not
                             // in the proper format
                        try {
                            throw new Exception(
                                    "Input file is not in the correct format! It has a null line after a resource tag!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void reOpenCIMFile()
    {
        try {
            this.oBufferedReader.close();
            openCIMFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Resource parseResource(Resource oResource)
    {

        oResource = parseResourceType(oResource);
        oResource = parseResourceProperties(oResource);
        oResource = parseResourceRepresentations(oResource);
        oResource = parseResourceCRUDActivities(oResource);

        return oResource;
    }

    private Resource parseResourceType(Resource oResource)
    {
        String strInputTextLine;

        try {
            if ((strInputTextLine = this.oBufferedReader.readLine()) != null) {
                if (strInputTextLine.contains("false")) {
                    oResource.setResourceType(false);
                } else if (strInputTextLine.contains("true")) {
                    oResource.setResourceType(true);
                } else {
                    try {
                        throw new Exception(
                                "Input file is not in the correct format! The IsAlgorithmic tag may only have true of false value!");
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oResource;
    }

    private Resource parseResourceProperties(Resource oResource)
    {
        String strInputTextLine;

        while (findNextProperty()) {
            Property oCurrentProperty = new Property();
            try {
                while (((strInputTextLine = this.oBufferedReader.readLine()) != null) && !strInputTextLine.isEmpty()) {
                    if (strInputTextLine.contains("Name:")) {
                        oCurrentProperty.setPropertyName(strInputTextLine.substring(6)); // keep the
                                                                                         // substring
                                                                                         // from the
                                                                                         // 6th
                                                                                         // character,
                                                                                         // which is
                                                                                         // the name
                                                                                         // of the
                                                                                         // property
                    } else if (strInputTextLine.contains("Type:")) {
                        oCurrentProperty.setPropertyType(strInputTextLine.substring(6)); // keep the
                                                                                         // substring
                                                                                         // from the
                                                                                         // 6th
                                                                                         // character,
                                                                                         // where is
                                                                                         // the type
                                                                                         // of the
                                                                                         // property
                    } else if (strInputTextLine.contains("Unique:")) {
                        if (strInputTextLine.contains("false")) {
                            oCurrentProperty.setPropertyUniqueness(false);
                        } else {
                            oCurrentProperty.setPropertyUniqueness(true);
                        }
                    } else if (strInputTextLine.contains("NamingProperty:")) {
                        if (strInputTextLine.contains("false")) {
                            oCurrentProperty.setPropertyNamingAbility(false);
                        } else {
                            oCurrentProperty.setPropertyNamingAbility(true);
                        }
                    }
                }
                oResource.addProperty(oCurrentProperty);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return oResource;
    }

    private boolean findNextProperty()
    {
        String strInputTextLine;

        try {
            while ((strInputTextLine = this.oBufferedReader.readLine()) != null) {
                if (strInputTextLine.equalsIgnoreCase("Representations:")) { // if the resource
                                                                             // representations tag
                                                                             // is found
                    return false; // then no more properties exist within this resource
                } else if (strInputTextLine.contains("Property:")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Resource parseResourceRepresentations(Resource oResource)
    {

        oResource = parseInputRepresentations(oResource);
        oResource = parseOutputRepresentations(oResource);

        return oResource;
    }

    private Resource parseInputRepresentations(Resource oResource)
    {
        findInputRepresentationTag();
        String strInputTextLine = "InputRepresentation";

        while (!strInputTextLine.isEmpty()) {
            try {
                if (((strInputTextLine = this.oBufferedReader.readLine()) != null)) {
                    if (strInputTextLine.contains("InputRepresentation:")) {
                        oResource.addInputRepresentation(strInputTextLine.substring(21)); // keep
                                                                                          // the
                                                                                          // substring
                                                                                          // from
                                                                                          // the
                                                                                          // 21st
                                                                                          // character,
                                                                                          // which
                                                                                          // is the
                                                                                          // string
                                                                                          // that
                                                                                          // contains
                                                                                          // the
                                                                                          // representation
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return oResource;
    }

    private void findInputRepresentationTag()
    {
        String strInputTextLine;

        try {
            while ((strInputTextLine = this.oBufferedReader.readLine()) != null) {
                if (strInputTextLine.equals("InputRepresentations:")) {
                    return;
                }
            }
            try {
                throw new Exception(
                        "Input file is not in the correct format! InputRepresentations resource tag is missing!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Resource parseOutputRepresentations(Resource oResource)
    {
        findOutputRepresentationTag();
        String strInputTextLine = "OutputRepresentation";

        while (!strInputTextLine.isEmpty()) {
            try {
                if (((strInputTextLine = this.oBufferedReader.readLine()) != null)) {
                    if (strInputTextLine.contains("OutputRepresentation:")) {
                        oResource.addOutputRepresentation(strInputTextLine.substring(22)); // keep
                                                                                           // the
                                                                                           // substring
                                                                                           // from
                                                                                           // the
                                                                                           // 22nd
                                                                                           // character,
                                                                                           // which
                                                                                           // is the
                                                                                           // string
                                                                                           // that
                                                                                           // contains
                                                                                           // the
                                                                                           // representation
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return oResource;
    }

    private void findOutputRepresentationTag()
    {
        String strInputTextLine;

        try {
            while ((strInputTextLine = this.oBufferedReader.readLine()) != null) {
                if (strInputTextLine.equals("OutputRepresentations:")) {
                    return;
                }
            }
            try {
                throw new Exception(
                        "Input file is not in the correct format! OutputRepresentations resource tag is missing!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Resource parseResourceCRUDActivities(Resource oResource)
    {
        findCRUDActivityTag();
        String strInputTextLine = "CRUDActivity";

        while (!strInputTextLine.isEmpty()) {
            CRUDActivity oCurrentCRUDActivity = new CRUDActivity();
            try {
                if (((strInputTextLine = this.oBufferedReader.readLine()) != null)) {
                    if (strInputTextLine.contains("CRUDActivity:")) {
                        oCurrentCRUDActivity.setActivityCRUDVerb(strInputTextLine.substring(14)); // keep
                                                                                                  // the
                                                                                                  // substring
                                                                                                  // from
                                                                                                  // the
                                                                                                  // 14th
                                                                                                  // character,
                                                                                                  // which
                                                                                                  // is
                                                                                                  // the
                                                                                                  // string
                                                                                                  // that
                                                                                                  // contains
                                                                                                  // the
                                                                                                  // CRUD
                                                                                                  // verb
                        oResource.addCRUDActivity(oCurrentCRUDActivity);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return oResource;
    }

    private void findCRUDActivityTag()
    {
        String strInputTextLine;

        try {
            while ((strInputTextLine = this.oBufferedReader.readLine()) != null) {
                if (strInputTextLine.equalsIgnoreCase("Relations:")) {
                    try {
                        throw new Exception("Bad file format! No CRUDActivities resource tag is found!");
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (strInputTextLine.equals("CRUDActivities:")) {
                    return;
                }
            }
            try {
                throw new Exception("Input file is not in the correct format! CRUDActivities resource tag is missing!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> parseResourceOutgoingRelations()
    {
        findOutgoingRelationsTag();
        String strInputTextLine;
        ArrayList<String> listOfReousrceOutgoingRelations = new ArrayList<String>();

        try {
            while ((strInputTextLine = this.oBufferedReader.readLine()) != null && !strInputTextLine.isEmpty()) {
                listOfReousrceOutgoingRelations.add(strInputTextLine.substring(17)); // keep the
                                                                                     // substring
                                                                                     // from the
                                                                                     // 17th
                                                                                     // character
                                                                                     // since it
                                                                                     // contains the
                                                                                     // name of the
                                                                                     // related
                                                                                     // resource
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfReousrceOutgoingRelations;
    }

    private void findOutgoingRelationsTag()
    {
        String strInputTextLine;

        try {
            while ((strInputTextLine = this.oBufferedReader.readLine()) != null) {
                if (strInputTextLine.equalsIgnoreCase("HasRelatedResources:")) { // should we
                                                                                 // encounter the
                                                                                 // HasRelatedResources
                                                                                 // tag
                    return; // then the outgoing relations definition part of this resource is found
                } else if (strInputTextLine.equalsIgnoreCase("Resource:")) {
                    try {
                        throw new Exception(
                                "Input file is not in the correct format! HasRelatedResources resource tag is missing!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> parseResourceIncomingRelations()
    {
        findIncomingRelationsTag();
        String strInputTextLine;
        ArrayList<String> listOfReousrceIncomingRelations = new ArrayList<String>();

        try {
            while ((strInputTextLine = this.oBufferedReader.readLine()) != null && !strInputTextLine.isEmpty()) {
                listOfReousrceIncomingRelations.add(strInputTextLine.substring(17)); // keep the
                                                                                     // substring
                                                                                     // from the
                                                                                     // 17th
                                                                                     // character
                                                                                     // since it
                                                                                     // contains the
                                                                                     // name of the
                                                                                     // related
                                                                                     // resource
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfReousrceIncomingRelations;
    }

    private void findIncomingRelationsTag()
    {
        String strInputTextLine;

        try {
            while ((strInputTextLine = this.oBufferedReader.readLine()) != null) {
                if (strInputTextLine.equalsIgnoreCase("IsRelatedResourceOfResources:")) { // should
                                                                                          // we
                                                                                          // encounter
                                                                                          // the
                                                                                          // IsRelatedResourceOfResources
                                                                                          // tag
                    return; // then the incoming relations definition part of this resource is found
                } else if (strInputTextLine.equalsIgnoreCase("Resource:")) {
                    try {
                        throw new Exception(
                                "Input file is not in the correct format! IsRelatedResourceOfResources resource tag is missing!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
