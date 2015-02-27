package eu.fp7.scase.assetregistry.rest;

import eu.fp7.scase.assetregistry.connector.SearchRepository;
import eu.fp7.scase.assetregistry.data.Artefact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationPath("rest")
@Path("/repository")
public class HttpEndPoint extends Application{

    private final static Logger LOG = LoggerFactory.getLogger(HttpEndPoint.class);

    @Inject
    SearchRepository search;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Artefact> search(@QueryParam("q") final String query) {
        LOG.info("search '{}'", query);
        final List<Artefact> artefacts = search.find(query);
        return artefacts;
    }
}
