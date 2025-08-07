package problems.ParkingLot.repository;

import problems.ParkingLot.entity.Vehicle;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleRepository {

    Map<UUID, Vehicle> vehicles = new ConcurrentHashMap<>();

    public Vehicle save(Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle);
        return vehicle;
    }

    public Optional<Vehicle> getVehicle(UUID vehicleId) {
        return Optional.ofNullable(vehicles.get(vehicleId));
    }
}