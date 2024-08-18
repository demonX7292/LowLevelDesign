package main.java.parkingspotservice.pricing_strategy;

import java.time.Duration;
import java.time.LocalTime;

public class TwoWheelerPricingStrategy implements PricingStrategy {

    private final long fixedCost = 10;
    private final long costPerHour = 20;

    @Override
    public long getPrice(LocalTime entryTime, LocalTime exitTime) {
        return getHours(entryTime, exitTime) * costPerHour + fixedCost;
    }

    private long getHours(LocalTime entryTime, LocalTime exitTime) {
        return Duration.between(entryTime, exitTime).toHours();
    }
}
