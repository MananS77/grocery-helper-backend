package com.manan.groceryhelper;

import com.manan.groceryhelper.model.Category;
import com.manan.groceryhelper.model.Item;
import com.manan.groceryhelper.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroceryHelperApplication implements CommandLineRunner{

	@Autowired
	private ItemCategoryRepository itemCategoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(GroceryHelperApplication.class, args);
	}

	@Override
	public void run(String... args) {

		itemCategoryRepository.save(
				new Category("Dairy Products",
						new Item("Milk"),
						new Item("Cottage Cheese")));


		itemCategoryRepository.save(
				new Category("Vegetables",
						new Item("Potato"),
						new Item("Onion"),
						new Item("Cabbage"),
						new Item("Carrot"),
						new Item("Beet")));

		itemCategoryRepository.save(
				new Category("Fruits",
						new Item("Apple"),
						new Item("Banana"),
						new Item("Kiwi"),
						new Item("Peach")));
	}

}
