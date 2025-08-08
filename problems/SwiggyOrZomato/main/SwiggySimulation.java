package problems.SwiggyOrZomato.main;

import problems.SwiggyOrZomato.entity.*;
import problems.SwiggyOrZomato.factory.order.OrderFactory;
import problems.SwiggyOrZomato.strategy.payment.CashPaymentStrategy;

import java.util.List;

public class SwiggySimulation {
    public static void main(String[] args) {

        System.out.println("Welcome to Swiggy food service app.");

        // add menu items
        MenuItem burger = new MenuItem(1, "A1", "Burger", 50.0);
        MenuItem pizza = new MenuItem(2, "A2", "Pizza", 120.0);
        MenuItem coke = new MenuItem(3, "A3", "CocaCola", 40.0);

        List<MenuItem> tajMenu =List.of(burger, pizza, coke);

        Restaurant taj = new Restaurant("Taj", "Connaught Place", tajMenu);


        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        restaurantManager.addRestaurant(taj);

        // User
        User jai = new User("Jai", "221-B Baker Street");
        jai.getCart().addToCart(pizza);
        jai.getCart().addToCart(coke);

        // create order
        Order jaiOrder = OrderFactory.createOrder(jai, taj, OrderType.DELIVERY);
        jaiOrder.setPaymentStrategy(new CashPaymentStrategy());
        OrderManager.getInstance().addOrder(jaiOrder);
        jaiOrder.placeOrder();
        jaiOrder.makePayment();

        OrderManager.getInstance().addOrder(jaiOrder);

        // assign delivery partner
    }
}