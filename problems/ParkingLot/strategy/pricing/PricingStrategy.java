package problems.ParkingLot.strategy.pricing;

import problems.ParkingLot.entity.Ticket;
import problems.ParkingLot.entity.PricingRule;

public interface PricingStrategy {
    double calculateFee(Ticket ticket, PricingRule pricingRule);
}
