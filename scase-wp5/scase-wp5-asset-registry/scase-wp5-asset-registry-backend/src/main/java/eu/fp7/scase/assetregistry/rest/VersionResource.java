package eu.fp7.scase.assetregistry.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import eu.fp7.scase.assetregistry.service.VersionService;

@Path( AssetRegistryRestApp.PART_VERSION)
public final class VersionResource {

    private VersionResource() {}

    @Inject
    VersionService versionService;
    
    
    @GET
    public String version()
    {
        return versionService.getVersion();
    }



}
