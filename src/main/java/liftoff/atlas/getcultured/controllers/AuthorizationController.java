package liftoff.atlas.getcultured.controllers;

import liftoff.atlas.getcultured.models.data.UserGroupRepository;
import liftoff.atlas.getcultured.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthorizationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGroupRepository userGroupRepository;

    @GetMapping("/unauthorized")
    @ResponseBody
    public String renderUnauthorizedAccessPage() {
        return "You do not have authorization to access that page.";
    }

    @GetMapping(path={"admin","admin/"})
    @ResponseBody
    public String renderAdminPanel() {
        return "This is where the admin panel will go. I just don't have it yet.";
    }
}
