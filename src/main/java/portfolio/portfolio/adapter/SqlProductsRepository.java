package portfolio.portfolio.adapter;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.portfolio.model.Product;
import portfolio.portfolio.repository.ProductsRepository;

import java.util.List;
import java.util.Optional;

public interface SqlProductsRepository extends ProductsRepository, JpaRepository<Product,Integer> {

    @Override
    List<Product> findAll(Sort sort);

    @Override
    List<Product> findAll();

    @Override
    Product save(Product entity);

    void deleteById(Integer id);

    @Override
    Optional<Product> findById(Integer id);

    @Override
    Optional<Product> findByName(String name);
}



