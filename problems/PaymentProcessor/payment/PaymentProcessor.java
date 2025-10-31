package problems.PaymentProcessor.payment;

import problems.PaymentProcessor.PaymentRequest;
import problems.PaymentProcessor.PaymentResponse;
import problems.PaymentProcessor.PaymentType;

public interface PaymentProcessor {

    PaymentType supportedType();
    PaymentResponse process(PaymentRequest request);
    double getSuccessRateEstimate();
    String getName();
}