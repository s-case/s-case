package eu.fp7.scase.assetregistry.rest;

import eu.fp7.scase.assetregistry.connector.ElasticSearchRepositoryService;
import eu.fp7.scase.assetregistry.data.Artefact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST class to perform Elastic Search operations
 */
@ApplicationPath("rest")
@Path("/repository")
public class HttpEndPoint extends Application{

    private static final Logger LOG = LoggerFactory.getLogger(HttpEndPoint.class);

    @Inject
    private ElasticSearchRepositoryService search;

    /**
     *
     * @param query
     * @return List<Artefact> artefacts
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Artefact> search(@QueryParam("q") final String query) {
        LOG.info("search '{}'", query);
        final List<Artefact> artefacts = search.find(query);
        return artefacts;
    }
}
