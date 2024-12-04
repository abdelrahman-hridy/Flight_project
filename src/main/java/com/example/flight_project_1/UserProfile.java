package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


   public void ShoeUserInfo(ActionEvent event) {
       username.setText("Username: " );
   }
   public void assignUser(Passenger user){
       this.user = user;
       System.out.println(user.getPhone());
   }
    public void backToSearchFlight(ActionEvent e) {
        Multi_used_methods.openFlightSearch(e, user);
    }
}
