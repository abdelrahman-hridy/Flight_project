package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Files;
import com.example.flight_project_1.Base_classes.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class UserSignUp implements Serializable{

    private Parent root;
    private Stage stage;
    private Scene scene;

    Alert alert;
    @FXML
    private TextField userup;
    @FXML
    private PasswordField passup;
    @FXML
    private TextField contactUp;


    public void submitSignUp(ActionEvent e) {
        String username = userup.getText();
        String password = passup.getText();
        String contact = contactUp.getText();

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty() || contact == null || contact.trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username ,Contact Info Or Password Are Empty");
            alert.setContentText("Username ,Contact Info and Password are Required");
            alert.showAndWait();
        } else if (password.length() < 6) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Password is less than 6 characters");
            alert.setContentText("Password is required more than 6 chars");
            alert.showAndWait();
        } else {
            Passenger passenger;
            boolean flagOfNameNotFound = true;
           try {
                int size = Files.getPassengers().size();
                for (int i = 0; i < size; i++) {
                    if (username.toLowerCase().equals(Files.getPassengers().get(i).getName().toLowerCase())) {
                        flagOfNameNotFound = false;
                        break;
                    }
                }
            } catch (Exception exe) {
                System.out.println("Error when searching for a unique user"+exe);
            }
            if (!flagOfNameNotFound) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username is used ");
                alert.setContentText("Username must be unique");
                alert.showAndWait();
            } else {
                try {
                    Files.getPassengers().add(new Passenger(username, contact, password));
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("signInForm.fxml"));
                        root = loader.load();
                    } catch (IOException exe) {
                        System.out.println("Can't Open userSign.fxml"+exe);
                    }
                    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();

                } catch (Exception exception) {
                    System.out.println("Error in adding user: " + exception);
                }
            }
        }
    }
    public void backToSign(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userSign.fxml"));
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Can't Open userSign.fxml");
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
