package portfolio.portfolio.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import portfolio.portfolio.model.Product;
import portfolio.portfolio.repository.ProductsRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc //klasa do testowania aplikacji webowych bez stawiania serwera
@ActiveProfiles("integration")
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductsRepository repo;

    @Test
    void httpGet_returnsGivenProduct() throws Exception {
        //given
        int id = repo.save(new Product("pralka", "Amica", 1299)).getId();
        //when + then
        mockMvc.perform(MockMvcRequestBuilders.get("/product/"+ id))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
