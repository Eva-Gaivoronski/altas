package liftoff.atlas.getcultured.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ForgotPasswordFormDTO {

    @NotNull
    @NotBlank(message = "No email provided; please provide the email address you registered your account with.")
    @Email(message = "Invalid email format; please provide a valid email address.")
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
