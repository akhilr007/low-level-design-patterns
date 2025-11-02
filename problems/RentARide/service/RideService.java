package problems.RentARide.service;

import problems.RentARide.Driver;
import problems.RentARide.RideRequest;
import problems.RentARide.factory.DefaultDriverSelector;
import problems.RentARide.strategy.FareStrategy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class RideService {
    private final DefaultDriverSelector selector;
    private final FareStrategy fareStrategy;

    public RideService(DefaultDriverSelector selector, FareStrategy strategy) {
        this.selector = selector;
        this.fareStrategy = strategy;
    }

    public Result bookRide(List<Driver> drivers, RideRequest request) {
        Optional<Driver> chosen = selector.selectDriver(drivers, request);
        if (chosen.isEmpty()) {
            return Result.noDriverFound();
        }

        Driver d = chosen.get();
        BigDecimal fare = fareStrategy.calculateFare(request.getDistanceKm());
        return Result.success(d, fare);
    }

    public static class Result {
        private final boolean success;
        private final Driver driver;
        private final BigDecimal fare;
        private final String message;

        private Result(boolean success, Driver driver, BigDecimal fare, String message) {
            this.success = success;
            this.driver = driver;
            this.fare = fare;
            this.message = message;
        }

        public static Result success(Driver d, BigDecimal fare) {
            return new Result(true, d, fare, "Driver assigned");
        }

        public static Result noDriverFound() {
            return new Result(
                    false,
                    null,
                    BigDecimal.ZERO,
                    "no matching driver found; ask user to pick another car");
        }

        @Override
        public String toString() {
            if (!success) return message;
            return String.format("driver %s will get you to the destination. your charge will be rs %s",
                    driver.getId(), fare.toPlainString());
        }
    }
}