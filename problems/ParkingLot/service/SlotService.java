package problems.ParkingLot.service;

import problems.ParkingLot.entity.ParkingSlot;
import problems.ParkingLot.entity.enums.VehicleType;
import problems.ParkingLot.repository.SlotRepository;
import problems.ParkingLot.strategy.SlotAllocationStrategy;
import problems.ParkingLot.strategy.impl.FirstAvailableSlotStrategy;

import java.util.Optional;
import java.util.UUID;

public class SlotService {
    private SlotRepository slotRepository;
    private SlotAllocationStrategy slotAllocationStrategy;

    public SlotService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
        this.slotAllocationStrategy = new FirstAvailableSlotStrategy();
    }

    public void setSlotAllocationStrategy(SlotAllocationStrategy slotAllocationStrategy) {
        this.slotAllocationStrategy = slotAllocationStrategy;
    }

    public Optional<ParkingSlot> allocateSlot(VehicleType vehicleType) {

        System.out.println("[SERVICE] Allocating slot for vehicle type: " + vehicleType);

        Optional<ParkingSlot> slot = slotAllocationStrategy.findSlot(
                slotRepository.findAvailableSlots(vehicleType), vehicleType);

        if (slot.isPresent()) {
            System.out.println("[SERVICE] Slot allocated successfully: " + slot.get().getId());
        }
        else{
            System.out.println("[SERVICE] No available slots for vehicle type: " + vehicleType.name());

        }
        return slot;
    }

    public void releaseSlot(UUID slotId) {
        System.out.println("[SERVICE] Releasing slot: " + slotId);
        slotRepository.releaseSlot(slotId);
        System.out.println("[SERVICE] Slot released successfully: " + slotId);
    }

    public ParkingSlot createSlot(VehicleType vehicleType, int floorNumber, int slotNumber) {
        ParkingSlot slot = new ParkingSlot(vehicleType, floorNumber, slotNumber);
        slotRepository.save(slot);
        return slot;
    }

    public long getAvailableSlotCount(VehicleType vehicleType) {
        return slotRepository.findAvailableSlots(vehicleType).size();
    }
}