package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteAirport implements Initializable {
    @FXML
    private ChoiceBox airportNameChoiceBox;
    @FXML
    private Label airportNameLabel, airportCodeLabel, airportLocationLabel, deleteAirportMessage;

    private ArrayList<String> airportsName = new ArrayList<>();
    private Airport choosenAirport;

    private Alert alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        for(int i = 0; i < Files.getAirports().size(); i++)
        {
            airportsName.add(Files.getAirports().get(i).getAirport_Name());
        }


        airportNameChoiceBox.getItems().addAll(airportsName);



        airportNameChoiceBox.setOnAction(this::changeAirport);
    }

    public void changeAirport(Event event){
        for(int i = 0; i < Files.getAirports().size(); i++)
        {
            if(airportNameChoiceBox.getValue().equals(Files.getAirports().get(i).getAirport_Name()))
            {
                choosenAirport = Files.getAirports().get(i);
            }
        }

        airportNameLabel.setText("Airport Name: " + choosenAirport.getAirport_Name());
        airportCodeLabel.setText("Airport Code: " + choosenAirport.getAirport_code());
        airportLocationLabel.setText("Airport Location: " + choosenAirport.getLocation());

        airportNameLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
        airportNameLabel.setWrapText(true);
        airportCodeLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
        airportCodeLabel.setWrapText(true);
        airportLocationLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
        airportLocationLabel.setWrapText(true);

        airportNameLabel.getStyleClass().add("custom-label");
        airportCodeLabel.getStyleClass().add("custom-label");
        airportLocationLabel.getStyleClass().add("custom-label");
        deleteAirportMessage.setVisible(false);

    }

    public void deleteAirport(){

        if(airportNameChoiceBox.getValue() != null && airportNameChoiceBox.getValue() != "") {
            boolean confirm;

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Are you sure you want to delete this airport");
            alert.setContentText("Airport will removed permanently");
            Optional<ButtonType> result = alert.showAndWait();

            // Check user response
            if (result.isPresent() && result.get() == ButtonType.OK) {
                confirm = true;
            } else {
                confirm = false;
            }

            if (confirm) {
                // Delete All Flights
                int flightSize = Files.getFlights().size();
                ArrayList<Flight> deletedFlights = new ArrayList<>();
                for (int k = 0; k < flightSize; k++)
                {
                    if(Files.getFlights().get(k).getDeapartureAirport().getAirport_code() == choosenAirport.getAirport_code() || Files.getFlights().get(k).getArrivalAirport().getAirport_code() == choosenAirport.getAirport_code())
                    {
                        Flight flight = Files.getFlights().get(k);
                        for (int i = 0; i < flight.getPassengers().size(); i++)
                        {
                            Passenger passenger = null;
                            for(int j = 0; j < Files.getPassengers().size(); j++) {
                                if (Files.getPassengers().get(j).getPassenger_ID().equals(flight.getPassengers().get(i).getPassenger_ID()))
                                    passenger = Files.getPassengers().get(j);
                            }
                            for (int j = 0; j < passenger.getTickets().size(); j++)
                            {
                                Ticket ticket = passenger.getTickets().get(j);
                                passenger.setPocket(passenger.getPocket() + ticket.getBookingTicket().getBookingPrice());
                                passenger.getTickets().remove(ticket);
                            }
                        }
                        deletedFlights.add(flight);
                    }
                }
                for(int i = 0; i < deletedFlights.size(); i++)
                {
                    Files.getFlights().remove(deletedFlights.get(i));
                }


                Files.getAirports().remove(choosenAirport);
                airportNameChoiceBox.setValue("");
                airportNameLabel.setText("");
                airportCodeLabel.setText("");
                airportLocationLabel.setText("");
                airportsName.remove(choosenAirport.getAirport_Name());
                airportNameChoiceBox.getItems().clear();
                airportNameChoiceBox.getItems().addAll(airportsName);

                airportNameLabel.getStyleClass().clear();
                airportCodeLabel.getStyleClass().clear();
                airportLocationLabel.getStyleClass().clear();
                deleteAirportMessage.setVisible(true);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(3), e -> deleteAirportMessage.setVisible(false))
                );
                timeline.setCycleCount(1); // Run only once
                timeline.play();
            }
        }
    }

}
