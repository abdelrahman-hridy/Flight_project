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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingConfirmationCont  {

    @FXML
    private Label userLabel,fromLabel,toLabel,classLabel,seatLabel,priceLabel,methodLabel,dDateLabel,aDateLabel;
    @FXML
    private TextField seatID;
    @FXML
    private TextField seatClass;

    @FXML
    private Button confirmButton;

    private Passenger user;
    private Flight flight;
    private ArrayList <Seat> seats;
    private Payment payment;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private static int AvailbleSeats;
//    private String allPrice;


    public void setData(Passenger user, Flight flight, ArrayList <Seat> seats, Payment payment,int AvailbleSeats ) {
        this.user=user;
        this.payment=payment;
        this.flight=flight;
        this.seats = seats;
        this.AvailbleSeats=AvailbleSeats;
//        this.allPrice=allPrice;
        ConfirmationInfo();
    }

    public void ConfirmationInfo(){

        if (user != null) {
            userLabel.setText(user.getName());
        }else {
            userLabel.setText("Null value");
        }
        if (flight != null) {

            fromLabel.setText(flight.getDeapartureAirport().getAirport_Name());
            toLabel.setText(flight.getArrivalAirport().getAirport_Name());
            aDateLabel.setText(flight.getArrivalTime().toString());
            dDateLabel.setText(flight.getDepartureTime().toString());

        }else {
            fromLabel.setText("Null value");
            toLabel.setText("Null value");
            aDateLabel.setText("Null value");
            dDateLabel.setText("Null value");
        }
        if (seats != null) {
            for (int i = 0; i < seats.size(); i++) {
                seatLabel.setText(seatLabel.getText() +", "+seats.get(i).getSeatId());
                classLabel.setText(classLabel.getText()+", "+seats.get(i).getSeatClass());
            }
        }else {
            seatLabel.setText("Null value");
            classLabel.setText("Null value");
        }
        if (payment != null) {
            methodLabel.setText(payment.getPaymentMethod());
            priceLabel.setText(String.valueOf(payment.getPaymentAmount()));

        }else {
            methodLabel.setText("Null value");
            priceLabel.setText("Null value");
        }


    }

    public void BackToPaymentGate(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            root = fxmlLoader.load();

            PaymentSceneController paymentSceneController = fxmlLoader.getController();
            paymentSceneController.PassingSeatDetailsValues(user,flight,seats,payment.getPaymentAmount(),payment,AvailbleSeats);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            System.out.println("Error When Go to payment Gate"+ex);
        }
    }
    public void confirmTheFlight(ActionEvent event) {
//        seat.setSeatStatus(false);
        for(int i=0;i<10;i++){
            for(int j=0;j<6;j++){
                if(this.flight.getSeats().contains(this.seats)){
                    this.flight.getSeats().get(i).get(j).setSeatStatus(false);
                    break;
                }
            }
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SeatSelection.fxml"));
            root = fxmlLoader.load();

            SeatSelectionController seatSelectionController= fxmlLoader.getController();
            seatSelectionController.paasingTheAvailbleSeats(--AvailbleSeats);
            seatSelectionController.passingFlight(this.flight);
            seatSelectionController.assignUser(user);


            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            System.out.println("Error When Confirm The FlightBooking "+ex);
        }
    }

}
