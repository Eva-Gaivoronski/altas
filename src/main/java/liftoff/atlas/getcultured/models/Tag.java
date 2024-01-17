package liftoff.atlas.getcultured.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
public class Tag extends AbstractEntity {

    @NotBlank(message = "Label cannot be blank")
    private String label;
    @NotBlank(message = "Color cannot be blank")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Invalid color format. Use hex code.")
    private String color;

    @ManyToOne
    private TagGroup tagGroup;

    public Tag() { }

    public Tag(String label, String color, TagGroup tagGroup) {
        super();
        this.label = label;
        this.color = color;
        this.tagGroup = tagGroup;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TagGroup getTagGroup() {
        return tagGroup;
    }

    public void setTagGroup(TagGroup tagGroup) {
        this.tagGroup = tagGroup;
    }
}