package main.java.parkingspotservice.exceptions;

import javax.naming.InsufficientResourcesException;

public class ParkingSpotNotAvailableException extends InsufficientResourcesException {

    private String message;

    public ParkingSpotNotAvailableException() {
        this.message = "Sorry for inconvenience !! All parking spots are currently booked";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
