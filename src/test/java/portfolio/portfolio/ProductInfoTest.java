package portfolio.portfolio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import portfolio.portfolio.logic.ProductValidator;
import portfolio.portfolio.model.Product;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;


public class ProductInfoTest {

    private ProductValidator undertest;

    @BeforeEach
    void setup(){
         undertest = new ProductValidator();
    }

    @ParameterizedTest
    @CsvSource({", tele, FALSE",
                "tv, , FALSE",
                "tv, Pikok, TRUE"
    })
    @DisplayName("should not create Product with empty Name or empty Descritpion")
    void validateProduct(String name, String  description, String expected){
        //given
        Product telewizor = new Product(name, description);
        //when
        boolean isValid = undertest.validacja(telewizor);
        //then
        System.out.println(isValid);
        assertThat(isValid).isEqualTo(Boolean.valueOf(expected));
//TO DO TESTY
    }

    @ParameterizedTest
    @CsvSource({
            "tv, , 100, FALSE",
            " ,telewizor , 100, FALSE",
            "tv,telwizor, 500, TRUE"
    })
    void validateProductWithPrice(String name, String desc, int price, String expected){
        //given
        Product product = new Product(name, desc, price);
        //when
        boolean isValid = undertest.validateProduct(product);
        //then
        System.out.println(isValid);
        assertThat(isValid).isEqualTo(Boolean.valueOf(expected));
    }
}
