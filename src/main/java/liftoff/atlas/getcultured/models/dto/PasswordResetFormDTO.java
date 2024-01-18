package liftoff.atlas.getcultured.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PasswordResetFormDTO {

    @NotNull
    @NotBlank(message = "Password cannot be left blank; please enter a valid password to register an account.")
    @Size(min=8, max=32, message="Invalid password length; your password must be between 8 - 32 characters in length.")
    private String newPassword;

    @NotNull
    @NotBlank (message = "Verify Password cannot be left blank; please enter a matching password to register an account.")
    private String verifyNewPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getVerifyNewPassword() {
        return verifyNewPassword;
    }

    public void setVerifyNewPassword(String verifyNewPassword) {
        this.verifyNewPassword = verifyNewPassword;
    }


}
