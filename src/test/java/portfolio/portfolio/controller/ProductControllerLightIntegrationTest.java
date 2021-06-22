package portfolio.portfolio.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import portfolio.portfolio.logic.CategoryService;
import portfolio.portfolio.logic.ProductsService;
import portfolio.portfolio.model.Product;
import portfolio.portfolio.repository.ProductsRepository;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
 class ProductControllerLightIntegrationTest {
    @MockBean
    ProductsRepository productsRepository;
    @Autowired
    CategoryService categoryService;

    @MockBean ////adnotacja by mockowac Beana i uzywac np when
    private ProductsService productsService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void httpGet_returnsGivenTask() throws Exception {
        //given
        String desc = "maka";
//        when(repository.findById(anyInt()))
//                .thenReturn(Optional.of(new Product("paka", desc, 12)));
        when(productsService.findProduct(anyInt()))
                .thenReturn(new Product("sss", "maka", 112));

        //when + then
        mockMvc.perform(MockMvcRequestBuilders.get("/product/12"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(containsString(desc)));
//        .andExpect(status().isOk());
    }



}
