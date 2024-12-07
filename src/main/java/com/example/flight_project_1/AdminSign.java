package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Admin;
import com.example.flight_project_1.Base_classes.Files;
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
import java.util.ArrayList;

public class AdminSign {
    @FXML
    private TextField userin;
    @FXML
    private PasswordField passin;
    @FXML
    Alert alert;

    private Stage stage;
    private Scene scene;
    private Parent root;



    public void submitLogin(ActionEvent e)  {
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
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Login Failed");
                alert.setContentText("Login Failed");
                alert.showAndWait();
            }
        }
    }

    public void backToSign(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userSign.fxml"));
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Can't Open userSign.fxml");
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
