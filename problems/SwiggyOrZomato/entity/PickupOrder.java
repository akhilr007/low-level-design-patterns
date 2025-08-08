package problems.SwiggyOrZomato.entity;

import problems.SwiggyOrZomato.factory.order.OrderFactory;
import problems.SwiggyOrZomato.observer.RestaurantNotification;
import problems.SwiggyOrZomato.observer.UserNotification;
import problems.SwiggyOrZomato.strategy.payment.UPIPaymentStrategy;

public class PickupOrder extends Order {

    private String location;

    public PickupOrder(User user, Restaurant restaurant, OrderType orderType) {
        this.id = ++id;
        this.location = restaurant.getAddress();
        this.paymentStrategy = new UPIPaymentStrategy();
        this.user = user;
        this.restaurant = restaurant;
        this.orderType = orderType;

    }

    @Override
    public boolean makePayment() {
        paymentStrategy.pay(this);
        return true;
    }

    @Override
    public void createOrder() {
        this.user.setOrders(OrderFactory.createOrder(user, restaurant, orderType));
        this.orderStatus = OrderStatus.ORDER_PLACED;
        NotificationManager.getInstance().addObserver(new UserNotification());
        NotificationManager.getInstance().addObserver(new RestaurantNotification());
        NotificationManager.getInstance().notifyAll();
    }
}