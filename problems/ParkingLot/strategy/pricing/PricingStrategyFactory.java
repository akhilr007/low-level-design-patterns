
package problems.ParkingLot.strategy.pricing;

import problems.ParkingLot.strategy.pricing.impl.FlatPricingStrategy;
import problems.ParkingLot.strategy.pricing.impl.HourlyPricingStrategy;
import problems.ParkingLot.strategy.pricing.impl.TieredPricingStrategy;

public class PricingStrategyFactory {
    public static PricingStrategy getStrategy(String strategyType) {
        switch (strategyType.toLowerCase()) {
            case "flat":
                return new FlatPricingStrategy();
            case "hourly":
                return new HourlyPricingStrategy();
            case "tiered":
                return new TieredPricingStrategy();
            default:
                throw new IllegalArgumentException("Unknown pricing strategy: " + strategyType);
        }
    }
}
