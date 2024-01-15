package liftoff.atlas.getcultured.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import liftoff.atlas.getcultured.models.EmailService;
import liftoff.atlas.getcultured.models.SecureToken;
import liftoff.atlas.getcultured.models.UserGroup;
import liftoff.atlas.getcultured.models.data.SecureTokenRepository;
import liftoff.atlas.getcultured.models.data.UserGroupRepository;
import liftoff.atlas.getcultured.models.data.UserRepository;
import liftoff.atlas.getcultured.models.User;
import liftoff.atlas.getcultured.models.dto.LoginFormDTO;
import liftoff.atlas.getcultured.models.dto.SignUpFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecureTokenRepository secureTokenRepository;

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    EmailService emailService;


    // TODO: Add config to handle all requests with "/" in path - https://stackoverflow.com/a/2515533
    @GetMapping(path= {"","/"}) // Handles requests for "user" and "user/",
    public String handleRootRequest() {
        /* TODO: Add logic to differentiate accessing user's authorization and route traffic in accordance.
        *   Examples: Not logged in? Serve login page. Logged in? Go to personal user profile page. */

        return "redirect:/user/login";
    }

    @GetMapping("sign-up")
    public String displayUserCreationForm(Model model)  {
        model.addAttribute(new SignUpFormDTO());
        return "user/create-user";
    }

    @PostMapping("sign-up")
    public String processRegistrationForm(@ModelAttribute @Valid SignUpFormDTO signUpFormDTO, Errors errors, HttpServletRequest request, Model model) {

        if (errors.hasErrors()) {
            return "user/create-user";
        }

        User existingUsername = userRepository.findByUsername(signUpFormDTO.getUsername());
        User existingEmailAddress = userRepository.findByEmailAddress(signUpFormDTO.getEmailAddress());
//        UserGroup

        if (existingEmailAddress != null) {
            errors.rejectValue("emailAddress", "emailAddress.alreadyregistered", "An account using that email address has already been registered");
            return "user/create-user";
        }

        if (existingUsername != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            return "user/create-user";
        }

        String password = signUpFormDTO.getPassword();
        String verifyPassword = signUpFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("verifyPassword", "passwords.mismatch", "Passwords do not match; please enter them again");
            return "user/create-user";
        }

        UserGroup userGroup = userGroupRepository.findByName("registered");

        User newUser = new User(signUpFormDTO.getUsername(), signUpFormDTO.getEmailAddress(), signUpFormDTO.getPassword(), userGroup);
        userRepository.save(newUser);
        AuthenticationController.setUserInSession(request.getSession(), newUser);

        // TODO: Take to personal profile page to add profile data
        return "redirect:";
    }

    @GetMapping("login")
    public String displayUserLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        return "user/login";
    }

    @PostMapping("login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors, HttpServletRequest request, Model model) {

        if (errors.hasErrors()) {
            return "user/login";
        }

        User theUser = userRepository.findByEmailAddress(loginFormDTO.getEmailAddress());

        if (theUser == null) {
            errors.rejectValue("emailAddress","emailAddress.notfound", "Unable to locate an account registered to that email address; please try again.");
            return "user/login";
        }

        String providedPassword = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(providedPassword)) {
            errors.rejectValue("password", "password.mismatch", "Incorrect password; please try again");
            return "user/login";
        }

        AuthenticationController.setUserInSession(request.getSession(), theUser);

        return "redirect:";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/user/login";
    }

    @GetMapping("profile")
    public String displayPersonalUserProfile(Model model)  {
        return "user/user-profile";
    }

    @GetMapping("profile/view/{userId}")
    public String displayPublicUserProfile(Model model)  {
        return "user/public-profile";
    }

    @GetMapping("sendEmail")
    @ResponseBody
    public String sendEmail() {
        String theUserEmailAddress = System.getenv("TEST_USER_EMAIL"); //Retrieves test user's email from the env variable
        User theUser = userRepository.findByEmailAddress(theUserEmailAddress);
        SecureToken emailVerificationToken = new SecureToken(theUser,"Email verification token");

        secureTokenRepository.save(emailVerificationToken);

        emailService.sendVerificationEmail(
                theUserEmailAddress,
                "Test send from SpringBoot",
                "This is a test email being sent from my local app. The token value is: " + emailVerificationToken.getTokenValue()
        );

        return "Email sent!";
    }
}