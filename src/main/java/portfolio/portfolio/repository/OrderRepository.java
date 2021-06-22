package portfolio.portfolio.repository;

import portfolio.portfolio.model.Order;

import java.util.Optional;

public interface OrderRepository {


    Order save(Order cart);
    Optional<Order> findById(Integer id);
}
