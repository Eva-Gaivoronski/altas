package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends CrudRepository<Tour, Integer> {

}
