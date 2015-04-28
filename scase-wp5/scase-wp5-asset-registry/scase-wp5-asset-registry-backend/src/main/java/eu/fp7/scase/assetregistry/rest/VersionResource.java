package eu.fp7.scase.assetregistry.rest;

import eu.fp7.scase.assetregistry.service.VersionService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * REST class for getting the version.
 * @author Robert Magnus
 *
 */
@Path( AssetRegistryRestApp.PART_VERSION)
public final class VersionResource {

    private VersionResource() {}

    @Inject
    VersionService versionService;


    /**
     * returns the version.
     * @return version
     */
    @GET
    public String version()
    {
        return this.versionService.getVersion();
    }



}
