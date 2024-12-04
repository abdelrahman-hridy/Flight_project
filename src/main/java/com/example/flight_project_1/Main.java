package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main extends Application {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ArrayList<Admin> admins = new ArrayList<>();
        admins.add(new Admin("Sohaib", "55435421"));
        admins.add(new Admin("Tony", "55435422"));

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Admins.txt"));
        oos.writeObject(admins);
//        ArrayList<Airport> airports = new ArrayList<>();
//        airports.add(new Airport(1, "Cairo", "Egypt"));
//        airports.add(new Airport(2, "London", "England"));
//        airports.add(new Airport(3, "Los Angles", "USA"));
//        airports.add(new Airport(4, "Berlin", "England"));
//
//        Seat seats[] = new Seat[3];
//        seats[0] = new Seat(1, "High", true);
//        seats[1] = new Seat(2, "Medium", true);
//        seats[2] = new Seat(3, "Low", true);
//
//        Passenger passengers[] = new Passenger[3];
//        passengers[0] = new Passenger("Sohaib", "01067700658", 1);
//        passengers[1] = new Passenger("Shehab", "01151692506", 2);
//        passengers[2] = new Passenger("Tony", "01225641027", 3);
//
//        ArrayList<Flight> flights_to_Write = new ArrayList<>();
//        flights_to_Write.add(new Flight(1, airports.get(0), airports.get(1), new Date(2024 - 1900, Calendar.NOVEMBER, 4, 6, 0),
//                new Date(2024 - 1900, Calendar.NOVEMBER, 4, 7, 20), seats, 2000));
//        flights_to_Write.add(new Flight(2, airports.get(1), airports.get(2), new Date(2024 - 1900, Calendar.NOVEMBER, 5, 5, 0),
//                new Date(2024 - 1900, Calendar.NOVEMBER, 5, 7, 0), seats, 3000));
//        flights_to_Write.add(new Flight(3, airports.get(2), airports.get(3), new Date(2024 - 1900, Calendar.NOVEMBER, 6, 8, 0),
//                new Date(2024 - 1900, Calendar.NOVEMBER, 6, 10, 0), seats, 4000));
//        flights_to_Write.add(new Flight(4, airports.get(3), airports.get(0), new Date(2024 - 1900, Calendar.NOVEMBER, 5, 2, 0),
//                new Date(2024 - 1900, Calendar.NOVEMBER, 5, 5, 0), seats, 1000));
//
//        File file = new File("Flights.txt");
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//        oos.writeObject(flights_to_Write);
//        oos.flush();
//        oos.close();
//
//        File file1 = new File("Airports.txt");
//        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(file1));
//        oos1.writeObject(airports);
//        oos1.flush();
//        oos1.close();
//
//        Flight flight = new Flight(5, airports.get(3), airports.get(1), new Date(2024 - 1900, Calendar.JULY, 4, 12, 0),
//                new Date(2024 - 1900, Calendar.JULY, 4, 14, 0),seats, 20000);
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//        ArrayList<Flight> flights = (ArrayList<Flight>) ois.readObject();
//
//        flights.add(flight);
//
//        oos = new ObjectOutputStream(new FileOutputStream(file));
//        oos.writeObject(flights);
//        oos.flush();
//        oos.close();


        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginInterface.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
            stage.setScene(scene);
        }catch (Exception e)
        {
            System.out.println("Can't see loginInterface");
        }
        stage.setTitle("Hello GUI");
        try {
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("DALL·E-2024-12-01-20.54.png"))));
        } catch (Exception e) {
            System.out.println("Can't open plane picture");
        }

        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });

//        stage.setOnCloseRequest(event ->{
//            event.consume();
//            logout(stage);
//        });
    }
    public void logout(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logut");
        alert.setHeaderText("You 're about to logout");
        alert.setContentText("Do you want to save before exiting?: ");

        if(alert.showAndWait().get() == ButtonType.OK){
            System.out.println("You Succesfully logged out!");
            stage.close();
        }

    }
}
