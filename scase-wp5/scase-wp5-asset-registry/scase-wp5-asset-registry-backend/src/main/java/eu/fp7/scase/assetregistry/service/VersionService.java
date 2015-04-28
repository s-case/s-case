package eu.fp7.scase.assetregistry.service;

import eu.fp7.scase.base.BuildProperties;

/**
 * service class for the version.
 * @author Robert Magnus
 *
 */
public class VersionService
{

    /**
     * return the build version.
     * @return the version.
     */
    public String getVersion()
    {
        return BuildProperties.getBuildVersion();
    }

}
