package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import com.example.flight_project_1.Base_classes.Seat;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import static java.awt.Color.red;

public class SeatSelectionController  {
//    private static final long serialVersionUID = 3362544085469329324L;
//    private final ArrayList<String> seatSelected = new ArrayList<>();
//    private ArrayList<ArrayList<Seat>> AllSeats=new ArrayList<>();
    private ArrayList<String> seatsIDSelected = new ArrayList<>();
    private Flight flight;
    private Passenger user;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int AvailbleSeats;
    @FXML
    private Label AvailableSeatLabel;
//    ArrayList<String> AllSeatsIds = new ArrayList<>();
//    private final ToggleGroup toggleGroup = new ToggleGroup();
    private int numberOfSeatSelected = 0;

    public void assignUser(Passenger user){
        this.user = user;
    }

    public void passingFlight(Flight flight){
        this.flight = flight;
    }
    public void paasingTheAvailbleSeats(int AvailbleSeats){
        this.AvailbleSeats=AvailbleSeats;
    }
//    public void getTheSeat(Seat seat){
//
//    }

    public void goToProfile(ActionEvent event){
        Multi_used_methods.GoToProfile(event, user, 3, flight);
    }

    public void GoToFlightShow(ActionEvent event){
        Multi_used_methods.GoToFlightShow(event,this.flight,this.user,this.AvailbleSeats);
    }

//    public void seatsSelected(ActionEvent event){
//        for(int i=0;i<)
//    }

    public void toggleSeat(ActionEvent event){
//        System.out.println(this.flight.toString());

         {
            ToggleButton toggledButton = (ToggleButton) event.getSource();
            boolean SeatStatus=true;
            String toggleId=toggledButton.getId();
            for(int i=0;i<10;i++){
                for(int j=0;j<6;j++){
                    if(toggleId.equals(flight.getSeats().get(i).get(j).getSeatId()) && !flight.getSeats().get(i).get(j).isSeatStatus()){
                        toggledButton.setStyle("-fx-background-color: red;");
//                        toggledButton.setSelected(false);
//                        AvailableSeatLabel.setText("This Seat Is Not Availble");
//                        AvailableSeatLabel.setStyle("-fx-text-fill: red");
                        SeatStatus=false;
                        break;
                    }
                }
                if(!SeatStatus){
                    break;
                }
            }

            if ((toggledButton.isSelected()) && SeatStatus) {
                if(numberOfSeatSelected < 4) {
//                    System.out.println(toggledButton.getId() + " is selected");
                    toggledButton.setStyle("-fx-background-color:yellow;");
                    seatsIDSelected.add(toggledButton.getId());
                    numberOfSeatSelected++;
                }
                else if(numberOfSeatSelected >= 4) {
                    toggledButton.setSelected(false);
                    return;
                }

            } else if(toggledButton.isSelected() && !SeatStatus) {
                AvailableSeatLabel.setText("This Seat Is Not Availble");
                AvailableSeatLabel.setStyle("-fx-text-fill: red");
            }
            else if(!(toggledButton.isSelected()) && SeatStatus) {
//                System.out.println(toggledButton.getId() + "is deselected");
                numberOfSeatSelected--;
                if (toggledButton.getId().endsWith("F")) {
                    toggledButton.setStyle("-fx-background-color:blue;");
                } else if (toggledButton.getId().endsWith("B")) {
                    toggledButton.setStyle("-fx-background-color:violet;");
                } else {
                    toggledButton.setStyle("-fx-background-color:lightGreen;");
                }
                seatsIDSelected.remove(toggledButton.getId());
            }
        }
    }

    public void recordSeats(ActionEvent event){
        try{
//            ObjectInputStream ois =new ObjectInputStream(new FileInputStream("AllSeats.txt"));
//            AllSeats=(ArrayList<ArrayList<Seat>>) ois.readObject();
//            System.out.println("Cols: "+AllSeats.get(0).size());
//            System.out.println("Rows: "+AllSeats.size());
            if(numberOfSeatSelected>0) {
                ArrayList<Seat> seatsSelected = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < seatsIDSelected.size(); k++) {
                            if (seatsIDSelected.get(k).equals(flight.getSeats().get(i).get(j).getSeatId()) && flight.getSeats().get(i).get(j).isSeatStatus()) {
                                seatsSelected.add(flight.getSeats().get(i).get(j));
                                break;
                            }
                        }
                    }
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowSeatDetail.fxml"));
                root = loader.load();

                ShowSeatDetail showSeatDetail = loader.getController();  // IMPPPPORTAANTT!!!!
                showSeatDetail.passingFlight(flight);
                showSeatDetail.assignUser(user);
                showSeatDetail.passingAvailbleSeats(AvailbleSeats);
                showSeatDetail.passingTheSeats(seatsSelected);
                showSeatDetail.SetDataOfTheSeat(event);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                AvailableSeatLabel.setText("No Seat Selected");
                AvailableSeatLabel.setStyle("-fx-text-fill:red;");

            }
            }catch(Exception e){
                System.out.println("Error in recordSeat: "+e);
            }
    }


}
