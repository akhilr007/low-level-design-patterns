package problems.SwiggyOrZomato.entity;

public class MenuItem {
    private int id;
    private String code;
    private String name;
    private double price;

    public MenuItem(int id, String code, String name, double price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;

    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}