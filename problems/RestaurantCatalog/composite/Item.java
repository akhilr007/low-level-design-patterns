package problems.RestaurantCatalog.composite;

import problems.RestaurantCatalog.AddOnGroup;
import problems.RestaurantCatalog.VariantGroup;

import java.util.ArrayList;
import java.util.List;

public class Item implements CatalogComponent{
    private final String name;
    private final List<VariantGroup> variantGroups = new ArrayList<>();
    private final List<AddOnGroup> addOnGroups = new ArrayList<>();

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<VariantGroup> getVariantGroups() {
        return variantGroups;
    }

    public List<AddOnGroup> getAddOnGroups() {
        return addOnGroups;
    }

    public void addVariantGroup(VariantGroup vg) {
        this.variantGroups.add(vg);
    }

    public void addAddOnGroup(AddOnGroup ag) {
        this.addOnGroups.add(ag);
    }

    @Override
    public void showDetails() {
        System.out.println("  Item:  " + name);
        for (var vg : variantGroups) vg.showDetails();
        for (var ag : addOnGroups) ag.showDetails();
    }
}