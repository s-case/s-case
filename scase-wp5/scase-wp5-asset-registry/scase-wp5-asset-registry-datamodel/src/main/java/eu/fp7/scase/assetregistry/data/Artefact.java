package eu.fp7.scase.assetregistry.data;

import java.util.Map;

public class Artefact extends BaseEntity {

    public String artefactURL;
    public Map<String,String> properties;


    public String getArtefactURL() {

        return artefactURL;
    }

    public void setArtefactURL(String artefactURL) {

        this.artefactURL = artefactURL;
    }

    public Map<String, String> getProperties() {

        return properties;
    }

    public void setProperties(Map<String, String> properties) {

        this.properties = properties;
    }

}
