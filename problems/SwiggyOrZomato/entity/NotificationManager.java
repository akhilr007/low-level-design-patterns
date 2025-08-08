package problems.SwiggyOrZomato.entity;

import problems.SwiggyOrZomato.observer.NotificationObserver;

import java.util.ArrayList;
import java.util.List;

// Singleton
public class NotificationManager {
    private List<NotificationObserver> observers = new ArrayList<>();
    private static NotificationManager instance = null;

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void notifyAll(Order order) {
        for (NotificationObserver observer : observers) {
            observer.notify(order);
        }
    }

    public static NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }
}