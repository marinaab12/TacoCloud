package tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import tacos.model.Taco;

@Repository
public interface JpaTacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
