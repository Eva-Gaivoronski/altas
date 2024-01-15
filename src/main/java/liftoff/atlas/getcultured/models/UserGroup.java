package liftoff.atlas.getcultured.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class UserGroup extends AbstractEntity {

    @ManyToMany(mappedBy = "userGroups", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    public UserGroup() {}

    public UserGroup(String groupName) {
        super();
        this.setName(groupName);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}