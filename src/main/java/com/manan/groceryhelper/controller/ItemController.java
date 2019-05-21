package com.manan.groceryhelper.controller;

import com.manan.groceryhelper.exception.ItemNotFoundException;
import com.manan.groceryhelper.model.Item;
import com.manan.groceryhelper.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

/**
 * Created by mananshah on 20/05/19.
 */
@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    ItemService itemService;

    ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/grocery/item")
    public Iterable<Item> getAllGroceryItems() {
        return itemService.getGroceryItemList();
    }

    @GetMapping("/grocery/item/{id}")
    public Item getGroceryItemById(@PathVariable long id) {
        return itemService.getGroceryItem(id);
    }

    @PutMapping("/grocery/item")
    public ResponseEntity<String> addGroceryItem(@RequestBody Item newGroceryItem) {
        try {
            itemService.addGroceryItem(newGroceryItem);
            return new ResponseEntity("Grocery Item added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Unable to add item to groceries list", e);
        }
        return new ResponseEntity("Grocery Item couldn't be added. Check if item already exists", HttpStatus.EXPECTATION_FAILED);
    }

    /*
    @PostMapping("/grocery/item/{id}")
    public ResponseEntity<String> updateGroceryItem(@PathVariable long id , @RequestBody Item groceryItem) {
        try {
            Item groceryItemFromService = itemService.getGroceryItem(id);
            groceryItemFromService.setItemName(groceryItem.getItemName());
            //groceryItemFromService.setItemCategory(groceryItem.getItemCategory());
            itemService.addGroceryItem(groceryItemFromService);
            return new ResponseEntity<String>("Grocery item " + groceryItem.getItemName() + " successfully updated",
                    HttpStatus.OK);
        } catch (Exception e) {
            //Todo: Add logging
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Grocery item " + groceryItem.getItemName() + " couldn't be updated",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    */

    @DeleteMapping("/grocery/item/{id}")
    public ResponseEntity<String> deleteGroceryItem(@PathVariable long id) {
        try {
            itemService.deleteGroceryItem(id);
            return new ResponseEntity("Grocery Item deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Grocery Item does not exist. So can't perform deletion");
        }
        return new ResponseEntity("Grocery Item doesn't exist", HttpStatus.NOT_FOUND);
    }

}
