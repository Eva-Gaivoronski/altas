package liftoff.atlas.getcultured.models.data;


import liftoff.atlas.getcultured.models.TourCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourCategoryRepository extends CrudRepository<TourCategory, Integer> {

}
