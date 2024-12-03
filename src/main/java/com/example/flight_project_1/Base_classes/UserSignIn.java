package com.example.flight_project_1.Base_classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class UserSignIn implements Serializable {

    @FXML
    private TextField userin;
    @FXML
    private PasswordField passin;


    private Stage stage;
    private Scene scene;
    private Parent root;
    Alert alert;

//    public void login(ActionEvent event){
//        Multi_used_methods.openFlightSearch(event);
//    }

    public void submitLogin(ActionEvent e) {
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
                ObjectInputStream ois = ois = new ObjectInputStream(new FileInputStream("Passenger.txt"));
                Passenger p;
                while ((p = (Passenger) ois.readObject()) != null) {
                    if (username.equalsIgnoreCase(p.getName()) && password.equals(p.getPassword())) {
                        flag = true;
                        break;
                    }
                }
                ois.close();
            } catch (Exception e1) {
                System.out.println("Error When Declare ois in login" + e1);
            }
            if (flag) {
                System.out.println("Login Successful");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("searchFlightScene.fxml"));
                    root = loader.load();
                } catch (IOException exe) {
                    System.out.println("Can't Open searchFlightScene.fxml: " + exe);
                }
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Login Failed");
                alert.setContentText("Login Failed");
                alert.showAndWait();
            }
        }
    }
}
