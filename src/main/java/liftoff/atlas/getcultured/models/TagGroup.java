package liftoff.atlas.getcultured.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class TagGroup extends AbstractEntity {
    private String label;
    private String domain;
    private boolean isActive;

    @OneToMany(mappedBy = "tagGroup")
    private List<Tag> tags;

    public TagGroup(String label, String domain, boolean isActive, List<Tag> tags) {
        super();
        this.label = label;
        this.domain = domain;
        this.isActive = isActive;
        this.tags = tags;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
