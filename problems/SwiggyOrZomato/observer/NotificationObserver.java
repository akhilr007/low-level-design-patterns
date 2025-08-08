package problems.SwiggyOrZomato.observer;


import problems.SwiggyOrZomato.entity.Order;

public interface NotificationObserver {
    void notify(Order order);
}