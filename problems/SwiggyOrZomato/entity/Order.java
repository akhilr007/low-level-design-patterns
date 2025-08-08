package problems.SwiggyOrZomato.entity;

import problems.SwiggyOrZomato.strategy.payment.PaymentStrategy;

import java.util.List;

public abstract class Order {

    protected static int id = 0;
    protected User user;
    protected Restaurant restaurant;
    protected OrderType orderType;
    protected OrderStatus orderStatus;
    protected PaymentStrategy paymentStrategy;
    protected List<MenuItem> menuItems;

    public static int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public abstract boolean makePayment();

    public abstract void placeOrder();


}