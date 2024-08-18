package main.java.parkingspotservice.types;

public enum ParkingStrategyType {

    FIRST_VACANT ("FIRST_VACANT"),
    CLOSEST_TO_ENTRANCE ("CLOSEST_TO_ENTRANCE"),
    CLOSEST_TO_EXIT ("CLOSEST_TO_EXIT");

    private final String strategyType;

    ParkingStrategyType (String strategyType) {
        this.strategyType = strategyType;
    }

    @Override
    public String toString() {
        return this.strategyType;
    }
}
