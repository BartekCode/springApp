package portfolio.portfolio.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.portfolio.model.Order;
import portfolio.portfolio.model.OrderDetail;
import portfolio.portfolio.model.Product;
import portfolio.portfolio.model.User;
import portfolio.portfolio.model.projection.CartInfo;
import portfolio.portfolio.model.projection.CartLineInfo;
import portfolio.portfolio.model.projection.CustomerInfo;
import portfolio.portfolio.model.projection.OrderInfo;
import portfolio.portfolio.repository.OrderRepository;
import portfolio.portfolio.repository.ProductsRepository;
import portfolio.portfolio.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductsRepository productsRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productsRepository = productsRepository;
    }

    public void createOrderForUser(int userId, Order orderShop){
        Optional<User> byId = userRepository.findById(userId);
        orderShop.setUser(byId.orElseThrow());
        orderShop.setOrderDate(LocalDateTime.now());
        orderRepository.save(orderShop);
    }

        public void saveOrder( int userId, CartInfo cartInfo){
        Order order = new Order();
        order.setId(UUID.randomUUID().variant());
        order.setOrderDate(LocalDateTime.now());
        order.setUser(userRepository.findById(userId).orElseThrow());
        order.setAmount(cartInfo.getAmountTotal());

    CustomerInfo customerInfo = cartInfo.getCustomerInfo();

            List<CartLineInfo> cartLines = cartInfo.getCartLines();
            for (CartLineInfo line: cartLines) {
                OrderDetail detail = new OrderDetail();
                detail.setId(UUID.randomUUID().variant());
                detail.setOrder(order);
                detail.setAmount(line.getAmount());
                detail.setPrice(line.getProductInfo().getPrice());

                int id = line.getProductInfo().getId();
                Optional<Product> product = productsRepository.findById(id);
                detail.setProduct(product.orElseThrow());
            }
        }
        public OrderInfo getOrderInfo(Integer id){
            Optional<Order> byId = orderRepository.findById(id);
            String firstName = byId.orElseThrow().getUser().getFirstName();
            String lastName = byId.orElseThrow().getUser().getLastName();
            String email = byId.orElseThrow().getUser().getEmail();

            return new OrderInfo(byId.orElseThrow().getId(),byId.orElseThrow().getOrderDate(),byId.orElseThrow().getAmount(), firstName, lastName,email);
        }


}
