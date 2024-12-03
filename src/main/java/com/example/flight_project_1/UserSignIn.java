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
//            File file=new File("Passenger.txt");
//            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
//                Passenger passenger=new Passenger();
//                while (true) {
//                    try {
//                        passenger = (Passenger) ois.readObject();
//                        if (username.equalsIgnoreCase(passenger.getName()) && password.equals(passenger.getPassword())) {
//                            flag = true;
//                            break;
//                        }
//                    } catch (EOFException ex) {
//                        break;  // End of file reached, stop reading
//                    }
//                }
//            } catch (IOException | ClassNotFoundException exe) {
//                System.out.println("Error when login: " + exe);
//            }
//
//            if (flag) {
//                System.out.println("Login Successful");
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
//            }
//            else if(1==1) {
//                alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setHeaderText("Login Failed");
//                alert.setContentText("Login Failed");
//                alert.showAndWait();
//            }
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
        stage.setScene(scene);
        stage.show();
    }
}
