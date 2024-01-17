package liftoff.atlas.getcultured.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import liftoff.atlas.getcultured.dto.StopForm;
import liftoff.atlas.getcultured.dto.TourForm;
import liftoff.atlas.getcultured.models.Stop;
import liftoff.atlas.getcultured.models.Tour;
import liftoff.atlas.getcultured.models.data.StopRepository;
import liftoff.atlas.getcultured.models.data.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TourService {

    private static final Logger logger = LoggerFactory.getLogger(TourService.class);
    private final TourRepository tourRepository;

    private final StopRepository stopRepository;

    private final StopService stopService;
    private final Path rootLocation; // Image storage location

    @Autowired
    public TourService(TourRepository tourRepository, StopRepository stopRepository, StopService stopService) {
        this.tourRepository = tourRepository;
        this.stopRepository = stopRepository;
        this.stopService = stopService;
        this.rootLocation = Paths.get("C:/Users/lhedr/LaunchCode/GetCultured/images");
    }

    public List<Tour> getAllTours() {
        return (List<Tour>) tourRepository.findAll();
    }

    public Tour getTourById(int tourId) {
        return tourRepository.findById(tourId).orElse(null);
    }

    @Transactional
    public void saveTour(Tour tour, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = storeImage(imageFile);
            tour.setImagePath(filename);
        } else {
            String defaultImagePath = "defaultLogo/DefaultLogo.jpg"; // Path to default image
            tour.setImagePath(defaultImagePath);
        }
        tourRepository.save(tour);
    }

    @Transactional
    public void deleteTour(int tourId) {
        tourRepository.deleteById(tourId);
    }

    public String storeImage(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String filename = UUID.randomUUID().toString() + fileExtension;
        Path filePath = rootLocation.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "images/" + filename;
    }

    // Revised method to handle image and stop updates during tour creation
    public Tour createTourFromForm(TourForm tourForm, MultipartFile imageFile) throws IOException {
        Tour tour = new Tour();
        // Set basic properties from the form
        setBasicTourPropertiesFromForm(tour, tourForm);

        // Handle image upload
        handleTourImageUpload(tour, imageFile);

        // Handling stops
        updateStopsForTour(tour, tourForm.getStops());

        return tourRepository.save(tour);
    }

    // Helper method to set basic tour properties from the form
    private void setBasicTourPropertiesFromForm(Tour tour, TourForm tourForm) {
        tour.setName(tourForm.getName());
        tour.setSummaryDescription(tourForm.getSummaryDescription());
        tour.setEstimatedLength(tourForm.getEstimatedLength());
        // ... other properties ...
    }

    // Helper method to handle tour image upload
    private void handleTourImageUpload(Tour tour, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = storeImage(imageFile);
            tour.setImagePath(filename);
        } else {
            // Set default image if no image is provided
            String defaultImagePath = "defaultLogo/DefaultLogo.jpg";
            tour.setImagePath(defaultImagePath);
        }
    }

    // Helper method to update stops for a tour
    private void updateStopsForTour(Tour tour, List<StopForm> stopForms) {
        for (StopForm stopForm : stopForms) {
            Stop stop = convertStopFormToEntity(stopForm);
            tour.addStop(stop);
        }
    }

    // Update functionality for stops
//    private void updateExistingStop(Stop existingStop, Stop newStop) {
//        existingStop.setName(newStop.getName());
//        existingStop.setStopDescription(newStop.getStopDescription());
//        existingStop.setStreetAddress(newStop.getStreetAddress());
//        existingStop.setCityName(newStop.getCityName());
//        existingStop.setStateName(newStop.getStateName());
//        existingStop.setZipCode(newStop.getZipCode());
//        // ... other properties ...
//    }

    public Tour updateTour(TourForm tourForm) throws Exception {
        Optional<Tour> existingTourOpt = tourRepository.findById(tourForm.getId());
        if (!existingTourOpt.isPresent()) {
            throw new Exception("Tour not found with ID: " + tourForm.getId());
        }

        Tour existingTour = existingTourOpt.get();

        // Update basic properties of the tour
        existingTour.setName(tourForm.getName());
        // ... other property updates ...

        // Handle stops
        handleStopsForTour(tourForm, existingTour);

        // Save the updated tour
        return tourRepository.save(existingTour);
    }

    private void handleStopsForTour(TourForm tourForm, Tour existingTour) throws Exception {
        Set<Integer> formStopIds = tourForm.getStops().stream()
                .map(StopForm::getId)
                .collect(Collectors.toSet());

        // Remove stops that are no longer in the tourForm
        existingTour.getStops().removeIf(stop -> !formStopIds.contains(stop.getId()));

        // Add or update stops from the tourForm
        for (StopForm stopForm : tourForm.getStops()) {
            Stop stop;
            if (stopForm.getId() != null) {
                // Existing stop, update it
                stop = stopRepository.findById(stopForm.getId())
                        .orElseThrow(() -> new Exception("Stop not found with ID: " + stopForm.getId()));
                updateStopFromStopForm(stop, stopForm);
            } else {
                // New stop, create it
                stop = createStopFromStopForm(stopForm);
            }
            existingTour.getStops().add(stop);
        }
    }

    private Stop createStopFromStopForm(StopForm stopForm) {
        // Create a new Stop entity from stopForm
        Stop stop = new Stop();
        stop.setName(stopForm.getStopName());
        // ... set other properties ...
        return stopRepository.save(stop);
    }

    public Tour updateTourFromForm(int tourId, TourForm tourForm, MultipartFile imageFile) throws IOException {
        Tour existingTour = tourRepository.findById(tourId)
                .orElseThrow(() -> new EntityNotFoundException("Tour not found with ID: " + tourId));

        // Update the existing tour's details
        existingTour.setName(tourForm.getName());
        existingTour.setSummaryDescription(tourForm.getSummaryDescription());
        existingTour.setEstimatedLength(tourForm.getEstimatedLength());
        // Add any other fields that you need to update

        // Handling the image
        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = storeImage(imageFile);
            existingTour.setImagePath(filename);
        }

        // Handling stops
        // Assuming you have a method to convert StopForm to Stop entity
        List<Stop> updatedStops = tourForm.getStops().stream()
                .map(this::convertStopFormToEntity)
                .collect(Collectors.toList());
        existingTour.setStops(updatedStops); // Update the stops

        tourRepository.save(existingTour); // Save the updated tour
        return existingTour;
    }

    // Improved method for converting StopForm to Stop entity
    @Transactional
    private Stop convertStopFormToEntity(StopForm stopForm) {
        if (stopForm.getId() == null) {
            logger.error("Stop ID is missing in StopForm");
            throw new IllegalArgumentException("Stop ID is missing");
        }

        Stop stop = stopService.findById(stopForm.getId());
        if (stop == null) {
            logger.error("Stop not found with ID: {}", stopForm.getId());
            throw new EntityNotFoundException("Stop not found with ID: " + stopForm.getId());
        }

        // Update stop details from StopForm
        updateStopFromStopForm(stop, stopForm);
        return stop;
    }

    // Helper method to update stop details from StopForm
    private Stop updateStopFromStopForm(Stop stop, StopForm stopForm) {
        stop.setName(stopForm.getStopName());
        stop.setStopDescription(stopForm.getDescription());
        stop.setStreetAddress(stopForm.getStreetAddress());
        stop.setCityName(stopForm.getCityName());
        stop.setStateName(stopForm.getStateName());
        stop.setZipCode(stopForm.getZipCode());
        return stopRepository.save(stop);
        // ... other fields ...
    }

}

