package problems.RestaurantCatalog;

import java.util.ArrayList;
import java.util.List;

public class AddOnGroup {
    private String name;
    private List<AddOn> addOns = new ArrayList<>();

    public AddOnGroup(String name) {
        this.name = name;
    }

    public void addAddOn(AddOn addOn) {
        addOns.add(addOn);
    }

    public List<AddOn> getAddOns() {
        return addOns;
    }

    public void showDetails() {
        System.out.println("    AddOnGroup: " + name);
        for (AddOn a : addOns) {
            System.out.println("      " + a.getName());
        }
    }

}