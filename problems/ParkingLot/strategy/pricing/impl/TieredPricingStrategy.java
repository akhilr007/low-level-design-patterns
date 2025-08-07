package problems.ParkingLot.strategy.pricing.impl;

import problems.ParkingLot.entity.Ticket;
import problems.ParkingLot.entity.PricingRule;
import problems.ParkingLot.strategy.pricing.PricingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class TieredPricingStrategy implements PricingStrategy {
    @Override
    public double calculateFee(Ticket ticket, PricingRule pricingRule) {
        Duration duration = Duration.between(ticket.getEntryTime(), LocalDateTime.now());
        long hours = duration.toHours();
        if (hours < 1) hours = 1;
        // Example: first 2 hours flat, then hourly
        if (hours <= 2) {
            return pricingRule.getFlatRate();
        } else {
            return pricingRule.getFlatRate() + (hours - 2) * pricingRule.getRatePerHour();
        }
    }
}
