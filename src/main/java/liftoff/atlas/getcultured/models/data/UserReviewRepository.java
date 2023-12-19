package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.UserReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewRepository extends CrudRepository<UserReview, Integer> {

}
