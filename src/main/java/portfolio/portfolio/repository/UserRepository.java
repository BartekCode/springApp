package portfolio.portfolio.repository;

import portfolio.portfolio.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository {

    List<User> findAll();
    User findByEmail(String email);
    Set<User> findAllByRole(String role);
    User save (User user);
   void deleteById(Integer id);
    Optional <User> findById(Integer id);
}
