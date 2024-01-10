package liftoff.atlas.getcultured.services;

import liftoff.atlas.getcultured.dto.StopForm;
import liftoff.atlas.getcultured.models.Stop;
import liftoff.atlas.getcultured.models.data.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StopService {

    private final StopRepository stopRepository;

    @Autowired
    public StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    // Method to find a stop by ID
    public Stop findById(int id) {
        Optional<Stop> stop = stopRepository.findById(id);
        return stop.orElse(null);
    }

    // Method to get all stops
    public List<Stop> findAll() {
        return (List<Stop>) stopRepository.findAll();
    }

    // Method to save a stop
    public void saveStop(Stop stop, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = storeImage(imageFile); // Store the image and get the filename
            stop.setImagePath(filename); // Save the file path in the Stop object
        }
        stopRepository.save(stop);
    }

    // Method to delete a stop
    public void deleteStop(int id) {
        stopRepository.deleteById(id);
    }

    // Method to store an image
    private String storeImage(MultipartFile file) throws IOException {
        // Define the directory where you want to store images
        Path imageDirectory = Paths.get("C:/Users/lhedr/LaunchCode/GetCultured/images");

        // Extract the original file extension
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // Generate a unique filename to avoid overwriting existing files
        String filename = UUID.randomUUID().toString() + fileExtension;

        // Resolve the file path
        Path filePath = imageDirectory.resolve(filename);

        // Save the file
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Return the relative path to be saved in the database
        return "images/" + filename;
    }

    public StopForm getStopFormById(int stopId) {
        Optional<Stop> stopOptional = stopRepository.findById(stopId);
        if (stopOptional.isPresent()) {
            return convertToStopForm(stopOptional.get());
        }
        return null; // or throw an exception if preferred
    }

    private StopForm convertToStopForm(Stop stop) {
        StopForm stopForm = new StopForm();
        stopForm.setId(stop.getId());
        stopForm.setName(stop.getName());
        stopForm.setDescription(stop.getStopDescription());
        // Set other fields as necessary
        return stopForm;
    }

    // Other methods
}