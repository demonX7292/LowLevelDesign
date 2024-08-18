package main.java;

import main.java.parkingspotservice.base_models.Invoice;
import main.java.parkingspotservice.base_models.ParkingSpot;
import main.java.parkingspotservice.base_models.ParkingTicket;
import main.java.parkingspotservice.base_models.Vehicle;
import main.java.parkingspotservice.exceptions.ParkingSpotNotAvailableException;
import main.java.parkingspotservice.gates.EntryGate;
import main.java.parkingspotservice.gates.ExitGate;
import main.java.parkingspotservice.manager.InvoiceManager;
import main.java.parkingspotservice.manager.ParkingSpotManager;
import main.java.parkingspotservice.parking_strategy.ParkingStrategyFactory;
import main.java.parkingspotservice.pricing_strategy.PricingStrategyFactory;
import main.java.parkingspotservice.types.Coordinates;
import main.java.parkingspotservice.types.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static main.java.parkingspotservice.types.ParkingStrategyType.CLOSEST_TO_EXIT;

public class ParkingSpotService {
    public static void main(String[] args) {
        List <ParkingSpot> parkingSpotList = new ArrayList<>();
        Coordinates entrance = new Coordinates(0, 0);
        Coordinates exit = new Coordinates(500, 500);
        boolean[][] points = new boolean[400][400];
        int count = 0;
        while (count < 100) {
            int x = (int) (Math.random() * 300) + 1;
            int y = (int) (Math.random() * 300) + 1;
            if (points[x][y]) {
                continue;
            }
            points[x][y] = true;
            count++;
            UUID id = UUID.randomUUID();
            parkingSpotList.add(new ParkingSpot(getRandomVehicleType(), new Coordinates(x, y), id));
        }
        ParkingStrategyFactory parkingStrategyFactory = new ParkingStrategyFactory(entrance, exit);
        ParkingSpotManager parkingSpotManager = ParkingSpotManager.getParkingSpotManager(
                parkingSpotList, parkingStrategyFactory);
        PricingStrategyFactory pricingStrategyFactory = new PricingStrategyFactory();
        InvoiceManager invoiceManager = InvoiceManager.getInvoiceManager(pricingStrategyFactory);
        EntryGate entryGate = EntryGate.getEntryGate(parkingSpotManager);
        ExitGate exitGate = ExitGate.getExitGate(parkingSpotManager, invoiceManager);


        Vehicle vehicle = new Vehicle(1234, VehicleType.BIG_VEHICLE);
        try {
            ParkingTicket ticket = entryGate.generateParkingTicket(vehicle, CLOSEST_TO_EXIT.toString());
            System.out.println(ticket.toString());
            Thread.sleep(1000 * 120);
            Invoice invoice = exitGate.unparkVehicleAndGenerateInvoice(ticket);
            System.out.println(invoice.toString());
        } catch (ParkingSpotNotAvailableException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    private static VehicleType getRandomVehicleType() {
        VehicleType[] vehicleTypes = VehicleType.values();
        return vehicleTypes[new Random().nextInt(vehicleTypes.length)];
    }
}
