package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Files;
import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.EventObject;

public class UserProfile {
    public Button open_edit;

    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private Label pass_username;
    @FXML
    private Label pass_phone;
    @FXML
    private Label pass_Id;
    @FXML
    private Label pocketLabel;
    @FXML

    private Passenger user;
    private int pass_index;
    private ArrayList<Passenger> passengers=new ArrayList<>();

//<<<<<<< HEAD
//   public void assignUser_sceneId(Passenger user, int sceneId){
//       this.sceneId = sceneId;
//       this.user = user;
//   }
//=======

    public void assignUser_sceneId(Passenger user){
        this.user = user;
        pass_username.setText("Username: "+user.getName());
        pass_phone.setText("Contact: "+user.getPhone());
        pass_Id.setText("Passenger ID: "+user.getPassenger_ID());
        pocketLabel.setText(pocketLabel.getText() + user.getPocket());

        //get passenger index in passenger.txt
        pass_index = Files.getPassengers().indexOf(user);
    }
    public void GoToEditProfile(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(Multi_used_methods.class.getResource("editProfileScene.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            EditProfileController ep = loader.getController();
            ep.standBy(user,pass_index);

            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            System.out.println("Can't Open editProfileScene.fxml");
        }
    }
    public void GoToManageBooking(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(Multi_used_methods.class.getResource("manageBooking.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            ManageBooking mb = loader.getController();
            mb.StandBy();
            mb.passUser(user);
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("buttonLogoutStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            System.out.println("Can't Open manageBooking.fxml");
        }
    }
    /*public void editUsername(){
        String username = user_edit.getText();
        if (username == null || username.trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username ,Contact Info Or Password Are Empty");
            alert.setContentText("Username ,Contact Info and Password are Required");
            alert.showAndWait();
        }
        else {
            boolean NameNotFound = true;
            try {
                File file=new File("Passenger.txt");
                FileInputStream fis=new FileInputStream(file);
                ObjectInputStream ois=new ObjectInputStream(fis);
                if(file.length() > 0) {
                    passengers = (ArrayList<Passenger>) ois.readObject();
                    int size = passengers.size();
                    for (int i = 0; i < size; i++) {
                        if (username.toLowerCase().equals(passengers.get(i).getName().toLowerCase())) {
                            NameNotFound = false;
                            break;
                        }
                    }
                }
            }
            catch (Exception EA) {
                System.out.println("Error when searching for a unique user"+EA);
            }
            if (!NameNotFound) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username is used ");
                alert.setContentText("Username must be unique");
                alert.showAndWait();
            } else {
                try {
                    System.out.println(passengers.size());
                    Files.getPassengers().get(pass_index).setName(username);
                    user_edit.setText("Username edit done!");
                    System.out.println(passengers.size());
                }
                catch (Exception exception) {
                    System.out.println("Error in editing username: " + exception);
                }
            }
        }
    }
    public void editPassword(){
        String password = pass_edit.getText();
        if (password == null || password.trim().isEmpty()) {
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
        }
        else {
            try {
                System.out.println(passengers.size());
                Files.getPassengers().get(pass_index).setPassword(password);
                pass_edit.setText("Password edit done!");
                System.out.println(passengers.size());
            }
            catch (Exception exception) {
                System.out.println("Error in adding user: " + exception);
            }
        }
    }
    public void editContact(){
        String contact = contact_edit.getText();
        if (contact == null || contact.trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username ,Contact Info Or Password Are Empty");
            alert.setContentText("Username ,Contact Info and Password are Required");
            alert.showAndWait();
        }
        else {
            try {
                System.out.println(passengers.size());
                Files.getPassengers().get(pass_index).setPassword(contact);
                contact_edit.setText("Contact edit done!");
                System.out.println(passengers.size());
            }
            catch (Exception exception) {
                System.out.println("Error in adding user: " + exception);
            }
        }
    }*/
//>>>>>>> b6e6bacd634dffbfc4a111800bb9b7e766e4f5d2
    public void backFromUserProfile(ActionEvent e) {
        // To Flight Search scene

        Multi_used_methods.openFlightSearch(e, user);

    }

}
