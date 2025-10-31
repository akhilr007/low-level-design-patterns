package problems.PaymentProcessor;

public class PaymentResponse {
    private final boolean success;
    private final String transactionID;
    private final String message;

    public PaymentResponse(boolean success, String transactionID, String message) {
        this.success = success;
        this.transactionID = transactionID;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "success=" + success +
                ", transactionID='" + transactionID + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}