package problems.RestaurantCatalog;

import problems.RestaurantCatalog.composite.Category;
import problems.RestaurantCatalog.composite.Item;
import problems.RestaurantCatalog.composite.Restaurant;
import problems.RestaurantCatalog.decorator.AddOnDecorator;
import problems.RestaurantCatalog.decorator.BaseItem;
import problems.RestaurantCatalog.decorator.PricedComponent;
import problems.RestaurantCatalog.decorator.VariantDecorator;

public class CatalogDemo {

    public static void main(String[] args) {

        // variants
        Variant small = new Variant("Small", 200);
        Variant medium = new Variant("Medium", 300);
        Variant large = new Variant("Large", 400);
        VariantGroup sizeGroup = new VariantGroup("Size");
        sizeGroup.addVariant(small);
        sizeGroup.addVariant(medium);
        sizeGroup.addVariant(large);

        // addons
        AddOn white = new AddOn("white cheese");
        white.setPriceForVariant("small", 30);
        white.setPriceForVariant("medium", 40);
        white.setPriceForVariant("large", 50);

        AddOn yellow = new AddOn("yellow cheese");
        yellow.setPriceForVariant("small", 20);
        yellow.setPriceForVariant("medium", 30);
        yellow.setPriceForVariant("large", 40);

        AddOnGroup cheese = new AddOnGroup("Extra Cheese");
        cheese.addAddOn(white);
        cheese.addAddOn(yellow);

        // item
        Item pizza = new Item("Farmhouse Pizza");
        pizza.addVariantGroup(sizeGroup);
        pizza.addAddOnGroup(cheese);

        Category pizzaCategory = new Category("Pizzas");
        pizzaCategory.addItem(pizza);

        Restaurant restaurant = new Restaurant("Swiggy Slice");
        restaurant.addCategory(pizzaCategory);

        restaurant.showDetails();

        PricedComponent order = new BaseItem("Farmhouse Pizza");
        order = new VariantDecorator(order, medium);
        order = new AddOnDecorator(order, white, "medium");
        order = new AddOnDecorator(order, yellow, "medium");

        System.out.println("Order: " + order.getDescription());
        System.out.println("Total Price: ₹" + order.getPrice());

        // --- TRY ANOTHER COMBINATION ---
        System.out.println("\n--- Another Order ---");
        PricedComponent order2 = new BaseItem("Farmhouse Pizza");
        order2 = new VariantDecorator(order2, large); // Selected "Large"
        order2 = new AddOnDecorator(order2, white, "large");   // Automatically uses "Large" price

        System.out.println("Order: " + order2.getDescription());
        System.out.println("Total Price: ₹" + order2.getPrice());


    }

}