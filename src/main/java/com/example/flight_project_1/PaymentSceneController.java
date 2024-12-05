package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import com.example.flight_project_1.Base_classes.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Serializable;

public class PaymentSceneController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    private Passenger user;
    private Flight flight;
    private Seat seat;

    public void assignUser(Passenger user){
        this.user = user;
    }

    public void passingFlight(Flight flight){
        this.flight = flight;
    }
    public void passingSeat(Seat seat){
        this.seat = seat;
    }

    public void BackToShowSeatDetails(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowSeatDetail.fxml"));
            root = fxmlLoader.load();

            ShowSeatDetail showSeatDetail = fxmlLoader.getController();
            showSeatDetail.assignUser(user);
            showSeatDetail.passingFlight(flight);
            showSeatDetail.passingTheSeat(seat);
            showSeatDetail.SetDataOfTheSeat(event);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            System.out.println("Error When Got To Show Seat Details"+ex);
        }
    }
}

