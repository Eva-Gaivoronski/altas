package liftoff.atlas.getcultured.models.dto;

import jakarta.validation.constraints.NotNull;
import liftoff.atlas.getcultured.models.Tag;
import liftoff.atlas.getcultured.models.Tour;

public class TourTagDTO {

    @NotNull
    private Tour tours;
    @NotNull
    private Tag tag;

    public TourTagDTO(){}

    public Tour getTours() {
        return tours;
    }

    public void setTours(Tour tours) {
        this.tours = tours;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
