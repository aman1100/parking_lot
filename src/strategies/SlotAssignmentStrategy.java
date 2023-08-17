package strategies;

import models.Gate;
import models.ParkingSlot;
import models.VehicleType;

public interface SlotAssignmentStrategy {
    public ParkingSlot getSlot(Gate gate, VehicleType vehicleType);
}
