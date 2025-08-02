package behavorial;

interface RideMatchingStrategy {
    void match(String riderLocation);
}

class NearestDriverStrategy implements RideMatchingStrategy {

    @Override
    public void match(String riderLocation) {
        System.out.println("Matching with the nearest available driver to " + riderLocation);
    }
}

class AirportQueueStrategy implements RideMatchingStrategy {

    @Override
    public void match(String riderLocation) {
        System.out.println("Matching using FIFO airport queue for " + riderLocation);
    }
}

class SurgePriorityStrategy implements RideMatchingStrategy {

    @Override
    public void match(String riderLocation) {
        System.out.println("Matching rider using surge pricing priority near " + riderLocation);
    }
}

class RideMatchingService {

    private RideMatchingStrategy strategy;

    public RideMatchingService(RideMatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RideMatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void matchRider(String location) {
        strategy.match(location);
    }
}

public class Strategy {
    public static void main(String[] args) {
        
        // Using airport queue strategy
        RideMatchingService rideMatchingService = new RideMatchingService(new AirportQueueStrategy());
        rideMatchingService.matchRider("Terminal 1");

        // Using nearest driver strategy and later switching to surge priority
        RideMatchingService rideMatchingService2 = new RideMatchingService(new NearestDriverStrategy());
        rideMatchingService2.matchRider("Downtown");
        rideMatchingService2.setStrategy(new SurgePriorityStrategy());
        rideMatchingService2.matchRider("Downtown");
    }
}
