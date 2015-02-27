package eu.fp7.scase.assetregistry.data;

import javax.persistence.*;

/**
 * entity class for project.
 * @author rmagnus
 *
 */
@Entity
@Table(name= "PROJECT")
public class Project extends BaseEntity 
{
    private static final long serialVersionUID = 1L;

    @Column(name="PROJECTNAME", nullable=false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
