package problems.SwiggyOrZomato.observer;

import problems.SwiggyOrZomato.entity.Order;

public class RestaurantNotification implements NotificationObserver{
    @Override
    public void notify(Order order) {
        System.out.println("📢 Notification to Restaurant: New order received.");
    }
}