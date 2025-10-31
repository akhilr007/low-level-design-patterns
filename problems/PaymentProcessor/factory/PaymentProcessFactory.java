package problems.PaymentProcessor.factory;

import problems.PaymentProcessor.PaymentType;
import problems.PaymentProcessor.payment.*;

import java.util.Optional;

public final class PaymentProcessFactory {

    private PaymentProcessFactory() {}

    public static Optional<PaymentProcessor> create(PaymentType type) {
        return switch (type) {
            case UPI -> Optional.of(new UpiProcessor());
            case CARD -> Optional.of(new CardProcessor());
            case COD -> Optional.of(new CodProcessor());
            case NETBANKING -> Optional.of(new NetbankingProcessor());
            case WALLET -> Optional.of(new WalletProcessor());
            default -> Optional.empty();
        };
    }
}