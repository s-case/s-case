package eu.fp7.scase.assetregistry.service.db;

import eu.fp7.scase.assetregistry.data.Artefact;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Service to handle CRUD operations of artefacts
 */
@Stateless
public class ArtefactDbService extends BaseCrudDbService<Artefact> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    Class<Artefact> getEntityClass() {
        return Artefact.class;
    }

    @Override
    public Artefact update(Artefact entity) {
        Artefact loaded = find(entity.getId());
        validateVersion(loaded, entity);
        loaded.setDependencies(entity.getDependencies());
        loaded.setDescription(entity.getDescription());
        loaded.setGroupId(entity.getGroupId());
        loaded.setName(entity.getName());

        loaded.setPayload(entity.getPayload());

        loaded.setTags(entity.getTags());
        loaded.setType(entity.getType());
        loaded.setUri(entity.getUri());
        loaded.setUpdatedAt(new Date());


        return loaded;
    }

    @Override
    protected EntityManager em() {
        return entityManager;
    }
}
