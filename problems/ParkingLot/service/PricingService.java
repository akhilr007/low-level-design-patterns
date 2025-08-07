package problems.ParkingLot.service;

import problems.ParkingLot.entity.PricingRule;
import problems.ParkingLot.entity.Ticket;
import problems.ParkingLot.entity.Vehicle;
import problems.ParkingLot.entity.enums.VehicleType;

import problems.ParkingLot.repository.PricingRuleRepository;
import problems.ParkingLot.repository.VehicleRepository;
import problems.ParkingLot.strategy.pricing.PricingStrategy;
import problems.ParkingLot.strategy.pricing.PricingStrategyFactory;

import java.util.Optional;

public class PricingService {

    private PricingRuleRepository pricingRuleRepository;
    private VehicleRepository vehicleRepository;

    // Default strategyType if not set in PricingRule
    private String defaultStrategyType = "flat";

    public PricingService(PricingRuleRepository pricingRuleRepository, VehicleRepository vehicleRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public void setDefaultStrategyType(String strategyType) {
        this.defaultStrategyType = strategyType;
    }

    public double calculateFee(Ticket ticket) {
        System.out.println("[SERVICE] Calculating fee for ticket: " + ticket.getId());

        Optional<Vehicle> vehicle = vehicleRepository.getVehicle(ticket.getVehicleId());
        if (vehicle.isEmpty()) {
            System.out.println("[SERVICE] Vehicle not found with for ticketId: " + ticket.getId());
            throw new IllegalStateException("Vehicle not found for ticketId: " + ticket.getId());
        }

        VehicleType vehicleType = vehicle.get().getVehicleType();
        Optional<PricingRule> rule = pricingRuleRepository.findByVehicleType(vehicleType);
        if (rule.isEmpty()) {
            throw new IllegalStateException("No pricing rule found for vehicle type: " + vehicleType);
        }

        PricingRule pricingRule = rule.get();
        String strategyType = pricingRule.getStrategyType() != null ? pricingRule.getStrategyType() : defaultStrategyType;
        PricingStrategy strategy = PricingStrategyFactory.getStrategy(strategyType);
        double fee = strategy.calculateFee(ticket, pricingRule);
        System.out.println("[SERVICE] Fee: " + fee + " for vehicle type: " + vehicleType + " using strategy: " + strategy.getClass().getSimpleName());
        return fee;
    }

    // ...existing code...

    public void addPricingRule(PricingRule rule) {
        pricingRuleRepository.save(rule);
    }

    public void updatePricingRule(PricingRule rule) {
        pricingRuleRepository.update(rule);
    }

}