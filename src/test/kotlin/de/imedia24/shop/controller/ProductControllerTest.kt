package de.imedia24.shop.controller

import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal


@WebMvcTest(ProductController::class)
internal class ProductControllerTest {

    @MockBean
    private lateinit var productService: ProductService
    @Autowired
    private  lateinit var mvc: MockMvc
    @Test
    fun test_get_list_product_by_list_of_skus(){
        val input : List<String> = listOf("1","2","3")
        val result : List<ProductResponse> = listOf(ProductResponse("","","", BigDecimal.ONE,""))
        Mockito.`when`(productService.findListProductBySku(input)).thenReturn(result);
        mvc.perform(MockMvcRequestBuilders.get("/products?skus=$input").requestAttr("skus",input)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }
}

