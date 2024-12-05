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

public class SeatSelectionController  {
//    private static final long serialVersionUID = 3362544085469329324L;
//    private final ArrayList<String> seatSelected = new ArrayList<>();
    private ArrayList<ArrayList<Seat>> AllSeats=new ArrayList<>();
    private String seatSelected;
    private Flight flight;
    private Passenger user;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label AvailableSeatLabel;
//    ArrayList<String> AllSeatsIds = new ArrayList<>();
//    private final ToggleGroup toggleGroup = new ToggleGroup();


    public void assignUser(Passenger user){
        this.user = user;
    }

    public void passingFlight(Flight flight){
        this.flight = flight;
    }

    public void goToProfile(ActionEvent event){
        Multi_used_methods.GoToProfile(event, user, 3, flight);
    }

    public void GoToFlightShow(ActionEvent event){
        Multi_used_methods.GoToFlightShow(event,this.flight,this.user);
    }

    public void toggleSeat(ActionEvent event){
        System.out.println(this.flight.toString());

        ToggleButton toggledButton = (ToggleButton) event.getSource();
//        toggleButtonSelectedBefore=toggledButton;
         seatSelected = toggledButton.getId();


        if((toggledButton.isSelected())){
            System.out.println(seatSelected + " is selected");
            toggledButton.setStyle("-fx-background-color:yellow;");
//            seatSelected.add(seatID);

        }else{
            System.out.println(seatSelected + "is deselected");
            if(seatSelected.endsWith("F")){
                toggledButton.setStyle("-fx-background-color:blue;");
            }else if(seatSelected.endsWith("B")){
                toggledButton.setStyle("-fx-background-color:violet;");
            }else{
                toggledButton.setStyle("-fx-background-color:lightGreen;");
            }
//            seatSelected.remove(seatID);
        }
    }

    public void recordSeats(ActionEvent event){
        try{
            ObjectInputStream ois =new ObjectInputStream(new FileInputStream("AllSeats.txt"));
            AllSeats=(ArrayList<ArrayList<Seat>>) ois.readObject();
            System.out.println("Cols: "+AllSeats.get(0).size());
            System.out.println("Rpws: "+AllSeats.size());
            boolean seatAvailble=false;
            Seat seatInfo = null;
            for(int i=0;i<10;i++){
                for(int j=0;j<6;j++){
                    if(seatSelected.equals(AllSeats.get(i).get(j).getSeatId()) && AllSeats.get(i).get(j).isSeatStatus()==true){
                        seatAvailble=true;
                        seatInfo=AllSeats.get(i).get(j);
                        break;
                    }
                    if(seatAvailble){
                        break;
                    }
                }
            }
            if(seatAvailble){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowSeatDetail.fxml"));
                root = loader.load();

                ShowSeatDetail showSeatDetail = loader.getController();  // IMPPPPORTAANTT!!!!
                showSeatDetail.passingFlight(flight);
                showSeatDetail.assignUser(user);
                showSeatDetail.passingTheSeat(seatInfo);
                showSeatDetail.SetDataOfTheSeat(event);

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();


            }
            else{
                AvailableSeatLabel.setText("The Seat Is Not Available");
                AvailableSeatLabel.setStyle("-fx-background-color:red;");
            }

        }catch(Exception e){
            System.out.println("Error in recordSeat: "+e);
        }
    }


}
