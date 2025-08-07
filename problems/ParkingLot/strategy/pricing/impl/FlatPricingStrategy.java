package problems.ParkingLot.strategy.pricing.impl;

import problems.ParkingLot.entity.Ticket;
import problems.ParkingLot.entity.PricingRule;
import problems.ParkingLot.strategy.pricing.PricingStrategy;

public class FlatPricingStrategy implements PricingStrategy {
    @Override
    public double calculateFee(Ticket ticket, PricingRule pricingRule) {
        return pricingRule.getFlatRate();
    }
}
