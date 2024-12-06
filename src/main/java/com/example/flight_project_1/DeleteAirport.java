package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Airport;
import com.example.flight_project_1.Base_classes.Files;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

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
            }
        }
    }

}
