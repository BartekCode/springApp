package portfolio.portfolio.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.portfolio.model.OrderDetail;
import portfolio.portfolio.repository.OrderDetailRepository;

import java.util.Optional;

public interface SqlOrderDetailRepository extends OrderDetailRepository, JpaRepository <OrderDetail, Integer> {

    OrderDetail save (OrderDetail tosave);
    Optional<OrderDetail> findById(Integer id);

}
