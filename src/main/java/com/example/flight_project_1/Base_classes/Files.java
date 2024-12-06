package com.example.flight_project_1.Base_classes;

import java.util.ArrayList;

public class Files {
    private static ArrayList<Flight> flights = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Airport> airports = new ArrayList<>();
    private static ArrayList<Seat> seats = new ArrayList<>();
    private static ArrayList<Passenger> passengers = new ArrayList<>();

    public static ArrayList<Flight> getFlights() {
        return flights;
    }

    public static void setFlights(ArrayList<Flight> flights) {
        Files.flights = flights;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static void setAdmins(ArrayList<Admin> admins) {
        Files.admins = admins;
    }

    public static ArrayList<Airport> getAirports() {
        return airports;
    }

    public static void setAirports(ArrayList<Airport> airports) {
        Files.airports = airports;
    }

    public static ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public static void setPassengers(ArrayList<Passenger> passengers) {
        Files.passengers = passengers;
    }

    public static ArrayList<Seat> getSeats() {
        return seats;
    }

    public static void setSeats(ArrayList<Seat> seats) {
        Files.seats = seats;
    }
}
