package problems.ParkingLot.entity;

import problems.ParkingLot.entity.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Receipt {
    private UUID id;
    private UUID ticketId;
    private LocalDateTime exitTime;
    private double totalFee;
    private PaymentStatus paymentStatus;

    public Receipt(UUID ticketId, double totalFee) {
        this.id = UUID.randomUUID();
        this.ticketId = ticketId;
        this.exitTime = LocalDateTime.now();
        this.totalFee = totalFee;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                ", exitTime=" + exitTime +
                ", price=" + totalFee +
                ", paymentStatus=" + paymentStatus +
                '}';
    }

    public void markAsPaid() {
        this.paymentStatus = PaymentStatus.COMPLETED;
    }

    public void markAsFailed() {
        this.paymentStatus = PaymentStatus.FAILED;
    }

}