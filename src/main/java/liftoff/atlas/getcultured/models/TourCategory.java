package liftoff.atlas.getcultured.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TourCategory extends AbstractEntity {

    @OneToMany(mappedBy = "tourCategory")
    private final List<Tour> tours = new ArrayList<>();

    public TourCategory(){
    }

    public List<Tour> getTours() {
        return tours;
    }


    @Override
    public String toString() {
        return getName();
    }

}
