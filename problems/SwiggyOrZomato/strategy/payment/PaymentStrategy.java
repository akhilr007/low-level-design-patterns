package problems.SwiggyOrZomato.strategy.payment;

import problems.SwiggyOrZomato.entity.Order;

public interface PaymentStrategy {
    void pay(Order order);
}