package main.java.parkingspotservice.manager;

import main.java.parkingspotservice.base_models.ParkingSpot;
import main.java.parkingspotservice.base_models.Vehicle;
import main.java.parkingspotservice.parking_strategy.ParkingStrategy;
import main.java.parkingspotservice.parking_strategy.ParkingStrategyFactory;
import main.java.parkingspotservice.types.ParkingStrategyType;
import main.java.parkingspotservice.types.VehicleType;

import java.util.List;
import java.util.Optional;

public class ParkingSpotManager {

    private static volatile ParkingSpotManager parkingSpotManager;
    private final List<ParkingSpot> parkingSpotList;
    private final ParkingStrategyFactory parkingStrategyFactory;

    public Optional<ParkingSpot> findAndBookParkingSpot(Vehicle vehicle, ParkingStrategyType parkingStrategyType) {
        ParkingStrategy parkingStrategy = parkingStrategyFactory.getParkingStrategy(parkingStrategyType);
        List<ParkingSpot> parkingSpots = getParkingSpotListAccordingToVehicleType(vehicle.getVehicleType());
        if (parkingSpots.isEmpty()) {
            return Optional.empty();
        }
        Optional<ParkingSpot> parkingSpot = parkingStrategy.getParkingSpot(parkingSpots);
        if (parkingSpot.isEmpty()) {
            return Optional.empty();
        }
        synchronized (ParkingSpot.class) {
            if (parkingSpot.get().isOccupied()) {
                return Optional.empty();
            }
            parkingSpot.get().setOccupied(true);
            parkingSpot.get().setVehicle(vehicle);
            return parkingSpot;
        }
    }

    public synchronized void unparkVehicle(ParkingSpot parkingSpot) {
        parkingSpot.setOccupied(false);
        parkingSpot.setVehicle(null);
    }

    private ParkingSpotManager(List<ParkingSpot> parkingSpots, ParkingStrategyFactory parkingStrategyFactory) {
        this.parkingSpotList = parkingSpots;
        this.parkingStrategyFactory = parkingStrategyFactory;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        parkingSpotList.add(parkingSpot);
    }

    public static ParkingSpotManager getParkingSpotManager(
            List<ParkingSpot> parkingSpots,
            ParkingStrategyFactory parkingStrategyFactory) {
        if (parkingSpotManager != null) {
            return parkingSpotManager;
        }
        synchronized (ParkingSpotManager.class) {
            if (parkingSpotManager == null) {
                parkingSpotManager = new ParkingSpotManager(parkingSpots, parkingStrategyFactory);
            }
            return parkingSpotManager;
        }
    }

    private List<ParkingSpot> getParkingSpotListAccordingToVehicleType(VehicleType vehicleType) {
        return parkingSpotList.stream().filter(parkingSpot -> parkingSpot.getVehicleType() == vehicleType).toList();
    }

}
