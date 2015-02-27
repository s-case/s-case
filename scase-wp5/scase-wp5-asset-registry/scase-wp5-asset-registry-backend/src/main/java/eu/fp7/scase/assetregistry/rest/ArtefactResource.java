package eu.fp7.scase.assetregistry.rest;

import eu.fp7.scase.assetregistry.data.Artefact;
import eu.fp7.scase.assetregistry.service.ArtefactService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

import static eu.fp7.scase.assetregistry.rest.ResourceTools.redirect;

@Path( AssetRegistryRestApp.PART_ARTEFACT)
@Produces( "application/json;charset=UTF-8" )
@Consumes("application/json")
public class ArtefactResource {

    @Inject
    private ArtefactService artefactService;


    @GET
    @Path("{id}")
    public Artefact get( @PathParam("id") long id ) {
        return this.artefactService.find( id );
    }

    @POST
    public Response create( Artefact artefact ) throws URISyntaxException {
        final Artefact created = this.artefactService.create( artefact );

        return redirect( "artefact/" + created.getId() );
    }

    @PUT
    @Path("{id}")
    public Response update( @PathParam("id") long id, Artefact artefact ) throws URISyntaxException {
        artefact.setId( id );
        final Artefact updated = this.artefactService.update( artefact );
        return redirect( "artefact/", updated );
    }

    @DELETE
    @Path("{id}")
    public void delete( @PathParam("id") long id ) {
        delete(id);
    }

}
