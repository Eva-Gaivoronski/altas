package liftoff.atlas.getcultured.models.dto;

import jakarta.validation.constraints.NotNull;
import liftoff.atlas.getcultured.models.Tag;
import liftoff.atlas.getcultured.models.Tour;

public class TourTagDTO {

    @NotNull
    private Tour tour;
    @NotNull
    private Tag tag;

    public TourTagDTO(){}

    public Tour getTour() {
        return tour;
    }

    public void setTours(Tour tour) {
        this.tour = tour;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
