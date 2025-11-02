package problems.RentARide.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SurgeFareStrategy implements FareStrategy{

    private final FareStrategy delegate;
    private final BigDecimal surgeMultiplier;

    public SurgeFareStrategy(FareStrategy delegate, BigDecimal surgeMultiplier) {
        this.delegate = delegate;
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public BigDecimal calculateFare(double distanceKm) {
        BigDecimal base = delegate.calculateFare(distanceKm);
        return base.multiply(surgeMultiplier).setScale(2, RoundingMode.HALF_UP);
    }
}