package eu.fp7.scase.assetregistry.service.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.fp7.scase.assetregistry.connector.ElasticSearchConnectorService;
import eu.fp7.scase.assetregistry.data.BaseEntity;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created by missler on 16/03/15.
 */
public abstract class AbstractEsService<E extends BaseEntity> {

    @EJB
    protected ElasticSearchConnectorService connectorService;

    public abstract List<E> find(final String query);

    public abstract IndexResponse index(final E entity) throws JsonProcessingException;

    public abstract UpdateResponse update(final E entity) throws JsonProcessingException;

    public DeleteResponse delete(long id, final String index, final String type){
        return connectorService.getClient().prepareDelete(index,type,Long.toString(id)).execute().actionGet();
    }

    public DeleteResponse delete(final E entity, final String index, final String type){
        return connectorService.getClient().prepareDelete(index,type,Long.toString(entity.getId())).execute().actionGet();
    }

    protected QueryBuilder queryStringQuery( final String query ) {
        QueryBuilder qb = QueryBuilders.queryString(query);
        return qb;
    }

    protected SearchResponse getSearchResponse(String index, String type, String query) {
        final QueryBuilder queryBuilder = queryStringQuery( query );

        return connectorService.getClient().prepareSearch(index)
                .setTypes(type )
                .setQuery( queryBuilder )
                .setFrom( 0 ).setSize( 500 ).setExplain( true )
                .execute()
                .actionGet();
    }

}
