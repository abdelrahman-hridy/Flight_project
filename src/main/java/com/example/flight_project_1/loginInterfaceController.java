package com.example.flight_project_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class loginInterfaceController {
    @FXML
    private Button userButton, adminButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void userEnter(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userSign.fxml"));
            root = loader.load();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void adminEnter(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
            root = loader.load();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
