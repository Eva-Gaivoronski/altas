package liftoff.atlas.getcultured.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class SecureToken {

    @Id
    @GeneratedValue
    private int tokenId;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user; //The id of the user the token is assigned to

    private String tokenValue;

    private LocalDateTime tokenExpiration;

    private String tokenType;

    private boolean isActive;

    public SecureToken() {}

    public SecureToken(User user, LocalDateTime expirationTimestamp, String type) {
        this.user = user;
        this.tokenValue = generateToken();
        this.tokenType = type;
        this.isActive = true;
    }

    // Method for testing generating in email, absent of user for test
    public SecureToken(User user, String type) {
        this.user = user;
        this.tokenValue = generateToken();
        this.tokenType = type;
        this.isActive = true;
    }

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public void deactivateToken() {
        this.isActive = false;
    }




    public int getTokenId() {
        return tokenId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user= user;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public LocalDateTime getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(LocalDateTime tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getUserId() {
        return this.user.getUserId();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
