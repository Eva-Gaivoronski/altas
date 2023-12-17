package liftoff.atlas.getcultured.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String renderHomePage () {

        return "index";
    }

}
