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
            Passenger user;
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
            try {
                int size = Files.getAdmins().size();
                for (int i = 0; i < size; i++) {
                    if (username.toLowerCase().equals(Files.getAdmins().get(i).getUsername().toLowerCase())) {
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
//<<<<<<< HEAD
                    user=new Passenger(username,contact,password);
                    Files.getPassengers().add(user);

//>>>>>>> b6e6bacd634dffbfc4a111800bb9b7e766e4f5d2
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
    public void backToSignIn(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signInForm.fxml"));
            root = loader.load();

        } catch (
                IOException e) {
            System.out.println("Can't Open signInForm.fxml");
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("buttonsStyleSignIn.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
