package problems.RestaurantCatalog;

import java.util.HashMap;
import java.util.Map;

public class AddOn {
    private String name;
    private Map<String, Double> priceByVariant = new HashMap<>();

    public AddOn(String name) {
        this.name = name;
    }

    public void setPriceForVariant(String variantName, double price) {
        priceByVariant.put(variantName, price);
    }

    public double getPriceForVariant(String variantName) {
        return priceByVariant.getOrDefault(variantName, 0.0);
    }

    public String getName() {
        return name;
    }
}