package liftoff.atlas.getcultured.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class UserReview extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "User cannot be null")
    private User user;

    @ManyToOne
    @NotNull(message = "Tour cannot be null")
    private Tour tour;

    @NotBlank(message = "Review text cannot be blank")
    private String reviewText;

    private int rating;

    public UserReview() { }

    public UserReview(User user, Tour tour, String reviewText, int rating) {
        super();
        this.user = user;
        this.tour = tour;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    // Add getters and setters for other fields

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

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
