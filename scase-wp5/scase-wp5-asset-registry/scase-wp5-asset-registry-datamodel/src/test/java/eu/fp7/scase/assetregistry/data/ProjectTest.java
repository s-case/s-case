package eu.fp7.scase.assetregistry.data;

import de.akquinet.jbosscc.needle.junit.DatabaseRule;
import eu.fp7.scase.assetregistry.data.testdata.ProjectTestdataBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;

/**
 * Test class of project entity
 */
public class ProjectTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    private EntityManager entityManager = databaseRule.getEntityManager();

    @Test
    public void testPersist() throws Exception{
        Project project = new ProjectTestdataBuilder(entityManager).buildAndSave();
        Project projectFromDb = entityManager.find(Project.class,project.getId());
        Assert.assertEquals(project.getId(), projectFromDb.getId());
    }
}
