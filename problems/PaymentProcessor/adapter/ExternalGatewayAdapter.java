package problems.PaymentProcessor.adapter;

import problems.PaymentProcessor.PaymentRequest;
import problems.PaymentProcessor.PaymentResponse;
import problems.PaymentProcessor.PaymentType;
import problems.PaymentProcessor.payment.PaymentProcessor;

public class ExternalGatewayAdapter implements PaymentProcessor {

    private final ExternalGatewayClient client;

    public ExternalGatewayAdapter(ExternalGatewayClient client) {
        this.client = client;
    }

    @Override
    public PaymentType supportedType() {
        return PaymentType.THIRD_PARTY;
    }

    @Override
    public PaymentResponse process(PaymentRequest request) {
        String token = request.getMetadata().getOrDefault("token", "no-token");
        double amount = request.getAmount().doubleValue();
        String extTx = client.submitPayment(token, amount);
        return new PaymentResponse(true, extTx, "processed by external gateway");
    }

    @Override
    public double getSuccessRateEstimate() {
        return 0.92;
    }

    @Override
    public String getName() {
        return "external-gateway-adapter";
    }
}