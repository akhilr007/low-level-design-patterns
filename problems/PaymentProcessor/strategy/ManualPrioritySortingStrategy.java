package problems.PaymentProcessor.strategy;

import problems.PaymentProcessor.PaymentType;
import problems.PaymentProcessor.payment.PaymentProcessor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ManualPrioritySortingStrategy implements SortingStrategy {

    private final Map<PaymentType, Integer> priorityMap;

    public ManualPrioritySortingStrategy(Map<PaymentType, Integer> priorityMap) {
        this.priorityMap = priorityMap;
    }

    @Override
    public void sort(List<PaymentProcessor> processors) {
        processors.sort(Comparator.comparingInt(
                p -> -priorityMap.getOrDefault(p.supportedType(), 0)
        ));
    }
}