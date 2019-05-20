package com.manan.groceryhelper.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mananshah on 20/05/19.
 */

@Data
@EqualsAndHashCode(exclude = "itemSet")

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @NonNull
    @Column(unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "itemCategory", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Item> itemSet = new HashSet<Item>();

    public Category() {

    }

    public Category(String categoryName, Item... itemSet) {
        this.categoryName = categoryName;
        this.itemSet = Stream.of(itemSet).collect(Collectors.toSet());
        this.itemSet.forEach(x -> x.setItemCategory(this));
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }
}
