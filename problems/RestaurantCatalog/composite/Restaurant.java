package problems.RestaurantCatalog.composite;

import java.util.ArrayList;
import java.util.List;

public class Restaurant implements CatalogComponent{

    private String name;
    private List<Category> categories = new ArrayList<>();

    public Restaurant(String name) {
        this.name = name;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    @Override
    public void showDetails() {
        System.out.println("Restaurant: " + name);
        for (var category : categories) {
            category.showDetails();
        }

    }
}