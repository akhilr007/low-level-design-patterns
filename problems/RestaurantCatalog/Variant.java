package problems.RestaurantCatalog;

public class Variant {
    private String name;
    private double basePrice;

    public Variant(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }
}