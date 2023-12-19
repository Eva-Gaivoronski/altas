package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {

}
