package liftoff.atlas.getcultured.controllers;

import jakarta.validation.Valid;
import liftoff.atlas.getcultured.models.City;
import liftoff.atlas.getcultured.models.data.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

   @GetMapping("")
    public String displayAllCities(Model model) {
        model.addAttribute("city", cityRepository.findAll());
        return "city/index";
    }

    @GetMapping("/add")
    public String displayAddCityForm(Model model){
        model.addAttribute("city", new City());
        return "city/add";
    }

    @PostMapping("/add")
    public String processAddCityForm(@ModelAttribute @Valid City city, Errors errors) {
        if (errors.hasErrors()) {
            return "city/add";
        } else {
            cityRepository.save(city);
            return "redirect:/city";
        }
    }

    @GetMapping("/delete")
    public String displayDeleteCityForm(Model model) {
       model.addAttribute("city", cityRepository.findAll());
       return "city/delete";
    }

    @PostMapping("/delete")
    public String processDeleteCityForm(@RequestParam(required = false) int[] cityId) {
      for (int id: cityId) {
          cityRepository.deleteById(id);
      }
      return "redirect:/city";
    }

    @GetMapping("view/{cityId}")
    public String displayViewCity(Model model, @PathVariable int cityId) {
        Optional optCity = cityRepository.findById(cityId);
        if (optCity.isPresent()) {
            City city = (City) optCity.get();
            model.addAttribute("city", city);
            return "city/view";
        } else {
            return "redirect:../";
        }
    }

}
