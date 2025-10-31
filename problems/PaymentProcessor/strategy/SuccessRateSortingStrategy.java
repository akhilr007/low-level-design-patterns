package problems.PaymentProcessor.strategy;

import problems.PaymentProcessor.payment.PaymentProcessor;

import java.util.Comparator;
import java.util.List;

public class SuccessRateSortingStrategy implements SortingStrategy{

    @Override
    public void sort(List<PaymentProcessor> processors) {
        processors.sort(Comparator.comparingDouble(PaymentProcessor::getSuccessRateEstimate).reversed());
    }
}