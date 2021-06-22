package portfolio.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import portfolio.portfolio.logic.CategoryService;
import portfolio.portfolio.logic.ProductsService;
import portfolio.portfolio.model.Category;
import portfolio.portfolio.model.Product;
import portfolio.portfolio.repository.CategoryRepository;
import portfolio.portfolio.repository.ProductsRepository;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;

@Configuration
public class TestConfiguration {


    @Bean
    @Primary
    @Profile("!integration")
    DataSource e2eTestDataSource(){ //tworzymy testową bazę h2 do testów e2e
        DriverManagerDataSource result = new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY= -1", "sa", "");
        result.setDriverClassName("org.h2.Driver");
        return result;
    }

    @Bean
    @Primary
    @Profile("integration")
    ProductsRepository testRepo() {
        return new ProductsRepository() {

            private Map<Integer, Product> products = new HashMap<>();

            @Override
            public List<Product> findAll() {
                return new ArrayList<>(products.values());
            }

            @Override
            public Product save(final Product entity) {
              int key = products.size() + 1;
                try {
                  var  field = Product.class.getDeclaredField("id");
                    field.setAccessible(true);
                    field.set(entity, key);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
              products.put(key, entity);
              return products.get(key);
             }


            @Override
            public void deleteById(Integer id) {
            }
            @Override
            public Optional<Product> findById(Integer id) {
                return
                        Optional.ofNullable(products.get(id));
            }
            @Override
            public Optional<Product> findByName(String name) {
                return Optional.ofNullable(products.get(name));
            }
        };
    }

    private Map<Integer, Category> categorys = new HashMap<>();
        CategoryRepository categoryTestRepository(){
            return new CategoryRepository(){

                @Override
                public List<Category> findAll() {
                    return null;
                }

                @Override
                public Category save(Category entity) {
                    return null;
                }

                @Override
                public Category findByName(String name) {
                    return null;
                }

                @Override
                public Category findById(int id) {
                    return categorys.get(id);
                }

                @Override
                public boolean existsById(Integer id) {
                    return false;
                }

                @Override
                public void deleteById(Integer id) {

                }
            };
        }
    }

