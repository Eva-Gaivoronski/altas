package liftoff.atlas.getcultured.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class City extends AbstractEntity {

    private String state;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Tour> tours;

    public City() {

    }

    public City(String state, List<Tour> tours) {
        super();
        this.state = state;
        this.tours = tours;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }
}
