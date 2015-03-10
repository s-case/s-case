package eu.fp7.scase.assetregistry.data;

import de.akquinet.jbosscc.needle.junit.DatabaseRule;
import de.akquinet.jbosscc.needle.junit.NeedleRule;
import eu.fp7.scase.assetregistry.data.testdata.ArtefactTestdataBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;

/**
 * Test class of artefact entity
 */
public class ArtefactTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();



    private EntityManager entityManager = databaseRule.getEntityManager();

    @Test
    public void testPersist() throws Exception{
        Artefact artefact = new ArtefactTestdataBuilder(entityManager).buildAndSave();
        Artefact artefactFromDb = entityManager.find(Artefact.class,artefact.getId());
        Assert.assertEquals(artefact.getId(),artefactFromDb.getId());

    }
}
