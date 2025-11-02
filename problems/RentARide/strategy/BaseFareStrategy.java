package problems.RentARide.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BaseFareStrategy implements FareStrategy{
    private final BigDecimal ratePerKm = BigDecimal.valueOf(8);

    @Override
    public BigDecimal calculateFare(double distanceKm) {
        BigDecimal d = BigDecimal.valueOf(distanceKm);
        return ratePerKm.multiply(d).setScale(2, RoundingMode.HALF_UP);
    }
}