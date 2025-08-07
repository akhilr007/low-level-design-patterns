package problems.ParkingLot.entity;

import problems.ParkingLot.entity.enums.VehicleType;

import java.util.UUID;

public class PricingRule {
    private UUID id;
    private VehicleType vehicleType;
    private double ratePerHour;
    private double flatRate;

    public PricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {
        this.id = UUID.randomUUID();
        this.vehicleType = vehicleType;
        this.ratePerHour = ratePerHour;
        this.flatRate = flatRate;
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

    public void updateFlatRate(double flatRate) {
        this.flatRate = flatRate;
    }

    public void updateHourlyRate(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }
}