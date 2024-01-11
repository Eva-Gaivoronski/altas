package liftoff.atlas.getcultured.controllers;


import jakarta.validation.Valid;
import liftoff.atlas.getcultured.models.TourCategory;
import liftoff.atlas.getcultured.models.data.TourCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("tourCategories")
public class TourCategoryController {

    @Autowired
    private TourCategoryRepository tourCategoryRepository;

    @GetMapping
    public String displayAllCategories(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", tourCategoryRepository.findAll());
        return "tourCategories/index";
    }

    @GetMapping("add")
    public String displayAddTourCategoryForm(Model model) {
        model.addAttribute("title",  "Create Category");
        model.addAttribute("tourCategory", new TourCategory());
        return "tourCategories/add";
    }

    @PostMapping("add")
    public String processAddTourCategoryForm(@Valid @ModelAttribute TourCategory tourCategory,
                                             Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            model.addAttribute("tourCategory",new TourCategory());
            return "tourCategories/add";
        }
        tourCategoryRepository.save(tourCategory);
        return "redirect:/tourCategories";
    }

    @GetMapping("/delete")
    public String displayDeleteTourCategoryForm (Model model) {
        model.addAttribute("tourCategory", tourCategoryRepository.findAll());
        return "tourCategories/delete";
    }

    @PostMapping("/delete")
    public String processDeleteTourCategoryForm(@RequestParam(required = false) int[] tourCategoryIds) {
        for (int id: tourCategoryIds) {
            tourCategoryRepository.deleteById(id);
        }
        return "redirect:/tourCategories";
    }

}
