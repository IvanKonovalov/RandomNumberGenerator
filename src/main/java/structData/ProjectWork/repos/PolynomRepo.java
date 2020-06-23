package structData.ProjectWork.repos;

import org.springframework.data.repository.CrudRepository;
import structData.ProjectWork.randomGenerator.Polynom;

import java.util.List;

public interface PolynomRepo extends CrudRepository<Polynom, Integer> {
    List<Polynom> findByDegree(Integer degree);
}
