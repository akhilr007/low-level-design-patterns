package problems.SwiggyOrZomato.factory.order;

import problems.SwiggyOrZomato.entity.*;

public class OrderFactory {

    public static Order createOrder(User user, Restaurant restaurant, OrderType orderType) {
        switch (orderType) {
            case DELIVERY -> {
                return new DeliveryOrder(user, restaurant, orderType);
            }
            case PICKUP -> {
                return new PickupOrder(user, restaurant, orderType);
            }
            default -> throw new IllegalArgumentException("Unknown orderType " + orderType.name());
        }
    }
}