package liftoff.atlas.getcultured.controllers;


import liftoff.atlas.getcultured.models.data.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private TourRepository tourRepository;

//    @RequestMapping("")
//    public String search(Model model) {
//
//    }

}
