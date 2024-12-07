package com.example.flight_project_1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private StackPane contentArea;
    private Stage stage;
    private Parent root;

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
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("addAirport.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            System.out.println("Cant' Open addAirport.fxml");
        }
    }
    public void ToDeleteAirport(ActionEvent event){
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("deleteAirport.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            System.out.println("Cant' Open deleteAirport.fxml");
        }
    }
    public void ToAddFlight(ActionEvent event){
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("addFlight.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            System.out.println("Cant' Open addFlight.fxml");
        }
    }
    public void ToUpdateFlight(ActionEvent event){
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("updateFlight.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            System.out.println("Cant' Open updateFlight.fxml");
        }
    }
    public void ToDeleteFlight(ActionEvent event){
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("deleteFlight.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            System.out.println("Cant' Open deleteFlight.fxml");
        }
    }
    public void backToSignIn(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginInterface.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Cant' Open deleteFlight.fxml");
        }

    }
}
