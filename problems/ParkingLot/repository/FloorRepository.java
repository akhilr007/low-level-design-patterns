package problems.ParkingLot.repository;

import problems.ParkingLot.entity.Floor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FloorRepository {

    private Map<UUID, Floor> floors = new ConcurrentHashMap<>();
    private Map<Integer, UUID> floorNumberToId = new ConcurrentHashMap<>();

    public Floor save(Floor floor) {
        floors.put(floor.getId(), floor);
        floorNumberToId.put(floor.getFloorNumber(), floor.getId());
        return floor;
    }

    public Optional<Floor> findById(UUID floorId) {
        return Optional.ofNullable(floors.get(floorId));
    }

    public Optional<Floor> findByNumber(int floorNumber) {
        UUID floorId = floorNumberToId.get(floorNumber);
        return floorId != null ? Optional.ofNullable(floors.get(floorId)) : Optional.empty();
    }

    public List<Floor> findAll() {
        return new ArrayList<>(floors.values());
    }

    public boolean existsByNumber(int floorNumber) {
        return floorNumberToId.containsKey(floorNumber);
    }

    public void delete(UUID floorId) {
        Floor floor = floors.remove(floorId);
        if (floor != null) {
            floorNumberToId.remove(floor.getFloorNumber());
        }
    }

    public void clear() {
        floors.clear();
        floorNumberToId.clear();
    }
}