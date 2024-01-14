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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_detail_id", referencedColumnName = "profile_id")
    private UserProfileDetails userProfileDetails;

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;

    public User() {}

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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public UserProfileDetails getUserProfileDetails() {
        return userProfileDetails;
    }

    public void setUserProfileDetails(UserProfileDetails userProfileDetails) {
        this.userProfileDetails = userProfileDetails;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
