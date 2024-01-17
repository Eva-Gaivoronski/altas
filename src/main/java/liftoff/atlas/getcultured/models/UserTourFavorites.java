package liftoff.atlas.getcultured.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class UserTourFavorites extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "User cannot be null")
    private User user;

    @ManyToOne
    @NotNull(message = "Tour cannot be null")
    private Tour tour;

    public UserTourFavorites() { }

    public UserTourFavorites(User user, Tour tour) {
        super();
        this.user = user;
        this.tour = tour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
