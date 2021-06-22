package portfolio.portfolio.repository;

import portfolio.portfolio.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {

    List<Product> findAll();
    Product save(Product entity);
    void deleteById(Integer id);
    Optional<Product> findById(Integer id);
    Optional<Product> findByName(String name);
}
