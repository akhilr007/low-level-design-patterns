package problems.RentARide;

import java.util.Optional;

public class RideRequest {
    private final double distanceKm;
    private final Optional<CarModel> requestedModel;

    public RideRequest(double distanceKm, Optional<CarModel> requestedModel) {
        if (distanceKm < 0) {
            throw new IllegalArgumentException("distance must be >= 0");
        }
        this.distanceKm = distanceKm;
        this.requestedModel = requestedModel;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public Optional<CarModel> getRequestedModel() {
        return requestedModel;
    }

    public static RideRequest of (double distanceKm, String requestedModelString) {
        CarModel model = requestedModelString == null ? null : CarModel.from(requestedModelString);
        return new RideRequest(
                distanceKm,
                Optional.ofNullable(model == CarModel.unknown ? null : model));
    }
}