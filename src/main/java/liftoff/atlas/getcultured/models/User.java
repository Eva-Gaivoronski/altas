package liftoff.atlas.getcultured.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class User extends AbstractEntity{


    @Email
    private String userEmail;

    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, message = "First name cannot be longer than 50 characters")
    private String userFirstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 50, message = "Last name cannot be longer than 50 characters")
    private String userLastName;


    @NotBlank(message = "Location cannot be blank")
    @Size(max = 50, message = "Location cannot be longer than 50 characters")
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Tour> toursAuthored;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserReview> tourFeedback;

    public User() {

    }

    public User(String userEmail, String userFirstName, String userLastName, String location) {
        super();
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.location = location;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Tour> getToursAuthored() {
        return toursAuthored;
    }

    public void setToursAuthored(List<Tour> toursAuthored) {
        this.toursAuthored = toursAuthored;
    }
}
