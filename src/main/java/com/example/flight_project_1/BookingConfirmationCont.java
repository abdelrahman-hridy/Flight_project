package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import com.example.flight_project_1.Base_classes.Payment;
import com.example.flight_project_1.Base_classes.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.print.DocFlavor;
import java.net.URL;
import java.util.ResourceBundle;

public class BookingConfirmationCont  {

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
    @FXML
    private Button confirmButton;

    private Passenger user;
    private Flight flight;
    private Seat seat;
    private Payment payment;


    public void setData(Passenger user, Flight flight, Seat seat, Payment payment ){
        this.user=user;
        this.payment=payment;
        this.flight=flight;
        this.seat=seat;
        ConfirmationInfo();
    }

    public void ConfirmationInfo(){

        if (user != null) {
            username.setText(user.getName());
        }else {
            username.setText("Null value");
        }
        if (flight != null) {
            flightFrom.setText(flight.getDeapartureAirport().getAirport_Name());
            flightTo.setText(flight.getArrivalAirport().getAirport_Name());
            arrivalDate.setText(flight.getArrivalTime().toString());
            departureDate.setText(flight.getDepartureTime().toString());
        }else {
            flightFrom.setText("Null value");
            flightTo.setText("Null value");
            arrivalDate.setText("Null value");
            departureDate.setText("Null value");
        }
        if (seat != null) {
            seatID.setText(seat.getSeatId());
            seatClass.setText(seat.getSeatClass());
        }else {
            seatID.setText("Null value");
            seatClass.setText("Null value");
        }
        if (payment != null) {
            paymentMethod.setText(payment.getPaymentMethod());
            totalPrice.setText(String.valueOf(payment.getPaymentAmount()));
        }else {
            paymentMethod.setText("Null value");
            totalPrice.setText("Null value");
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
//    @FXML
//    public void initialize(URL url, ResourceBundle resources){
//        try{
//            ConfirmationInfo();
//        }catch (NullPointerException e){
//            System.out.println("There is an info has not been set  "+e);
//        }
//
//    }
}
