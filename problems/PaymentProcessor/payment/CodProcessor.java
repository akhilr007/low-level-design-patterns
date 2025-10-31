package problems.PaymentProcessor.payment;

import problems.PaymentProcessor.PaymentRequest;
import problems.PaymentProcessor.PaymentResponse;
import problems.PaymentProcessor.PaymentType;

import java.util.UUID;

public class CodProcessor implements PaymentProcessor{
    @Override
    public PaymentType supportedType() {
        return PaymentType.COD;
    }

    @Override
    public PaymentResponse process(PaymentRequest request) {
        String transactionID = "cod-"+ UUID.randomUUID();
        return new PaymentResponse(true, transactionID, "cod created - payment on delivery");
    }

    @Override
    public double getSuccessRateEstimate() {
        return 0.7;
    }

    @Override
    public String getName() {
        return "cod-processor";
    }
}