package eu.fp7.scase.assetregistry.connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.fp7.scase.assetregistry.data.Artefact;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ElasticSearchRepositoryService {

    private final static Logger LOG = LoggerFactory.getLogger(ElasticSearchRepositoryService.class);

    @EJB
    private ElasticSearchConnectorService elasticSearchConnectorService;

    @Inject
    ObjectMapper mapper;

    public List<Artefact> find(final String query){
        final QueryBuilder queryBuilder = queryStringQuery( query );

        SearchResponse response = getElasticSearchClient().prepareSearch(ElasticSearchConnectorService.INDEX)
                .setTypes(ElasticSearchConnectorService.SEARCH_TYPE )
                .setQuery( queryBuilder )
                .setFrom( 0 ).setSize( 500 ).setExplain( true )
                .execute()
                .actionGet();

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

    public DeleteResponse delete(final Long id, final String type){
        return getElasticSearchClient().prepareDelete(ElasticSearchConnectorService.INDEX,type,id.toString()).execute().actionGet();
    }

    private QueryBuilder queryStringQuery( final String query ) {
        QueryBuilder qb = QueryBuilders.queryString(query);
        return qb;
    }

    private Client getElasticSearchClient(){
       return elasticSearchConnectorService.getClient();
    }
}
