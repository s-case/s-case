package eu.fp7.scase.assetregistry.data.testdata;

import de.akquinet.jbosscc.needle.db.testdata.AbstractTestdataBuilder;
import eu.fp7.scase.assetregistry.data.Artefact;
import eu.fp7.scase.assetregistry.data.ArtefactType;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;

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
        artefact.setName("Test Artefact");
        artefact.setType(ArtefactType.TEXTUAL);
        artefact.setTags(Arrays.asList("Test", "Needle"));
        artefact.setDescription("Test Artefact Description");
        return artefact;
    }

    public Artefact buildWithPayload(){
        final Artefact artefact = build();
        artefact.addPayload(new ArtefactPayloadTestdataBuilder().build());
        return artefact;
    }
}
