package eu.fp7.scase.assetregistry.service.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.fp7.scase.assetregistry.connector.ElasticSearchConnectorService;
import eu.fp7.scase.assetregistry.data.Artefact;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Artefact related ElasticSearch operations
 */
@Stateless
public class ArtefactEsService extends AbstractEsService<Artefact>{

    private final static Logger LOG = LoggerFactory.getLogger(ArtefactEsService.class);

    @Inject
    ObjectMapper mapper;

    public List<Artefact> find(final String query){
        SearchResponse response = getSearchResponse(ElasticSearchConnectorService.INDEX_ARTEFACTS, ElasticSearchConnectorService.TYPE_ARTEFACT, query);

        final List<Artefact> result = new ArrayList<Artefact>();
        for ( SearchHit hit : response.getHits().hits() ) {
            try {
                final Artefact artefact = mapper.readValue( hit.sourceAsString(), Artefact.class );
                result.add( artefact );
                LOG.info( "found {} because of {}", artefact, hit.getExplanation() );
            } catch ( IOException e ) {
                LOG.error( "reading object failed", e );
            }
        }
        return result;
    }

    public IndexResponse index(final Artefact artefact) throws JsonProcessingException {
        String json = mapper.writeValueAsString(artefact);
        IndexResponse response = connectorService.getClient().prepareIndex(ElasticSearchConnectorService.INDEX_ARTEFACTS, ElasticSearchConnectorService.TYPE_ARTEFACT, artefact.getId().toString()).setSource(json).execute().actionGet();
        return response;
    }

    public UpdateResponse update(final Artefact artefact) throws JsonProcessingException {
        String json = mapper.writeValueAsString(artefact);
        UpdateResponse response = connectorService.getClient().prepareUpdate(ElasticSearchConnectorService.INDEX_ARTEFACTS,ElasticSearchConnectorService.TYPE_ARTEFACT,artefact.getId().toString()).setDoc(json).execute().actionGet();
             return response;
    }
}
