package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    User findByEmailAddress(String emailAddress);

}
