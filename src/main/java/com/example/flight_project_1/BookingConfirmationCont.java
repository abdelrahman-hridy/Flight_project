package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import com.example.flight_project_1.Base_classes.Payment;
import com.example.flight_project_1.Base_classes.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private Parent root;
    private Stage stage;
    private Scene scene;
//    private String allPrice;


    public void setData(Passenger user, Flight flight, Seat seat, Payment payment ){
        this.user=user;
        this.payment=payment;
        this.flight=flight;
        this.seat=seat;
//        this.allPrice=allPrice;
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
    public void BackToPaymentGate(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            root = fxmlLoader.load();

            PaymentSceneController paymentSceneController = fxmlLoader.getController();
            paymentSceneController.PassingSeatDetailsValues(user,flight,seat,payment.getPaymentAmount(),payment);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            System.out.println("Error When Go to payment Gate"+ex);
        }
    }
//    public void confirmTheFlight(ActionEvent event) {
//        seat.setSeatStatus(false);
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SeatSelection.fxml"));
//            root = fxmlLoader.load();
//
//            SeatSelectionController seatSelectionController = fxmlLoader.getController();
//            seatSelectionController.
//
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }catch (Exception ex){
//            System.out.println("Error When "+ex);
//        }
//    }


}
