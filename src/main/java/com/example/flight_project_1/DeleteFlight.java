package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteFlight implements Initializable {
    @FXML
    private ChoiceBox flightNameChoiceBox;
    @FXML
    private Label flightNumberLabel, flightDepartureLabel, flightArrivalLabel, flightPriceLabel,flightDepartureTimeLabel,flightArrivalTimeLabel,deleteFlightMessage;

    private ArrayList<String> flightNumbers = new ArrayList<>();
    private Flight choosenFlight;

    private Alert alert;

    public void initialize(URL location, ResourceBundle resources) {


        for(int i = 0; i < Files.getFlights().size(); i++)
        {
            flightNumbers.add(String.valueOf(Files.getFlights().get(i).getFlightNumber()));
        }


        flightNameChoiceBox.getItems().addAll(flightNumbers);



        flightNameChoiceBox.setOnAction(this::changeFlight);
    }

    public void changeFlight(Event event){
        for(int i = 0; i < Files.getFlights().size(); i++)
        {
            if(flightNameChoiceBox.getValue().equals(String.valueOf(Files.getFlights().get(i).getFlightNumber())))
            {
                choosenFlight = Files.getFlights().get(i);
            }
        }

        flightNumberLabel.setText("Airport Number: " + choosenFlight.getFlightNumber());
        flightDepartureLabel.setText("Departure Airport: " + choosenFlight.getDeapartureAirport().getAirport_Name());
        flightArrivalLabel.setText("Arrival Airport: " + choosenFlight.getArrivalAirport().getAirport_Name());
        flightDepartureTimeLabel.setText("Departure Time:" + choosenFlight.getDepartureTime());
        flightArrivalTimeLabel.setText("Arrival Time:" + choosenFlight.getArrivalTime());
        flightPriceLabel.setText("Flight Price: "+choosenFlight.getPrice());

        flightNumberLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
        flightNumberLabel.setWrapText(true);
        flightDepartureLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
        flightDepartureLabel.setWrapText(true);
        flightArrivalLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
        flightArrivalLabel.setWrapText(true);
        flightDepartureTimeLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
        flightDepartureTimeLabel.setWrapText(true);
        flightArrivalTimeLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
        flightArrivalTimeLabel.setWrapText(true);
        flightPriceLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
        flightPriceLabel.setWrapText(true);


        flightNumberLabel.getStyleClass().add("custom-label");
        flightDepartureLabel.getStyleClass().add("custom-label");
        flightArrivalLabel.getStyleClass().add("custom-label");
        flightDepartureTimeLabel.getStyleClass().add("custom-label");
        flightArrivalTimeLabel.getStyleClass().add("custom-label");
        flightPriceLabel.getStyleClass().add("custom-label");
        deleteFlightMessage.setVisible(false);
    }

    public void deleteFlight() {

        if (flightNameChoiceBox.getValue() != null && flightNameChoiceBox.getValue() != "") {
            boolean confirm;

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Are you sure you want to delete this Flight");
            alert.setContentText("Flight will removed permanently");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                confirm = true;
            } else {
                confirm = false;
            }
            if (confirm) {

                // Loop for passengers and return their mony
                ArrayList<Ticket> ticketsBack = new ArrayList<>();
                for (int i = 0; i < choosenFlight.getPassengers().size(); i++)
                {
                    Passenger passenger = null;
                    for(int j = 0; j < Files.getPassengers().size(); j++)
                    {
                        if(Files.getPassengers().get(j).getPassenger_ID().equals(choosenFlight.getPassengers().get(i).getPassenger_ID()))
                            passenger = Files.getPassengers().get(j);
                    }
                    for (int j = 0; j < passenger.getTickets().size(); j++)
                    {
                        Ticket ticket = passenger.getTickets().get(j);
                        if(ticket.getBookingTicket().getFlight().getFlightNumber() == choosenFlight.getFlightNumber())
                        {
                            passenger.setPocket(passenger.getPocket() + ticket.getBookingTicket().getBookingPrice());
                            ticketsBack.add(ticket);
                        }
                    }
                    for (int j = 0; j < ticketsBack.size(); j++) {
                        passenger.getTickets().remove(ticketsBack.get(j));
                    }
                }

                // delete Flight
                Files.getFlights().remove(choosenFlight);
                flightNameChoiceBox.setValue("");
                flightNumberLabel.setText("");
                flightPriceLabel.setText("");
                flightArrivalLabel.setText("");
                flightDepartureLabel.setText("");
                flightDepartureTimeLabel.setText("");
                flightArrivalTimeLabel.setText("");
                flightNumbers.remove(String.valueOf(choosenFlight.getFlightNumber()));
                flightNameChoiceBox.getItems().clear();
                flightNameChoiceBox.getItems().addAll(flightNumbers);

                flightNumberLabel.getStyleClass().clear();
                flightPriceLabel.getStyleClass().clear();
                flightArrivalLabel.getStyleClass().clear();
                flightDepartureLabel.getStyleClass().clear();
                flightDepartureTimeLabel.getStyleClass().clear();
                flightArrivalTimeLabel.getStyleClass().clear();
                deleteFlightMessage.setVisible(true);
            }

        }
    }

}
