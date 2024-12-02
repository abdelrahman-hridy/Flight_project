package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Airport;
import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class searchFlightController implements Initializable, Serializable {

    @FXML
    private TableColumn<FlightString, String> departureAirport;
    @FXML
    private TableColumn<FlightString, String> arrivalAirport;
    @FXML
    private TableColumn<FlightString, String> departureTime;
    @FXML
    private TableColumn<FlightString, String> arrivalTime;
    @FXML
    private TableColumn<FlightString, String> flightDuration;
    @FXML
    private TableColumn<FlightString, Integer> price;
    @FXML
    private TableColumn<FlightString, String> Action;
    @FXML
    private TableView<FlightString> myTable;
    ObservableList<FlightString> data;
    @FXML
    private ChoiceBox<String> departureAirportChoiceBox, arrivalAirportChoiceBox;
    @FXML
    private DatePicker departureDatePicker, arrivalDatePicker;

    Passenger p1 = new Passenger("Abdo", "01001277917", 5);

    ArrayList<Flight>flightsFiltered = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = FXCollections.observableArrayList();
        myTable.setItems(data);
        departureAirport.setCellValueFactory(new PropertyValueFactory<FlightString, String>("departureAirportName"));
        arrivalAirport.setCellValueFactory(new PropertyValueFactory<FlightString, String>("arrivalAirportName"));
        departureTime.setCellValueFactory(new PropertyValueFactory<FlightString, String>("departureTime"));
        arrivalTime.setCellValueFactory(new PropertyValueFactory<FlightString, String>("arrivalTime"));
        flightDuration.setCellValueFactory(new PropertyValueFactory<FlightString, String>("flight_Duration"));
        price.setCellValueFactory(new PropertyValueFactory<FlightString, Integer>("price"));
        Action.setCellValueFactory(new PropertyValueFactory<FlightString, String>("button"));

        Label placeholderLabel = new Label("No Flights Available With Your Demands");
        placeholderLabel.setFont(Font.font("Arial", 20));  // Set font size and family
        placeholderLabel.setTextFill(Color.RED);          // Set text color

        // Set the custom placeholder
        myTable.setPlaceholder(placeholderLabel);

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
                    , String.valueOf(hours) + ":" + String.valueOf(minutes), flightsFiltered.get(i).getPrice(), i);
            assert false;
            data.add(row);
            row.getButton().setId(String.valueOf(i));
            row.getButton().getStyleClass().add("tableButton");
            row.getButton().setOnAction(this::handleButtonClick);
        }


    }
    // Move to Show Flight Details Scene
    public void handleButtonClick(ActionEvent event) {

        {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("flightShow.fxml"));
                root = loader.load();

                FlightShow flightShow = loader.getController();
                flightShow.setAll(flightsFiltered.get(Integer.parseInt(((Button) event.getSource()).getId())));

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("flightShow.css").toExternalForm());
                scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
                stage.setScene(scene);
                stage.show();

            } catch (
                    IOException e) {
                System.out.println("Can't Open flightShow.fxml");
            }

        }
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
                    , String.valueOf(hours) + ":" + String.valueOf(minutes), flightsFiltered.get(i).getPrice(), i);
            assert false;
            data.add(row);
            row.getButton().setId(String.valueOf(i));
            row.getButton().getStyleClass().add("tableButton");
            row.getButton().setOnAction(this::handleButtonClick);

        }
    }

    public void backToSignIn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signInForm.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (
                IOException e) {
            System.out.println("Can't Open signInForm.fxml");
        }
    }
    public void clearDepartureDate(){
        departureDatePicker.setValue(null);
    }
    public void clearArrivalDate(){
        arrivalDatePicker.setValue(null);
    }
}
