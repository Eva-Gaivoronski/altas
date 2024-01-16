package liftoff.atlas.getcultured.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import liftoff.atlas.getcultured.models.User;

public class UserProfileDetailsEditFormDTO {

    @Size(min=2,max=24,message="First name is restricted between 2-24 characters; please try again.")
    private String firstName;

    @Size(min=2,max=24,message="Last name is restricted between 2-24 characters; please try again.")
    private String lastName;

    @Size(min=3, max=50,message="Location is restricted between 3-50 characters; please try again.")
    private String location;

    @Size(min=3, max=500,message="Your About Me section is restricted between 3-500 characters; please try again.")
    private String aboutMe;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

}
