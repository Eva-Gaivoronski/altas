package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, Integer> {

}
