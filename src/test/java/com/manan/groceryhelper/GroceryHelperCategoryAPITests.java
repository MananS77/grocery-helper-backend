package com.manan.groceryhelper;

import com.manan.groceryhelper.controller.CategoryController;
import com.manan.groceryhelper.model.Category;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mananshah on 21/05/19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GroceryHelperApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroceryHelperCategoryAPITests {

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void contextLoads() {
        Assertions.assertThat(categoryController).isNotNull();
    }

    @Test
    public void test_A_Get_GroceryCategories() {
        ResponseEntity<Iterable<Category>> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/grocery/category", HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Category>>() {
                });

        Iterable<Category> groceryItems = responseEntity.getBody();
        assertThat(groceryItems, hasItem(hasProperty("categoryName", is("Dairy Products"))));
        assertThat(groceryItems, hasItem(hasProperty("categoryName", is("Vegetables"))));
        assertThat(groceryItems, hasItem(hasProperty("categoryName", is("Fruits"))));
    }

    @Test
    public void test_B_Get_GroceryCategoryById() {
        ResponseEntity<Category> response = restTemplate.exchange("http://localhost:" + port + "/grocery/category/2",
                HttpMethod.GET, null, new ParameterizedTypeReference<Category>() {
                });

        Category category = response.getBody();
        MatcherAssert.assertThat(category, hasProperty("categoryName", is("Vegetables")));
    }

    @Test
    public void test_C_Get_GroceryCategoryById() {
        ResponseEntity<Category> response = restTemplate.exchange("http://localhost:" + port + "/grocery/category/null",
                HttpMethod.GET, null, new ParameterizedTypeReference<Category>() {
                });

        Category category = response.getBody();
        MatcherAssert.assertThat(category, hasProperty("categoryName", isEmptyOrNullString()));
    }

    @Test
    public void test_D_Get_GroceryCategoryById() {
        ResponseEntity<Category> response = restTemplate.exchange("http://localhost:" + port + "/grocery/category/4",
                HttpMethod.GET, null, new ParameterizedTypeReference<Category>() {
                });

        Category category = response.getBody();
        MatcherAssert.assertThat(category, hasProperty("categoryName", isEmptyOrNullString()));
    }

    @Test
    public void test_E_Add_GroceryCategoryById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\n\t\"categoryName\": \"Biscuits\"\n}";
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/grocery/category",
                HttpMethod.PUT, entity, new ParameterizedTypeReference<String>() {
                });
        String responseString = responseEntity.getBody();
        assertEquals(responseString, "Grocery Category added successfully");
    }

    @Test
    public void test_F_Add_GroceryCategoryById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\n\t\"categoryName\": null\n}";
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/grocery/category",
                HttpMethod.PUT, entity, new ParameterizedTypeReference<String>() {
                });
        String responseString = responseEntity.getBody();
        assertNotEquals(responseString, "Grocery Category added successfully");
    }

}
