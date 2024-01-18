package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopRepository extends CrudRepository<Stop, Integer> {
//    List<Stop> findByTour(Tour tour);

}
