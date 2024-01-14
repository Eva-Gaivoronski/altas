package liftoff.atlas.getcultured.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class UserReview extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "User cannot be null")
    private UserProfileDetails userProfile;

    @ManyToOne
    @NotNull(message = "Tour cannot be null")
    private Tour tour;

    @NotBlank(message = "Review text cannot be blank")
    private String reviewText;

    private int rating;

    public UserReview(UserProfileDetails userProfile, Tour tour, String reviewText, int rating) {
        super();
        this.userProfile = userProfile;
        this.tour = tour;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public UserProfileDetails getUserProfileDetails() {
        return userProfile;
    }

    public void setUserProfile(UserProfileDetails userProfile) {
        this.userProfile = userProfile;
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
