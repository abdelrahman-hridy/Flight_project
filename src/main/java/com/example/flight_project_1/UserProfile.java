package com.example.flight_project_1;

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
   public void ShoeUserInfo(ActionEvent event) {
       username.setText("Username: " );
   }
    public void backToSearchFlight(ActionEvent e){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchFlightScene.fxml"));
            root=fxmlLoader.load();
        }catch (Exception ex){
            System.out.println("Error on go Back to search Flight"+ex);
        }
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
