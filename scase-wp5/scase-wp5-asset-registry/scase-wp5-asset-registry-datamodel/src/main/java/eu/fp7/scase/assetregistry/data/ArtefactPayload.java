package eu.fp7.scase.assetregistry.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * JPA entity representation of an artefact payload
 */
@Entity
@Table(name = "ARTEFACTPAYLOAD")
public class ArtefactPayload extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 542435835493989083L;

    @Column(name = "TYPE", nullable = false)
    private PlayloadType type;

    @Column(name = "PAYLOADNAME",nullable = false)
    private String name;

    @Lob
    @Column(name = "PAYLOAD")
    private byte[] playload;

    public byte[] getPlayload() {
        return playload;
    }

    public void setPlayload(byte[] playload) {
        this.playload = playload;
    }

    public PlayloadType getType() {
        return type;
    }

    public void setType(PlayloadType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
