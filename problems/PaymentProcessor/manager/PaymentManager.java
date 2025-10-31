package problems.PaymentProcessor.manager;

import problems.PaymentProcessor.PaymentRequest;
import problems.PaymentProcessor.PaymentResponse;
import problems.PaymentProcessor.payment.PaymentProcessor;
import problems.PaymentProcessor.strategy.SortingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PaymentManager {

    private final List<PaymentProcessor> processors = new ArrayList<>();
    private SortingStrategy sortingStrategy;

    public PaymentManager(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void register(PaymentProcessor processor) {
        processors.add(processor);
    }

    public void unregister(PaymentProcessor processor) {
        processors.remove(processor);
    }

    public List<PaymentProcessor> getAvailableProcessors() {
        return Collections.unmodifiableList(processors);
    }

    public void setSortingStrategy(SortingStrategy strategy) {
        this.sortingStrategy = strategy;
    }

    public List<PaymentProcessor> sortedProcessors() {
        List<PaymentProcessor> copy = new ArrayList<>(processors);
        sortingStrategy.sort(copy);
        return copy;
    }

    public Optional<PaymentResponse> tryProcess(PaymentRequest request) {
        for (PaymentProcessor p : sortedProcessors()) {
            if (p.supportedType() != request.getPaymentType()) continue;
            try {
                PaymentResponse response = p.process(request);
                if (response.isSuccess()) return Optional.of(response);
            } catch (Exception e) {
                System.err.println("processor " + p.getName() + " failed with: " + e.getMessage());
            }
        }
        return Optional.empty();
    }
}