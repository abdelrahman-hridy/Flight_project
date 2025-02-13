package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Files;
import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class EditProfileController {
    public Button edit_username;
    public Button edit_password;
    public Button edit_contact;
    public Button back_to_edit;
    @FXML
    private TextField user_edit;
    @FXML
    private PasswordField pass_edit;
    @FXML
    private TextField contact_edit;
    Alert alert;
    private Passenger user;
    private int pass_index;
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label editedSuccessfullyLabel;
//    private int sceneId;


    Flight flight;
    ArrayList<Passenger> passengers=new ArrayList<>();
    /*public EditProfileController(Passenger user,int pass_index){
        this.user = user;
        this.pass_index = pass_index;
    }*/
    public void standBy(Passenger user,int pass_index){
        this.user = user;
        this.pass_index = pass_index;
    }
    public void editUsername(ActionEvent e){
        String username = user_edit.getText();
        if (username == null || username.trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username Is Empty");
            alert.setContentText("Username Is Required");
            alert.showAndWait();
        }
        else if(!EditProfileController.checkValid(0, username)) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username is used ");
            alert.setContentText("Username must be unique");
            alert.showAndWait();
        }
        else {
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
                        user.setName(username);
                        Files.getPassengers().get(pass_index).setName(username);
                        editedSuccessfullyLabel.setText("Username Edited Successfully");
                        editedSuccessfullyLabel.setVisible(true);
                        Timeline timeline = new Timeline(
                                new KeyFrame(Duration.seconds(2), event -> editedSuccessfullyLabel.setVisible(false))
                        );
                        timeline.setCycleCount(1); // Run only once
                        timeline.play();
                        user_edit.clear();
                    } catch (Exception exception) {
                        System.out.println("Error in editing username: " + exception);
                    }
                }
        }
    }
    public void editPassword(ActionEvent e){
        String password = pass_edit.getText();
        if (password == null || password.trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Password Is Empty");
            alert.setContentText("Password Is Required");
            alert.showAndWait();
        } else if (password.length() < 6) {
            System.out.println(password);
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Password is less than 6 characters");
            alert.setContentText("Password is required more than 6 chars");
            alert.showAndWait();
        } else {
            try {
                user.setPassword(password);
                System.out.println(passengers.size());
                Files.getPassengers().get(pass_index).setPassword(password);
                editedSuccessfullyLabel.setText("Password Edited Successfully");
                editedSuccessfullyLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
                editedSuccessfullyLabel.setVisible(true);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(2), event -> editedSuccessfullyLabel.setVisible(false))
                );
                timeline.setCycleCount(1); // Run only once
                timeline.play();
                pass_edit.clear();
            }
            catch (Exception exception) {
                System.out.println("Error in editing password: " + exception);
            }
        }
    }
    public void editContact(ActionEvent e){
        String contact = contact_edit.getText();
        if (contact == null || contact.trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Contact Is Empty");
            alert.setContentText("Contact Is Required");
            alert.showAndWait();
        } else if (!EditProfileController.checkValid(2, contact)) {


        } else {
            try {
                user.setPhone(contact);
                contact_edit.setText("Contact edit done!");
                editedSuccessfullyLabel.setText("Contact Edited Successfully");
                editedSuccessfullyLabel.setVisible(true);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(2), event -> editedSuccessfullyLabel.setVisible(false))
                );
                timeline.setCycleCount(1); // Run only once
                timeline.play();
                contact_edit.clear();
            }
            catch (Exception exception) {
                System.out.println("Error in editing contact: " + exception);
            }
        }
    }
    public static boolean checkValid(int op,String compare){
        boolean NotFound = true;
        if(op==0){
            for(int i = 0; i < Files.getPassengers().size(); i++){
                if(compare.toLowerCase().equals(Files.getPassengers().get(i).getName().toLowerCase())){
                    NotFound = false;
                    break;
                }
            }
        }
        else{
            for(int i = 0; i < Files.getPassengers().size(); i++){
                if(compare.toLowerCase().equals(Files.getPassengers().get(i).getPhone().toLowerCase())){
                    NotFound = false;
                    break;
                }
            }
        }
        return NotFound;
    }
    public void backToUserProfile(ActionEvent event) throws IOException {
        Multi_used_methods.GoToProfile(event, user);
    }

}
