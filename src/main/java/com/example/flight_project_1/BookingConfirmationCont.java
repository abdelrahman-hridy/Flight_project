package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import com.example.flight_project_1.Base_classes.Payment;
import com.example.flight_project_1.Base_classes.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BookingConfirmationCont {

    @FXML
    private TextField flightFrom;
    @FXML
    private TextField flightTo;
    @FXML
    private TextField seatID;
    @FXML
    private TextField seatClass;
    @FXML
    private TextField arrivalDate;
    @FXML
    private TextField departureDate;
    @FXML
    private TextField username;
    @FXML
    private TextField paymentMethod;
    @FXML
    private TextField totalPrice;

    private Passenger user;
    private Flight flight;
    private Seat seat;
    private Payment payment;

    public  void passingPayment(Payment payment){this.payment=payment;}
    public  void assignUser(Passenger user){
        this.user = user;
    }
    public  void passingFlight(Flight flight){
        this.flight = flight;
    }
    public  void passingTheSeat(Seat seat){
        this.seat = seat;
    }

    public void ConfirmationInfo(){

        if (user != null) {
            username.setText(user.getName());
        }
        if (flight != null) {
            flightFrom.setText(flight.getDeapartureAirport().getAirport_Name());
            flightTo.setText(flight.getArrivalAirport().getAirport_Name());
            arrivalDate.setText(flight.getArrivalTime().toString());
            departureDate.setText(flight.getDepartureTime().toString());
        }
        if (seat != null) {
            seatID.setText(seat.getSeatId());
            seatClass.setText(seat.getSeatClass());
        }
        if (payment != null) {
            paymentMethod.setText(payment.getPaymentMethod());
            totalPrice.setText(String.valueOf(payment.getPaymentAmount()));
        }

        makeFieldsReadOnly();
    }
    private void makeFieldsReadOnly(){
        username.setEditable(false);
        flightFrom.setEditable(false);
        flightTo.setEditable(false);
        seatID.setEditable(false);
        seatClass.setEditable(false);
        arrivalDate.setEditable(false);
        departureDate.setEditable(false);
        paymentMethod.setEditable(false);
        totalPrice.setEditable(false);
    }
    @FXML
    public void initialize(){
        try{
            ConfirmationInfo();
        }catch (NullPointerException e){
            System.out.println("There is an info has not been set  "+e);
        }

    }
}
