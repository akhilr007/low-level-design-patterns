package problems.PaymentProcessor.adapter;

import java.util.UUID;

public class ExternalGatewayClient {
    public String submitPayment(String token, double amount) {
        return "ext-" + UUID.randomUUID();
    }
}