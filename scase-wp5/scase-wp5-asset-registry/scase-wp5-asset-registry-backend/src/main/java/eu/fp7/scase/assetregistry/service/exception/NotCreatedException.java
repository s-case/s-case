package eu.fp7.scase.assetregistry.service.exception;

import eu.fp7.scase.assetregistry.data.BaseEntity;
import eu.fp7.scase.base.ScaseException;

/**
 * Exception to be thrown if an asset could not be stored
 */
public class NotCreatedException extends ScaseException {

    private static final long serialVersionUID = 3162547251893625565L;

    private final Class<? extends BaseEntity> clazz;
    private final long id;


    /**
     * constructor.
     *
     * @param clazz entity class
     * @param id    primary key
     */
    public NotCreatedException(Class<? extends BaseEntity> clazz, long id, Throwable thrown) {
        super(clazz.getSimpleName() + " with ID " + id + " could not be stored inside of the Asset Registry." + System.getProperty("line.separator") + thrown.getMessage());
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
