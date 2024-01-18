package liftoff.atlas.getcultured.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int userId;

    @NotNull
    private String username;

    @NotNull
    @Email
    private String emailAddress;

    @NotNull
    private String passwordHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;

    // TODO: Move toursAuthors & tourFeedback to UserProfile

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Tour> toursAuthored;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserReview> tourFeedback;

    public User(String username, String emailAddress, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.passwordHash = encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, passwordHash);
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public List<Tour> getToursAuthored() {
        return toursAuthored;
    }

    public void setToursAuthored(List<Tour> toursAuthored) {
        this.toursAuthored = toursAuthored;
    }
}
