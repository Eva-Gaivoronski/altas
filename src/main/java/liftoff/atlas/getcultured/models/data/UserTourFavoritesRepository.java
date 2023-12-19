package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.UserTourFavorites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTourFavoritesRepository extends CrudRepository<UserTourFavorites, Integer> {

}
