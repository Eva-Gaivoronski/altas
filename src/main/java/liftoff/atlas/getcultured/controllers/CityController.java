package liftoff.atlas.getcultured.controllers;

import jakarta.validation.Valid;
import liftoff.atlas.getcultured.models.City;
import liftoff.atlas.getcultured.models.data.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public String displayCityPage(Model model) {
        List<City> cities = (List<City>) cityRepository.findAll();
        model.addAttribute("city", cities);
        return "/cities/index";
    }

    @GetMapping("/add")
    public String displayAddCityForm(Model model){
        model.addAttribute("city", new City());
        return "cities/add";
    }

    @PostMapping("/add")
    public String processAddCityForm(@ModelAttribute @Valid City city, Errors errors) {
        if (errors.hasErrors()) {
            return "cities/add";
        } else {
            cityRepository.save(city);
            return "redirect:/city";
        }
    }

}
