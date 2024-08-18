package main.java.parkingspotservice.base_models;

import main.java.parkingspotservice.types.VehicleType;

public class Vehicle {
    private final long vehicleNumber;
    private final VehicleType vehicleType;

    public Vehicle(long vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public long getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return vehicleType.toString() + " : " + vehicleNumber;
    }
}
