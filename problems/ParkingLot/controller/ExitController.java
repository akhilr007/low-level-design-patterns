package problems.ParkingLot.controller;

import problems.ParkingLot.entity.Receipt;
import problems.ParkingLot.entity.Ticket;
import problems.ParkingLot.service.*;

import java.util.Optional;
import java.util.UUID;

public class ExitController {
    private TicketService ticketService;
    private SlotService slotService;
    private PaymentService paymentService;
    private ReceiptService receiptService;
    private PricingService pricingService;

    public ExitController(TicketService ticketService,
                          SlotService slotService,
                          PaymentService paymentService,
                          ReceiptService receiptService,
                          PricingService pricingService) {
        this.ticketService = ticketService;
        this.slotService = slotService;
        this.paymentService = paymentService;
        this.receiptService = receiptService;
        this.pricingService = pricingService;
        System.out.println("[CONTROLLER] ExitController initialized");
    }

    public ExitResult exitVehicle(UUID ticketId) {

        System.out.println("[CONTROLLER] Vehicle exit request - Ticket: " + ticketId);

        try{
            // retrieve ticket
            Optional<Ticket> ticketResponse = ticketService.getTicket(ticketId);
            if(ticketResponse.isEmpty()) {
                return new ExitResult(false, null, 0.0, "Ticket not found");
            }

            Ticket ticket = ticketResponse.get();
            if (!ticket.isActive()) {
                return new ExitResult(false, null, 0.0, "Ticket is not active");
            }

            //calculate fee
            double fee = pricingService.calculateFee(ticket);
            System.out.println("[CONTROLLER] Fee calculated: " + fee);

            // Process payment with retry
            boolean paymentSuccess = paymentService.processPayment(ticketId, fee);
            if (!paymentSuccess) {
                return new ExitResult(false, null, fee, "Payment failed");
            }

            // Generate receipt
            Receipt receipt = receiptService.generateReceipt(ticket, fee);
            receiptService.markReceiptAsPaid(receipt);

            // Release slot
            slotService.releaseSlot(ticket.getSlotId());

            // Deactivate ticket
            ticketService.deactivateTicket(ticketId);

            System.out.println("[CONTROLLER] Vehicle exit successful - Receipt: " + receipt.getId());
            return new ExitResult(true, receipt.getId(), fee, "Exit successful");
        } catch (Exception e) {
            System.out.println("[CONTROLLER] Vehicle exit failed: " + e.getMessage());
            return new ExitResult(false, null, 0.0, e.getMessage());
        }
    }

    public String generateReceiptText(UUID ticketId) {
        System.out.println("[CONTROLLER] Generating receipt text for ticket: " + ticketId);

        try {
            Optional<Ticket> ticketResponse = ticketService.getTicket(ticketId);
            if (ticketResponse.isEmpty()) {
                return "Ticket not found";
            }

            Ticket ticket = ticketResponse.get();
            double fee = pricingService.calculateFee(ticket);
            Receipt receipt = receiptService.generateReceipt(ticket, fee);

            String receiptText = receiptService.generateReceiptText(receipt, ticket);
            System.out.println("[CONTROLLER] Receipt text generated successfully");
            return receiptText;

        } catch (Exception e) {
            System.out.println("[CONTROLLER] Receipt text generation failed: " + e.getMessage());
            return "Error generating receipt: " + e.getMessage();
        }
    }

    public static class ExitResult {
        private final boolean success;
        private final UUID receiptId;
        private final double fee;
        private final String message;

        public ExitResult(boolean success, UUID receiptId, double fee, String message) {
            this.success = success;
            this.receiptId = receiptId;
            this.fee = fee;
            this.message = message;
        }

        public boolean isSuccess() { return success; }
        public UUID getReceiptId() { return receiptId; }
        public double getFee() { return fee; }
        public String getMessage() { return message; }
    }
}