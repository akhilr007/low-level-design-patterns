package problems.RestaurantCatalog.decorator;

import problems.RestaurantCatalog.AddOn;
import problems.RestaurantCatalog.Variant;

public class AddOnDecorator implements PricedComponent{

    private final PricedComponent component;
    private final AddOn addOn;
    private final String variantName;

    public AddOnDecorator(PricedComponent component, AddOn addOn, String name) {
        this.component = component;
        this.addOn = addOn;
        this.variantName = name;
    }

    @Override
    public double getPrice() {
        return component.getPrice() + addOn.getPriceForVariant(variantName);
    }

    @Override
    public String getDescription() {
        return component.getDescription() + " + " + addOn.getName();
    }

}