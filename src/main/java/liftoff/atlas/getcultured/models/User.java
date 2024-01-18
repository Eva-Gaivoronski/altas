package liftoff.atlas.getcultured.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_usergroup",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="usergroup_id")
    )
    private Set<UserGroup> userGroups = new HashSet<>();

    public User() {}

    // User constructor w/ no UserGroup param
    public User(String username, String emailAddress, String password) {
        this.username = username.trim().toLowerCase();
        this.emailAddress = emailAddress;
        this.passwordHash = encoder.encode(password);
        this.userProfileDetails = new UserProfileDetails();
    }

    // User constructor that takes a userGroup as an argument
    public User(String username, String emailAddress, String password, UserGroup userGroup) {
        this.username = username.trim().toLowerCase();
        this.emailAddress = emailAddress.trim().toLowerCase();
        this.passwordHash = encoder.encode(password);
        this.userProfileDetails = new UserProfileDetails();
        this.addUserGroupToUser(userGroup);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, passwordHash);
    }

    public void updatePassword(String newPassword) {
        this.passwordHash = encoder.encode(newPassword);
    }

    public void addUserGroupToUser(UserGroup userGroup) {
        this.getUserGroups().add(userGroup);
        userGroup.getUsers().add(this);
    }

    public void removeUserGroupFromUser(UserGroup userGroup) {
        this.getUserGroups().remove(userGroup);
        userGroup.getUsers().remove(this);
    }

    public int getUserProfileDetailsID() {
        return this.userProfileDetails.getProfileId();
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim().toLowerCase();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress.trim().toLowerCase();
    }

    public UserProfileDetails getUserProfileDetails() {
        return userProfileDetails;
    }

    public void setUserProfileDetails(UserProfileDetails userProfileDetails) {
        this.userProfileDetails = userProfileDetails;
    }

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUserId() == user.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}
