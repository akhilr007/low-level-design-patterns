package problems.ParkingLot.service;

import problems.ParkingLot.adapter.PaymentGatewayAdapter;
import problems.ParkingLot.adapter.RazorpayAdapter;
import problems.ParkingLot.entity.Payment;
import problems.ParkingLot.entity.enums.PaymentGateway;
import problems.ParkingLot.entity.enums.PaymentStatus;
import problems.ParkingLot.repository.PaymentRepository;

import java.util.UUID;

public class PaymentService {

    private PaymentRepository paymentRepository;
    private PaymentGatewayAdapter defaultGateway;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
        this.defaultGateway = new RazorpayAdapter();
        System.out.println("[SERVICE] PaymentService initialized with default gateway: Razorpay");
    }

    public boolean processPayment(UUID ticketId, double amount) {
        System.out.println("[SERVICE] Processing payment for ticket: " + ticketId + " amount: " + amount);

        Payment payment = new Payment(ticketId, amount, PaymentGateway.RAZORPAY);
        paymentRepository.save(payment);

        boolean success = defaultGateway.pay(ticketId, amount);
        if (success) {
            payment.markAsSuccess();
        }
        else {
            payment.markAsFailed();
        }

        paymentRepository.update(payment);
        System.out.println("[SERVICE] Payment processed with status: " + (success ? "SUCCESS" : "FAILED"));

        return success;


    }
}