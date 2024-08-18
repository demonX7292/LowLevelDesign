package main.java.parkingspotservice.parking_strategy;

import main.java.parkingspotservice.types.Coordinates;
import main.java.parkingspotservice.types.ParkingStrategyType;

public class ParkingStrategyFactory {

    private final Coordinates entrance;
    private final Coordinates exit;

    public ParkingStrategyFactory(Coordinates entrance, Coordinates exit) {
        this.entrance = entrance;
        this.exit = exit;
    }

    public ParkingStrategy getParkingStrategy(ParkingStrategyType parkingStrategyType) {
        switch (parkingStrategyType) {
            case FIRST_VACANT -> {
                return new FirstVacantFoundParkingStrategy();
            }
            case CLOSEST_TO_ENTRANCE -> {
                return new ClosestToEntranceStrategy(entrance);
            }
            case CLOSEST_TO_EXIT -> {
                return new ClosestToExitStrategy(exit);
            }
        }
        return new FirstVacantFoundParkingStrategy();
    }
}
