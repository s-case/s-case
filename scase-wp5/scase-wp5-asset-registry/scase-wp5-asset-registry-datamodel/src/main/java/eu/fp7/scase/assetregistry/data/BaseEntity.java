package eu.fp7.scase.assetregistry.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;


@MappedSuperclass
public abstract class BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    // XmlElement needed! otherwise jaxb does not serialize this field
    @XmlElement
    private Long version;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getVersion()
    {
        return version;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (o instanceof BaseEntity) {
            BaseEntity that = (BaseEntity) o;

            if (getId() == null) {
                return super.equals(o);
            }
            if (getId().equals(that.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return (getId() != null) ? getId().hashCode() : super.hashCode();
    }
}
