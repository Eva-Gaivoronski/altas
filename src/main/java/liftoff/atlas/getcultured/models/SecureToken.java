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

    public SecureToken(User user) {
        this.user = user;
        this.tokenValue = generateTokenValue();
        this.isActive = false;
    }

    // TODO: Add expiration functionality for tokens; disabled for now
//    public SecureToken(User user, LocalDateTime expirationTimestamp) {
//        this.user = user;
//        this.tokenValue = generateTokenValue();
//        this.isActive = false;
//    }

    public static String generateTokenValue() {
        return UUID.randomUUID().toString();
    }

    public void setTypeVerify() {
        this.setTokenType("email_verify");
        this.setActive(true);
    }

    public void setTypePasswordReset() {
        this.setTokenType("password_reset");
        this.setActive(true);
    }

    public boolean isTypeVerify() {
        return this.tokenType.equals("email_verify");
    }

    public  boolean isTypePasswordReset() {
        return this.tokenType.equals("password_reset");
    }

    public void deactivateToken() {
        this.isActive = false;
    }

    public int getUserId() {
        return this.user.getUserId();
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
