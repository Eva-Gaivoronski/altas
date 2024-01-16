package liftoff.atlas.getcultured.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
public class UserProfileDetails {

    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private int profileId;

    private String firstName;

    private String lastName;

    private String location;

    @Column(length = 500)
    private String aboutMe;

    @OneToMany(mappedBy = "authorProfile", cascade = CascadeType.ALL)
    private List<Tour> toursAuthored;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private List<UserReview> tourFeedback;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

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

    public List<Tour> getToursAuthored() {
        return toursAuthored;
    }

    public void setToursAuthored(List<Tour> toursAuthored) {
        this.toursAuthored = toursAuthored;
    }

}
