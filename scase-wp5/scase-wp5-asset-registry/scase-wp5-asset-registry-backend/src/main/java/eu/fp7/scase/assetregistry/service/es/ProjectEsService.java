package eu.fp7.scase.assetregistry.service.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.fp7.scase.assetregistry.connector.ElasticSearchConnectorService;
import eu.fp7.scase.assetregistry.data.Project;
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
 * Service class for Project related ElasticSearch operations
 */
@Stateless
public class ProjectEsService extends AbstractEsService<Project>{

    private final static Logger LOG = LoggerFactory.getLogger(ProjectEsService.class);

    @Inject
    private ObjectMapper mapper;

    public List<Project> find(final String query){
        SearchResponse response = getSearchResponse(ElasticSearchConnectorService.INDEX_PROJECTS, ElasticSearchConnectorService.TYPE_PROJECT, query);

        final List<Project> result = new ArrayList<Project>();
        for ( SearchHit hit : response.getHits().hits() ) {
            try {
                final Project project = mapper.readValue( hit.sourceAsString(), Project.class );
                result.add( project );
                LOG.info( "found {} because of {}", project, hit.getExplanation() );
            } catch ( IOException e ) {
                LOG.error( "reading object failed", e );
            }
        }
        return result;
    }
    public IndexResponse index(final Project project) throws JsonProcessingException {
        String json = mapper.writeValueAsString(project);
        IndexResponse response = connectorService.getClient().prepareIndex(ElasticSearchConnectorService.INDEX_PROJECTS, ElasticSearchConnectorService.TYPE_PROJECT, project.getId().toString()).setSource(json).execute().actionGet();
        return response;
    }

    public UpdateResponse update(final Project project) throws JsonProcessingException {
        String json = mapper.writeValueAsString(project);
        UpdateResponse response = connectorService.getClient().prepareUpdate(ElasticSearchConnectorService.INDEX_PROJECTS,ElasticSearchConnectorService.TYPE_PROJECT,project.getId().toString()).setDoc(json).execute().actionGet();
        return response;
    }
}
