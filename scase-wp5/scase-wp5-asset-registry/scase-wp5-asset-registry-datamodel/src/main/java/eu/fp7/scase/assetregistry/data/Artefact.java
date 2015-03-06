package eu.fp7.scase.assetregistry.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * JPA entity representation of an artefact
 *
 */
@Entity
@Table(name = "ARTEFACT")
public class Artefact extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -8329957242653476838L;

    @Column(name="URI")
    private String uri;

    @Column(name = "GROUPID")
    private String groupId;

    @Column(name = "ARTEFACTNAME")
    private String name;

    @Column(name = "DEPENDENCIES")
    private List<Long> dependencies;

    @Column(name = "TYPE")
    private ArtefactType type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TAGS")
    private List<String> tags;

    @OneToMany
    @Column(name = "PLAYLOAD")
    private List<ArtefactPayload> payload;

    public String getUri() {

        return uri;
    }

    public void setUri(String uri) {

        this.uri = uri;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Long> dependencies) {
        this.dependencies = dependencies;
    }

    public ArtefactType getType() {
        return type;
    }

    public void setType(ArtefactType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<ArtefactPayload> getPayload() {
        return payload;
    }

    public void setPayload(List<ArtefactPayload> payload) {
        this.payload = payload;
    }

}
