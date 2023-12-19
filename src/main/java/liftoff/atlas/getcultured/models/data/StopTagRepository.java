package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.StopTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopTagRepository extends CrudRepository<StopTag, Integer> {

}
