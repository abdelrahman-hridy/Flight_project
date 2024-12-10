package com.example.flight_project_1.Base_classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Booking implements Serializable {
    private static int booking_ID = 0;
    private Passenger passenger;
    private Flight flight;
    private ArrayList<Seat> seats;
    private String bookingStatus;

    public int getBooking_ID() {
        return booking_ID;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public ArrayList<Seat> getSeat() {
        return seats;
    }

    public void setSeat(ArrayList<Seat> seat) {
        this.seats = seat;
    }

    public Booking( Passenger passenger, Flight flight, ArrayList<Seat> seats) {
        booking_ID = booking_ID++;
        this.passenger = passenger;
        this.flight = flight;
        this.seats = seats;
    }
    public Booking() {

    }
}
