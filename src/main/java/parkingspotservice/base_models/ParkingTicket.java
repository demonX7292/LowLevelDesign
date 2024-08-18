package main.java.parkingspotservice.base_models;

import java.time.LocalTime;

public class ParkingTicket {

    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalTime entryTime;

    public ParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot, LocalTime entryTime) {
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.parkingSpot = parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return vehicle.toString() + "\n" + parkingSpot.toString() + "\n" + entryTime.toString();
    }
}
