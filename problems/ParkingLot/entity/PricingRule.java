package problems.ParkingLot.entity;

import problems.ParkingLot.entity.enums.VehicleType;

import java.util.UUID;


public class PricingRule {
    private UUID id;
    private VehicleType vehicleType;
    private double ratePerHour;
    private double flatRate;
    private String strategyType; // e.g., "flat", "hourly", "tiered"


    public PricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {
        this(vehicleType, ratePerHour, flatRate, "flat");
    }

    public PricingRule(VehicleType vehicleType, double ratePerHour, double flatRate, String strategyType) {
        this.id = UUID.randomUUID();
        this.vehicleType = vehicleType;
        this.ratePerHour = ratePerHour;
        this.flatRate = flatRate;
        this.strategyType = strategyType;
    }


    public UUID getId() {
        return id;
    }


    public VehicleType getVehicleType() {
        return vehicleType;
    }


    public double getRatePerHour() {
        return ratePerHour;
    }


    public double getFlatRate() {
        return flatRate;
    }

    public String getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(String strategyType) {
        this.strategyType = strategyType;
    }

    @Override
    public String toString() {
        return "PricingRule{" +
                "id=" + id +
                ", vehicleType=" + vehicleType +
                ", ratePerHour=" + ratePerHour +
                ", flatRate=" + flatRate +
                '}';
    }


    public void updateRates(double ratePerHour, double flatRate) {
        this.ratePerHour = ratePerHour;
        this.flatRate = flatRate;
    }

    public void updateRates(double ratePerHour, double flatRate, String strategyType) {
        this.ratePerHour = ratePerHour;
        this.flatRate = flatRate;
        this.strategyType = strategyType;
    }

    public void updateFlatRate(double flatRate) {
        this.flatRate = flatRate;
    }

    public void updateHourlyRate(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }
}