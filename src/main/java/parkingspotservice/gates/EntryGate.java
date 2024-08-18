package main.java.parkingspotservice.gates;

import main.java.parkingspotservice.base_models.ParkingSpot;
import main.java.parkingspotservice.base_models.ParkingTicket;
import main.java.parkingspotservice.base_models.Vehicle;
import main.java.parkingspotservice.exceptions.ParkingSpotNotAvailableException;
import main.java.parkingspotservice.manager.ParkingSpotManager;
import main.java.parkingspotservice.types.ParkingStrategyType;

import java.time.LocalTime;
import java.util.Optional;

public class EntryGate {

    private static volatile EntryGate entryGate;
    private ParkingSpotManager parkingSpotManager;

    private EntryGate(ParkingSpotManager parkingSpotManager) {
        this.parkingSpotManager = parkingSpotManager;
    }

    public static EntryGate getEntryGate(ParkingSpotManager parkingSpotManager) {
        if (entryGate != null) {
            return entryGate;
        }
        synchronized (EntryGate.class) {
            if (entryGate == null) {
                entryGate = new EntryGate(parkingSpotManager);
            }
            return entryGate;
        }
    }

    public ParkingTicket generateParkingTicket(Vehicle vehicle, String parkingStrategy)
            throws ParkingSpotNotAvailableException {
        Optional<ParkingSpot> parkingSpot = parkingSpotManager.findAndBookParkingSpot(
                vehicle, ParkingStrategyType.valueOf(parkingStrategy));
        if (parkingSpot.isEmpty()) {
            throw new ParkingSpotNotAvailableException();
        }
        return new ParkingTicket(vehicle, parkingSpot.get(), LocalTime.now());
    }
}
