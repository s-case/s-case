package eu.fp7.scase.assetregistry.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * class for holding all the names.
 * @author Robert Magnus
 *
 */
@ApplicationPath(AssetRegistryRestApp.BASE)
public class AssetRegistryRestApp extends Application {

    public static final String ROOT = "/assetregistry";

    public static final String BASE = "/rest";

    public static final String PART_VERSION = "/version/";
    public static final String VERSION = ROOT + BASE + PART_VERSION;

}
