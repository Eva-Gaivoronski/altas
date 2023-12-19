package liftoff.atlas.getcultured.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;

@Entity
public class UserGroup extends AbstractEntity {

    @OneToMany(mappedBy = "userGroup", cascade = CascadeType.ALL)
    private List<User> users;

    @ManyToMany
    private Set<AdminPermission> adminPermissions;

    public UserGroup() {
        super();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Set<AdminPermission> getAdminPermissions() {
        return adminPermissions;
    }

    public void setAdminPermissions(Set<AdminPermission> adminPermissions) {
        this.adminPermissions = adminPermissions;
    }
}