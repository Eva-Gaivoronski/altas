package liftoff.atlas.getcultured.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Tour extends AbstractEntity {

//    @NotBlank(message = "Image path cannot be blank")
    private String imagePath;
    private String summaryDescription;
    private Double estimatedLength;
    private Double estimatedTravelTime;
    private Double userRating;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Embedded
    private MapMarker location;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    private List<Stop> stops;

    public Tour() {
    }

    public Tour(String summaryDescription, Double estimatedLength,
                Double estimatedTravelTime, Double userRating, User author, MapMarker location, City city, String imagePath) {
        super();
        this.summaryDescription = summaryDescription;
        this.estimatedLength = estimatedLength;
        this.estimatedTravelTime = estimatedTravelTime;
        this.userRating = userRating;
        this.author = author;
        this.location = location;
        this.city = city;
        this.imagePath = imagePath;
    }

    public String getSummaryDescription() {
        return summaryDescription;
    }

    public void setSummaryDescription(String summaryDescription) {
        this.summaryDescription = summaryDescription;
    }

    public Double getEstimatedLength() {
        return estimatedLength;
    }

    public void setEstimatedLength(Double estimatedLength) {
        this.estimatedLength = estimatedLength;
    }

    public Double getEstimatedTravelTime() {
        return estimatedTravelTime;
    }

    public void setEstimatedTravelTime(Double estimatedTravelTime) {
        this.estimatedTravelTime = estimatedTravelTime;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public MapMarker getLocation() {
        return location;
    }

    public void setLocation(MapMarker location) {
        this.location = location;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
