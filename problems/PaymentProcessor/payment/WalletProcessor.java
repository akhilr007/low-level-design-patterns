package problems.PaymentProcessor.payment;

import problems.PaymentProcessor.PaymentRequest;
import problems.PaymentProcessor.PaymentResponse;
import problems.PaymentProcessor.PaymentType;

import java.util.UUID;

public class WalletProcessor implements PaymentProcessor{
    @Override
    public PaymentType supportedType() {
        return PaymentType.WALLET;
    }

    @Override
    public PaymentResponse process(PaymentRequest request) {
        String transactionID = "wallet-"+ UUID.randomUUID();
        return new PaymentResponse(true, transactionID, "wallet debited");
    }

    @Override
    public double getSuccessRateEstimate() {
        return 0.9;
    }

    @Override
    public String getName() {
        return "wallet-processor";
    }
}