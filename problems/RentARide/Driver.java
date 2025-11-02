package problems.RentARide;

public class Driver {
    private final String id;
    private final CarModel carModel;
    private final double rating;
    private final int distanceMeters; // distance from customer in metres

    public Driver(Builder b) {
        this.id = b.id;
        this.carModel = b.model;
        this.rating = b.rating;
        this.distanceMeters = b.distanceMeters;
    }

    public String getId() {
        return id;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public double getRating() {
        return rating;
    }

    public int getDistanceMeters() {
        return distanceMeters;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", carModel=" + carModel +
                ", rating=" + rating +
                ", distanceMeters=" + distanceMeters +
                '}';
    }

    public static class Builder {
        private final String id;
        private CarModel model = CarModel.unknown;
        private double rating = 0.0;
        private int distanceMeters = Integer.MAX_VALUE;

        public Builder(String id) {
            this.id = id;
        }

        public Builder model(CarModel model) {
            this.model = model;
            return this;
        }

        public Builder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder distanceMeters(int distance) {
            this.distanceMeters = distance;
            return this;
        }

        public Driver build() {
            return new Driver(this);
        }
    }
}