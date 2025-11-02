package problems.RentARide.factory;

import problems.RentARide.Driver;
import problems.RentARide.RideRequest;

import java.util.List;
import java.util.Optional;

public interface DriverSelector {
    Optional<Driver> selectDriver(List<Driver> availableDrivers, RideRequest request);
}