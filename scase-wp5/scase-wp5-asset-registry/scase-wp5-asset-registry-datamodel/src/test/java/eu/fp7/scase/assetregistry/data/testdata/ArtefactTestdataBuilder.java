package eu.fp7.scase.assetregistry.data.testdata;

import de.akquinet.jbosscc.needle.db.testdata.AbstractTestdataBuilder;
import eu.fp7.scase.assetregistry.data.Artefact;

import javax.persistence.EntityManager;

/**
 * Artefact test data builder
 */
public class ArtefactTestdataBuilder extends AbstractTestdataBuilder<Artefact>{

    public ArtefactTestdataBuilder(final EntityManager entityManager){
        super(entityManager);
    }

    @Override
    public Artefact build() {
        final Artefact artefact = new Artefact();
        return artefact;
    }
}
