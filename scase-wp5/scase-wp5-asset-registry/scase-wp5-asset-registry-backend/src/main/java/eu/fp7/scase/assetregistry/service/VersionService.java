package eu.fp7.scase.assetregistry.service;

import eu.fp7.scase.base.BuildProperties;

public class VersionService
{

    /**
     * return the build version.
     * @return
     */
    public String getVersion()
    {
        return BuildProperties.getBuildVersion();
    }

}
