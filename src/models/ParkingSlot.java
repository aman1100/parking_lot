package models;

import enums.ParkingSlotStatus;

import java.util.List;

public class ParkingSlot extends BaseModel{
    private List<VehicleType> vehicleTypes;
    private ParkingSlotStatus parkingSpotStatus;
    private  int slotNumber;
    private ParkingFloor parkingFloor;
}
