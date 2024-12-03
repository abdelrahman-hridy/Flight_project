package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Airport;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class AddAirportController {
    @FXML
    TextField nameTextField, codeTextField, locationTextField;

    public void addAirport(){
        Airport airport = null;

        if(!codeTextField.getText().isEmpty() && !nameTextField.getText().isEmpty() && !locationTextField.getText().isEmpty()) {
            try {
                airport = new Airport(Integer.parseInt(codeTextField.getText()), nameTextField.getText(), locationTextField.getText());
                System.out.println(airport.getAirport_Name());
                codeTextField.clear();
                nameTextField.clear();
                locationTextField.clear();

                File file = new File("Airports.txt");

                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                ArrayList<Airport> airports = (ArrayList<Airport>) ois.readObject();
                airports.add(airport);

                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(airports);
                oos.flush();
                oos.close();

            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

    }
}
