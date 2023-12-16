package liftoff.atlas.getcultured.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class PasswordResetToken extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "User cannot be null")
    private User user;

    private String token;

    private LocalDateTime expiryDateTime;

    // Constructors, getters, setters, and other methods

    public PasswordResetToken(int id, String name, User user, String token, LocalDateTime expiryDateTime) {
        super();
        this.user = user;
        this.token = token;
        this.expiryDateTime = expiryDateTime;
    }

    // Add getters and setters for other fields

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }
}