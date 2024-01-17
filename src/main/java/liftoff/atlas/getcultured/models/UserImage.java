package liftoff.atlas.getcultured.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class UserImage extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "User cannot be null")
    private User user;

    @Lob
    private byte[] imageData;

    public UserImage() { }

    public UserImage(User user, byte[] imageData) {
        super();
        this.user = user;
        this.imageData = imageData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
