package portfolio.portfolio.repository;

import portfolio.portfolio.model.Category;

import java.util.List;


public interface CategoryRepository {

    List<Category>findAll();
    Category save (Category entity);
    Category findByName(String name);
    Category findById(int id);
    boolean existsById(Integer id);
    void deleteById(Integer id);

}
