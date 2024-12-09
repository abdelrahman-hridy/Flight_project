package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserProfile {
    private Parent root;
    private Scene scene;
    private Stage stage;
   @FXML
   private Label username;
   private Passenger user;
   private int sceneId;
   Flight flight;
   private int AvailbleSeats;

   public void assignFlight(Flight flight){
       this.flight = flight;
   }

   public void assignUser_sceneId(Passenger user, int sceneId){
       this.sceneId = sceneId;
       this.user = user;

   }
    public void backFromUserProfile(ActionEvent e) {
       // To Flight Search scene
       if(sceneId == 1)
            Multi_used_methods.openFlightSearch(e, user,AvailbleSeats);
       // To Flight Show scene
       else if(sceneId == 2)
           Multi_used_methods.GoToFlightShow(e, flight, user,AvailbleSeats);
       // To Flight Seat Selection scene
       else if (sceneId == 3)
           Multi_used_methods.GoToChooseSeat(e, flight, user,AvailbleSeats);

    }
}
