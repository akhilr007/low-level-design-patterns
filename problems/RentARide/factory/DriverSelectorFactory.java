package problems.RentARide.factory;

public class DriverSelectorFactory {
    public enum SelectorType {
        DEFAULT_SELECTOR,
    }

    public static DriverSelector get (SelectorType type) {
        switch (type) {
            case DEFAULT_SELECTOR:
            default:
                return new DefaultDriverSelector();
        }
    }
}