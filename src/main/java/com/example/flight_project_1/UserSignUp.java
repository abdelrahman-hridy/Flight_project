package com.example.flight_project_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserSignUp {

    @FXML
    private TextField nameTextField, phoneTextField;
    @FXML
    private Button loginButton, backButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event){
        System.out.println("Login Successfully");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchFlightScene.fxml"));
            root = loader.load();

        } catch (
                IOException e) {
            System.out.println("Can't Open searchFlightScene.fxml");
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        stage.setScene(scene);
        stage.show();
    }
}
