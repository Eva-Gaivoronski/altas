package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.UserProfileDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileDetailsRepository extends CrudRepository<UserProfileDetails, Integer> {
    //TODO: add custom find method to retrive profile by associated User
}
