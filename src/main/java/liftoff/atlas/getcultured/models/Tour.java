package liftoff.atlas.getcultured.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tour extends AbstractEntity {

    @NotBlank(message = "Title is required.")
    private String tourName;

    @Size(min = 15, max = 500, message = "Summary description must be 15-500 characters long.")
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

    @ManyToOne
    @NotNull(message="Category is required")
    private TourCategory tourCategory;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    private List<Stop> stops;

    public Tour() {
    }

    public Tour(String summaryDescription, Double estimatedLength,
                Double estimatedTravelTime, Double userRating, User author, MapMarker location, City city, TourCategory tourCategory) {
        super();
        this.summaryDescription = summaryDescription;
        this.estimatedLength = estimatedLength;
        this.estimatedTravelTime = estimatedTravelTime;
        this.userRating = userRating;
        this.author = author;
        this.location = location;
        this.city = city;
        this.tourCategory = tourCategory;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
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

    public TourCategory getTourCategory() {
        return tourCategory;
    }

    public void setTourCategory(TourCategory tourCategory) {
        this.tourCategory = tourCategory;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

}
