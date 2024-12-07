package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import com.example.flight_project_1.Base_classes.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.Serializable;

public class ShowSeatDetail implements Serializable {
    private Passenger user;
    private Flight flight;
    private Seat seat;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private double service;
    private String allprice;
    @FXML
    TextField SeatId=new TextField();
    @FXML
    TextField SeatClass=new TextField();
    @FXML
    TextField SeatPrice=new TextField();
    @FXML
    TextField Service=new TextField();
    @FXML
    TextField AllPrice=new TextField();
    @FXML
    TextField UserName=new TextField();
    @FXML
    TextField UserId=new TextField();
    @FXML
    TextField UserPhone=new TextField();

    public void assignUser(Passenger user){
        this.user = user;
    }
    public void passingFlight(Flight flight){
        this.flight = flight;
    }
    public void passingTheSeat(Seat seat){
        this.seat = seat;
    }

    public void SetDataOfTheSeat(ActionEvent event){
        SeatId.setText(seat.getSeatId());
        SeatId.setEditable(false);
        SeatClass.setText(seat.getSeatClass());
        SeatClass.setEditable(false);
        SeatPrice.setText(seat.calcSeatPrice(flight));
        SeatPrice.setEditable(false);
        if(seat.getSeatId().startsWith("A") || seat.getSeatId().startsWith("F")){
            service=500;
            Service.setText("Window Seat: "+service);
            Service.setEditable(false);
            allprice= String.valueOf(Integer.parseInt(seat.calcSeatPrice(flight))+service);

        }
        else if(seat.getSeatId().startsWith("B") || seat.getSeatId().startsWith("E")){
            service=300;
            Service.setText("Middle Seat: "+service);
            Service.setEditable(false);
            allprice= String.valueOf(Integer.parseInt(seat.calcSeatPrice(flight))+service);
        }
        else{
            service=100;
            Service.setText("Way Seat: "+service);
            Service.setEditable(false);
            allprice= String.valueOf(Integer.parseInt(seat.calcSeatPrice(flight))+service);
        }
        AllPrice.setText(String.valueOf(allprice));
        AllPrice.setEditable(false);
        UserName.setText(user.getName());
        UserName.setEditable(false);
        UserId.setText(user.getPassenger_ID());
        UserId.setEditable(false);
        UserPhone.setText(user.getPhone());
        UserPhone.setEditable(false);
    }

    public void BackToSeats(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SeatSelection.fxml"));
            root = fxmlLoader.load();

            SeatSelectionController seatSelectionController = fxmlLoader.getController();
            seatSelectionController.assignUser(user);
            seatSelectionController.passingFlight(flight);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            System.out.println("Error In back to SeatSelection.fxml");
        }
    }
    public void ConfirmTheSeat(ActionEvent event){
//        System.out.println("Confirmed");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            root = fxmlLoader.load();

            PaymentSceneController paymentSceneController = fxmlLoader.getController();
            paymentSceneController.assignUser(user);
            paymentSceneController.passingFlight(flight);
            paymentSceneController.passingSeat(seat);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("PaymentStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println("Error when to go the payment"+e);
        }
    }

}
