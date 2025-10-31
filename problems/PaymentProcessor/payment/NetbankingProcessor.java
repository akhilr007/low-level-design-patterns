package problems.PaymentProcessor.payment;

import problems.PaymentProcessor.PaymentRequest;
import problems.PaymentProcessor.PaymentResponse;
import problems.PaymentProcessor.PaymentType;

import java.util.UUID;

public class NetbankingProcessor implements PaymentProcessor{
    @Override
    public PaymentType supportedType() {
        return PaymentType.NETBANKING;
    }

    @Override
    public PaymentResponse process(PaymentRequest request) {
        String transactionID = "netbanking-"+ UUID.randomUUID();
        return new PaymentResponse(true, transactionID, "netbanking transfer initiated");
    }

    @Override
    public double getSuccessRateEstimate() {
        return 0.85;
    }

    @Override
    public String getName() {
        return "netbanking-processor";
    }
}