package com.example.flight_project_1;

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

public class UserSignIn {


    @FXML
    private TextField userin;
    @FXML
    private PasswordField passin;
    @FXML
    Alert alert;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ArrayList<Passenger>passengers=new ArrayList<>();


    public void submitLogin(ActionEvent e)  {
        String username = userin.getText();
        String password = passin.getText();
        Passenger p =new Passenger(); // Passenger
        boolean flag = false;
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username Or Password Are Empty");
            alert.setContentText("Username and Password are Required");
            alert.showAndWait();
        } else {
            try {
                FileInputStream fis = new FileInputStream("Passenger.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                passengers=(ArrayList<Passenger>) ois.readObject();
                int size=passengers.size();
                for(int i=0;i<size;i++){
                    if(username.toLowerCase().equals(passengers.get(i).getName().toLowerCase()) && password.equals(passengers.get(i).getPassword())){
                        flag=true;
                        break;
                    }
                }
//                while (((p = (Passenger) ois.readObject()) != null)) {
//                    if (username.equals(p.getName()) && password.equals(p.getPassword())) {
//                        flag = true;
//                        break;
//                    }
//                }
            }catch (Exception exe){
                System.out.println("Error when login"+exe);
            }
            if (flag) {
                System.out.println("Login Successful"); //
                System.out.println("Username: "+p.getName()+" ,Password: "+p.getPassword());
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("searchFlightScene.fxml"));
                    root = loader.load();
                } catch (IOException exe) {
                    System.out.println("Can't Open userSign.fxml"+exe);
                }
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
            scene.getStylesheets().add(Multi_used_methods.class.getResource("style.css").toExternalForm());
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

    public void backToSign(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userSign.fxml"));
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Can't Open userSign.fxml");
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

