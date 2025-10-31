package problems.PaymentProcessor.decorator;

import problems.PaymentProcessor.PaymentRequest;
import problems.PaymentProcessor.PaymentResponse;
import problems.PaymentProcessor.PaymentType;
import problems.PaymentProcessor.payment.PaymentProcessor;

public abstract class OfferDecorator implements PaymentProcessor {

    protected final PaymentProcessor processor;

    public OfferDecorator(PaymentProcessor processor) {
        this.processor = processor;
    }

    @Override
    public PaymentType supportedType() {
        return processor.supportedType();
    }

    @Override
    public PaymentResponse process(PaymentRequest request) {
        return processor.process(request);
    }

    @Override
    public double getSuccessRateEstimate() {
        return processor.getSuccessRateEstimate();
    }

    @Override
    public String getName() {
        return processor.getName();
    }
}