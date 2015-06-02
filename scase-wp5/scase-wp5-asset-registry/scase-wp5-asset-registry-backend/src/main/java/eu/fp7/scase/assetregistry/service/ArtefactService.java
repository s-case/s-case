package eu.fp7.scase.assetregistry.service;

import eu.fp7.scase.assetregistry.connector.ElasticSearchConnectorService;
import eu.fp7.scase.assetregistry.data.Artefact;
import eu.fp7.scase.assetregistry.service.db.ArtefactDbService;
import eu.fp7.scase.assetregistry.service.es.ArtefactEsService;
import eu.fp7.scase.assetregistry.service.exception.NotCreatedException;
import eu.fp7.scase.assetregistry.service.exception.NotUpdatedException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by missler on 16/03/15.
 */
@Stateless
public class ArtefactService {

    @EJB
    private ArtefactDbService dbService;

    @EJB
    private ArtefactEsService esService;

    public Artefact find(long id){
        Artefact artefact = dbService.find(id);
        return artefact;
    }

    public List<Artefact> find(String query){
        List<Artefact> artefacts = esService.find(query);
        return artefacts;
    }

    public Artefact create(Artefact artefact){

        try {
            artefact = dbService.create(artefact);
            esService.index(artefact);
        } catch (Throwable thrown) {
            throw new NotCreatedException(Artefact.class,artefact.getId(),thrown);
        }

        return artefact;
    }

    public Artefact update(Artefact artefact){
        try{
            artefact = dbService.update(artefact);
            esService.update(artefact);
        }catch(Throwable thrown){
            throw new NotUpdatedException(Artefact.class,artefact.getId(),thrown);
        }
        return artefact;
    }

    public void delete(long id){
        esService.delete(id, ElasticSearchConnectorService.INDEX_ARTEFACTS,ElasticSearchConnectorService.TYPE_ARTEFACT);
        dbService.delete(id);
    }

    public void delete(Artefact artefact){
        esService.delete(artefact, ElasticSearchConnectorService.INDEX_ARTEFACTS,ElasticSearchConnectorService.TYPE_ARTEFACT);
        dbService.delete(artefact);
    }
}
