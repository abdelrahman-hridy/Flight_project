package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Multi_used_methods {

    static Stage stage;
    static Scene scene;
    static Parent root;

    public static void openFlightSearch(ActionEvent event, Passenger user){
        try {
            FXMLLoader loader = new FXMLLoader(Multi_used_methods.class.getResource("searchFlightScene.fxml"));
            root = loader.load();

            searchFlightController sF = loader.getController();
            sF.assignUser(user);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(Multi_used_methods.class.getResource("style.css").toExternalForm());
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
            Up.assignUser(user);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (
                IOException e) {
            System.out.println("Can't Open userProfileScene.fxml");
        }
    }

}