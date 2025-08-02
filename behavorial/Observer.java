package behavorial;

import java.util.ArrayList;
import java.util.List;

interface OrderObserver {
    void update(String orderId, String orderStatus);
}

class TrackingSystem implements OrderObserver {

    @Override
    public void update(String orderId, String orderStatus) {
        System.out.println("Tracking System: Order " + orderId + " updated to " + orderStatus);
    }
}

class DeliveryRider implements OrderObserver {

    @Override
    public void update(String orderId, String orderStatus) {
        if (orderStatus.equals("Out for Delivery")) {
            System.out.println("Delivery Partner notified: Ready to pick up Order " + orderId);
        }
    }
}

class Customer implements OrderObserver {

    private final String customerName;

    public Customer(String name) {
        this.customerName = name;
    }

    @Override
    public void update(String orderId, String orderStatus) {
        System.out.println("Customer " + customerName + " notified: Order " + orderId + " is now " + orderStatus);
    }

}

interface Subject {
    void subscribe(OrderObserver subscriber);
    void unsubscribe(OrderObserver subscriber);
    void notifySubscriber();
}

class Order implements Subject {

    private String orderId;
    private String orderStatus;

    private final List<OrderObserver> subscribers = new ArrayList<>();

    public String getOrderId() {
        return orderId;
    }

    public Order(String orderId) {
        this.orderId = orderId;
        this.orderStatus = "Order Placed";
    }

    @Override
    public void subscribe(OrderObserver subscriber) {
       subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(OrderObserver subscriber) {
       subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscriber() {
        for (OrderObserver subscriber: subscribers) {
            subscriber.update(orderId, orderStatus);
        }
    }

    public void updateStatus(String status) {
        this.orderStatus = status;
        notifySubscriber();
    }

    
}
public class Observer {
    public static void main(String[] args) {
        
        Order order = new Order("A123");

        OrderObserver customer = new Customer("Alice");
        OrderObserver deliveryPartner = new DeliveryRider();
        OrderObserver tracking = new TrackingSystem();

        order.subscribe(customer);
        order.subscribe(deliveryPartner);
        order.subscribe(tracking);

        order.updateStatus("Preparing meal.");
        order.updateStatus("Meal ready for pickup.");
        order.updateStatus("Out for Delivery");
        order.updateStatus("Meal delivered");
    }
}
