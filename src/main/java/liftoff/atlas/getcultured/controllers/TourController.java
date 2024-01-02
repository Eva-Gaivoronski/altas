package liftoff.atlas.getcultured.controllers;

import jakarta.validation.Valid;
import liftoff.atlas.getcultured.models.Tour;
import liftoff.atlas.getcultured.models.data.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourRepository tourRepository;

    @GetMapping("")
    public String displayAllTours(Model model) {
        model.addAttribute("tours", tourRepository.findAll());
        return "tours/index";
    }

    // Corresponds to http://localhost:8080/tours/add
    @GetMapping("/add")
    public String displayAddTourForm(Model model){
        model.addAttribute("tour", new Tour());
        return "tours/add";
    }

    @PostMapping("/add")
    public String processAddTourForm(@ModelAttribute @Valid Tour tour, Errors errors, Model model){
        if (errors.hasErrors()){
            return "tours/add";
        } else {
            tourRepository.save(tour);
            return "redirect:/tours";
        }
    }
}
