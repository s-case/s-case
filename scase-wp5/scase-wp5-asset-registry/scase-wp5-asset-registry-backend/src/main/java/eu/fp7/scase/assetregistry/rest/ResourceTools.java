package eu.fp7.scase.assetregistry.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.Response;

import eu.fp7.scase.assetregistry.data.BaseEntity;

/**
 * Response Helpers.
 */
public final class ResourceTools {

    private ResourceTools(){}

    public static Response redirect(final String to){
        try {
            return Response.seeOther( new URI( to ) ).build();
        } catch ( URISyntaxException e ) {
            throw new RuntimeException( "failed to build uri", e );
        }
    }

    /**
     * Build a redirect UIR like path + entity.getId().
     * @param path
     * @param entity
     * @return da URI
     */
    public static Response redirect(final String path, final BaseEntity entity){
        return redirect( path + entity.getId() );
    }
}