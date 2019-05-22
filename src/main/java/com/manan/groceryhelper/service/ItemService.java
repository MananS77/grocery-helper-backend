package com.manan.groceryhelper.service;

import com.manan.groceryhelper.exception.ItemNotFoundException;
import com.manan.groceryhelper.model.Item;
import com.manan.groceryhelper.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

/**
 * Created by mananshah on 20/05/19.
 */

@Service
@Transactional
public class ItemService {

    ItemRepository itemRepository;

    ItemService (ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Iterable<Item> getGroceryItemList() {
        Iterable <Item> list = itemRepository.findAll();
        return list;
    }

    public Item getGroceryItem(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Grocery Item not found"));
        return item;
    }

    public void addGroceryItem(Item newGroceryItem) {
        if(newGroceryItem != null) {
            Iterable<Item> list = getGroceryItemList();
            Iterator<Item> iter = list.iterator();
            while (iter.hasNext()) {
                if(newGroceryItem.getItemId() == iter.next().getItemId()) {
                    return;
                }
            }
            itemRepository.save(newGroceryItem);
        } else {
            throw new NullPointerException("Null/Malformed item passed. Please add a valid item");
        }

    }

    public void deleteGroceryItem(long id) {
        Item groceryItem = getGroceryItem(id);

        if (groceryItem != null && !groceryItem.getItemName().isEmpty()) {
            itemRepository.delete(groceryItem);
        } else {
            try {
                throw new ItemNotFoundException("No Grocery Item found to delete");
            } catch (ItemNotFoundException e) {
                //TODO: Add logging
                e.printStackTrace();
            }
        }
    }

    public void updateGroceryItem(long id, String newItemName) {
        Item groceryItem = getGroceryItem(id);

        if (groceryItem != null && !groceryItem.getItemName().isEmpty()) {
            groceryItem.setItemName(newItemName);
            itemRepository.save(groceryItem);
        } else {
            try {
                throw new ItemNotFoundException("No Grocery Item found to update");
            } catch (ItemNotFoundException e) {
                //TODO: Add logging
                e.printStackTrace();
            }
        }
    }

}
