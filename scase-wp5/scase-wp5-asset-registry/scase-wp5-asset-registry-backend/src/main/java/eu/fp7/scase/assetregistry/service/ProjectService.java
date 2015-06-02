package eu.fp7.scase.assetregistry.service;

import eu.fp7.scase.assetregistry.connector.ElasticSearchConnectorService;
import eu.fp7.scase.assetregistry.data.Project;
import eu.fp7.scase.assetregistry.service.db.ProjectDbService;
import eu.fp7.scase.assetregistry.service.es.ProjectEsService;
import eu.fp7.scase.assetregistry.service.exception.NotCreatedException;
import eu.fp7.scase.assetregistry.service.exception.NotUpdatedException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by missler on 17/03/15.
 */
@Stateless
public class ProjectService {

    @EJB
    private ProjectDbService dbService;

    @EJB
    private ProjectEsService esService;

    public Project find(long id){
        Project project = dbService.find(id);
        return project;
    }

    public List<Project> find(String query){
        List<Project> projects = esService.find(query);
        return projects;
    }

    public Project create(Project project){

        try {
            project = dbService.create(project);
            esService.index(project);
        } catch (Throwable thrown) {
            throw new NotCreatedException(Project.class,project.getId(),thrown);
        }

        return project;
    }

    public Project update(Project project){
        try{
            project = dbService.update(project);
            esService.update(project);
        }catch(Throwable thrown){
            throw new NotUpdatedException(Project.class,project.getId(),thrown);
        }
        return project;
    }

    public void delete(long id){
        esService.delete(id, ElasticSearchConnectorService.INDEX_PROJECTS,ElasticSearchConnectorService.TYPE_PROJECT);
        dbService.delete(id);
    }

    public void delete(Project project){
        esService.delete(project, ElasticSearchConnectorService.INDEX_PROJECTS,ElasticSearchConnectorService.TYPE_PROJECT);
        dbService.delete(project);
    }
}
