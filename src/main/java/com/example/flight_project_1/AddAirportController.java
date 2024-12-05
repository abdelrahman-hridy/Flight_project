package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Airport;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class AddAirportController {
    @FXML
    TextField nameTextField, codeTextField, locationTextField;
    @FXML
    Label codeInvalidMessage, nameInvalidMessage, airportAddedSuccessfulyMessage, codeInvalidMessage1;
    private ArrayList<Airport> airports;
    private boolean isValid;
    private Airport airportAdded = null;


    public void addAirport(){

        codeInvalidMessage.setVisible(false);
        nameInvalidMessage.setVisible(false);
        airportAddedSuccessfulyMessage.setVisible(false);


        if(!codeTextField.getText().isEmpty() && !nameTextField.getText().isEmpty() && !locationTextField.getText().isEmpty()) {
            isValid = true;
            File file = new File("Airports.txt");
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                airports = (ArrayList<Airport>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                int x = Integer.parseInt(codeTextField.getText());
            }catch (Exception e)
            {
                isValid = false;
                codeInvalidMessage1.setVisible(true);
                codeTextField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        checkNameLabel();
                    }
                });
            }

            if(isValid) {
                for (int i = 0; i < airports.size(); i++) {
                    if (nameTextField.getText().equals(airports.get(i).getAirport_Name())) {
                        isValid = false;
                        nameInvalidMessage.setVisible(true);
                        nameTextField.textProperty().addListener(new ChangeListener<String>() {
                            @Override
                            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                checkNameLabel();
                            }
                        });
                    }
                    if (Integer.parseInt(codeTextField.getText()) == airports.get(i).getAirport_code()) {
                        isValid = false;
                        codeInvalidMessage.setVisible(true);
                        codeTextField.textProperty().addListener(new ChangeListener<String>() {
                            @Override
                            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                checkNameLabel();
                            }
                        });
                    }
                }
                if (isValid) {
                    try {
                        airportAdded = new Airport(Integer.parseInt(codeTextField.getText()), nameTextField.getText(), locationTextField.getText());
                        airports.add(airportAdded);
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(airports);
                        oos.flush();
                        oos.close();
                        airportAddedSuccessfulyMessage.setVisible(true);
                        codeTextField.clear();
                        nameTextField.clear();
                        locationTextField.clear();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    codeInvalidMessage.setVisible(false);
                    codeInvalidMessage1.setVisible(false);
                    nameInvalidMessage.setVisible(false);
                }
            }
        }
    }

    private void checkNameLabel() {
        isValid = true;
        codeInvalidMessage1.setVisible(false);
        try {
            int x = Integer.parseInt(codeTextField.getText());
        }catch (Exception e)
        {
            isValid = false;
            codeInvalidMessage1.setVisible(true);
        }
        if(isValid) {
            nameInvalidMessage.setVisible(false);
            codeInvalidMessage.setVisible(false);
            for (int i = 0; i < airports.size(); i++) {
                if (nameTextField.getText().equals(airports.get(i).getAirport_Name())) {
                    isValid = false;
                    nameInvalidMessage.setVisible(true);
                }
                if (Integer.parseInt(codeTextField.getText()) == airports.get(i).getAirport_code()) {
                    isValid = false;
                    codeInvalidMessage.setVisible(true);
                }

            }
        }
    }



}
