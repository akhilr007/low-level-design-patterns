package problems.SwiggyOrZomato.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private static int id = 0;
    private String name;
    private String address;
    private Cart cart;
    private List<Order> orders = new ArrayList<>();

    public User(String name, String address) {
        this.id = ++id;
        this.name = name;
        this.address = address;
        this.cart = new Cart();
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Cart getCart() {
        return cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(Order order) {
        orders.add(order);
    }
}