package creational;

import java.util.List;

class BurgerMeal {

    // mandatory components
    private final String bun;
    private final String patty;

    // optional components
    private final String sides;
    private final List<String> toppings;
    private final boolean cheese;

    private BurgerMeal(BurgerBuilder builder) {
       this.bun = builder.bun;
       this.patty = builder.patty;
       this.toppings = builder.toppings;
       this.cheese = builder.cheese;
       this.sides = builder.sides;
    }


    public static class BurgerBuilder {

        // mandatory components
        private final String bun;
        private final String patty;

        // optional components
        private String sides;
        private List<String> toppings;
        private boolean cheese;

        public BurgerBuilder(String bunType, String patty) {
            this.bun = bunType;
            this.patty = patty;
        }

        public BurgerBuilder cheese(boolean hasCheese) {
            this.cheese = hasCheese;
            return this;
        }

        public BurgerBuilder toppings(List<String> toppings) {
            this.toppings = toppings;
            return this;
        }

        public BurgerBuilder sides(String sides) {
            this.sides = sides;
            return this;
        }

        public BurgerMeal build() {
            return new BurgerMeal(this);
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Your burger meal is here:\n" +
            "  Bun Type: %s\n" +
            "  Patty Type: %s\n" +
            "  Extra Cheese: %s\n" +
            "  Toppings: %s\n" +
            "  Sides: %s",
            bun, patty, cheese, toppings, sides
        );
    }
}
public class Builder {
    public static void main(String[] args) {
        
        BurgerMeal plainBurger = new BurgerMeal.BurgerBuilder("wheat", "veg").build();
        System.out.println(plainBurger.toString());

        BurgerMeal fatBurger = new BurgerMeal
        .BurgerBuilder("wheat", "veg")
        .cheese(true)
        .toppings(List.of("onion", "white cottage cheese"))
        .sides("french fries")
        .build();

        System.out.println(fatBurger.toString());

    }
}
