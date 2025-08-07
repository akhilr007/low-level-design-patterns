package problems.ParkingLot.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Floor {

    private UUID id;
    private int floorNumber;
    private List<ParkingSlot> slots;

    public Floor(int floorNumber) {
        this.id = UUID.randomUUID();
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
    }

    public void addSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    public UUID getId() {
        return id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", floorNumber=" + floorNumber +
                ", slots=" + slots +
                '}';
    }

    public int getTotalSlots() {
        return slots.size();
    }
}