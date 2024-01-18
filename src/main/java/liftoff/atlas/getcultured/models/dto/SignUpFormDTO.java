package liftoff.atlas.getcultured.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignUpFormDTO {

    // TODO: Extend from LoginFormDTO to DRY code

    @NotNull
    @NotBlank (message = "Username field cannot be left blank; please provide a valid username to register an account.")
    private String username;

    @NotNull
    @NotBlank (message = "Email cannot be left blank; please provide an email address to register an account.")
    @Email (message = "Invalid email format; please enter a valid email to register an account.")
    // TODO: Error not thrown if any character is present after @ (even without a .tld to end); add REGEX to further scrutinize value
    private String emailAddress;

    @NotNull
    @NotBlank (message = "Password cannot be left blank; please enter a valid password to register an account.")
    @Size(min=8, max=32, message="Invalid password length; your password must be between 8 - 32 characters in length.")
    private String password;

    @NotNull
    @NotBlank (message = "Verify Password cannot be left blank; please enter a matching password to register an account.")
    private String verifyPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
