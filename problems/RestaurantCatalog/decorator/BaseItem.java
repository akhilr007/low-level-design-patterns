package problems.RestaurantCatalog.decorator;

public class BaseItem implements PricedComponent {

    private final String name;

    public BaseItem(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getDescription() {
        return name;
    }

}