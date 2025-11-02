package problems.RentARide;

import problems.RentARide.factory.DefaultDriverSelector;
import problems.RentARide.factory.DriverSelectorFactory;
import problems.RentARide.service.RideService;
import problems.RentARide.strategy.BaseFareStrategy;
import problems.RentARide.strategy.SurgeFareStrategy;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class RentARideDemo {
    public static void main(String[] args) {

        List<Driver> drivers = Arrays.asList(
                new Driver.Builder("A").model(CarModel.sedan).rating(4.0).distanceMeters(500).build(),
                new Driver.Builder("B").model(CarModel.hatchback).rating(4.3).distanceMeters(1000).build(),
                new Driver.Builder("C").model(CarModel.five_seater).rating(4.8).distanceMeters(200).build(),
                new Driver.Builder("D").model(CarModel.sedan).rating(4.1).distanceMeters(700).build(),
                new Driver.Builder("E").model(CarModel.hatchback).rating(4.7).distanceMeters(430).build()
        );

        RideRequest request1 = RideRequest.of(43.0, "sedan");

        RideService service = new RideService(
                (DefaultDriverSelector) DriverSelectorFactory.get(DriverSelectorFactory.SelectorType.DEFAULT_SELECTOR),
                new BaseFareStrategy());

        System.out.println("sample input 1:");
        System.out.println(service.bookRide(drivers, request1)); // expected driver A and fare 344.00

        // sample 2: different input set where driver A has rating 3 (ineligible)
        List<Driver> drivers2 = Arrays.asList(
                new Driver.Builder("A").model(CarModel.hatchback).rating(3.0).distanceMeters(500).build(),
                new Driver.Builder("B").model(CarModel.hatchback).rating(4.3).distanceMeters(1000).build(),
                new Driver.Builder("C").model(CarModel.five_seater).rating(4.8).distanceMeters(200).build(),
                new Driver.Builder("D").model(CarModel.sedan).rating(4.1).distanceMeters(700).build(),
                new Driver.Builder("E").model(CarModel.hatchback).rating(4.7).distanceMeters(430).build()
        );

        RideRequest req2 = RideRequest.of(20.5, "hatchback");
        System.out.println("\nsample input 2:");
        System.out.println(service.bookRide(drivers2, req2)); // expected driver E and fare 164.00

        // sample 3: no specific car requested -> choose closest rated >= 4
        RideRequest req3 = RideRequest.of(12.3, null);
        System.out.println("\nsample input 3 (no specific car):");
        System.out.println(service.bookRide(drivers, req3)); // expected driver C (closest 200m) and fare 98.40

        // sample 4: show surge pricing easily pluggable
        RideService surgeService = new RideService(
                (DefaultDriverSelector) DriverSelectorFactory.get(DriverSelectorFactory.SelectorType.DEFAULT_SELECTOR),
                new SurgeFareStrategy(new BaseFareStrategy(), BigDecimal.valueOf(1.25)) // 25% surge
        );
        System.out.println("\nwith surge (25%):");
        System.out.println(surgeService.bookRide(drivers, request1)); // fare 344 * 1.25 = 430.00
    }
}