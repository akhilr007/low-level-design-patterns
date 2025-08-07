package problems.ParkingLot.strategy.impl;

import problems.ParkingLot.entity.ParkingSlot;
import problems.ParkingLot.entity.enums.VehicleType;
import problems.ParkingLot.strategy.SlotAllocationStrategy;

import java.util.*;

public class NearestSlotStrategy implements SlotAllocationStrategy {

    @Override
    public Optional<ParkingSlot> findSlot(List<ParkingSlot> slots, VehicleType vehicleType) {
        return slots.stream()
                .filter(slot -> slot.canFitVehicle(vehicleType))
                .min(Comparator.comparingInt(ParkingSlot::getFloorNumber)
                        .thenComparing(ParkingSlot::getSlotNumber));
    }
}