package liftoff.atlas.getcultured.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginFormDTO {
    // TODO: Set up validation annotations

    @NotNull
    @NotBlank (message = "No email provided; please your email to access your account.")
    @Email (message = "Invalid email format; please enter a valid email to access your account.")
    private String emailAddress;

    @NotNull
    @NotBlank (message = "No password provided; please enter your password to access your account.")
    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
