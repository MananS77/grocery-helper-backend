package com.manan.groceryhelper;

import com.manan.groceryhelper.controller.ItemController;
import com.manan.groceryhelper.model.Item;
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
@SpringBootTest(classes = { GroceryHelperApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroceryHelperItemAPITests {
    @Autowired
    private ItemController itemController;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void contextLoads() {
        Assertions.assertThat(itemController).isNotNull();
    }

    @Test
    public void test_A_Get_GroceryItems() {
        ResponseEntity<Iterable<Item>> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/grocery/item", HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Item>>() {
                });

        Iterable<Item> groceryItems = responseEntity.getBody();
        assertThat(groceryItems, hasItem(hasProperty("itemName", is("Milk"))));
        assertThat(groceryItems, hasItem(hasProperty("itemName", is("Cottage Cheese"))));
        assertThat(groceryItems, hasItem(hasProperty("itemName", is("Potato"))));
        assertThat(groceryItems, hasItem(hasProperty("itemName", is("Onion"))));
        assertThat(groceryItems, hasItem(hasProperty("itemName", is("Cabbage"))));
        assertThat(groceryItems, hasItem(hasProperty("itemName", is("Carrot"))));
        assertThat(groceryItems, hasItem(hasProperty("itemName", is("Beet"))));
    }

    @Test
    public void test_B_Get_GroceryItemById() {
        ResponseEntity<Item> response = restTemplate.exchange("http://localhost:" + port + "/grocery/item/1",
                HttpMethod.GET, null, new ParameterizedTypeReference<Item>() {
                });

        Item item = response.getBody();
        MatcherAssert.assertThat(item, hasProperty("itemName", is("Milk")));
    }

    @Test
    public void test_C_Get_GroceryItemById() {
        ResponseEntity<Item> response = restTemplate.exchange("http://localhost:" + port + "/grocery/item/null",
                HttpMethod.GET, null, new ParameterizedTypeReference<Item>() {
                });

        Item item = response.getBody();
        MatcherAssert.assertThat(item, hasProperty("itemName", isEmptyOrNullString()));
        MatcherAssert.assertThat(item, hasProperty("itemCategory", isEmptyOrNullString()));
    }

    @Test
    public void test_D_Add_GroceryItemById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\n\t\"itemName\": \"Mushroom\",\n\t\"itemCategory\": {\n" +
                "            \"categoryId\": 2,\n            \"categoryName\": \"Vegetables\"\n    }\n}";
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/grocery/item",
                HttpMethod.PUT, entity, new ParameterizedTypeReference<String>() {
                });
        String responseString = responseEntity.getBody();
        assertEquals(responseString, "Grocery Item added successfully");
    }

    @Test
    public void test_E_Delete_GroceryItemById() {
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/grocery/item/2",
                HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {
                });

        String responseString = response.getBody();
        assertEquals(responseString, "Grocery Item deleted successfully");
    }

    @Test
    public void test_F_Delete_GroceryItemById() {
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "grocery/item/2",
                HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {
                });

        String responseString = response.getBody();
        assertEquals(responseString, "Grocery Item doesn't exist");
    }

    @Test
    public void test_G_Delete_GroceryItemById() {
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "grocery/item/200",
                HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {
                });

        String responseString = response.getBody();
        assertEquals(responseString, "Grocery Item doesn't exist");
    }


    @Test
    public void test_H_Get_GroceryItemsUnderCategory() {
        ResponseEntity<Iterable<Item>> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/grocery/category/items?categoryId=1", HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Item>>() {
                });

        Iterable<Item> groceryItems = responseEntity.getBody();
        assertThat(groceryItems, hasItem(hasProperty("itemName", is("Milk"))));
    }

    @Test
    public void test_I_Update_GroceryItemById() {
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/grocery/item/8?newItemName=Pome",
                HttpMethod.POST, null, new ParameterizedTypeReference<String>() {
                });
        String responseString = response.getBody();
        assertEquals(responseString, "Grocery item successfully updated");
    }

    @Test
    public void test_J_Update_GroceryItemById() {
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/grocery/item/17?newItemName=Pome",
                HttpMethod.POST, null, new ParameterizedTypeReference<String>() {
                });
        String responseString = response.getBody();
        assertNotEquals(responseString, "Grocery item successfully updated");
    }

    @Test
    public void test_K_Update_GroceryItemById() {
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/grocery/item/17?newItemName=Pome",
                HttpMethod.POST, null, new ParameterizedTypeReference<String>() {
                });
        String responseString = response.getBody();
        assertEquals(responseString, "Grocery item couldn't be updated");
    }

}
