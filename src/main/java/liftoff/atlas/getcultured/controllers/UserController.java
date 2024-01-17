package liftoff.atlas.getcultured.controllers;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import liftoff.atlas.getcultured.models.*;
import liftoff.atlas.getcultured.models.data.SecureTokenRepository;
import liftoff.atlas.getcultured.models.data.UserGroupRepository;
import liftoff.atlas.getcultured.models.data.UserProfileDetailsRepository;
import liftoff.atlas.getcultured.models.data.UserRepository;
import liftoff.atlas.getcultured.models.dto.ForgotPasswordFormDTO;
import liftoff.atlas.getcultured.models.dto.LoginFormDTO;
import liftoff.atlas.getcultured.models.dto.SignUpFormDTO;
import liftoff.atlas.getcultured.models.dto.UserProfileDetailsEditFormDTO;
import liftoff.atlas.getcultured.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
    UserProfileDetailsRepository userProfileDetailsRepository;

    @Autowired
    AuthenticationController authenticationController;

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

        UserGroup registeredUserGroup = userGroupRepository.findByName("registered");

        User newUser = new User(signUpFormDTO.getUsername(), signUpFormDTO.getEmailAddress(), signUpFormDTO.getPassword(), registeredUserGroup); //registeredUserGroup added to all new Users.
        userRepository.save(newUser);

        SecureToken newUserVerificationToken = new SecureToken(newUser);
        newUserVerificationToken.setTypeVerify();
        secureTokenRepository.save(newUserVerificationToken);

        emailService.sendUserVerificationEmailHTML(signUpFormDTO.getEmailAddress(), newUserVerificationToken.getTokenValue());

        AuthenticationController.setUserInSession(request.getSession(), newUser);

        return "redirect:/user/profile/edit";
    }

    @GetMapping("verifyEmail/{tokenValue}")
    @ResponseBody
    public String verifyNewUserByVerificationTokenId(@PathVariable String tokenValue) {
        SecureToken validToken = secureTokenRepository.findByTokenValue(tokenValue);

        // If a token was found and is of type verify
        if (validToken != null && validToken.isTypeVerify()) {

            // If token is active, verify user
            if (validToken.isActive()) {
                User userToVerify = validToken.getUser();
                userToVerify.addUserGroupToUser(userGroupRepository.findByName("verified"));
                userRepository.save(userToVerify);

                // Invalidate token after use & update in token repo
                validToken.deactivateToken();
                secureTokenRepository.save(validToken);

                return "Success! Your account has been validated! <a href=\"/\">Return to Homepage</a>";
            }

            // Token exists in the repo, but is no longer active
            return "That token is no longer active. <a href=\"/\">Return to Homepage</a>";
        }

        // There is no valid token found in the repo
        return "Something went wrong. <a href=\"/\">Return to Homepage</a>";
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

        User user = userRepository.findByEmailAddress(loginFormDTO.getEmailAddress());

        if (user == null) {
            errors.rejectValue("emailAddress","emailAddress.notfound", "Unable to locate an account registered to that email address; please try again.");
            return "user/login";
        }

        String providedPassword = loginFormDTO.getPassword();

        if (!user.isMatchingPassword(providedPassword)) {
            errors.rejectValue("password", "password.mismatch", "Incorrect password; please try again");
            return "user/login";
        }

        AuthenticationController.setUserInSession(request.getSession(), user);

        return "redirect:../";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/user/login";
    }

    @GetMapping("forgot-password")
    public String displayForgotPasswordForm(Model model) {
        model.addAttribute(new ForgotPasswordFormDTO());
        return "user/forgot-password";
    }

    @PostMapping("forgot-password")
    public String processForgotPasswordForm(@ModelAttribute @Valid ForgotPasswordFormDTO forgotPasswordFormDTO, Errors errors, Model model) {
        if(errors.hasErrors()) {
            return "user/forgot-password";
        }

        User user = userRepository.findByEmailAddress(forgotPasswordFormDTO.getEmailAddress());

        // If no user was found, communicate issue in error and return to form
        if (user == null) {
            errors.rejectValue("emailAddress", "email.notfound", "No user account was found associated with that email address.");
            return "user/forgot-password";
        }

        // Create new password reset token, relate it with the user,
        SecureToken newPasswordResetToken = new SecureToken(user);
        newPasswordResetToken.setTypePasswordReset();
        secureTokenRepository.save(newPasswordResetToken);

        emailService.sendPasswordResetEmailHTML(forgotPasswordFormDTO.getEmailAddress(), newPasswordResetToken.getTokenValue());

        // TODO: Update to communicate the email was sent successfully and pass into login template
        return "redirect:/user/login";
    }

    @GetMapping("password-reset/{tokenValue}")
    @ResponseBody
    public String resetPasswordByValidResetToken(@PathVariable String tokenValue, HttpServletRequest request) {
        HttpSession session = request.getSession();
        SecureToken validToken = secureTokenRepository.findByTokenValue(tokenValue);

        // If a token was found and is of type password reset
        if (validToken != null && validToken.isTypePasswordReset()) {

            // If token is active, verify user
            if (validToken.isActive()) {

                // Retrieve user related to token; pass user as session attribute to be referenced on reset form
                User userToResetPasswordFor = validToken.getUser();
                session.setAttribute("userToResetPasswordFor", userToResetPasswordFor);

                // Invalidate token after use & update in token repo
                validToken.deactivateToken();
                secureTokenRepository.save(validToken);

                return "Success! Now send User to password reset form page. <a href=\"/\">Return to Homepage</a>";
            }

            // Token exists in the repo, but is no longer active
            return "That token is no longer active. <a href=\"/\">Return to Homepage</a>";
        }

        // There is no valid token found in the repo
        return "Something went wrong. That token does not exist. <a href=\"/\">Return to Homepage</a>";
    }


    @GetMapping("profile/edit")
    public String displayProfileEditPage(Model model, HttpServletRequest request)  {

        // Retrieves and stores the currently authenticated user by pulling the associated user from the current session
        HttpSession session = request.getSession();
        User currentUser = authenticationController.getUserFromSession(session);

        // Retrieves and stores the UserGroup memberships owned by the User
        Set<UserGroup> currentUserAllGroups = currentUser.getUserGroups();
        String allGroupMemberships = currentUserAllGroups.toString();

        // If the 'verified' user group is not found on the currently authenticated user, pass boolean flag to model attributes
        if(!allGroupMemberships.contains("verified")) {
            boolean userNeedsToVerifyEmail = true;
            model.addAttribute("userNeedsToVerifyEmail", userNeedsToVerifyEmail);
        }

        // Store data in session so it can be retrieved in @PostMapping's error conditional before returning to this @GetMapping
        session.setAttribute("userId", currentUser.getUserId());
        session.setAttribute("username", currentUser.getUsername());
        session.setAttribute("allGroupMemberships", allGroupMemberships);

        // Retrieves the existing UserProfileDetails related to the current User
        UserProfileDetails currentUserProfileDetails = currentUser.getUserProfileDetails();

        // Creates a new DTO that will be populated by existing values for the current user's profile
        UserProfileDetailsEditFormDTO currentUserProfileDetailsDTO = new UserProfileDetailsEditFormDTO();

        // TODO: Perhaps move this functionality into a method on UserProfileDetailsDTO class
        // Accesses the data stored in the user's existing profile and stores it in the DTO to be passed to the view
        currentUserProfileDetailsDTO.setFirstName(currentUserProfileDetails.getFirstName());
        currentUserProfileDetailsDTO.setLastName(currentUserProfileDetails.getLastName());
        currentUserProfileDetailsDTO.setLocation(currentUserProfileDetails.getLocation());
        currentUserProfileDetailsDTO.setAboutMe(currentUserProfileDetails.getAboutMe());

        // Pass the DTO and session attributes to the view
        model.addAttribute(currentUserProfileDetailsDTO);
        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("allGroupMemberships", session.getAttribute("allGroupMemberships"));

        return "user/edit-profile";
    }

    @PostMapping("profile/edit")
    public String processEditsToUserProfile(@ModelAttribute @Valid UserProfileDetailsEditFormDTO userProfileDetailsEditFormDTO, Errors errors, HttpServletRequest request, Model model) {

        // Retrieves the currently authenticated user by pulling the associated user from the current session and then accesses and stores the associated profile to be accessed within the method
        HttpSession session = request.getSession();
        UserProfileDetails currentUserProfileDetails = authenticationController.getUserFromSession(session).getUserProfileDetails();

        // If any validation errors are present, then return back to @GetMapping and pass User information to the view
        if (errors.hasErrors()) {
            model.addAttribute("userId", session.getAttribute("userId"));
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("allGroupMemberships", session.getAttribute("allGroupMemberships"));
            return "user/edit-profile";
        }

        // Updates the stored user profile by setting values accessed from the DTO
        currentUserProfileDetails.setFirstName(userProfileDetailsEditFormDTO.getFirstName());
        currentUserProfileDetails.setLastName(userProfileDetailsEditFormDTO.getLastName());
        currentUserProfileDetails.setLocation(userProfileDetailsEditFormDTO.getLocation());
        currentUserProfileDetails.setAboutMe(userProfileDetailsEditFormDTO.getAboutMe());

        // Save UserProfileDetails variable back into the DB to persist the changes
        userProfileDetailsRepository.save(currentUserProfileDetails);
        return "redirect:/user/profile/view";
    }

    @GetMapping("profile/view")
    public String displayUserProfilePage(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User currentUser = authenticationController.getUserFromSession(session);
        UserProfileDetails currentUserProfileDetails = currentUser.getUserProfileDetails();

        // Retrieves and stores the UserGroup memberships owned by the User
        Set<UserGroup> currentUserAllGroups = currentUser.getUserGroups();
        String allGroupMemberships = currentUserAllGroups.toString();

        model.addAttribute("userId", currentUser.getUserId());
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("allGroupMemberships", allGroupMemberships);
        model.addAttribute("firstName",currentUserProfileDetails.getFirstName());
        model.addAttribute("lastName",currentUserProfileDetails.getLastName());
        model.addAttribute("location",currentUserProfileDetails.getLocation());
        model.addAttribute("aboutMe",currentUserProfileDetails.getAboutMe());

        return "user/view-profile";
    }

    @GetMapping("profile/view/{userId}")
    public String displayPublicUserProfile(Model model)  {
        return "user/public-profile";
    }
}