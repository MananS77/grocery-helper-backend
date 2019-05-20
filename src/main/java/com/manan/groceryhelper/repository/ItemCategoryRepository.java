package com.manan.groceryhelper.repository;

import com.manan.groceryhelper.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mananshah on 20/05/19.
 */
public interface ItemCategoryRepository extends JpaRepository<Category, Long> {
}
