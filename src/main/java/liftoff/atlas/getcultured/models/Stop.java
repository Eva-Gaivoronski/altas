package liftoff.atlas.getcultured.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Stop extends AbstractEntity {

    @ManyToOne
    private Tour tour;
    private boolean stopStatus;
    @Size(max = 300, message = "Description can be no longer than 300 characters")
    private String stopDescription;

    private String streetAddress;

    private String cityName;

    private String stateName;

    private int zipCode;

    @Column(name = "latitude", insertable = false, updatable = false)
    private Double latitude;

    @Column(name = "longitude", insertable = false, updatable = false)
    private Double longitude;

    @Embedded
    private MapMarker mapMarker;

    private double cost;

    private String hoursOfOperation;

    private double stopRating;

    private String category;

    private boolean popularStopDesignation;

    @OneToMany(mappedBy = "stop")
    private List<StopTag> stopTag;

    public Stop(Tour tour, boolean stopStatus, String stopDescription, String streetAddress, String cityName, String stateName, int zipCode, Double latitude, Double longitude, MapMarker mapMarker, int cost, String hoursOfOperation, int stopRating, String category, boolean popularStopDesignation, List<StopTag> stopTags) {
        super();
        this.tour = tour;
        this.stopStatus = stopStatus;
        this.stopDescription = stopDescription;
        this.streetAddress = streetAddress;
        this.cityName = cityName;
        this.stateName = stateName;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mapMarker = mapMarker;
        this.cost = cost;
        this.hoursOfOperation = hoursOfOperation;
        this.stopRating = stopRating;
        this.category = category;
        this.popularStopDesignation = popularStopDesignation;
        this.stopTag = stopTags;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public boolean isStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(boolean stopStatus) {
        this.stopStatus = stopStatus;
    }

    public String getStopDescription() {
        return stopDescription;
    }

    public void setStopDescription(String stopDescription) {
        this.stopDescription = stopDescription;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public MapMarker getMapMarker() {
        return mapMarker;
    }

    public void setMapMarker(MapMarker mapMarker) {
        this.mapMarker = mapMarker;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public double getStopRating() {
        return stopRating;
    }

    public void setStopRating(int stopRating) {
        this.stopRating = stopRating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPopularStopDesignation() {
        return popularStopDesignation;
    }

    public void setPopularStopDesignation(boolean popularStopDesignation) {
        this.popularStopDesignation = popularStopDesignation;
    }

    public List<StopTag> getStopTag() {
        return stopTag;
    }

    public void setStopTag(List<StopTag> stopTag) {
        this.stopTag = stopTag;
    }
}
