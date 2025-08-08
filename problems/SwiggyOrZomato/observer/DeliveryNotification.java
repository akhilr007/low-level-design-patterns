package problems.SwiggyOrZomato.observer;

import problems.SwiggyOrZomato.entity.Order;

public class DeliveryNotification implements NotificationObserver{
    @Override
    public void notify(Order order) {

        System.out.println("📢 Notification to Delivery Partner: New delivery order assigned.");

    }
}