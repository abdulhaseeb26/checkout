package com.ecommerce.challenge.controller;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"classpath:application-test.properties"})
public class CheckoutControllerTest {

    //Should have used parameterized type tests, which helps you to write the tests based on parameters given
    // because all tests have only one thing change which is the parameters, by using that we could write one test
    // and there would be much less code


    //Did not write tests for edge cases like asserting exceptions
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCheckout_whenAllProductsLiesOnce() throws Exception {

        List<String> productCodes = Arrays.asList("a", "b", "c", "d");

        BigDecimal price = performRequest(productCodes);

        assertThat(price).isEqualTo(new BigDecimal("135.0"));
    }

    @Test
    public void testCheckout_whenOnly2ProductsLies() throws Exception {

        List<String> productCodes = Arrays.asList("a", "a");

        BigDecimal price = performRequest(productCodes);

        assertThat(price).isEqualTo(new BigDecimal("135.0"));
    }

    @Test
    public void testCheckout_whenProductAOccursTwice() throws Exception {

        List<String> productCodes = Arrays.asList("a", "a", "b", "c", "d");

        BigDecimal price = performRequest(productCodes);

        assertThat(price).isEqualTo(new BigDecimal("175.0"));
    }

    @Test
    public void testCheckout_whenProductAOccursAsMuchAsDealQuantity() throws Exception {

        List<String> productCodes = Arrays.asList("a", "a", "b", "b", "c", "d");

        BigDecimal price = performRequest(productCodes);

        assertThat(price).isEqualTo(new BigDecimal("195.0"));
    }

    @Test
    public void testCheckout_whenProductBOccursAsMuchAsDealQuantity() throws Exception {

        List<String> productCodes = Arrays.asList("a", "b", "b", "c", "d");

        BigDecimal price = performRequest(productCodes);

        assertThat(price).isEqualTo(new BigDecimal("165.0"));
    }

    private BigDecimal performRequest(List<String> productCodes) throws Exception {

        //Should not include the dependency just for converting it to json as major part of sdk is not being utilized,
        // either write a method of your own or use something which spring provides already,
        // because its hard to maintain libraries in concern of security updates etc
        MvcResult result = mockMvc.perform(post("/checkout/").content(new Gson().toJson(productCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        return JsonPath.parse(response).read("$.price", BigDecimal.class);

    }
}
