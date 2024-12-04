package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class SeatSelectionController{

    @FXML
//    private Button confirmSeat;
    private final ArrayList<String> seatSelected = new ArrayList<>();
    private Flight flight;
    private Passenger user;

    public void assignUser(Passenger user){
        this.user = user;
    }

    public void passingFlight(Flight flight){
        this.flight = flight;
    }

    public void goToProfile(ActionEvent event){
        Multi_used_methods.GoToProfile(event, user, 3, flight);
    }

    public void toggleSeat(ActionEvent event){
        System.out.println(this.flight.toString());

        ToggleButton toggledButton = (ToggleButton) event.getSource();
        String seatID = toggledButton.getId();

        if(toggledButton.isSelected()){
            System.out.println(seatID + " is selected");
            toggledButton.setStyle("-fx-background-color:yellow;");
            seatSelected.add(seatID);
        }else{
            System.out.println(seatID + "is deselected");
            if(seatID.endsWith("F")){
                toggledButton.setStyle("-fx-background-color:blue;");
            }else if(seatID.endsWith("B")){
                toggledButton.setStyle("-fx-background-color:violet;");
            }else{
                toggledButton.setStyle("-fx-background-color:lightGreen;");
            }
            seatSelected.remove(seatID);
        }
    }

    public void recordSeats(){
        System.out.println("The selected seats are: " + seatSelected);
    }

}
