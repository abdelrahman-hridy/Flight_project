package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Airport;
import com.example.flight_project_1.Base_classes.Files;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class UpdateFlight implements Initializable {
    @FXML
    private ChoiceBox flightCodeChoice;
    @FXML
    private Label departureDateL, arrivalDateL, priceL;
    @FXML
    private Label  departureDateLabel, arrivalDateLabel, priceLabel;
    @FXML
    private Button departureDateEditButton, arrivalDateEditButton, priceEditButton;
    @FXML
    private DatePicker newDepartureDate, newArrivalDate;
    @FXML
    private Label priceInvalidMessage;
    @FXML
    private TextField newPrice;
    @FXML
    private TextField newDepartureHour, newDepartureMinute, newArrivalHour, newArrivalMinute;
    @FXML
    private Label departureHourInvalidMessage, departureMinuteInvalidMessage, arrivalHourInvalidMessage, arrivalMinuteInvalidMessage;
    @FXML
    private Label newDepartureDateLabel, newDepartureHourLabel, newDepartureMinuteLabel, newArrivalHourLabel, newArrivalMinuteLabel, newArrivalDateLabel, newPriceLabel;
    @FXML
    private Button  departureDateSubmitButton, arrivalDateSubmitButton, priceSubmitButton;
    @FXML
    private Label warningLabel;

    int index = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Integer> flightsCode = new ArrayList<>();
        for(int i = 0; i < Files.getFlights().size(); i++)
        {
            flightsCode.add(Files.getFlights().get(i).getFlightNumber());
        }
        flightCodeChoice.getItems().addAll(flightsCode);
        flightCodeChoice.setOnAction(this::changeFlightShown);
        // Edit Items visible until wee use them
        setVisibility(0, false);
        setVisibility(1, false);
        setVisibility(2, false);
        setVisibility(3, false);

        departureDateEditButton.setVisible(false);
        arrivalDateEditButton.setVisible(false);
        priceEditButton.setVisible(false);
        departureDateL.setVisible(false);
        arrivalDateL.setVisible(false);
        priceL.setVisible(false);
        departureDateLabel.setVisible(false);
        arrivalDateLabel.setVisible(false);
        priceLabel.setVisible(false);

        departureDateL.getStyleClass().add("l");
        arrivalDateL.getStyleClass().add("l");
        priceL.getStyleClass().add("l");

        departureDateLabel.getStyleClass().add("lLabel");
        arrivalDateLabel.getStyleClass().add("lLabel");
        priceLabel.getStyleClass().add("lLabel");
    }

    private void changeFlightShown(Event event) {
        if(!departureDateL.isVisible())
        {
            departureDateEditButton.setVisible(true);
            arrivalDateEditButton.setVisible(true);
            priceEditButton.setVisible(true);
            departureDateL.setVisible(true);
            arrivalDateL.setVisible(true);
            priceL.setVisible(true);
            departureDateLabel.setVisible(true);
            arrivalDateLabel.setVisible(true);
            priceLabel.setVisible(true);
        }
        // Assign Labels
        for(int i = 0; i < Files.getFlights().size(); i++)
        {
            if(Integer.parseInt(((Integer) flightCodeChoice.getValue()).toString()) == Files.getFlights().get(i).getFlightNumber())
                index = i;
        }
        departureDateLabel.setText(Files.getFlights().get(index).getDepartureTime().toString());
        arrivalDateLabel.setText(Files.getFlights().get(index).getArrivalTime().toString());
        priceLabel.setText(String.valueOf(Files.getFlights().get(index).getPrice()));
    }

    public void editDepartureDate(ActionEvent event){
        if(newDepartureDate.isVisible()) {
            setVisibility(0, false);
            departureHourInvalidMessage.setVisible(false);
            departureMinuteInvalidMessage.setVisible(false);
        }
        else {
            // set value in date picker (the current flight date)
            int currentYear = Files.getFlights().get(index).getDepartureTime().getYear() + 1900;
            int currentMonth = Files.getFlights().get(index).getDepartureTime().getMonth() + 1;
            int currentDay = Files.getFlights().get(index).getDepartureTime().getDate();
            int currentHour = Files.getFlights().get(index).getDepartureTime().getHours();
            int currentMinute = Files.getFlights().get(index).getDepartureTime().getMinutes();

            newDepartureDate.setValue(LocalDate.of(currentYear, currentMonth, currentDay));
            newDepartureHour.setText(String.valueOf(currentHour));
            newDepartureMinute.setText(String.valueOf(currentMinute));
            setVisibility(0, true);
        }

    }
    public void editArrivalDate(ActionEvent event){
        if(newArrivalDate.isVisible()) {
            setVisibility(1, false);
            arrivalHourInvalidMessage.setVisible(false);
            arrivalMinuteInvalidMessage.setVisible(false);
        }else {

            int currentYear = Files.getFlights().get(index).getArrivalTime().getYear() + 1900;
            int currentMonth = Files.getFlights().get(index).getArrivalTime().getMonth() + 1;
            int currentDay = Files.getFlights().get(index).getArrivalTime().getDate();
            int currentHour = Files.getFlights().get(index).getArrivalTime().getHours();
            int currentMinute = Files.getFlights().get(index).getArrivalTime().getMinutes();

            newArrivalDate.setValue(LocalDate.of(currentYear, currentMonth, currentDay));
            newArrivalHour.setText(String.valueOf(currentHour));
            newArrivalMinute.setText(String.valueOf(currentMinute));
            setVisibility(1, true);
        }
    }
    public void editPrice(ActionEvent event){
        if(newPrice.isVisible()) {
            setVisibility(2, false);
            priceInvalidMessage.setVisible(false);
        }        else {
            newPrice.setText(String.valueOf(Files.getFlights().get(index).getPrice()));
            setVisibility(2, true);
        }
    }

    public void submitDepartureDate(ActionEvent event){
        departureHourInvalidMessage.setVisible(false);
        departureMinuteInvalidMessage.setVisible(false);
        if(newDepartureDate.getValue() != null && newDepartureHour != null && newDepartureMinute != null) {
            int hours = 0, minutes = 0;
            boolean isValidDate = true;
            // check if the value is string
            try {
                hours = Integer.parseInt(newDepartureHour.getText());
            }catch (Exception e)
            {
                departureHourInvalidMessage.setVisible(true);
                isValidDate = false;
            }
            try {
                minutes = Integer.parseInt(newDepartureMinute.getText());
            }catch (Exception e)
            {
                departureMinuteInvalidMessage.setVisible(true);
                isValidDate = false;
            }
            if(isValidDate){
                if(hours < 0 || hours > 24)
                    departureHourInvalidMessage.setVisible(true);
                else if(minutes < 0 || minutes > 60)
                    departureMinuteInvalidMessage.setVisible(true);
                else {
                    departureHourInvalidMessage.setVisible(false);
                    departureMinuteInvalidMessage.setVisible(false);

                    Date newDate = new Date();
                    newDate.setHours(hours);
                    newDate.setMinutes(minutes);
                    newDate.setDate(newDepartureDate.getValue().getDayOfMonth());
                    newDate.setMonth(newDepartureDate.getValue().getMonthValue() - 1);
                    newDate.setYear(newDepartureDate.getValue().getYear() - 1900);
                    newDate.setSeconds(0);

                    if(newDate.getTime() >= Files.getFlights().get(index).getArrivalTime().getTime())
                    {
                        warningLabel.setText("The Arrival Time Must Be After Departure Time");
                        warningLabel.setVisible(true);
                    }
                    else {
                        Files.getFlights().get(index).setDepartureTime(newDate);
                        changeFlightShown(event);
                        setVisibility(0, false);
                        newDepartureDate.setValue(null);
                        newDepartureHour.setText(null);
                        newDepartureMinute.setText(null);
                        warningLabel.setVisible(false);
                    }

                }
            }
        }
    }
    public void submitArrivalDate(ActionEvent event){
        arrivalHourInvalidMessage.setVisible(false);
        arrivalMinuteInvalidMessage.setVisible(false);
        if(newArrivalDate.getValue() != null && newArrivalHour != null && newArrivalMinute != null) {
            int hours1 = 0, minutes1 = 0;
            boolean isValidDate1 = true;
            // check if the value is string
            try {
                hours1 = Integer.parseInt(newArrivalHour.getText());
            } catch (Exception e) {
                arrivalHourInvalidMessage.setVisible(true);
                isValidDate1 = false;
            }
            try {
                minutes1 = Integer.parseInt(newArrivalMinute.getText());
            } catch (Exception e) {
                arrivalMinuteInvalidMessage.setVisible(true);
                isValidDate1 = false;
            }

            if (isValidDate1){
                if (hours1 < 0 || hours1 > 24)
                    arrivalHourInvalidMessage.setVisible(true);
                else if (minutes1 < 0 || minutes1 > 60)
                    arrivalMinuteInvalidMessage.setVisible(true);
                else {
                    arrivalHourInvalidMessage.setVisible(false);
                    arrivalHourInvalidMessage.setVisible(false);

                    Date newDate = new Date();
                    newDate.setHours(hours1);
                    newDate.setMinutes(minutes1);
                    newDate.setDate(newArrivalDate.getValue().getDayOfMonth());
                    newDate.setMonth(newArrivalDate.getValue().getMonthValue() - 1);
                    newDate.setYear(newArrivalDate.getValue().getYear() - 1900);
                    newDate.setSeconds(0);

                    if(newDate.getTime() <= Files.getFlights().get(index).getDepartureTime().getTime())
                    {
                        warningLabel.setText("The Arrival Time Must Be After Departure Time");
                        warningLabel.setVisible(true);
                    }
                    else {
                        Files.getFlights().get(index).setArrivalTime(newDate);
                        changeFlightShown(event);
                        setVisibility(1, false);
                        newArrivalDate.setValue(null);
                        newArrivalHour.setText(null);
                        newArrivalMinute.setText(null);
                        warningLabel.setVisible(false);
                    }

                }
            }
        }
    }
    public void submitPrice(ActionEvent event){

        if(newPrice.getText() != null) {
            int newValue = 0;
            // check if the value is string
            try {
                newValue = Integer.parseInt(newPrice.getText());
                if(newValue < 0)
                    priceInvalidMessage.setVisible(true);
                else {
                    // The newValue is number and more than zero
                    if(priceInvalidMessage.isVisible())
                        priceInvalidMessage.setVisible(false);
                    Files.getFlights().get(index).setPrice(newValue);
                    changeFlightShown(event);
                    setVisibility(2, false);
                    newPrice.setText(null);
                }
            }catch (Exception e)
            {
                priceInvalidMessage.setVisible(true);
            }
        }
    }

    public void setVisibility(int i, boolean condition){
        if(i == 0){
            newDepartureDate.setVisible(condition);
            newDepartureHour.setVisible(condition);
            newDepartureMinute.setVisible(condition);
            newDepartureDateLabel.setVisible(condition);
            newDepartureHourLabel.setVisible(condition);
            newDepartureMinuteLabel.setVisible(condition);
            departureDateSubmitButton.setVisible(condition);
        }
        else if(i == 1){
            newArrivalDate.setVisible(condition);
            newArrivalHour.setVisible(condition);
            newArrivalMinute.setVisible(condition);
            newArrivalHourLabel.setVisible(condition);
            newArrivalMinuteLabel.setVisible(condition);
            newArrivalDateLabel.setVisible(condition);
            arrivalDateSubmitButton.setVisible(condition);
        }
        else if(i == 2)
        {
            newPrice.setVisible(condition);
            newPriceLabel.setVisible(condition);
            priceSubmitButton.setVisible(condition);
        }
    }

}

