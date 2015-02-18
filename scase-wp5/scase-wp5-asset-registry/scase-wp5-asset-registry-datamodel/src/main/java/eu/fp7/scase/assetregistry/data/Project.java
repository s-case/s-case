package eu.fp7.scase.assetregistry.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * entity class for project.
 * @author rmagnus
 *
 */
@Entity
@Table(name="project")
public class Project extends BaseEntity 
{
    @Column(name="column", nullable=false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
