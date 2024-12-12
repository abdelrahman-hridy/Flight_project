package com.example.flight_project_1.Base_classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Flight implements Serializable {
    private static int FlightNumberStatic;
    private int FlightNumber;

    private Airport deapartureAirport;
    private Airport arrivalAirport;
    private Date departureTime;
    private Date arrivalTime;
    private int price;
    private ArrayList<ArrayList<Seat>> seats;
    private Passenger passengers[];

    public static void setFlightNumberStatic(int flightNumberStatic) {
        FlightNumberStatic = flightNumberStatic;
    }

    public int getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        FlightNumber = flightNumber;
    }

    public Airport getDeapartureAirport() {
        return deapartureAirport;
    }

    public void setDeapartureAirport(Airport deapartureAirport) {
        this.deapartureAirport = deapartureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public ArrayList<ArrayList<Seat>> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<ArrayList<Seat>> seats) {
        this.seats = seats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public void setPassengers(Passenger[] passengers) {
        this.passengers = passengers;
    }

    public Flight() {
    }

    public Flight(Airport deapartureAirport, Airport arrivalAirport, Date departureTime,
                  Date arrivalTime, ArrayList<ArrayList<Seat>> seats, int price)
    {
        FlightNumber = FlightNumberStatic;
        FlightNumberStatic++;
        this.deapartureAirport = deapartureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;

            ArrayList<ArrayList<Seat>> seats1 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ArrayList<Seat> row = new ArrayList<>();
                for (int j = 0; j < 6; j++) {
                    {
                        if(i < 2)
                             row.add(new FirstSeat(Files.getSeats().get(i).get(j).getSeatId(), true));
                        else if(i < 5){
                            row.add(new BusinessSeat(Files.getSeats().get(i).get(j).getSeatId(), true));
                        }
                        else{
                            row.add(new EconomySeat(Files.getSeats().get(i).get(j).getSeatId(), true));
                        }
                    }
                }
                seats1.add(row);
            }

        this.seats = seats1;
        this.price = price;
    }
}
