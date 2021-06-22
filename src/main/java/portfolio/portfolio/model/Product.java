package portfolio.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table (name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private int price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    public Product(@NotNull String name, @NotNull String description, @NotNull int prize) { //konstruktor by mozna bylo utworzyć DTO
//        this.name = name;
//        this.description = description;
//        this.prize = prize;
//    }
//
    public Product(@NotNull String name, @NotNull String description, @NotNull int prize, Category category) {
        this.name = name;
        this.description = description;
        this.price = prize;
        if (category!=null) {
            this.category = category;
        }
        }
    public Product() {
    }
    public Product(@NotNull String name, @NotNull String description, @NotNull int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int prize) {
        this.price = prize;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public void validateProductName() {
        if (this.description.isEmpty()) {
            throw new RuntimeException("Description Cannot be null or empty");
        }
    }
    public void validateProductDescription() {
        if (this.name.isEmpty()) {
            throw new RuntimeException("Name Cannot be null or empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && name.equals(product.name) && description.equals(product.description) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, category);
    }
}
