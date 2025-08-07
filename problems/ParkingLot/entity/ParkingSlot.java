package problems.ParkingLot.entity;

import problems.ParkingLot.entity.enums.VehicleType;

import java.util.UUID;

public class ParkingSlot {

    private UUID id;
    private VehicleType slotType;
    private boolean isOccupied;
    private int floorNumber;
    private int slotNumber;

    public ParkingSlot(VehicleType slotType, int floorNumber, int slotNumber) {
        this.id = UUID.randomUUID();
        this.slotType = slotType;
        this.isOccupied = false;
        this.floorNumber = floorNumber;
        this.slotNumber = slotNumber;
    }

    public UUID getId() {
        return id;
    }

    public VehicleType getSlotType() {
        return slotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id=" + id +
                ", slotType=" + slotType +
                ", isOccupied=" + isOccupied +
                ", floorNumber=" + floorNumber +
                '}';
    }

    public boolean canFitVehicle(VehicleType vehicleType) {
        return !isOccupied && this.slotType == vehicleType;
    }
}