package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Files;
import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class EditProfileController {
    public Button edit_username;
    public Button edit_password;
    public Button edit_contact;
    @FXML
    private TextField user_edit;
    @FXML
    private PasswordField pass_edit;
    @FXML
    private TextField contact_edit;
    Alert alert;
    private Passenger user;
    private int sceneId;
    private int pass_index;
    Flight flight;
    ArrayList<Passenger> passengers=new ArrayList<>();
    /*public EditProfileController(Passenger user,int pass_index){
        this.user = user;
        this.pass_index = pass_index;
    }*/
    public void standBy(Passenger user,int pass_index){}
    public void editUsername(ActionEvent e){
        String username = user_edit.getText();
        if (username == null || username.trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username ,Contact Info Or Password Are Empty");
            alert.setContentText("Username ,Contact Info and Password are Required");
            alert.showAndWait();
        }
        else {
            /*boolean NameNotFound = true;
            try {
                for(int i = 0; i < Files.getPassengers().size(); i++){
                    if(username.toLowerCase().equals(Files.getPassengers().get(i).getName().toLowerCase())){
                        NameNotFound = false;
                        break;
                    }
                }
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
            }*/
            if ( !EditProfileController.checkValid(0, username)) {
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
    public void editPassword(ActionEvent e){
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
    public void editContact(ActionEvent e){
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
        else if(op==1){
            for(int i = 0; i < Files.getPassengers().size(); i++){
                if(compare.toLowerCase().equals(Files.getPassengers().get(i).getName().toLowerCase())){
                    NotFound = false;
                    break;
                }
            }
        }
        else{
            for(int i = 0; i < Files.getPassengers().size(); i++){
                if(compare.toLowerCase().equals(Files.getPassengers().get(i).getName().toLowerCase())){
                    NotFound = false;
                    break;
                }
            }
        }
        return NotFound;
    }
    public void backToUserProfil(ActionEvent event){
        Multi_used_methods M;

        /*try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userProfileScene.fxml"));
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Can't Open userSign.fxml");
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }

}
