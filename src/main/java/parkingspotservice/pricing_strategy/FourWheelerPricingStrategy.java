package main.java.parkingspotservice.pricing_strategy;

import java.time.Duration;
import java.time.LocalTime;

public class FourWheelerPricingStrategy implements PricingStrategy{
    private final long fixedCost = 40;
    private final long costPerMinute = 1;

    @Override
    public long getPrice(LocalTime entryTime, LocalTime exitTime) {
        return getMinutes(entryTime, exitTime) * costPerMinute + fixedCost;
    }

    private long getMinutes(LocalTime entryTime, LocalTime exitTime) {
        return Duration.between(entryTime, exitTime).toMinutes();
    }
}
