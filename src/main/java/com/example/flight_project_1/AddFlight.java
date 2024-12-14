package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Airport;
import com.example.flight_project_1.Base_classes.Files;
import com.example.flight_project_1.Base_classes.Flight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AddFlight implements Initializable {
    @FXML
    private Label departureAirportLabel, arrivalAirportLabel,  departureDateLabel, arrivalDateLabel, priceLabel;
    @FXML
    private ChoiceBox departureAirportChoiceBox, arrivalAirportChoiceBox;
    @FXML
    private DatePicker departureDatePicker, arrivalDatePicker;
    @FXML
    private Label priceInvalidMessage;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField departureHourTextField, departureMinuteTextField, arrivalHourTextField, arrivalMinuteTextField;
    @FXML
    private Label departureHourInvalidMessage, departureMinuteInvalidMessage, arrivalHourInvalidMessage, arrivalMinuteInvalidMessage;
    @FXML
    private Button  addButton;
    @FXML
    Label flightAddedSuccessfulyMessage, warningLabel;
    private Date departureDate = new Date();
    private Date arrivalDate = new Date();




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<String> airportsName = new ArrayList<>();
        for(int i = 0; i < Files.getAirports().size(); i++)
        {
            airportsName.add(Files.getAirports().get(i).getAirport_Name());
        }
        departureAirportChoiceBox.getItems().addAll(airportsName);
        arrivalAirportChoiceBox.getItems().addAll(airportsName);



        departureDateLabel.getStyleClass().add("l");
        arrivalDateLabel.getStyleClass().add("l");
        priceLabel.getStyleClass().add("l");
        departureAirportLabel.getStyleClass().add("l");
        arrivalAirportLabel.getStyleClass().add("l");
    }
    public void add(){
        departureHourInvalidMessage.setVisible(false);
        departureMinuteInvalidMessage.setVisible(false);
        if(departureDatePicker.getValue() != null && departureHourTextField != null && departureMinuteTextField != null &&
              arrivalDatePicker.getValue() != null && arrivalHourTextField != null && arrivalDatePicker != null &&
              departureAirportChoiceBox.getValue() != null && arrivalAirportChoiceBox.getValue() != null &&
              priceTextField.getText() != null ) {

            int hours1 = 0, minutes1 = 0;
            boolean isValidDate = true;
            // check if the value is string
            try {
                hours1 = Integer.parseInt(departureHourTextField.getText());
            } catch (Exception e) {
                departureHourInvalidMessage.setVisible(true);
                isValidDate = false;
            }
            try {
                minutes1 = Integer.parseInt(departureMinuteTextField.getText());
            } catch (Exception e) {
                departureMinuteInvalidMessage.setVisible(true);
                isValidDate = false;
            }
            if (isValidDate) {
                if (hours1 < 0 || hours1 > 24) {
                    departureHourInvalidMessage.setVisible(true);
                    isValidDate = false;
                }else if (minutes1 < 0 || minutes1 > 60) {
                    departureMinuteInvalidMessage.setVisible(true);
                    isValidDate = false;
                }else {
                    departureHourInvalidMessage.setVisible(false);
                    departureMinuteInvalidMessage.setVisible(false);
                }
                departureDate.setSeconds(0);
                departureDate.setHours(hours1);
                departureDate.setMinutes(minutes1);
                departureDate.setDate(departureDatePicker.getValue().getDayOfMonth());
                departureDate.setMonth(departureDatePicker.getValue().getMonthValue() - 1);
                departureDate.setYear(departureDatePicker.getValue().getYear() - 1900);
            }
            // arrival Date check
            int hours2 = 0, minutes2 = 0;
            try {
                hours2 = Integer.parseInt(arrivalHourTextField.getText());
            } catch (Exception e) {
                arrivalHourInvalidMessage.setVisible(true);
                isValidDate = false;
            }
            try {
                minutes2 = Integer.parseInt(arrivalMinuteTextField.getText());
            } catch (Exception e) {
                arrivalMinuteInvalidMessage.setVisible(true);
                isValidDate = false;
            }
            if (isValidDate) {
                if (hours2 < 0 || hours2 > 24) {
                    isValidDate = false;
                    arrivalHourInvalidMessage.setVisible(true);
                }                else if (minutes2 < 0 || minutes2 > 60) {
                    isValidDate = false;
                    arrivalMinuteInvalidMessage.setVisible(true);
                }else {
                    arrivalHourInvalidMessage.setVisible(false);
                    arrivalMinuteInvalidMessage.setVisible(false);
                }
                arrivalDate.setSeconds(0);
                arrivalDate.setHours(hours2);
                arrivalDate.setMinutes(minutes2);
                arrivalDate.setDate(arrivalDatePicker.getValue().getDayOfMonth());
                arrivalDate.setMonth(arrivalDatePicker.getValue().getMonthValue() - 1);
                arrivalDate.setYear(arrivalDatePicker.getValue().getYear() - 1900);
            }

            // price Text Field
            int flightPrice = 0;
            try {
                flightPrice = Integer.parseInt(priceTextField.getText());
                if(flightPrice <= 0) {
                    priceInvalidMessage.setVisible(true);
                    isValidDate = false;
                }else {
                    // The newValue is number and more than zero
                    if(priceInvalidMessage.isVisible())
                        priceInvalidMessage.setVisible(false);
                }
            }catch (Exception e)
            {
                priceInvalidMessage.setVisible(true);
                isValidDate = false;
            }

            // find departure and arrival Airport
            Airport departureAirport = null, arrivalAirport = null;
            for (int i = 0; i < Files.getAirports().size(); i++)
            {
                if(Files.getAirports().get(i).getAirport_Name().equals(departureAirportChoiceBox.getValue()))
                    departureAirport = Files.getAirports().get(i);
                if(Files.getAirports().get(i).getAirport_Name().equals(arrivalAirportChoiceBox.getValue()))
                    arrivalAirport = Files.getAirports().get(i);
            }

            if(departureDate.getTime() >= arrivalDate.getTime())
            {
                isValidDate = false;
                warningLabel.setText("The Arrival Time Must Be After Departure Time");
                warningLabel.setVisible(true);
            }
            else {
                warningLabel.setVisible(false);
            }

            Date currentDate = new Date();
            if(departureDate.before(currentDate) ||  arrivalDate.before(currentDate))
            {
                isValidDate = false;
                warningLabel.setText("Flight Time must be after current time");
                warningLabel.setVisible(true);
            }
            else {
                warningLabel.setVisible(false);
            }

            // confirm Adding after check all inputs

            if(isValidDate){
                Flight flight = new Flight(departureAirport, arrivalAirport, departureDate, arrivalDate, Files.getSeats(), flightPrice);
                Files.getFlights().add(flight);

                // clear all Inputs
                departureAirportChoiceBox.setValue(null);
                arrivalAirportChoiceBox.setValue(null);
                departureDatePicker.setValue(null);
                arrivalDatePicker.setValue(null);
                priceTextField.setText(null);
                departureHourTextField.setText(null);
                departureMinuteTextField.setText(null);
                arrivalHourTextField.setText(null);
                arrivalMinuteTextField.setText(null);

                flightAddedSuccessfulyMessage.setVisible(true);
                departureDate = new Date();
                arrivalDate = new Date();
                warningLabel.setVisible(false);
            }
        }
        else {
            warningLabel.setText("Complete All Data Required");
            warningLabel.setVisible(true);
        }
    }



}

