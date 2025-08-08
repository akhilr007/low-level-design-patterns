package problems.SwiggyOrZomato.strategy.payment;

import problems.SwiggyOrZomato.entity.Order;

public class CashPaymentStrategy implements PaymentStrategy{

    @Override
    public void pay(Order order) {
        System.out.println("Total amount: " + order.getUser().getCart().getTotalCost() + " paid using card.");
    }
}