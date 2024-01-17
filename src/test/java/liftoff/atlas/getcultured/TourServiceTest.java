package liftoff.atlas.getcultured;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import liftoff.atlas.getcultured.controllers.TourController;
import liftoff.atlas.getcultured.dto.TourForm;
import liftoff.atlas.getcultured.models.Stop;
import liftoff.atlas.getcultured.models.Tour;
import liftoff.atlas.getcultured.models.data.TourRepository;
import liftoff.atlas.getcultured.services.TourService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class TourServiceTest {

    @Mock
    private TourRepository tourRepository;

    @InjectMocks
    private TourService tourService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllToursTest() {
        List<Tour> tours = new ArrayList<>();
        tours.add(new Tour()); // Add mock data as needed

        when(tourRepository.findAll()).thenReturn(tours);

        List<Tour> result = tourService.getAllTours();

        assertNotNull(result);
        assertEquals(tours.size(), result.size());
        verify(tourRepository, times(1)).findAll();
    }

    @Test
    void getTourByIdTest() {
        Tour mockTour = new Tour(); // Assuming Tour has a name, set it
        mockTour.setName("Test Tour");

        when(tourRepository.findById(anyInt())).thenReturn(Optional.of(mockTour));

        Tour result = tourService.getTourById(1); // Using 1 or any other int as a test case

        assertNotNull(result);
        assertEquals("Test Tour", result.getName());
        verify(tourRepository, times(1)).findById(anyInt());
    }

    @Test
    void saveTourTest() throws IOException {
        // Create a mock Tour object
        Tour tour = new Tour();
        tour.setName("Test Tour");
        // Set other properties of the tour as needed

        // Create a mock MultipartFile object
        MultipartFile mockImageFile = new MockMultipartFile(
                "image", // field name
                "test.jpg", // filename
                "image/jpeg", // content type
                new byte[0] // content (empty array for test)
        );

        // Call the saveTour method with both the tour and the mock MultipartFile
        tourService.saveTour(tour, mockImageFile);

        // Perform assertions to verify the expected behavior
        verify(tourRepository).save(any(Tour.class));
    }

    @Test
    void deleteTourTest() {
        int tourId = 1;
        doNothing().when(tourRepository).deleteById(tourId);

        tourService.deleteTour(tourId);

        verify(tourRepository, times(1)).deleteById(tourId);
    }

//    @Test
//    void updateTourTest() throws IOException {
//        // Create an existing tour
//        Tour existingTour = new Tour();
//        existingTour.setName("Old Name");
//
//        // Create an updated tour (simulate the one received from the client)
//        Tour updatedTour = new Tour();
//        updatedTour.setName("New Name");
//
//        // Create some mock Stop objects
//        Stop stop1 = new Stop();
//        Stop stop2 = new Stop();
//        List<Stop> stops = Arrays.asList(stop1, stop2);
//
//        // Create a mock MultipartFile object
//        MockMultipartFile imageFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", new byte[0]);
//
//        // Mock the behavior of the repository
//        when(tourRepository.findById(anyInt())).thenReturn(Optional.of(existingTour));
//        when(tourRepository.save(any(Tour.class))).thenReturn(updatedTour);
//
//        // Call the service method
//        Tour result = tourService.updateTour(updatedTour.getId(), updatedTour, imageFile, stops);
//
//        // Assertions and verifications
//        assertNotNull(result);
//        assertEquals("New Name", result.getName());
//        verify(tourRepository, times(1)).save(any(Tour.class));
//    }

    @Test
    void tourNotFoundTest() {
        when(tourRepository.findById(1)).thenReturn(Optional.empty());

        Tour result = tourService.getTourById(1);

        assertNull(result);
        verify(tourRepository, times(1)).findById(1);
    }
}

