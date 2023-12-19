package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.UserImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends CrudRepository<UserImage, Integer> {

}
