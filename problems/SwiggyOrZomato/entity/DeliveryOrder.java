package problems.SwiggyOrZomato.entity;

import problems.SwiggyOrZomato.factory.order.OrderFactory;
import problems.SwiggyOrZomato.observer.DeliveryNotification;
import problems.SwiggyOrZomato.observer.NotificationObserver;
import problems.SwiggyOrZomato.observer.RestaurantNotification;
import problems.SwiggyOrZomato.observer.UserNotification;
import problems.SwiggyOrZomato.strategy.DeliveryPartner.DeliveryPartnerAllocationStrategy;
import problems.SwiggyOrZomato.strategy.DeliveryPartner.NearRestaurantDeliveryPartnerStrategy;
import problems.SwiggyOrZomato.strategy.payment.UPIPaymentStrategy;

public class DeliveryOrder extends Order{

    private DeliveryPartner deliveryPartner;
    private DeliveryPartnerAllocationStrategy deliveryPartnerAllocationStrategy;

    public DeliveryOrder(User user, Restaurant restaurant, OrderType orderType) {
        this.id = ++id;
        this.user = user;
        this.restaurant = restaurant;
        this.orderType = orderType;
        this.paymentStrategy = new UPIPaymentStrategy();
        this.deliveryPartnerAllocationStrategy = new NearRestaurantDeliveryPartnerStrategy();
    }

    @Override
    public boolean makePayment() {
        paymentStrategy.pay(this);
        return true;
    }

    @Override
    public void placeOrder() {
        this.user.setOrders(OrderFactory.createOrder(user, restaurant, orderType));
        this.deliveryPartner = deliveryPartnerAllocationStrategy.assignDeliveryPartner();
        this.orderStatus = OrderStatus.ORDER_PLACED;
        NotificationManager.getInstance().addObserver(new UserNotification());
        NotificationManager.getInstance().addObserver(new DeliveryNotification());
        NotificationManager.getInstance().addObserver(new RestaurantNotification());
        NotificationManager.getInstance().notifyAll(this);
    }

}