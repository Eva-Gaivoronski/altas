package liftoff.atlas.getcultured.dto;

import jakarta.annotation.ManagedBean;
import liftoff.atlas.getcultured.models.Stop;
import liftoff.atlas.getcultured.models.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourForm {

    private int id;

    private String imagePath;

    private String name;

    private String summaryDescription;
    private Tour tour;
    private List<StopForm> stops;

    private Double estimatedLength;

    private List<StopForm> newStops; // List to hold new stops to be added
    private List<Integer> stopsToRemove; // List of stop IDs to be removed

    public TourForm() {
        this.tour = new Tour();
        this.stops = new ArrayList<>();
        this.newStops = new ArrayList<>();
        this.stopsToRemove = new ArrayList<>();
    }

    // Getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public List<StopForm> getStops() {
        return stops;
    }

    public void setStops(List<StopForm> stops) {
        this.stops = stops;
    }

    // Method to add a stop
    public void addStop(StopForm stop) {
        this.stops.add(stop);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<StopForm> getNewStops() {
        return newStops;
    }

    public void setNewStops(List<StopForm> newStops) {
        this.newStops = newStops;
    }

    public List<Integer> getStopsToRemove() {
        return stopsToRemove;
    }

    public void setStopsToRemove(List<Integer> stopsToRemove) {
        this.stopsToRemove = stopsToRemove;
    }
}
