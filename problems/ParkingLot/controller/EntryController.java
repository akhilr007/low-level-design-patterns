package problems.ParkingLot.controller;

import problems.ParkingLot.entity.Ticket;
import problems.ParkingLot.entity.Vehicle;
import problems.ParkingLot.entity.enums.VehicleType;
import problems.ParkingLot.service.SlotService;
import problems.ParkingLot.service.TicketService;
import problems.ParkingLot.service.VehicleService;

import java.util.Optional;
import java.util.UUID;

public class EntryController {

    private TicketService ticketService;
    private SlotService slotService;
    private VehicleService vehicleService;

    public EntryController(TicketService ticketService,
                           SlotService slotService,
                           VehicleService vehicleService) {
        this.ticketService = ticketService;
        this.slotService = slotService;
        this.vehicleService = vehicleService;
        System.out.println("[CONTROLLER] EntryController initialised");
    }

    public EntryResult enterVehicle(String licensePlate, VehicleType vehicleType) {
        System.out.println("[CONTROLLER] Vehicle entry requested - License: " + licensePlate +
                ", TYPE: " + vehicleType.name());

        try {
            // create vehicle
            Vehicle vehicle = new Vehicle(licensePlate, vehicleType);
            vehicleService.Save(vehicle);
            System.out.println("[CONTROLLER] Vehicle created: " + vehicle.getId());

            // allocate slot
            Optional<UUID> slotId = slotService.allocateSlot(vehicleType).map(slot -> slot.getId());

            if (slotId.isEmpty()) {
                return new EntryResult(false, null, null,
                        "No available slots for vehicle type: " + vehicleType.name());
            }

            // Generate ticket
            Ticket ticket = ticketService.generateTicket(vehicle, slotId.get());

            System.out.println("[CONTROLLER] Vehicle entry successful - Ticket: " + ticket.getId()  +
                    ", Slot: " + slotId.get());
            return  new EntryResult(true, ticket.getId(), slotId.get(), "Entry successful");
        }
        catch (Exception e) {
            System.out.println("[CONTROLLER] Vehicle entry failed: " + e.getMessage());
            return new EntryResult(false, null, null, e.getMessage());
        }
    }

    public static class EntryResult {
        private final boolean success;
        private final UUID ticketId;
        private final UUID slotId;
        private final String message;

        public EntryResult(boolean success, UUID ticketId, UUID slotId, String message) {
            this.success = success;
            this.ticketId = ticketId;
            this.slotId = slotId;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public UUID getTicketId() {
            return ticketId;
        }

        public UUID getSlotId() {
            return slotId;
        }

        public String getMessage() {
            return message;
        }
    }
}