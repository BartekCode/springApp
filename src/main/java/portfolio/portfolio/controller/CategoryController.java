package portfolio.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import portfolio.portfolio.logic.CategoryService;
import portfolio.portfolio.model.Category;
import portfolio.portfolio.model.projection.CategoryDTO;
import portfolio.portfolio.repository.CategoryRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private static  CategoryRepository categoryRepository;
    private static CategoryService categoryService;


    @Autowired
    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    ResponseEntity<Category> deleteCategoryById(@PathVariable int id){
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @ResponseBody //by dodawac postmanem JSON
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryDTO toCreate) {
        Category category = categoryService.createCategory(categoryService.categoryMapper(toCreate));
        return ResponseEntity.created(URI.create("/" + category.getId())).body(category); //201
    }


    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Category>> readAllCategorys() {
        return ResponseEntity.ok(categoryService.readAll());
    }


    @PostMapping("/add")
    public String addUser(@ModelAttribute @Validated Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "categoryForm";
        } else {
            categoryService.createCategory(category);
            return "redirect:/category"; //musi byc redirect na getMapping ponizej inaczej nie mamy obiektu newCategory i categories i jest błąd
        }//czyli robimy przekierowanie do metody ktora ma uzupelnione te obiekty
    }
    @GetMapping()
    public String register(Model model, Category category){
        model.addAttribute("newCategory", new Category()); //bindujemy category
        List<Category> categories = categoryService.readAll();
        model.addAttribute("categories", categories);
        return "categoryForm";
    }

}