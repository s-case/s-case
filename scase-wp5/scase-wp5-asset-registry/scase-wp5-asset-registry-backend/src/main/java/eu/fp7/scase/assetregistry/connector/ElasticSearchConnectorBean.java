package eu.fp7.scase.assetregistry.connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;

@Singleton
@Startup
public class ElasticSearchConnectorBean {

    private final static Logger LOG = LoggerFactory.getLogger(ElasticSearchConnectorBean.class);

    private Client client;

    // instance of a json mapper
    private ObjectMapper mapper;


    public static final String INDEX = "searchindex";
    public static final String SEARCH_TYPE = "searchtype";

    @PostConstruct
    public void init(){

        LOG.info( "Sarting ElasticSearch Connector" );

        final String esServerNode = System.getProperty("es_node");

        client = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress(esServerNode, 9300));
    }

    @Produces
    public Client getClient() {

        return client;
    }

    @Produces
    public ObjectMapper getMapper(){

        return mapper;
    }

    @PreDestroy
    public void destroy(){

        client.close();
    }
}
