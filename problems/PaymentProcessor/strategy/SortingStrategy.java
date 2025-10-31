package problems.PaymentProcessor.strategy;

import problems.PaymentProcessor.payment.PaymentProcessor;

import java.util.List;

public interface SortingStrategy {
    void sort(List<PaymentProcessor> processors);
}