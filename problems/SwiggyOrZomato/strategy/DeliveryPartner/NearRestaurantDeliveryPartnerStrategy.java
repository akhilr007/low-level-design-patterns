package problems.SwiggyOrZomato.strategy.DeliveryPartner;

import problems.SwiggyOrZomato.entity.DeliveryPartner;

import java.util.List;
import java.util.Random;

public class NearRestaurantDeliveryPartnerStrategy implements DeliveryPartnerAllocationStrategy {

    @Override
    public DeliveryPartner assignDeliveryPartner() {
        List<DeliveryPartner> partners = List.of(
                new DeliveryPartner(1, "Ankur"),
                new DeliveryPartner(2, "Manish")
        );
        Random random = new Random();
        int randomIndex = random.nextInt(partners.size()); // picks 0 or 1

        return partners.get(randomIndex);
    }
}