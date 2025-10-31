package problems.PaymentProcessor.payment;

import problems.PaymentProcessor.PaymentRequest;
import problems.PaymentProcessor.PaymentResponse;
import problems.PaymentProcessor.PaymentType;

import java.util.UUID;

public class UpiProcessor implements PaymentProcessor{

    @Override
    public PaymentType supportedType() {
        return PaymentType.UPI;
    }

    @Override
    public PaymentResponse process(PaymentRequest request) {
        String transactionID = "upi-" + UUID.randomUUID();
        return new PaymentResponse(true, transactionID, "upi payment processed");
    }

    @Override
    public double getSuccessRateEstimate() {
        return 0.98;
    }

    @Override
    public String getName() {
        return "upi-processor";
    }
}