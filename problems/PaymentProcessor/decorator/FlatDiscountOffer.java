package problems.PaymentProcessor.decorator;

import problems.PaymentProcessor.PaymentRequest;
import problems.PaymentProcessor.PaymentResponse;
import problems.PaymentProcessor.payment.PaymentProcessor;

import java.math.BigDecimal;

public class FlatDiscountOffer extends OfferDecorator{

    private final BigDecimal discountAmount;

    public FlatDiscountOffer(PaymentProcessor processor, BigDecimal discountAmount) {
        super(processor);
        this.discountAmount = discountAmount;
    }

    @Override
    public PaymentResponse process(PaymentRequest request) {

        BigDecimal original = request.getAmount();
        BigDecimal adjusted = original.subtract(discountAmount).max(BigDecimal.ZERO);

        // 2. Create a new request with the discounted amount to pass to the delegate
        PaymentRequest discountedRequest = new PaymentRequest(
                request.getUserId(),
                adjusted,
                request.getPaymentType(),
                request.getMetadata()
        );
        PaymentResponse base = processor.process(discountedRequest);
        // 4. Enrich the response message for clarity
        if (base.isSuccess()) {
            String enrichedMessage = String.format(
                    "%s | Offer Applied: Flat ₹%s discount. Original: ₹%s, Charged: ₹%s",
                    base.getMessage(),
                    discountAmount,
                    original,
                    adjusted
            );
            return new PaymentResponse(base.isSuccess(), base.getTransactionID(), enrichedMessage);
        }
        return base;
    }
}