package portfolio.portfolio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import portfolio.portfolio.model.Product;
import portfolio.portfolio.repository.ProductsRepository;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.notNullValue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsControllerE2ETest {
    @LocalServerPort //by zaczytac aktulanie zaczytany port z RANDOM_PORT
    private  int port;

    @Autowired
    private TestRestTemplate restTemplate; //klasa pozwalająca odpytywac istniejące usługi

    @MockBean
    private ProductsRepository repository;

    @Test
    void httpGet_returnsAllProducts(){
        //given
        int size = repository.findAll().size();
        repository.save(new Product("Pilot", "Do tv", 200 ));
        repository.save(new Product("Słuchawki", "Apple", 150));
        //when
        Product[] result = restTemplate.getForObject("http://localhost:" + port + "/product", Product[].class);
        //then
        assertThat(result).hasSize(size+2);
    }
    @Test
    void saveProductWithoutNameThrowsException() {
        //given
        try {
            HttpEntity<Product> request = new HttpEntity<>(new Product(null, "plazam", 1000));
            Product do_tv = repository.save(new Product(null, "Do tv", 200));
        //when
        Product[] result = restTemplate.postForObject("http://localhost:" + port + "/product{"+do_tv.getId(),request, Product[].class);
        //then
        assertThatThrownBy(()->repository.save(do_tv))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("must not be null");
        } catch (ConstraintViolationException e) {
            System.out.println("must not be null");
        }

    }

//    @Test
//    void saveProduct() {
//        //given
////            HttpEntity<Product> request = new HttpEntity<>(new Product("telewizor", "plazam", 1000));
//            Product do_tv = repository.save(new Product("telewizor", "Do tv", 200));
//            //when
//        HttpEntity<Product> request = new HttpEntity<>(new Product("telewizor", "Do tv", 200));
//            ResponseEntity<Product> result = restTemplate.postForObject("http://localhost:" + port + "/product{"+do_tv.getId()+"}",request, HttpMethod.POST, Product.class);
//            //then
//        assertThat(result.getStatusCode(), is(HttpStatus.CREATED));
//
//    }

}