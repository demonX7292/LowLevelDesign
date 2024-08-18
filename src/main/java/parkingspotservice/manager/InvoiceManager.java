package main.java.parkingspotservice.manager;

import main.java.parkingspotservice.base_models.Invoice;
import main.java.parkingspotservice.base_models.ParkingTicket;
import main.java.parkingspotservice.pricing_strategy.PricingStrategy;
import main.java.parkingspotservice.pricing_strategy.PricingStrategyFactory;

import java.time.LocalTime;

public class InvoiceManager {

    private static volatile InvoiceManager invoiceManager;
    private final PricingStrategyFactory pricingStrategyFactory;

    private InvoiceManager(PricingStrategyFactory pricingStrategyFactory) {
        this.pricingStrategyFactory = pricingStrategyFactory;
    }

    public Invoice getInvoice(ParkingTicket parkingTicket) {
        LocalTime exitTime = LocalTime.now();
        PricingStrategy pricingStrategy = pricingStrategyFactory
                .getPricingStrategy(parkingTicket.getParkingSpot().getVehicleType());
        long priceToPay = pricingStrategy.getPrice(parkingTicket.getEntryTime(), exitTime);
        return Invoice.builder
                .entryTime(parkingTicket.getEntryTime())
                .exitTime(exitTime)
                .parkingSpot(parkingTicket.getParkingSpot())
                .vehicle(parkingTicket.getVehicle())
                .priceToPay(priceToPay)
                .build();
    }

    public static InvoiceManager getInvoiceManager(PricingStrategyFactory pricingStrategyFactory) {
        if (invoiceManager != null) {
            return invoiceManager;
        }
        synchronized (InvoiceManager.class) {
            if (invoiceManager == null) {
                invoiceManager = new InvoiceManager(pricingStrategyFactory);
            }
            return invoiceManager;
        }
    }
}
