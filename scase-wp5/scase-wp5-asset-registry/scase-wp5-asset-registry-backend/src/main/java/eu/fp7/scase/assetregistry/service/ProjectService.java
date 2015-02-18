package eu.fp7.scase.assetregistry.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import eu.fp7.scase.assetregistry.data.Project;

/**
 * service class for project.
 * @author rmagnus
 *
 */
public class ProjectService extends BaseCrudService<Project> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    Class<Project> getEntityClass() {
        return Project.class;
    }

    @Override
    public Project update(Project entity) {
        Project loaded = find(entity.getId());
        validateVersion(loaded, entity);
        loaded.setName(entity.getName());

        return loaded;
    }

    @Override
    protected EntityManager em() {
        return entityManager;
    }

}
