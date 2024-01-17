package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.SecureToken;
import liftoff.atlas.getcultured.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureTokenRepository extends CrudRepository<SecureToken, Integer> {

    SecureToken findByTokenValue(String tokenValue);
    SecureToken findByUser(User user);
}
