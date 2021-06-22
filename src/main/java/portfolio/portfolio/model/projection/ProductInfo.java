package portfolio.portfolio.model.projection;

import javax.validation.constraints.NotNull;

public class ProductInfo {

    private int id;

    @NotNull
    private String name;
    @NotNull
    private String description;

    private int price;

    private CategoryDTO categoryDTO;

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public ProductInfo() {
    }

    public ProductInfo(int id, @NotNull String name, @NotNull String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductInfo(String name, String descritpion, int price) {
        this.name = name;
        this.description = descritpion;
        this.price = price;
    }

    public ProductInfo(String name, String descritpion, int price, CategoryDTO categoryDTO) {
        this.name = name;
        this.description = descritpion;
        this.price = price;
        this.categoryDTO = categoryDTO;
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

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
