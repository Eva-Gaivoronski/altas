package liftoff.atlas.getcultured.models.data;

import liftoff.atlas.getcultured.models.AdminPermission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPermissionRepository extends CrudRepository<AdminPermission, Integer> {

}
