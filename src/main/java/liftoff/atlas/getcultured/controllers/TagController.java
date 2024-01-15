package liftoff.atlas.getcultured.controllers;

import jakarta.validation.Valid;
import liftoff.atlas.getcultured.models.Tag;
import liftoff.atlas.getcultured.models.data.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayTags(Model model) {
        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/index";
    }

    @GetMapping("add")
    public String displayAddTagForm(Model model) {
        model.addAttribute("title", "Add Tag");
        model.addAttribute(new Tag());
        return "tags/add";
    }

    @PostMapping("add")
    public String processAddTagForm(@ModelAttribute @Valid Tag tag, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Tag");
            model.addAttribute(tag);
            return "tags/add";
        }

        tagRepository.save(tag);
        return "redirect:/tags";

    }

}