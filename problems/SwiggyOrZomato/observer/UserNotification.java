package problems.SwiggyOrZomato.observer;

import problems.SwiggyOrZomato.entity.Order;

public class UserNotification implements NotificationObserver{
    @Override
    public void notify(Order order) {
        System.out.println("📢 Notification to User: Your order from " +
                order.getRestaurant().getName() + " has been placed successfully.");
    }
}