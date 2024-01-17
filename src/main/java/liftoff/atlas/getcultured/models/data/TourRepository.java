package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends CrudRepository<Tour, Integer> {

    // Find Tours by Author:
//    List<Tour> findByAuthor(User author);
//
//    // Find Users by User Group:
//    List<User> findByUserGroup(UserGroup userGroup);
//
//    // Find Tours with a Minimum Rating:
//    List<Tour> findByRatingGreaterThanEqual(double minRating);
//
//    // Find Stops in a Tour:
//    List<Tour> findByStopsCity(City city);
//
//    // Find Tours with a Tag:
//    List<Tour> findByTourTags(Tag tag);
}
