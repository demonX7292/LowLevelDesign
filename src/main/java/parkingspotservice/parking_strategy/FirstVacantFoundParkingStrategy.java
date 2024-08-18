package main.java.parkingspotservice.parking_strategy;

import main.java.parkingspotservice.base_models.ParkingSpot;

import java.util.List;
import java.util.Optional;

public class FirstVacantFoundParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> getParkingSpot(List<ParkingSpot> parkingSpots) {
        for (int i = 0; i < parkingSpots.size(); i++) {
            if (!parkingSpots.get(i).isOccupied()) {
                return Optional.ofNullable(parkingSpots.get(i));
            }
        }
        return Optional.empty();
    }
}
