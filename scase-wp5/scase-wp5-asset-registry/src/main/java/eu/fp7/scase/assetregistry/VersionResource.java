package eu.fp7.scase.assetregistry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path( AssetRegistryRestApp.PART_VERSION)
public final class VersionResource {

    private VersionResource() {}

    @GET
    public String version()
    {
        return "";
    }



}
