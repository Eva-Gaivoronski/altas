package liftoff.atlas.getcultured.models.data;


import liftoff.atlas.getcultured.models.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Integer> {

}
