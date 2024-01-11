package liftoff.atlas.getcultured.controllers;

import jakarta.validation.Valid;
import liftoff.atlas.getcultured.models.Tag;
import liftoff.atlas.getcultured.models.Tour;
import liftoff.atlas.getcultured.models.data.CityRepository;
import liftoff.atlas.getcultured.models.data.TagRepository;
import liftoff.atlas.getcultured.models.data.TourCategoryRepository;
import liftoff.atlas.getcultured.models.data.TourRepository;
import liftoff.atlas.getcultured.models.dto.TourTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TourCategoryRepository tourCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("")
    public String displayAllTours(Model model) {
        model.addAttribute("tours", tourRepository.findAll());
        return "tours/index";
    }

    // Corresponds to http://localhost:8080/tours/add
    @GetMapping("/add")
    public String displayAddTourForm(Model model){
        model.addAttribute("tour", new Tour());
        model.addAttribute("city", cityRepository.findAll());
        model.addAttribute("tourCategory", tourCategoryRepository.findAll());
        model.addAttribute("tags", tagRepository.findAll());
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

    // This will allow you to add a tag to a specific tour
    //responds to /tours/add-tag?tourId=tourId
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer tourId, Model model) {
        Optional<Tour> result = tourRepository.findById(tourId);
        Tour tour = result.get();
        model.addAttribute("title", "Add Tag to: " + tour.getName());
        model.addAttribute("tags", tagRepository.findAll());
        TourTagDTO tourTag = new TourTagDTO();
        tourTag.setTours(tour);
        model.addAttribute("tourTag", new TourTagDTO());
        return "tours/add-tag.html";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid TourTagDTO tourTag,
                                    Errors errors,
                                    Model model){
        if (!errors.hasErrors()) {
            Tour tour = tourTag.getTour();
            Tag tag = tourTag.getTag();
            if (!tour.getTags().contains(tag)) {
                tour.addTag(tag);
                tourRepository.save(tour);
            }
            return "redirect:/tours";
        }
        return "redirect:add-tag";
    }

}
