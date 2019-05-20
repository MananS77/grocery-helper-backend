package com.manan.groceryhelper.repository;

import com.manan.groceryhelper.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mananshah on 20/05/19.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
