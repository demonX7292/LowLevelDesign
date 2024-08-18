package main.java.parkingspotservice.base_models;

import java.time.LocalTime;

public class Invoice {

    public static InvoiceBuilder builder = new InvoiceBuilder();
    private final Vehicle vehicle;
    private final LocalTime entryTime;
    private final ParkingSpot parkingSpot;
    private final LocalTime exitTime;
    private final long priceToPay;

    public Invoice(Vehicle vehicle, LocalTime entryTime, ParkingSpot parkingSpot, LocalTime exitTime, long priceToPay) {
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.parkingSpot = parkingSpot;
        this.exitTime = exitTime;
        this.priceToPay = priceToPay;
    }

    public Invoice(InvoiceBuilder builder) {
        this.vehicle = builder.vehicle;
        this.entryTime = builder.entryTime;
        this.parkingSpot = builder.parkingSpot;
        this.exitTime = builder.exitTime;
        this.priceToPay = builder.priceToPay;
    }

    public static class InvoiceBuilder {

        private Vehicle vehicle;
        private LocalTime entryTime;
        private ParkingSpot parkingSpot;
        private LocalTime exitTime;
        private long priceToPay;

        public InvoiceBuilder vehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public InvoiceBuilder entryTime(LocalTime entryTime) {
            this.entryTime = entryTime;
            return this;
        }

        public InvoiceBuilder parkingSpot(ParkingSpot parkingSpot) {
            this.parkingSpot = parkingSpot;
            return this;
        }

        public InvoiceBuilder exitTime(LocalTime exitTime) {
            this.exitTime = exitTime;
            return this;
        }

        public InvoiceBuilder priceToPay(long priceToPay) {
            this.priceToPay = priceToPay;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }
    }
}
