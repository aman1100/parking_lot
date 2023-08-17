package services;

import enums.SlotAllotmentStrategyType;
import exceptions.GateNotFoundException;
import models.*;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import strategies.SlotAssignmentStrategy;
import strategies.SlotAssignmentStrategyFactory;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingLotRepository parkingLotRepository,
                         TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(VehicleType vehicleType,
                              String vehicleNumber,
                              String vehicleOwnerName,
                              Long gateId) throws GateNotFoundException {
        //create ticket object
        //Assign spot
        //Assign time
        //save to db
        //return

        Ticket ticket = new Ticket();
        ticket.setEntryTime(Instant.now());
        //get gate model using gateId
        Optional<Gate> optionalGate = gateRepository.findGateById(gateId);
        if(optionalGate.isEmpty()) {
            throw new GateNotFoundException();
        }

        Gate gate = optionalGate.get();
        ticket.setGate(gate);
        ticket.setGeneratedBy(gate.getOperator());

        Vehicle savedVehicle;
        Optional<Vehicle> optionalVehicle =vehicleRepository.getVehicleByNumber(vehicleNumber);
        if(optionalVehicle.isEmpty()){ //save the vehicle
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setOwnerName(vehicleOwnerName);

            savedVehicle = vehicleRepository.saveVehicle(vehicle);
        }else{
            savedVehicle = optionalVehicle.get();
        }

        ticket.setVehicle(savedVehicle);

        SlotAllotmentStrategyType slotAllotmentStrategyType = parkingLotRepository.getParkingLotForGate(gate).getSlotAllotmentStrategyType();

        SlotAssignmentStrategy slotAssignmentStrategy = SlotAssignmentStrategyFactory.getSlotAssignmentStrategy(slotAllotmentStrategyType);
        ticket.setParkingSlot(slotAssignmentStrategy.getSlot(gate, vehicleType));

        ticket.setNumber("Ticket-" + UUID.randomUUID());

        //save the ticket object
        return ticketRepository.saveTicket(ticket);
    }
}
