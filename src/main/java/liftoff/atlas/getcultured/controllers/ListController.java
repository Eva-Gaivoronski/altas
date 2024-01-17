package liftoff.atlas.getcultured.controllers;

import liftoff.atlas.getcultured.models.Tour;
import liftoff.atlas.getcultured.models.TourData;
import liftoff.atlas.getcultured.models.data.CityRepository;
import liftoff.atlas.getcultured.models.data.TourCategoryRepository;
import liftoff.atlas.getcultured.models.data.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TourCategoryRepository tourCategoryRepository;

   static HashMap<String, String> columnChoices = new HashMap<>();

   public ListController() {
       columnChoices.put("all", "All");
       columnChoices.put("city", "City");
       columnChoices.put("tourCategory","Tour Category");
   }

   @RequestMapping("")
    public String list(Model model) {
       model.addAttribute("city", cityRepository);
       model.addAttribute("tourCategory", tourCategoryRepository);
       return "list";
   }

   @RequestMapping(value= "tours")
    public String listToursByColumnAndValue(Model model, @RequestParam String column , @RequestParam String value) {
       Iterable<Tour> tours;
       if (column.toLowerCase().equals("all")) {
           tours = tourRepository.findAll();
           model.addAttribute("title", "All Tours");
       } else {
           tours = TourData.findByColumnAndValue(column, value, tourRepository.findAll());
           model.addAttribute("title", "Tours with" + columnChoices.get(column) + ": " + value);
       }
       model.addAttribute("tours", tours);
       return "list-tours";
   }

}
