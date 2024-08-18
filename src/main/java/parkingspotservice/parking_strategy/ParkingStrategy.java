package main.java.parkingspotservice.parking_strategy;

import main.java.parkingspotservice.base_models.ParkingSpot;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> getParkingSpot(List<ParkingSpot> parkingSpots);
}
