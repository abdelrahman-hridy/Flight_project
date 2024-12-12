package com.example.flight_project_1.Base_classes;

import java.io.Serializable;

public class Ticket implements Serializable {
    private static int ticketNumbercounter = 0;
    private int ticketNumber;
    private double fare;
    private Booking bookingTicket;


    public static void setTicketNumbercounter(int ticketNumbercounter) {
        Ticket.ticketNumbercounter = ticketNumbercounter;
    }

    public Ticket(Booking bookingTicket){
        ticketNumber = ticketNumbercounter++;
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