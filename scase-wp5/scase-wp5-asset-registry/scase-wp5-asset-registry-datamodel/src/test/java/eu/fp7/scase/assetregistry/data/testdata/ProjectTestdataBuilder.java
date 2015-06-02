package eu.fp7.scase.assetregistry.data.testdata;

import de.akquinet.jbosscc.needle.db.testdata.AbstractTestdataBuilder;

import eu.fp7.scase.assetregistry.data.Project;

import javax.persistence.EntityManager;
import java.util.Arrays;

/**
 *
 */
public class ProjectTestdataBuilder extends AbstractTestdataBuilder<Project> {

    public ProjectTestdataBuilder(final EntityManager entityManager){
        super(entityManager);
    }

    @Override
    public Project build() {
        final Project project = new Project();
        project.setName("Testproject");
        project.setArtefacts(Arrays.asList(new ArtefactTestdataBuilder(getEntityManager()).buildWithPayload()));
        return project;
    }
}
