package problems.ParkingLot.repository;

import problems.ParkingLot.entity.ParkingSlot;
import problems.ParkingLot.entity.enums.VehicleType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SlotRepository {

    private Map<UUID, ParkingSlot> slots;

    public SlotRepository() {
        this.slots = new ConcurrentHashMap<>();
    }

    public ParkingSlot save(ParkingSlot slot) {
        slots.put(slot.getId(), slot);
        return slot;
    }

    public Optional<ParkingSlot> findById(UUID slotId) {
        return Optional.ofNullable(slots.get(slotId));
    }

    public List<ParkingSlot> findAvailableSlots(VehicleType vehicleType) {
        return slots.values()
                .stream()
                .filter(slot -> slot.canFitVehicle(vehicleType) && !slot.isOccupied())
                .collect(Collectors.toList());
    }


    public void releaseSlot(UUID slotId) {
        slots.computeIfPresent(slotId, (id, slot) -> {
            slot.setIsOccupied(false);
            return slot;
        });
    }

    public Map<VehicleType, Long> getSlotStatistics() {
        return slots.values()
                .stream()
                .collect(Collectors.groupingBy(
                        ParkingSlot::getSlotType,
                        Collectors.counting()
                ));
    }

    public void clear() {
        slots.clear();
    }
}