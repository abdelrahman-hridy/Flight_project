package com.example.flight_project_1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private StackPane contentArea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("addAirport.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            System.out.println("Cant' Open addAirport.fxml");
        }
    }

    public void ToAddAirport(ActionEvent event){
        System.out.println("1");
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("addAirport.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            System.out.println("Cant' Open addAirport.fxml");
        }
    }
    public void ToDeleteAirport(ActionEvent event){
        System.out.println("2");

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("deleteAirport.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            System.out.println("Cant' Open deleteAirport.fxml");
        }
    }
    public void ToAddFlight(ActionEvent event){
        System.out.println("3");

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("addFlight.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            System.out.println("Cant' Open addFlight.fxml");
        }
    }
    public void ToDeleteFlight(ActionEvent event){
        System.out.println("4");

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("deleteFlight.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            System.out.println("Cant' Open deleteFlight.fxml");
        }
    }
}