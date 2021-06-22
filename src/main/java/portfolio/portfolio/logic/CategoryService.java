package portfolio.portfolio.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.portfolio.model.Category;
import portfolio.portfolio.model.Product;
import portfolio.portfolio.model.projection.CategoryDTO;
import portfolio.portfolio.repository.CategoryRepository;
import portfolio.portfolio.repository.ProductsRepository;

import java.util.List;

@Service
public class CategoryService {


    private CategoryRepository categoryRepository;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }
    public Category createCategory(Category category){
        Category save = categoryRepository.save(category);
     return save;
    }
//    public Category addProductToCategoryById(Integer categoryId, Product product){
//        Category byId = categoryRepository.findById(categoryId);
//        Product save = productsRepository.save(product);
//        product.setCategory(byId);
//        List<Product> products = byId.getProducts();
//        save.setCategory(byId);
//        products.add(save);
//        byId.setProducts(products);
//        return byId;
//    }

    public List<Category> readAll(){
        List<Category> all = categoryRepository.findAll();
        return all;
    }

    public Category categoryMapper(CategoryDTO categoryDTO) {
        String name = categoryDTO.getName();
        String description = categoryDTO.getDescription();
//        if (categoryDTO.getProducts().isEmpty()) {
            return new Category(name, description, null);
//        }
//            List<Product> collect = categoryDTO.getProducts().stream().map(ProductsService::productMapper).collect(Collectors.toList());

//            return new Category(name, description, null); //mozemy utworzyć kategorie z produktami lub bez ,
            // w POstamnie musimy przekazac pustą liste produktow lub tutaj null
        }

    }


