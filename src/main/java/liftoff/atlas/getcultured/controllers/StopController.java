package liftoff.atlas.getcultured.controllers;

import jakarta.validation.Valid;
import liftoff.atlas.getcultured.dto.StopForm;
import liftoff.atlas.getcultured.models.Stop;
import liftoff.atlas.getcultured.services.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Context;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/stops")
public class StopController {

    private static final Logger logger = LoggerFactory.getLogger(StopController.class);


    private final StopService stopService; // Replace with your StopService

    @Autowired
    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    // Display form to create a new stop
    @GetMapping("/create")
    public String showCreateStopForm(Model model, @RequestParam(required = false, defaultValue = "stops") String referrer) {
        model.addAttribute("stopForm", new StopForm());
        model.addAttribute("referrer", referrer); // Pass referrer to the model for form submission
        return "stops/create";
    }

    // Process the form for creating a new stop

    @PostMapping("/create")
    public String createStop(@ModelAttribute("stopForm") @Valid StopForm stopForm,
                             BindingResult bindingResult,
                             @RequestParam(value = "image", required = false) MultipartFile imageFile,
                             @RequestParam(value = "referrer", defaultValue = "stops") String referrer,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "stops/create";
        }
        try {
            Stop stop;
            if (imageFile != null && !imageFile.isEmpty()) {
                // Create stop with the provided image
                stop = stopService.createStopFromForm(stopForm, imageFile);
            } else {
                // Set default image path if no image is uploaded
                String defaultImagePath = "defaultLogo/DefaultLogo.jpg";
                stopForm.setImagePath(defaultImagePath);
                // Create stop with the default image
                stop = stopService.createStopFromForm(stopForm, null);
            }

            String redirectUrl = "redirect:/";
            if ("tourCreation".equals(referrer)) {
                redirectAttributes.addFlashAttribute("selectedStopId", stop.getId());
                redirectUrl += "tours/create";
            } else {
                redirectUrl += "stops";
            }
            redirectAttributes.addFlashAttribute("message", "Stop created successfully");
            return redirectUrl;
        } catch (IOException e) {
            logger.error("Error while saving stop and image", e);
            model.addAttribute("errorMessage", "Error processing image upload.");
            return "stops/create";
        }
    }

    // Method to display a specific stop
    @GetMapping("/{stopId}")
    public String viewStop(@PathVariable int stopId, @RequestParam(required = false) Integer tourId, Model model, @RequestParam(required = false) String context) {
        Stop stop = stopService.findById(stopId);
        if (stop != null) {
            model.addAttribute("stop", stop);
            model.addAttribute("tourId", tourId);
            model.addAttribute("context", context); // Add context to the model
            return "stops/view";
        } else {
            // Handle the case where stop is null
            return "redirect:/stops"; // or an error page
        }
    }

    @GetMapping
    public String listStops(Model model, @RequestParam(required = false) String context) {
        List<Stop> stops = stopService.findAll();
        model.addAttribute("stops", stops);

        // Set context to 'create' or 'update' if provided and valid, otherwise set to 'none'
        String validContext = (context != null && (context.equals("create") || context.equals("update"))) ? context : "none";
        model.addAttribute("context", validContext);

        return "stops/stops";
    }

    @GetMapping("/stop/{id}")
    public ModelAndView getStopDetails(@PathVariable("id") int id) {
        // Fetch the stop details based on the provided id.
        Stop stopDetails = stopService.findById(id);

        // Create a ModelAndView object with the stop details.
        ModelAndView modelAndView = new ModelAndView("stop-details");
        modelAndView.addObject("stopDetails", stopDetails);

        return modelAndView;
    }

    // Mapping for deleting a tour
    @GetMapping("/{stopId}/delete")
    public String deleteStop(@PathVariable int stopId, RedirectAttributes redirectAttributes) {
        try {
            stopService.deleteStop(stopId);
            redirectAttributes.addFlashAttribute("successMessage", "Stop deleted successfully");
        } catch (Exception e) {
            logger.error("Error while deleting stop", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting stop.");
        }
        return "redirect:/stops";
    }


    // Other methods for handling stop creation, editing, etc.
}
