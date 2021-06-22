package portfolio.portfolio.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import portfolio.portfolio.model.Category;
import portfolio.portfolio.model.Product;
import portfolio.portfolio.model.projection.ProductInfo;
import portfolio.portfolio.repository.CategoryRepository;
import portfolio.portfolio.repository.ProductsRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    public ProductsService(ProductsRepository productsRepository, CategoryRepository categoryRepository,@Lazy CategoryService categoryService) {
        this.productsRepository = productsRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }


    private final ProductsRepository productsRepository;
    private  final  CategoryRepository categoryRepository;
    private final  CategoryService categoryService;

    public List<Product> findAll(){
        return productsRepository.findAll();
    }

    public Product save(Product products){
        return productsRepository.save(products);
    }

    public Product addProduct(Product product, int categoryId) {
        Category category = categoryRepository.findById(categoryId);
        if (category == null) {
            throw new NullPointerException("Category cannot be found!");
        } else {
            List<Product> products = category.getProducts();
            product.setCategory(category);
            product.setName(product.getName());
            product.setDescription(product.getDescription());
            product.setPrice(product.getPrice());
            validacja(product);
            if (product.getName().equals(findProduct(product.getName()).toString())){
                throw new RuntimeException("Product already in base");
            } else {
                products.add(product);
            }
            if (categoryId == product.getCategory().getId()) {
                System.out.println("Dodano produkt");
            }
            System.out.println(product.getName() + " " + product.getCategory().getId() + " " + product.getPrice());
            return product;
        }
    }
    public Product productMapper(ProductInfo productDTO){
        String name = productDTO.getName();
        String descritpion = productDTO.getDescription();
        int price = productDTO.getPrice();
        return new Product(name,descritpion,price,null);
    }
    public ProductInfo productInfoMapper(Product product){

        return new ProductInfo(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
    public Product findProduct(Integer id) throws Exception {
        Optional<Product> product = productsRepository.findById(id);
        return product.orElseThrow(()-> new Exception("Product not found"));
    }

    public Optional <Product> findProduct(String name) throws NoSuchElementException {
        Optional<Product> byName = productsRepository.findByName(name);
        return  byName;
    }

    public ProductInfo findProductInfo(Integer id) throws Exception {
        Product product = findProduct(id);
        ProductInfo productInfo = productInfoMapper(product);
        return new ProductInfo(productInfo.getId(), productInfo.getName(), productInfo.getDescription(),productInfo.getPrice());
    }


    public boolean validacja(Product product){
        String name = product.getName();
        String description = product.getDescription();
        if ((name!=null) && (description!=null)){
            return true;
        }
        else {
            return false;
        }
    }

    }

