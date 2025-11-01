package problems.RestaurantCatalog;

import java.util.ArrayList;
import java.util.List;

public class VariantGroup {
    private String name;
    private List<Variant> variants = new ArrayList<>();

    public VariantGroup(String name) {
        this.name = name;
    }

    public void addVariant(Variant variant) {
        variants.add(variant);
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void showDetails() {
        System.out.println("    VariantGroup: " + name);
        for (Variant v : variants) {
            System.out.println("      " + v.getName() + " ₹" + v.getBasePrice());
        }
    }
}