package portfolio.portfolio.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.portfolio.model.Order;
import portfolio.portfolio.repository.OrderRepository;

import java.util.Optional;

@Repository
public interface SQLOrderRepository extends JpaRepository<Order, Integer>, OrderRepository {

    Order save(Order cart);
    Optional<Order> findById(Integer id);





}
