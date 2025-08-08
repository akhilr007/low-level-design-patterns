package problems.SwiggyOrZomato.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static int id = 0;
    private List<MenuItem> menuItems;

    public Cart() {
        this.menuItems = new ArrayList<>();
    }

    public void addToCart(MenuItem item) {
        menuItems.add(item);
    }

    public void removeFromCart(MenuItem item) {
        menuItems.remove(item);
    }

    public double getTotalCost() {
        double total = 0;
        for (var item : menuItems) {
            total += item.getPrice();
        }
        return total;
    }

    public boolean isEmpty() {
        return menuItems.isEmpty();
    }
}