package liftoff.atlas.getcultured.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class AdminPermission extends AbstractEntity {

    @ManyToMany(mappedBy = "adminPermissions")
    private Set<UserGroup> userGroups;

    public AdminPermission() {
        super();
    }

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }
}
