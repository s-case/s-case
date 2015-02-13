package eu.fp7.scase.assetregistry.service.exception;

import eu.fp7.scase.assetregistry.data.BaseEntity;
import eu.fp7.scase.base.ScaseException;


/**
 * exception for not found entities.
 * @author Robert Magnus
 *
 */
public class NotFoundException extends ScaseException {

    private static final long serialVersionUID = 3162547251893625565L;

    private final Class<? extends BaseEntity> clazz;
    private final long id;


    /**
     * constructor.
     * @param clazz entity class
     * @param id primary key
     */
    public NotFoundException( Class<? extends BaseEntity> clazz, long id ) {
        super( clazz.getSimpleName() + " mit der ID " + id + " wurde nicht gefunden.");
        this.clazz = clazz;
        this.id = id;
    }

    public Class<? extends BaseEntity> getClazz() {
        return this.clazz;
    }

    public long getId() {
        return this.id;
    }
}
