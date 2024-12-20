package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Multi_used_methods {

    private static Stage stage;
    private static Scene scene;
    private static Parent root;


    public static void openFlightSearch(ActionEvent event, Passenger user) {
        try {
            FXMLLoader loader = new FXMLLoader(Multi_used_methods.class.getResource("searchFlightScene.fxml"));
            root = loader.load();

            searchFlightController sF = loader.getController();
            sF.assignUser(user);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(Multi_used_methods.class.getResource("style.css").toExternalForm());
            scene.getStylesheets().add(Multi_used_methods.class.getResource("buttonLogoutStyle.css").toExternalForm());

            stage.setScene(scene);
            stage.show();
        } catch (IOException exe) {
            System.out.println("Can't Open serchFlightScene.fxml"+exe);
        }
    }

    public static void GoToProfile(ActionEvent event, Passenger user){
        try {
            FXMLLoader loader = new FXMLLoader(Multi_used_methods.class.getResource("userProfileScene.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            UserProfile Up = loader.getController();
            Up.assignUser_sceneId(user);

            scene = new Scene(root);
            scene.getStylesheets().add(Multi_used_methods.class.getResource("buttonsStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (
                IOException e) {
            System.out.println("Can't Open userProfileScene.fxml");
        }
    }

    public static void GoToFlightShow(ActionEvent event, Flight flight, Passenger user,int AvailbleSeats){
        try {
            FXMLLoader loader = new FXMLLoader(Multi_used_methods.class.getResource("flightShow.fxml"));
            root = loader.load();

            FlightShow flightShow = loader.getController();
            flightShow.setAll(flight, user, AvailbleSeats);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(Multi_used_methods.class.getResource("flightShow.css").toExternalForm());
            scene.getStylesheets().add(Multi_used_methods.class.getResource("buttonsStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Can't Open flightShow.fxml");
        }
    }

    public static void GoToChooseSeat(ActionEvent event, Flight flight, Passenger user,int AvailbleSeats){
        try {
            FXMLLoader loader = new FXMLLoader(Multi_used_methods.class.getResource("SeatSelection.fxml"));
            root = loader.load();

            SeatSelectionController seatSelectionController = loader.getController();
            seatSelectionController.passingFlight(flight);
            seatSelectionController.assignUser(user);
            seatSelectionController.paasingTheAvailbleSeats(AvailbleSeats);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(Multi_used_methods.class.getResource("buttonsStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't Open SeatSelection.fxml"+e);
        }
    }

}