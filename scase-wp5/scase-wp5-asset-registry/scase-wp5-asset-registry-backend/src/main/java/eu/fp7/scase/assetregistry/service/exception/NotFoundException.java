package eu.fp7.scase.assetregistry.service.exception;

import eu.fp7.scase.assetregistry.data.BaseEntity;
import eu.fp7.scase.base.ScaseException;


public class NotFoundException extends ScaseException {

    private static final long serialVersionUID = 3162547251893625565L;

    private Class<? extends BaseEntity> clazz;
    private long id;
    
    public NotFoundException( Class<? extends BaseEntity> clazz, long id ) {
        super( clazz.getSimpleName() + " mit der ID " + id + " wurde nicht gefunden.");
        this.clazz = clazz;
        this.id = id;
    }

    public Class<? extends BaseEntity> getClazz() {
        return clazz;
    }

    public long getId() {
        return id;
    }
}
