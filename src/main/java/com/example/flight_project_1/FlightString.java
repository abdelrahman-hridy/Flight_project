package com.example.flight_project_1;

import javafx.scene.control.Button;

public class FlightString {
    private String departureAirportName, arrivalAirportName;
    private String departureTime, arrivalTime;
    private String flight_Duration;
    private int price;
    private Button button;

    public FlightString(String departureAirportName, String arrivalAirportName, String departureTime, String arrivalTime, String flight_Duration, int price, int i) {
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flight_Duration = flight_Duration;
        this.price = price;
        this.button = new Button("Show");
        this.button.setId("Button" + i);
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFlight_Duration() {
        return flight_Duration;
    }

    public void setFlight_Duration(String flight_Duration) {
        this.flight_Duration = flight_Duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
