package portfolio.portfolio.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import portfolio.portfolio.model.Product;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductsServiceTest {

    @Test
    @DisplayName("should throw Exception when its no Product")
    void findProduct_throwsException() {
        //given
        var mockProductService = mock(ProductsService.class);//mockujemy klase zaczytujemy definicje klasy
        //when
        try {
            when(mockProductService.findProduct(anyInt())).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //then
        Assertions.assertThrows(Exception.class, () -> {
            throw new Exception("Product not found");
        });
    }
    @Test
    void verifyIfProductIsValidated(){
        //given
        ProductsService mock = mock(ProductsService.class);
        Product product = new Product("TV", null, 999);
        //when
        mock.validacja(product);
        //then
        Assertions.assertThrows(Exception.class, ()-> {
          throw new NullPointerException("description cannot by null");
        });
    }
    @Test
    void verifyValidatedProduct(){
        //given
        ProductsService productsService = new ProductsService(null,null,null);
        Product product = new Product("Pika", "sss", 999);
        //when
        boolean validacja1 = productsService.validacja(product);
        //then
        assertThat(validacja1).isTrue();
    }
    @Test
    @DisplayName("should add only unique products")
    void verifyIfProductIsAlreadyAdded(){
        //given
        Product product = new Product("Telewizor", null, anyInt());
        Product newProduct = new Product("Telewizor", null, anyInt());
        //when
        assertEquals(product.getName(),newProduct.getName());
    }
}