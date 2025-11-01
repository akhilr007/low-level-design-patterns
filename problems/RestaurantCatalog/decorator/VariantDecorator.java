package problems.RestaurantCatalog.decorator;

import problems.RestaurantCatalog.Variant;

public class VariantDecorator implements PricedComponent{

    private final PricedComponent component;
    private final Variant variant;

    public VariantDecorator(PricedComponent pricedComponent, Variant variant) {
        this.component = pricedComponent;
        this.variant = variant;
    }

    @Override
    public double getPrice() {
        return variant.getBasePrice();
    }

    @Override
    public String getDescription() {
        return component.getDescription() + " (" + variant.getName() + ")";
    }

}