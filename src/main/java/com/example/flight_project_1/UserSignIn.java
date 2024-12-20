package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Files;
import com.example.flight_project_1.Base_classes.Passenger;
import com.example.flight_project_1.Base_classes.Ticket;
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

public class UserSignIn {


    @FXML
    private TextField userin;
    @FXML
    private PasswordField passin;

    Alert alert;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void submitLogin(ActionEvent e)  {
        Passenger user = null;
        String username = userin.getText();
        String password = passin.getText();
        boolean flag = false;

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username Or Password Are Empty");
            alert.setContentText("Username and Password are Required");
            alert.showAndWait();
        } else {
            try {
                int size= Files.getAdmins().size();
                for(int i=0;i<size;i++){
                    if(username.equalsIgnoreCase(Files.getAdmins().get(i).getUsername()) && password.equalsIgnoreCase(Files.getAdmins().get(i).getPassword())){
                        flag=true;
                        break;
                    }
                }
            }catch (Exception exe){
                System.out.println("Error when login"+exe);
            }
            if (flag) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
                    root = loader.load();
                    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    scene.getStylesheets().add(Multi_used_methods.class.getResource("style.css").toExternalForm());
                    scene.getStylesheets().add(Multi_used_methods.class.getResource("buttonsStyle.css").toExternalForm());
                    scene.getStylesheets().add(Multi_used_methods.class.getResource("airportDelete.css").toExternalForm());
                    scene.getStylesheets().add(getClass().getResource("flightUpdate.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException exe) {
                    System.out.println("Can't Open serchFlightScene.fxml"+exe);
                }
            } else {
                try {
                    int size= Files.getPassengers().size();
                    for(int i=0;i<size;i++) {
                        if (username.toLowerCase().equals(Files.getPassengers().get(i).getName().toLowerCase()) && password.equals(Files.getPassengers().get(i).getPassword())) {
                            flag = true;
                            user = Files.getPassengers().get(i);
                            break;
                        }
                    }
                    if (flag) {
                        Multi_used_methods.openFlightSearch(e, user);
                        Ticket.setTicketNumbercounter(user.getTickets().size());
                    }
                    else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Login Failed");
                        alert.setContentText("Login Failed");
                        alert.showAndWait();
                    }
                }catch (Exception exe){
                    System.out.println("Error when login"+exe);
                }
            }
        }
    }

    public void goToSignUp(ActionEvent event) {
        System.out.println("Clicked Button");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signUpForm.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (
                IOException e) {
            System.out.println("Can't Open signUpForm.fxml");
        }
    }

}

