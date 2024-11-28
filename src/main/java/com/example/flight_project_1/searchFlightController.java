package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Airport;
import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class searchFlightController implements Initializable, Serializable {

    @FXML
    private TableColumn<FlightString, String> departureAirport, arrivalAirport, departureTime, arrivalTime, flightDuration, price, Action;
    @FXML
    private TableView<FlightString> myTable;
    ObservableList<FlightString> data;
    @FXML
    private ChoiceBox<String> departureAirportChoiceBox, arrivalAirportChoiceBox;
    @FXML
    private DatePicker departureDatePicker, arrivalDatePicker;

    Passenger p1 = new Passenger("Abdo", "01001277917", 5);

    ArrayList<Flight>flightsFiltered = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = FXCollections.observableArrayList();
        myTable.setItems(data);
        departureAirport.setCellValueFactory(new PropertyValueFactory<FlightString, String>("departureAirportName"));
        arrivalAirport.setCellValueFactory(new PropertyValueFactory<FlightString, String>("arrivalAirportName"));
        departureTime.setCellValueFactory(new PropertyValueFactory<FlightString, String>("departureTime"));
        arrivalTime.setCellValueFactory(new PropertyValueFactory<FlightString, String>("arrivalTime"));
        flightDuration.setCellValueFactory(new PropertyValueFactory<FlightString, String>("flight_Duration"));
        price.setCellValueFactory(new PropertyValueFactory<FlightString, String>("price"));
        Action.setCellValueFactory(new PropertyValueFactory<FlightString, String>("button"));

        myTable.setStyle("-fx-font-size: 16px;");

        ObjectInputStream ois = null;
        ArrayList<Airport> airports = new ArrayList<>();
        try {
            try {
                File file1 = new File("Airports.txt");
                ois = new ObjectInputStream(new FileInputStream(file1));
            } catch (IOException e) {
                System.out.println("Can't Find Airport.txt");
            }
            try {
                airports = (ArrayList<Airport>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        ArrayList<String> airportsName = new ArrayList<>();
        for(int i = 0; i < airports.size(); i++)
        {
            airportsName.add(airports.get(i).getAirport_Name());
        }
        departureAirportChoiceBox.getItems().add("~All~");
        arrivalAirportChoiceBox.getItems().add("~All~");
        departureAirportChoiceBox.getItems().addAll(airportsName);
        arrivalAirportChoiceBox.getItems().addAll(airportsName);

        departureAirportChoiceBox.setValue("~All~");
        arrivalAirportChoiceBox.setValue("~All~");

        departureAirportChoiceBox.setOnAction(this::changeDepartureAirport);
        arrivalAirportChoiceBox.setOnAction(this::changeDepartureAirport);
        departureDatePicker.setOnAction(this::changeDepartureAirport);
        arrivalDatePicker.setOnAction(this::changeDepartureAirport);


//        departureDatePicker.setValue(LocalDate.now());
//        arrivalDatePicker.setValue(LocalDate.now());



        try {
            flightsFiltered = p1.flightSearch("~All~", "~All~",
                    0, 0, 0,
                    0, 0,0);
        } catch (Exception e) {
            System.out.println("Can't do flight search function");
        }
        FlightString row;
        for(int i = 0; i < flightsFiltered.size(); i++) {
            long duration = flightsFiltered.get(i).getArrivalTime().getTime() - flightsFiltered.get(i).getDepartureTime().getTime();
            long minutes = (duration / (1000 * 60)) % 60;
            long hours = duration / (1000 * 60 * 60);
            row = new FlightString(flightsFiltered.get(i).getDeapartureAirport().getAirport_Name(), flightsFiltered.get(i).getArrivalAirport().getAirport_Name(),
                    flightsFiltered.get(i).getDepartureTime().toString(), flightsFiltered.get(i).getArrivalTime().toString()
                    , String.valueOf(hours) + ":" + String.valueOf(minutes), String.valueOf(flightsFiltered.get(i).getPrice()), i);
            assert false;
            data.add(row);
            row.getButton().setId("Button" + (i + 1));
            row.getButton().setOnAction(event -> {
                // Identify the button clicked using its ID
                Button clickedButton = (Button) event.getSource();
                String buttonId = clickedButton.getId();
                handleButtonClick(buttonId); // Pass ID to the handler method
            });
        }


    }
    public void handleButtonClick(String buttonId) {
        System.out.println("Clicked: " + buttonId);

    }
    public void changeDepartureAirport(ActionEvent ec){
        data.clear();
        if(departureDatePicker.getValue() != null && arrivalDatePicker.getValue() != null) {
            try {
                flightsFiltered = p1.flightSearch(departureAirportChoiceBox.getValue(), arrivalAirportChoiceBox.getValue(),
                        departureDatePicker.getValue().getYear(), departureDatePicker.getValue().getMonthValue(),
                        departureDatePicker.getValue().getDayOfMonth(), arrivalDatePicker.getValue().getYear(), arrivalDatePicker.getValue().getMonthValue(),
                        arrivalDatePicker.getValue().getDayOfMonth());
            } catch (Exception e) {
                System.out.println("Can't do flight search function");
            }
        }
        else if(departureDatePicker.getValue() == null && arrivalDatePicker.getValue() == null)
        {
            try {
                flightsFiltered = p1.flightSearch(departureAirportChoiceBox.getValue(), arrivalAirportChoiceBox.getValue(),
                        0, 0, 0,
                        0, 0,0);
            } catch (Exception e) {
                System.out.println("Can't do flight search function");
            }
        }
        else if(departureDatePicker.getValue() == null){
            try {
                flightsFiltered = p1.flightSearch(departureAirportChoiceBox.getValue(), arrivalAirportChoiceBox.getValue(),
                        0, 0, 0,
                        arrivalDatePicker.getValue().getYear(), arrivalDatePicker.getValue().getMonthValue(),
                        arrivalDatePicker.getValue().getDayOfMonth());
            } catch (Exception e) {
                System.out.println("Can't do flight search function");
            }
        }
        else if(arrivalDatePicker.getValue() == null){
            try {
                flightsFiltered = p1.flightSearch(departureAirportChoiceBox.getValue(), arrivalAirportChoiceBox.getValue(),
                        departureDatePicker.getValue().getYear(), departureDatePicker.getValue().getMonthValue(),
                        departureDatePicker.getValue().getDayOfMonth(),
                        0, 0, 0);
            } catch (Exception e) {
                System.out.println("Can't do flight search function");
            }
        }

        //Fill the Table
        FlightString row;
        for(int i = 0; i < flightsFiltered.size(); i++) {
            long duration = flightsFiltered.get(i).getArrivalTime().getTime() - flightsFiltered.get(i).getDepartureTime().getTime();
            long minutes = (duration / (1000 * 60)) % 60;
            long hours = duration / (1000 * 60 * 60);
            row = new FlightString(flightsFiltered.get(i).getDeapartureAirport().getAirport_Name(), flightsFiltered.get(i).getArrivalAirport().getAirport_Name(),
                    flightsFiltered.get(i).getDepartureTime().toString(), flightsFiltered.get(i).getArrivalTime().toString()
                    , String.valueOf(hours) + ":" + String.valueOf(minutes), String.valueOf(flightsFiltered.get(i).getPrice()), i);
            assert false;
            data.add(row);
            row.getButton().setId("Button" + (i + 1));
            row.getButton().setOnAction(event -> {
                // Identify the button clicked using its ID
                Button clickedButton = (Button) event.getSource();
                String buttonId = clickedButton.getId();
                handleButtonClick(buttonId); // Pass ID to the handler method
            });
        }
    }

}
