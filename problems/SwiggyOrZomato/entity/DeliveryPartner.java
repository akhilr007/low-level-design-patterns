package problems.SwiggyOrZomato.entity;

public class DeliveryPartner {

    private static int id = 0;
    private String name;
    private boolean isAvailable;

    public DeliveryPartner(int id, String name) {
        this.id = ++id;
        this.name = name;
        isAvailable = false;
    }

    public void setAvailable() {
        this.isAvailable = true;
    }
}