package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UserSign {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button signInButton, signUpButton;

    public void userSignIn(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signInForm.fxml"));
            root = loader.load();

        } catch (
        IOException e) {
            System.out.println("Can't Open signInForm.fxml");
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
    public void userSignUp(ActionEvent event){
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
