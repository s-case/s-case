package eu.fp7.scase.assetregistry.rest;

import static eu.fp7.scase.assetregistry.rest.ResourceTools.redirect;

import java.net.URISyntaxException;

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

import eu.fp7.scase.assetregistry.data.Project;
import eu.fp7.scase.assetregistry.service.ProjectService;

@Path( AssetRegistryRestApp.PART_PROJECT)
@Produces( "application/json;charset=UTF-8" )
@Consumes("application/json")
public class ProjectRessource {

    @Inject
    private ProjectService projectService;

    
    @GET
    @Path("{id}")
    public Project get( @PathParam("id") long id ) {
        return this.projectService.find( id );
    }

    @POST
    public Response create( Project project ) throws URISyntaxException {
        final Project created = this.projectService.create( project );

        return redirect( "project/" + created.getId() );
    }

    @PUT
    @Path("{id}")
    public Response update( @PathParam("id") long id, Project project ) throws URISyntaxException {
        project.setId( id );
        final Project updated = this.projectService.update( project );
        return redirect( "project/", updated );
    }

    @DELETE
    @Path("{id}")
    public void delete( @PathParam("id") long id ) {
        delete(id);
    }

}
