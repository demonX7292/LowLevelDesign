package main.java.parkingspotservice.base_models;

import main.java.parkingspotservice.types.Coordinates;
import main.java.parkingspotservice.types.VehicleType;

import java.util.UUID;

public class ParkingSpot {
    private final UUID id;
    private VehicleType vehicleType;
    private boolean isOccupied;
    private Vehicle vehicle;
    private Coordinates coordinates;

    public ParkingSpot(VehicleType vehicleType, Coordinates coordinates, UUID id) {
        this.vehicleType = vehicleType;
        this.coordinates = coordinates;
        this.vehicle = null;
        this.isOccupied = false;
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return coordinates.getX()
                + " : " + coordinates.getY()
                + " : " + id + " : " + vehicleType;
    }
}
