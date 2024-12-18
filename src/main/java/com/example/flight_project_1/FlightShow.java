package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FlightShow{
    @FXML
    Label flightDetails, departureAirportLabel, departureTimeLabel, arrivalAirportLabel, arrivalTimeLabel, priceLabel, seatsLabel;
    @FXML
    Button backButton;
    Flight flight;
    private Passenger user;

    Stage stage;
    Scene scene;
    Parent root;

    public void setAll(Flight flight, Passenger user){
        this.flight = flight;
        this.user = user;
        departureAirportLabel.setText(departureAirportLabel.getText() + flight.getDeapartureAirport().getAirport_Name());
        arrivalAirportLabel.setText(arrivalAirportLabel.getText() + flight.getArrivalAirport().getAirport_Name());
        departureTimeLabel.setText(departureTimeLabel.getText() + flight.getDepartureTime().toString());
        arrivalTimeLabel.setText(arrivalTimeLabel.getText() + flight.getArrivalTime().toString());
        priceLabel.setText(priceLabel.getText() + flight.getPrice() + "$");
        int n = 0;
        for (int i = 0; i < 3; i++)
        {
            if((flight.getSeats())[i].isSeatStatus())
                n++;
        }
        seatsLabel.setText(seatsLabel.getText() + n);

        departureAirportLabel.getStyleClass().add("custom-label");
        arrivalAirportLabel.getStyleClass().add("custom-label");
        departureTimeLabel.getStyleClass().add("custom-label");
        arrivalTimeLabel.getStyleClass().add("custom-label");
        priceLabel.getStyleClass().add("custom-label");
        seatsLabel.getStyleClass().add("custom-label");

        flightDetails.setStyle("-fx-font-weight: bold;");

    }


    public void backToSearch(ActionEvent event) {
        Multi_used_methods.openFlightSearch(event, user);
    }
    public void transferToChooseSeat(ActionEvent event){
        Multi_used_methods.GoToChooseSeat(event, flight, user);
    }
    public void goToProfile(ActionEvent event){
        Multi_used_methods.GoToProfile(event, user, 2, flight);
    }
}
