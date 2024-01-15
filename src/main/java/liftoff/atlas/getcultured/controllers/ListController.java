package liftoff.atlas.getcultured.controllers;

import liftoff.atlas.getcultured.models.data.CityRepository;
import liftoff.atlas.getcultured.models.data.TourCategoryRepository;
import liftoff.atlas.getcultured.models.data.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
