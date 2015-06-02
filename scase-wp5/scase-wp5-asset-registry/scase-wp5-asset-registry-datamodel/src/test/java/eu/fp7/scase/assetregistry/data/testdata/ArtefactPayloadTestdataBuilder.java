package eu.fp7.scase.assetregistry.data.testdata;

import de.akquinet.jbosscc.needle.db.testdata.AbstractTestdataBuilder;
import eu.fp7.scase.assetregistry.data.ArtefactPayload;
import eu.fp7.scase.assetregistry.data.PlayloadType;


/**
 *
 */
public class ArtefactPayloadTestdataBuilder extends AbstractTestdataBuilder<ArtefactPayload> {

    @Override
    public ArtefactPayload build() {
        final ArtefactPayload artefactPlayload = new ArtefactPayload();
        artefactPlayload.setType(PlayloadType.BINARY);
        artefactPlayload.setName("TestPayload Name");
        artefactPlayload.setPlayload("TestPayload Content".getBytes());
        return artefactPlayload;
    }
}
