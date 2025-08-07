package problems.ParkingLot.strategy;

import problems.ParkingLot.entity.ParkingSlot;
import problems.ParkingLot.entity.enums.VehicleType;

import java.util.List;
import java.util.Optional;

public interface SlotAllocationStrategy {
    Optional<ParkingSlot> findSlot(List<ParkingSlot> slots, VehicleType vehicleType);
}