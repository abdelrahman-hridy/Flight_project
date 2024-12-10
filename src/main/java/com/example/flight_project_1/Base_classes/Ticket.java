package com.example.flight_project_1.Base_classes;

import java.io.Serializable;

public class Ticket implements Serializable {
    private static int ticketNumber = 0;
    private double fare;
    private Booking bookingTicket;

    public Ticket(Booking bookingTicket){
        ticketNumber = ticketNumber++;
        this.bookingTicket = bookingTicket;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public Booking getBookingTicket() {
        return bookingTicket;
    }

    public void setBookingTicket(Booking bookingTicket) {
        this.bookingTicket = bookingTicket;
    }
}