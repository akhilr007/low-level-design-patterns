package problems.PaymentProcessor;

import java.math.BigDecimal;
import java.util.Map;

public class PaymentRequest {
    private final String userId;
    private final BigDecimal amount;
    private final PaymentType paymentType;
    private final Map<String, String> metadata;

    public PaymentRequest(String userId, BigDecimal amount, PaymentType paymentType, Map<String, String> metadata) {
        this.userId = userId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.metadata = metadata;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }
}