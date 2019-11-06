package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.User;

public interface JpaUserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
