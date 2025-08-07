package problems.ParkingLot.strategy.impl;

import problems.ParkingLot.entity.ParkingSlot;
import problems.ParkingLot.entity.enums.VehicleType;
import problems.ParkingLot.strategy.SlotAllocationStrategy;

import java.util.List;
import java.util.Optional;

public class FirstAvailableSlotStrategy implements SlotAllocationStrategy {

    @Override
    public Optional<ParkingSlot> findSlot(List<ParkingSlot> slots, VehicleType vehicleType) {
        return slots.stream()
                .filter(slot -> slot.canFitVehicle(vehicleType))
                .findFirst();
    }
}