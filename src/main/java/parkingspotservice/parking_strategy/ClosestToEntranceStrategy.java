package main.java.parkingspotservice.parking_strategy;

import main.java.parkingspotservice.base_models.ParkingSpot;
import main.java.parkingspotservice.types.Coordinates;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ClosestToEntranceStrategy implements ParkingStrategy {

    private final Coordinates entrance;

    public ClosestToEntranceStrategy(Coordinates entranceGate) {
        entrance = entranceGate;
    }

    @Override
    public Optional<ParkingSpot> getParkingSpot(List<ParkingSpot> parkingSpots) {
        return parkingSpots.stream().filter(parkingSpot -> !parkingSpot.isOccupied())
                .min(Comparator.comparingInt(this::getDistanceExtractor));
    }

    private int getDistanceExtractor (ParkingSpot parkingSpot) {
        int x = parkingSpot.getCoordinates().getX();
        int y = parkingSpot.getCoordinates().getY();
        int entranceX = entrance.getX();
        int entranceY = entrance.getY();
        return Math.abs((x*x + y*y - entranceX*entranceX - entranceY*entranceY));
    }
}
