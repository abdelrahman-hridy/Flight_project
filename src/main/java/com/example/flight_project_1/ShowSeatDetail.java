package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import com.example.flight_project_1.Base_classes.Payment;
import com.example.flight_project_1.Base_classes.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowSeatDetail implements Serializable {
    private Passenger user;
    private Flight flight;
    private Payment payment=new Payment();
    private ArrayList <Seat> seats;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private double service = 0;
    private int AvailbleSeats;
    public String allprice;
    @FXML
    TextField SeatId;
    @FXML
    TextField SeatClass;
    @FXML
    TextField SeatPrice;
    @FXML
    TextField Service;
    @FXML
    TextField AllPrice;

    public void assignUser(Passenger user){
        this.user = user;
    }
    public void passingFlight(Flight flight){
        this.flight = flight;
    }
    public void passingTheSeats(ArrayList<Seat> seats){
        this.seats = seats;
    }
    public void passingPayment(Payment payment){this.payment = payment;}
    public void passingAvailbleSeats(int AvailbleSeats){this.AvailbleSeats = AvailbleSeats;}

    public void SetDataOfTheSeat(ActionEvent event){
        SeatId.setText("");
        for (int i = 0; i < seats.size(); i++) {
            if (i != seats.size() - 1)
                SeatId.setText(SeatId.getText() + seats.get(i).getSeatId() + " - ");
            else {
                SeatId.setText(SeatId.getText() + seats.get(i).getSeatId());
            }
        }
        SeatId.setEditable(false);
        SeatId.setPrefWidth(SeatId.getText().length() * 10.5F + 40);
        SeatClass.setText("");
        for (int i = 0; i < seats.size(); i++) {
            if (i != seats.size() - 1)
                SeatClass.setText(SeatClass.getText() + seats.get(i).getSeatClass() + " - ");
            else
                SeatClass.setText(SeatClass.getText() + seats.get(i).getSeatClass());
        }
        double AllSeatsPrice = 0;
        for (int i = 0; i < seats.size(); i++)
            AllSeatsPrice += seats.get(i).calcSeatPrice(flight);
        SeatPrice.setText(String.valueOf(AllSeatsPrice));
        SeatPrice.setEditable(false);

        double allPriceValue = 0;
        String ServiceString = "";
        for (int i = 0; i < seats.size(); i++) {
            int Seatservice;
            if (seats.get(i).getSeatId().startsWith("A") || seats.get(i).getSeatId().startsWith("F")) {
                Seatservice = 500;
                seats.get(i).setSeatservice(Seatservice);
                service += Seatservice;
                if (i != seats.size() - 1)
                    ServiceString += "Window Seat: " + Seatservice + " - ";
                else
                    ServiceString += "Window Seat: " + Seatservice;
                allPriceValue += seats.get(i).calcSeatPrice(flight) + Seatservice;
            }
            else if(seats.get(i).getSeatId().startsWith("B") || seats.get(i).getSeatId().startsWith("E")){
                Seatservice = 300;
                service += Seatservice;
                seats.get(i).setSeatservice(Seatservice);
                if (i != seats.size() - 1)
                    ServiceString += "Middle Seat: " + Seatservice + " - ";
                else
                    ServiceString += "Middle Seat: " + Seatservice;
                allPriceValue += seats.get(i).calcSeatPrice(flight) + Seatservice;
            }
            else{
                Seatservice = 100;
                seats.get(i).setSeatservice(Seatservice);
                service += Seatservice;
                if (i != seats.size() - 1)
                    ServiceString += "Way Seat: " + Seatservice + " - ";
                else
                    ServiceString += "Way Seat: " + Seatservice;
                allPriceValue += seats.get(i).calcSeatPrice(flight) + Seatservice;
            }
        }
        Service.setText(ServiceString);
        Service.setPrefWidth(ServiceString.length() * 9F + 30);
        Service.setEditable(false);

        allprice= String.valueOf(allPriceValue);
        AllPrice.setText(String.valueOf(allPriceValue));
        AllPrice.setEditable(false);

        SeatClass.setPrefWidth(SeatClass.getText().length() * 10.5F + 45);
        SeatClass.setEditable(false);
    }

    public void BackToSeats(ActionEvent event){
        Multi_used_methods.GoToChooseSeat(event, flight, user, AvailbleSeats);
    }
    public void ConfirmTheSeat(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            root = fxmlLoader.load();

            PaymentSceneController paymentSceneController = fxmlLoader.getController();
            paymentSceneController.PassingSeatDetailsValues(user,flight,seats,allprice,payment,AvailbleSeats);

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
