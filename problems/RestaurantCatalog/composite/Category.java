package problems.RestaurantCatalog.composite;

import java.util.ArrayList;
import java.util.List;

public class Category implements CatalogComponent{

    private final String name;
    private final List<Item> items = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void showDetails() {
        System.out.println("Category: " + name);
        for (var item : items) {
            item.showDetails();
        }
    }
}