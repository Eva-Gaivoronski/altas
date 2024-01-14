package liftoff.atlas.getcultured.models;

import jakarta.persistence.*;

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

    private String aboutMe;

    @OneToMany(mappedBy = "authorProfile", cascade = CascadeType.ALL)
    private List<Tour> toursAuthored;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private List<UserReview> tourFeedback;


    public List<Tour> getToursAuthored() {
        return toursAuthored;
    }

    public void setToursAuthored(List<Tour> toursAuthored) {
        this.toursAuthored = toursAuthored;
    }

}
