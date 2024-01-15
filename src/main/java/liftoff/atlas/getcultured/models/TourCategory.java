package liftoff.atlas.getcultured.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TourCategory extends AbstractEntity {

    private String label;

    @OneToMany(mappedBy = "tourCategory")
    private final List<Tour> tours = new ArrayList<>();

    public TourCategory(@Size(min = 3, message = "Name must be at least 3 characters long") String label){
        this.label = label;
    }

    public TourCategory(){
    }

    public List<Tour> getTours() {
        return tours;
    }


    @Override
    public String toString() {
        return getName();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
