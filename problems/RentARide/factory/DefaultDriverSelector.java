package problems.RentARide.factory;

import problems.RentARide.CarModel;
import problems.RentARide.Driver;
import problems.RentARide.RideRequest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultDriverSelector implements DriverSelector{
    private static final double MIN_RATING = 4.0;

    @Override
    public Optional<Driver> selectDriver(List<Driver> availableDrivers, RideRequest request) {
        if (availableDrivers == null || availableDrivers.isEmpty()) {
            return Optional.empty();
        }

        List<Driver> eligible = availableDrivers.stream()
                .filter(d -> d.getRating() >= MIN_RATING)
                .toList();

        if (eligible.isEmpty()) return Optional.empty();

        if (request.getRequestedModel().isPresent()) {
            CarModel wanted = request.getRequestedModel().get();
            List<Driver> sameModelDriver = eligible.stream()
                    .filter(d -> d.getCarModel() == wanted)
                    .sorted(Comparator.comparingInt(Driver::getDistanceMeters))
                    .toList();
            if (!sameModelDriver.isEmpty()) {
                return Optional.of(sameModelDriver.getFirst());
            }
            else {
                return Optional.empty();
            }
        }
        else {
            // no specific model requested -> take closest among eligible
            return eligible.stream()
                    .min(Comparator.comparingInt(Driver::getDistanceMeters));
        }
    }
}