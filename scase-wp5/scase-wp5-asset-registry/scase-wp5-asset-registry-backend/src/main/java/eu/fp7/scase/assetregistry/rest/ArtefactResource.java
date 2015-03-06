package eu.fp7.scase.assetregistry.rest;

import eu.fp7.scase.assetregistry.data.Artefact;
import eu.fp7.scase.assetregistry.service.ArtefactService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

import static eu.fp7.scase.assetregistry.rest.ResourceTools.redirect;

@Path( AssetRegistryRestApp.PART_ARTEFACT)
@Produces( "application/json;charset=UTF-8" )
@Consumes("application/json")
public class ArtefactResource {

    @Inject
    private ArtefactService artefactService;

    /**
     * Find an artefact in the repository by ID
     * @param id
     * @return Artefact artefact
     */
    @GET
    @Path("{id}")
    public Artefact get( @PathParam("id") long id ) {
        return this.artefactService.find( id );
    }

    /**
     * Create and store a new artefact in the repository
     * @param artefact
     * @return
     * @throws URISyntaxException
     */
    @POST
    public Response create( Artefact artefact ) throws URISyntaxException {
        final Artefact created = this.artefactService.create( artefact );

        return redirect( "artefact/" + created.getId() );
    }

    /**
     * Update an artefact in the repository
     * @param id
     * @param artefact
     * @return
     * @throws URISyntaxException
     */
    @PUT
    @Path("{id}")
    public Response update( @PathParam("id") long id, Artefact artefact ) throws URISyntaxException {
        artefact.setId( id );
        final Artefact updated = this.artefactService.update( artefact );
        return redirect( "artefact/", updated );
    }

    /**
     * Delete an artefact from the repository
     * @param id
     */
    @DELETE
    @Path("{id}")
    public void delete( @PathParam("id") long id ) {
        this.artefactService.delete(id);
    }

}
