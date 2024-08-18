package main.java.parkingspotservice.pricing_strategy;

import java.time.Duration;
import java.time.LocalTime;

public class BigVehiclePricingStrategy implements PricingStrategy {
    private final long fixedCost = 100;
    private final double costPerSecond = 0.05;

    @Override
    public long getPrice(LocalTime entryTime, LocalTime exitTime) {
        return (long)(getSeconds(entryTime, exitTime) * costPerSecond) + fixedCost;
    }

    private long getSeconds(LocalTime entryTime, LocalTime exitTime) {
        return Duration.between(entryTime, exitTime).toSeconds();
    }
}
