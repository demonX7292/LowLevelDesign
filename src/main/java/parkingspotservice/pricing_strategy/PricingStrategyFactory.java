package main.java.parkingspotservice.pricing_strategy;

import main.java.parkingspotservice.types.VehicleType;

public class PricingStrategyFactory {

    private VehicleType vehicleType;

    public PricingStrategy getPricingStrategy(VehicleType vehicleType) {
        switch (vehicleType) {
            case TWO_WHEELER -> {
                return new TwoWheelerPricingStrategy();
            }
            case FOUR_WHEELER -> {
                return new FourWheelerPricingStrategy();
            }
            case BIG_VEHICLE -> {
                return new BigVehiclePricingStrategy();
            }
        }
        return new FourWheelerPricingStrategy();
    }
}
