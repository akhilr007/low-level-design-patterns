package problems.PaymentProcessor.payment;

import problems.PaymentProcessor.PaymentRequest;
import problems.PaymentProcessor.PaymentResponse;
import problems.PaymentProcessor.PaymentType;

import java.util.UUID;

public class CardProcessor implements PaymentProcessor{
    @Override
    public PaymentType supportedType() {
        return PaymentType.CARD;
    }

    @Override
    public PaymentResponse process(PaymentRequest request) {
        String transactionID = "card-"+ UUID.randomUUID();
        return new PaymentResponse(true, transactionID, "card charged successfully");
    }

    @Override
    public double getSuccessRateEstimate() {
        return 0.95;
    }

    @Override
    public String getName() {
        return "card-processor";
    }
}