package problems.ParkingLot.service;

import problems.ParkingLot.entity.Vehicle;
import problems.ParkingLot.repository.VehicleRepository;

public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle Save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return vehicle;
    }
}