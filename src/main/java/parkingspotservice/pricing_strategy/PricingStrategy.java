package main.java.parkingspotservice.pricing_strategy;

import java.time.LocalTime;

public interface PricingStrategy {
    long getPrice(LocalTime entryTime, LocalTime exitTime);
}
