package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.TagGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagGroupRepository extends CrudRepository<TagGroup, Integer> {

}
