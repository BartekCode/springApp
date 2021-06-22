package portfolio.portfolio.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import portfolio.portfolio.model.User;
import portfolio.portfolio.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SqlUserRepository extends UserRepository, JpaRepository<User,Integer> {


    Optional<User> findById(Integer id);

    @Override
    List<User> findAll();

    @Override
    User findByEmail(String email);

    @Override
    default Set<User> findAllByRole(String role) {
        return null;
    }
    @Override
    User save(User user);

    @Override
    void deleteById(Integer id);

//    @Transactional
//    @Modifying
//    @Query("UPDATE User a " +
//            "SET a.enabled = TRUE WHERE a.email = ?1")
//    int enableAppUser(String email);

}
