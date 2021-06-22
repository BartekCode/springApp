package portfolio.portfolio.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.portfolio.model.Category;
import portfolio.portfolio.repository.CategoryRepository;

import java.util.List;

@Repository
public interface SqlCategoryRepository  extends CategoryRepository, JpaRepository<Category, Integer> {

    @Override
    List<Category> findAllById(Iterable<Integer> iterable);

    @Override
    List<Category> findAll();

    @Override
    Category save(Category entity);

    @Override
    Category findByName(String name);

    @Override
    Category findById(int id);

    void deleteById(int id);
}
