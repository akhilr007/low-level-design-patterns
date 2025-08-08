package problems.SwiggyOrZomato.entity;

import java.util.List;

public class Restaurant {
    private static int id = 0;
    private String name;
    private String address;
    private List<MenuItem> menuItems;

    public Restaurant(String name, String address, List<MenuItem> items) {
        this.id = ++id;
        this.name = name;
        this.address = address;
        this.menuItems = items;
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

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}