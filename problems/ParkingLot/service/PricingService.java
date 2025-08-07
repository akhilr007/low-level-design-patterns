package problems.ParkingLot.service;

import problems.ParkingLot.entity.PricingRule;
import problems.ParkingLot.entity.Ticket;
import problems.ParkingLot.entity.Vehicle;
import problems.ParkingLot.entity.enums.VehicleType;
import problems.ParkingLot.repository.PricingRuleRepository;
import problems.ParkingLot.repository.VehicleRepository;

import java.util.Optional;

public class PricingService {

    private PricingRuleRepository pricingRuleRepository;
    private VehicleRepository vehicleRepository;

    public PricingService(PricingRuleRepository pricingRuleRepository, VehicleRepository vehicleRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public double calculateFee(Ticket ticket) {

        System.out.println("[SERVICE] Calculating fee for ticket: " + ticket.getId());

        Optional<Vehicle> vehicle = vehicleRepository.getVehicle(ticket.getVehicleId());
        if (vehicle.isEmpty()) {
            System.out.println(
                    "[SERVICE] Vehicle not found with for ticketId: " + ticket.getId());
        }

        VehicleType vehicleType = vehicle.get().getVehicleType();

        Optional<PricingRule> rule = pricingRuleRepository.findByVehicleType(vehicleType);
        if (rule.isEmpty()) {
            throw new IllegalStateException("No pricing rule found for vehicle type: " + vehicleType);
        }

        PricingRule pricingRule = rule.get();
        // calculate both flat and hourly fee
        double flatFee = pricingRule.getFlatRate();
        double hourlyFee = calculateHourlyFee(ticket, pricingRule.getRatePerHour());

        // Return the minimum of flat and hourly pricing
        double finalFee = Math.min(flatFee, hourlyFee);

        System.out.println("[SERVICE] Flat fee: " + flatFee + ", Hourly fee: " + hourlyFee + ", Final fee: " + finalFee + " for vehicle type: " + vehicleType);

        return finalFee;
    }

    private double calculateHourlyFee(Ticket ticket, double ratePerHour) {
        java.time.Duration duration = java.time.Duration.between(ticket.getEntryTime(), java.time.LocalDateTime.now());
        long hours = duration.toHours();

        // Minimum 1 hour charge
        if (hours < 1) {
            hours = 1;
        }

        return hours * ratePerHour;
    }

    public void addPricingRule(PricingRule rule) {
        pricingRuleRepository.save(rule);
    }

    public void updatePricingRule(PricingRule rule) {
        pricingRuleRepository.update(rule);
    }

}