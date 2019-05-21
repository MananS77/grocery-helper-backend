package com.manan.groceryhelper.controller;

import com.manan.groceryhelper.model.Category;
import com.manan.groceryhelper.model.Item;
import com.manan.groceryhelper.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mananshah on 20/05/19.
 */

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/grocery/category")
    public Iterable<Category> getAllGroceryCategories() {
        return categoryService.getCategoryList();
    }

    @GetMapping("/grocery/category/{id}")
    public Category getGroceryCategoryById(@PathVariable long id) {
        return categoryService.getCategory(id);
    }

    @GetMapping("/grocery/category/items")
    public Iterable<Item> getGroceryItemsUnderCategory(@RequestParam long categoryId) {
        return categoryService.getGroceryItemsUnderCategory(categoryId);
    }

    @PutMapping("/grocery/category")
    public ResponseEntity<String> addGroceryItem(@RequestBody Category newGroceryCategory) {
        try {
            categoryService.addGroceryCategory(newGroceryCategory);
            return new ResponseEntity("Grocery Category added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Unable to add new category to list", e);
        }
        return new ResponseEntity("Grocery Category couldn't be added. Check if category already exists", HttpStatus.EXPECTATION_FAILED);
    }
}
