package eu.fp7.scase.assetregistry.data;

import de.akquinet.jbosscc.needle.db.transaction.VoidRunnable;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;
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
    public void testPersist() throws Exception {
        final ArtefactTestdataBuilder builder = new ArtefactTestdataBuilder(entityManager);
        Artefact artefact = builder.buildAndSave();
        Artefact artefactFromDb = entityManager.find(Artefact.class, artefact.getId());
        Assert.assertEquals(artefact.getId(), artefactFromDb.getId());
        Assert.assertEquals(2,artefactFromDb.getTags().size());

        databaseRule.getTransactionHelper().executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(EntityManager entityManager) throws Exception {
                final Artefact artefactWithPlayload = builder.buildWithPayload();
                entityManager.persist(artefactWithPlayload);
                Artefact artefactFromDbWithPayload = entityManager.find(Artefact.class, artefactWithPlayload.getId());
                Assert.assertEquals(artefactWithPlayload.getPayload().size(),artefactFromDbWithPayload.getPayload().size());
                Assert.assertEquals(2,artefactFromDbWithPayload.getPayload().size());
            }
        });
    }
}
