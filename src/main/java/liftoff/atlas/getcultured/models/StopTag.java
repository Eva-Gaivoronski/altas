package liftoff.atlas.getcultured.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class StopTag extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Stop cannot be null")
    private Stop stop;

    @ManyToOne
    @NotNull(message = "Tag cannot be null")
    private Tag tag;

    public StopTag() { }

    public StopTag(Stop stop, Tag tag) {
        super();
        this.stop = stop;
        this.tag = tag;

    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
