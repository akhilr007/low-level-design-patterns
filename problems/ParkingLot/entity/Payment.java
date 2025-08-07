package problems.ParkingLot.entity;

import problems.ParkingLot.entity.enums.PaymentGateway;
import problems.ParkingLot.entity.enums.PaymentStatus;

import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID ticketId;
    private double amount;
    private PaymentGateway gateway;
    private PaymentStatus status;

    public Payment(UUID ticketId, double amount, PaymentGateway gateway) {
        this.id = UUID.randomUUID();
        this.ticketId = ticketId;
        this.amount = amount;
        this.gateway = gateway;
        this.status = PaymentStatus.PENDING;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void markAsSuccess() {
        this.status = PaymentStatus.COMPLETED;
    }

    public void markAsFailed() {
        this.status = PaymentStatus.FAILED;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}