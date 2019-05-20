package com.manan.groceryhelper.service;

import com.manan.groceryhelper.exception.CategoryNotFoundException;
import com.manan.groceryhelper.model.Category;
import com.manan.groceryhelper.model.Item;
import com.manan.groceryhelper.repository.ItemCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by mananshah on 20/05/19.
 */

@Service
@Transactional
public class CategoryService {

    ItemCategoryRepository itemCategoryRepository;

    CategoryService(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    public Iterable<Category> getCategoryList() {
        Iterable<Category> list = itemCategoryRepository.findAll();
        return list;
    }

    public Category getCategory(long id) {
        Category category = itemCategoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return category;
    }

    public Set<Item> getGroceryItemsUnderCategory(long id) {
        Category category = getCategory(id);
        Set<Item> itemsUnderCategory = new HashSet<Item>();
        if (category != null) {
            itemsUnderCategory = category.getItemSet();
        }
        return itemsUnderCategory;
    }

    public void addGroceryCategory(Category newGroceryCategory) {
        if(newGroceryCategory != null) {
            Iterable<Category> list = getCategoryList();
            Iterator<Category> iter = list.iterator();
            while (iter.hasNext()) {
                if(newGroceryCategory.equals(iter.next())) {
                    return;
                }
            }
            itemCategoryRepository.save(newGroceryCategory);
        } else {
            throw new NullPointerException("Null/Malformed category passed. Please add a category product");
        }
    }
}
