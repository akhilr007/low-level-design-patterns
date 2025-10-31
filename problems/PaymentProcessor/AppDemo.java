package problems.PaymentProcessor;

import problems.PaymentProcessor.adapter.ExternalGatewayAdapter;
import problems.PaymentProcessor.adapter.ExternalGatewayClient;
import problems.PaymentProcessor.decorator.FlatDiscountOffer;
import problems.PaymentProcessor.factory.PaymentProcessFactory;
import problems.PaymentProcessor.manager.PaymentManager;
import problems.PaymentProcessor.payment.PaymentProcessor;
import problems.PaymentProcessor.strategy.ManualPrioritySortingStrategy;
import problems.PaymentProcessor.strategy.SuccessRateSortingStrategy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppDemo {
    public static void main(String[] args) {

        PaymentManager manager = new PaymentManager(new SuccessRateSortingStrategy());

        PaymentProcessFactory.create(PaymentType.UPI).ifPresent(manager::register);
        PaymentProcessFactory.create(PaymentType.NETBANKING).ifPresent(manager::register);
        PaymentProcessFactory.create(PaymentType.WALLET).ifPresent(manager::register);
        PaymentProcessFactory.create(PaymentType.CARD).ifPresent(manager::register);
        PaymentProcessFactory.create(PaymentType.COD).ifPresent(manager::register);

        // example: integrate third-party via adapter
        ExternalGatewayClient sdk = new ExternalGatewayClient();
        ExternalGatewayAdapter adapter = new ExternalGatewayAdapter(sdk);
        manager.register(adapter);

        List<PaymentProcessor> processors = manager.getAvailableProcessors();
        for (PaymentProcessor p : processors) {
            if (p.supportedType() == PaymentType.UPI) {
                manager.unregister(p);
                manager.register(new FlatDiscountOffer(p, BigDecimal.TEN));
                break;
            }
        }

        System.out.println("sorted by success rate:");
        manager.sortedProcessors().forEach(p ->
                System.out.println(p.getName() + " : " + p.getSuccessRateEstimate()));

//        // switch to manual priority: put upi top, then card, others lower
//        Map<PaymentType, Integer> manualPriority = new HashMap<>();
//        manualPriority.put(PaymentType.UPI, 10);
//        manualPriority.put(PaymentType.CARD, 8);
//        manualPriority.put(PaymentType.WALLET, 6);
//        manualPriority.put(PaymentType.NETBANKING, 4);
//        manualPriority.put(PaymentType.COD, 1);
//        manualPriority.put(PaymentType.THIRD_PARTY, 5);
//        manager.setSortingStrategy(new ManualPrioritySortingStrategy(manualPriority));
//
//        System.out.println("\nsorted by manual priority:");
//        manager.sortedProcessors().forEach(p -> System.out.println(p.getName() + " : " + p.supportedType()));

        Map<String, String> metadata = new HashMap<>();
        metadata.put("upiId", "ram@upi");
        PaymentRequest request = new PaymentRequest(
                "user-1",
                BigDecimal.valueOf(250),
                PaymentType.UPI,
                metadata);

        manager.tryProcess(request).ifPresentOrElse(
                response -> System.out.println("\n payment success: " + response),
                () -> System.out.println("\n payment failed for all processors")
        );
    }
}