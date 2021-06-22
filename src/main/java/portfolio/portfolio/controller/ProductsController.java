package portfolio.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import portfolio.portfolio.logic.CategoryService;
import portfolio.portfolio.logic.ProductsService;
import portfolio.portfolio.logic.Utils.Utils;
import portfolio.portfolio.model.Category;
import portfolio.portfolio.model.Product;
import portfolio.portfolio.model.projection.CartInfo;
import portfolio.portfolio.model.projection.ProductInfo;
import portfolio.portfolio.repository.ProductsRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductsController {

    private final ProductsService productsService;
    private final ProductsRepository repository;
    private final CategoryService categoryService;

    @Autowired
    public ProductsController(ProductsService productsService, ProductsRepository repository,@Lazy CategoryService categoryService) {
        this.productsService = productsService;
        this.repository = repository;
        this.categoryService = categoryService;
    }
    
    @GetMapping
    @ResponseBody
    ResponseEntity<List<Product>> readAllProducts() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Transactional
    @PostMapping("/{id}")
    @ResponseBody
    ResponseEntity<Product> createAndAddProduct(@PathVariable int id, @RequestBody ProductInfo productDTO) {
        Product product = productsService.productMapper(productDTO);
        Product product1 = productsService.addProduct(product, id);
        productsService.save(product1);
        return ResponseEntity.created(URI.create("/" + product1.getId())).body(product1);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    ResponseEntity<Product> deleteProductById(@PathVariable int id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/productList")
    public String productList(Model model) {
        List<Category> categories = categoryService.readAll();
        model.addAttribute("products", categories);
        return "productList";
    }

    @GetMapping("/buyProduct/{id}")
    private String buyProduct(HttpServletRequest request, Model model, @PathVariable int id) throws Exception {
        Product product = productsService.findProduct(id);
        int quantiy = 1;
        if (product != null) {
            CartInfo cartInSession = Utils.getCartInSession(request);
            ProductInfo productInfo = productsService.productInfoMapper(product);
            System.out.println(quantiy);
            cartInSession.addProduct(productInfo, quantiy);
            model.addAttribute("cart", cartInSession);
            double toPay = cartInSession.getAmountTotal();
            model.addAttribute("toPay", toPay);
        }
        return "/shoppingCart";
    }

    @GetMapping("/shoppingCart") //cart from the session
    public String showShoppingCart(HttpServletRequest request, Model model) {
        CartInfo cartInSession = Utils.getCartInSession(request);
        model.addAttribute("cart", cartInSession);
        return "shoppingCart";
    }

    @GetMapping("/{id}")
    ResponseEntity<?> readProductById(@PathVariable int id){
        if (repository.findById(id).isPresent()){
            return ResponseEntity.ok(repository.findById(id));
        }
        return ResponseEntity.notFound().build();
    }
}

//TO DO klasa DTO dla Product i połączyć ze mozna dodawac product do istniejącej Categori

