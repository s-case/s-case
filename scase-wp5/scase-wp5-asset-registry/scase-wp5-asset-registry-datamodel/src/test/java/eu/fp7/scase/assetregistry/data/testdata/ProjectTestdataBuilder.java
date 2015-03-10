package eu.fp7.scase.assetregistry.data.testdata;

import de.akquinet.jbosscc.needle.db.testdata.AbstractTestdataBuilder;

import eu.fp7.scase.assetregistry.data.Project;

import javax.persistence.EntityManager;

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
        return project;
    }
}
