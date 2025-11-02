package problems.RentARide.strategy;

import java.math.BigDecimal;

public interface FareStrategy {
    BigDecimal calculateFare(double distanceKm);
}