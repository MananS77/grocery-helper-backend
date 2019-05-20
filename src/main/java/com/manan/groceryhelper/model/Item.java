package com.manan.groceryhelper.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;

/**
 * Created by mananshah on 20/05/19.
 */

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @NonNull
    @Column(unique = true)
    private String itemName;

    @ManyToOne
    @JoinColumn
    private Category itemCategory;


    public Item() {

    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemCategory(Category itemCategory) {
        this.itemCategory = itemCategory;
    }
}
