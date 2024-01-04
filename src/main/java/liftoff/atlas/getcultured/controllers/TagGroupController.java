package liftoff.atlas.getcultured.controllers;

import jakarta.validation.Valid;
import liftoff.atlas.getcultured.models.TagGroup;
import liftoff.atlas.getcultured.models.data.TagGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tag-groups")
public class TagGroupController {

    @Autowired
    private TagGroupRepository tagGroupRepository;

    @GetMapping
    public String displayGroupTags(Model model) {
        model.addAttribute("title", "All Group Tags");
        model.addAttribute("tags", tagGroupRepository.findAll());
        return "tagGroup/index";
    }

    @GetMapping("add")
    public String displayAddGroupTagForm(Model model) {
        model.addAttribute("title", "Add Tag Group");
        model.addAttribute(new TagGroup());
        return "tags/add";
    }

    @PostMapping("add")
    public String processAddTagGroupForm(@ModelAttribute @Valid TagGroup tagGroup, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Tag Group");
            model.addAttribute(tagGroup);
            return "tagGroup/add";
        }
        tagGroupRepository.save(tagGroup);
        return "redirect:/tagGroup";
    }
}
