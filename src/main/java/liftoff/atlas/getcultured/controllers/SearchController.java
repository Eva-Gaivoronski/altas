package liftoff.atlas.getcultured.controllers;


import liftoff.atlas.getcultured.models.Tour;
import liftoff.atlas.getcultured.models.TourData;
import liftoff.atlas.getcultured.models.data.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static liftoff.atlas.getcultured.controllers.ListController.columnChoices;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private TourRepository tourRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        Iterable<Tour> tours;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            tours = tourRepository.findAll();
        } else {
            tours = TourData.findByColumnAndValue(searchType, searchTerm, tourRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Tours with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("tours", tours);

        return "search";
    }

}
