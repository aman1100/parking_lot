package repositories;

import models.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {

    private Map<String, Vehicle> vehicles = new HashMap<>();
    private Long previousId = 0l;
    public Optional<Vehicle> getVehicleByNumber(String vehicleNumber){
        if(vehicles.containsKey(vehicleNumber)){
            return Optional.of(vehicles.get(vehicleNumber));
        }
        return Optional.empty();
    }

    public Vehicle saveVehicle(Vehicle vehicle){
        vehicles.put(vehicle.getVehicleNumber(), vehicle);
        return vehicle;
    }
}
