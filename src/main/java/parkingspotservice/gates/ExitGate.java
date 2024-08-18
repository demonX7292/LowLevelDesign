package main.java.parkingspotservice.gates;

import main.java.parkingspotservice.base_models.Invoice;
import main.java.parkingspotservice.base_models.ParkingTicket;
import main.java.parkingspotservice.manager.InvoiceManager;
import main.java.parkingspotservice.manager.ParkingSpotManager;

public class ExitGate {
    private static volatile ExitGate exitGate;
    private ParkingSpotManager parkingSpotManager;
    private InvoiceManager invoiceManager;

    private ExitGate(ParkingSpotManager parkingSpotManager, InvoiceManager invoiceManager) {
        this.parkingSpotManager = parkingSpotManager;
        this.invoiceManager = invoiceManager;
    }

    public static ExitGate getExitGate(ParkingSpotManager parkingSpotManager, InvoiceManager invoiceManager) {
        if (exitGate != null) {
            return exitGate;
        }
        synchronized (EntryGate.class) {
            if (exitGate == null) {
                exitGate = new ExitGate(parkingSpotManager, invoiceManager);
            }
            return exitGate;
        }
    }

    public Invoice unparkVehicleAndGenerateInvoice(ParkingTicket parkingTicket) {
        parkingSpotManager.unparkVehicle(parkingTicket.getParkingSpot());
        return invoiceManager.getInvoice(parkingTicket);
    }
}
