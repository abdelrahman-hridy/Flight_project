package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main extends Application implements Serializable {


    public static void main(String[] args)  {

        // Read Airport
        ObjectInputStream ois = null;
        try {
            File file1 = new File("Airports.txt");
            ois = new ObjectInputStream(new FileInputStream(file1));
            Files.setAirports((ArrayList<Airport>) ois.readObject());
        } catch (IOException e) {
            System.out.println("Can't Find Airport.txt");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Read Flights
        try {
            File file = new File("Flights.txt");
            ois = new ObjectInputStream(new FileInputStream(file));
            Files.setFlights((ArrayList<Flight>) ois.readObject());

        } catch (IOException e) {
            System.out.println("Cant't Find Flights.txt");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        File file = new File("counterVariables.txt");
        Scanner scan = null;
        int flightCounter;
        int userCounter;
        try {
            scan = new Scanner(file);
            flightCounter = Integer.parseInt(scan.nextLine());
            userCounter = Integer.parseInt(scan.nextLine());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // assign counter number in flight by flights number after reading it
        Flight.setFlightNumberStatic(flightCounter);

        // Read Admins
        try {
            FileInputStream fis = new FileInputStream("Admins.txt");
            ois = new ObjectInputStream(fis);
            Files.setAdmins((ArrayList<Admin>) ois.readObject());
        }catch (Exception exe){
            System.out.println("Error when login"+exe);
        }
        // Read Passengers
        try {
            FileInputStream fis = new FileInputStream("Passenger.txt");
            ois = new ObjectInputStream(fis);
            Files.setPassengers((ArrayList<Passenger>) ois.readObject());
        }catch (Exception exe){
            System.out.println("Error when login"+exe);
        }
        Passenger.setCounterPassId(userCounter);

        // Read Seats
        try {
            FileInputStream fis = new FileInputStream("AllSeats.txt");
            ois = new ObjectInputStream(fis);
            Files.setSeats((ArrayList<ArrayList<Seat>>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


//<<<<<<< HEAD
//        ArrayList <ArrayList<Seat>> AllSeats=new ArrayList<>();
//
//        for(int i=0;i<10;i++){
//            ArrayList<Seat> row1=new ArrayList<>();
//            for(int j=0;j<6;j++){
//               if(i<2){
//                   if(j==0){
//                       String id="A"+(i+1)+"F";
//                   row1.add(new FirstSeat(id,true));
//                   }
//                   else if(j==1){
//                       String id="B"+(i+1)+"F";
//                       row1.add(new FirstSeat(id,true));
//                   }
//                   else if(j==2){
//                       String id="C"+(i+1)+"F";
//                       row1.add(new FirstSeat(id,true));
//                   }
//                   else if(j==3){
//                       String id="D"+(i+1)+"F";
//                       row1.add(new FirstSeat(id,true));
//                   }
//                   else if(j==4){
//                       String id="E"+(i+1)+"F";
//                       row1.add(new FirstSeat(id,true));
//                   }
//                   else{
//                       String id="F"+(i+1)+"F";
//                       row1.add(new FirstSeat(id,true));
//                   }
//               }
//               else if(i<5){
//                   if(j==0){
//                       String id="A"+(i+1)+"B";
//                       row1.add(new BusinessSeat(id,true));
//                   }
//                   else if(j==1){
//                       String id="B"+(i+1)+"B";
//                       row1.add(new BusinessSeat(id,true));
//                   }
//                   else if(j==2){
//                       String id="C"+(i+1)+"B";
//                       row1.add(new BusinessSeat(id,true));
//                   }
//                   else if(j==3){
//                       String id="D"+(i+1)+"B";
//                       row1.add(new BusinessSeat(id,true));
//                   }
//                   else if(j==4){
//                       String id="E"+(i+1)+"B";
//                       row1.add(new BusinessSeat(id,true));
//                   }
//                   else{
//                       String id="F"+(i+1)+"B";
//                       row1.add(new BusinessSeat(id,true));
//                   }
//               }
//               else{
//                   if(j==0){
//                       String id="A"+(i+1)+"E";
//                       row1.add(new EconomySeat(id,true));
//                   }
//                   else if(j==1){
//                       String id="B"+(i+1)+"E";
//                       row1.add(new EconomySeat(id,true));
//                   }
//                   else if(j==2){
//                       String id="C"+(i+1)+"E";
//                       row1.add(new EconomySeat(id,true));
//                   }
//                   else if(j==3){
//                       String id="D"+(i+1)+"E";
//                       row1.add(new EconomySeat(id,true));
//                   }
//                   else if(j==4){
//                       String id="E"+(i+1)+"E";
//                       row1.add(new EconomySeat(id,true));
//                   }
//                   else {
//                       String id="F"+(i+1)+"E";
//                       row1.add(new EconomySeat(id,true));
//                   }
//               }
//            }
//            AllSeats.add(row1);
//        }
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("AllSeats.txt"));
//            oos.writeObject(AllSeats);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        ArrayList <ArrayList<Seat>> AllSeats=new ArrayList<>();
//        for(int i=0;i<10;i++){
//            ArrayList<Seat> row1=new ArrayList<>();
//            for(int j=0;j<6;j++){
//                if(i<2){
//                    if(j==0){
//                        String id="A"+(i+1)+"F";
//                        row1.add(new FirstSeat(id,true));
//                    }
//                    else if(j==1){
//                        String id="B"+(i+1)+"F";
//                        row1.add(new FirstSeat(id,true));
//                    }
//                    else if(j==2){
//                        String id="C"+(i+1)+"F";
//                        row1.add(new FirstSeat(id,true));
//                    }
//                    else if(j==3){
//                        String id="D"+(i+1)+"F";
//                        row1.add(new FirstSeat(id,true));
//                    }
//                    else if(j==4){
//                        String id="E"+(i+1)+"F";
//                        row1.add(new FirstSeat(id,true));
//                    }
//                    else{
//                        String id="F"+(i+1)+"F";
//                        row1.add(new FirstSeat(id,true));
//                    }
//                }
//                else if(i<5){
//                    if(j==0){
//                        String id="A"+(i+1)+"B";
//                        row1.add(new BusinessSeat(id,true));
//                    }
//                    else if(j==1){
//                        String id="B"+(i+1)+"B";
//                        row1.add(new BusinessSeat(id,true));
//                    }
//                    else if(j==2){
//                        String id="C"+(i+1)+"B";
//                        row1.add(new BusinessSeat(id,true));
//                    }
//                    else if(j==3){
//                        String id="D"+(i+1)+"B";
//                        row1.add(new BusinessSeat(id,true));
//                    }
//                    else if(j==4){
//                        String id="E"+(i+1)+"B";
//                        row1.add(new BusinessSeat(id,true));
//                    }
//                    else{
//                        String id="F"+(i+1)+"B";
//                        row1.add(new BusinessSeat(id,true));
//                    }
//                }
//                else{
//                    if(j==0){
//                        String id="A"+(i+1)+"E";
//                        row1.add(new EconomySeat(id,true));
//                    }
//                    else if(j==1){
//                        String id="B"+(i+1)+"E";
//                        row1.add(new EconomySeat(id,true));
//                    }
//                    else if(j==2){
//                        String id="C"+(i+1)+"E";
//                        row1.add(new EconomySeat(id,true));
//                    }
//                    else if(j==3){
//                        String id="D"+(i+1)+"E";
//                        row1.add(new EconomySeat(id,true));
//                    }
//                    else if(j==4){
//                        String id="E"+(i+1)+"E";
//                        row1.add(new EconomySeat(id,true));
//                    }
//                    else {
//                        String id="F"+(i+1)+"E";
//                        row1.add(new EconomySeat(id,true));
//                    }
//                }
//            }
//            AllSeats.add(row1);
//        }
//
//        File file1 =new File("AllSeats.txt");
//        FileOutputStream fileOutputStream = null;
//        ObjectOutputStream objectOutputStream = null;
//        try {
//            fileOutputStream = new FileOutputStream(file1);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            objectOutputStream = new ObjectOutputStream(fileOutputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            objectOutputStream.writeObject(AllSeats);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//
//
////====
//<<<<<<< HEAD
        ArrayList<Admin> admins = new ArrayList<>();
        admins.add(new Admin("Shehab", "11223344"));
        admins.add(new Admin("Sohaib", "55435421"));
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Admins.txt"));
            oos.writeObject(admins);
        }catch (Exception exe){
            System.out.println("Error when login"+exe);
        }
//>>>>>>> e69380d5760ff1ffcafe54f99369bfb909840b61

//        Passenger passengers[] = new Passenger[3];
//        passengers[0] = new Passenger("Sohaib", "01067700658", "123");
//        passengers[1] = new Passenger("Shehab", "01151692506", "123");
//        passengers[2] = new Passenger("Tony", "01225641027", "123");
//
//
//        ArrayList<Airport> airports = new ArrayList<>();
//        airports.add(new Airport(1, "Cairo", "Egypt"));
//        airports.add(new Airport(2, "London", "England"));
//        airports.add(new Airport(3, "Los Angles", "USA"));
//        airports.add(new Airport(4, "Berlin", "England"));
//        try {
//            File file1 = new File("Airports.txt");
//            ObjectOutputStream oos90 = new ObjectOutputStream(new FileOutputStream(file1));
//            oos90.writeObject(airports);
//            oos90.flush();
//            oos90.close();
//        }catch (Exception e){
//            System.out.println("Cant add Airports");
//        }
////
//          ArrayList<ArrayList<Seat>>AllFlightSeats=null;
//          try {
//              ObjectInputStream ois123 = new ObjectInputStream (new FileInputStream("AllSeats.txt"));
//              AllFlightSeats= (ArrayList<ArrayList<Seat>>) ois123.readObject();
//              for(int i=0;i<10;i++){
//                  for(int j=0;j<6;j++){
//                      System.out.println();
//                  }
//                  System.out.println();
//              }
//          }catch (Exception ex){
//              System.out.println("Error cant Read The Seats For the flights");
//          }
//=======
//        ArrayList<Admin> admins = new ArrayList<>();
//        admins.add(new Admin("Shehab", "11223344"));
//        admins.add(new Admin("Sohaib", "55435421"));
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Admins.txt"));
//            oos.writeObject(admins);
//        }catch (Exception exe){
//            System.out.println("Error when login"+exe);
//        }
//>>>>>>> 939142d11406a96c3ffd5a14503e9817bcb4d17b




        launch(args);

    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signInForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("buttonsStyleSignIn.css").toExternalForm());
            stage.setScene(scene);
        }catch (Exception e)
        {
            System.out.println("Can't see userSign");
        }


        stage.setTitle("Flight System");
        try {
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("DALL·E-2024-12-01-20.54.png"))));
        } catch (Exception e) {
            System.out.println("Can't open plane picture");
        }


        stage.show();


        stage.setOnCloseRequest(event ->{
            logout(stage);
            event.consume();
        });

    }
    public void logout(Stage stage){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Close");
        alert.setHeaderText("You Are About To Leaving The Application");
        alert.setContentText("Do you want to save changes ? ");

        // Create custom buttons
        ButtonType OkButton = new ButtonType("OK");
        ButtonType NoButton = new ButtonType("No");
        ButtonType CancelButton = new ButtonType("Cancel");

        // Set the buttons to the alert
        alert.getButtonTypes().setAll(OkButton, NoButton, CancelButton);

        // Show the alert and wait for a response
        alert.showAndWait().ifPresent(response -> {
            if (response == OkButton) {
                //Here We will write
                ObjectOutputStream oos;
                // Write Airport
                try {
                    File file1 = new File("Airports.txt");
                    oos = new ObjectOutputStream(new FileOutputStream(file1));
                    oos.writeObject(Files.getAirports());
                } catch (IOException e) {
                    System.out.println("Can't Find Airport.txt");
                }
                // Write Flights
                try {
                    File file = new File("Flights.txt");
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(Files.getFlights());
                } catch (IOException e) {
                    System.out.println("Cant't Find Flights.txt");
                }
                PrintWriter pr = null;
                try {
                    pr = new PrintWriter("counterVariables.txt");
                    pr.println(Flight.getFlightNumberStatic());
                    pr.println(Passenger.getCounterPassId());
                    pr.flush();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }finally {
                    pr.close();
                }

                // Write Admins
                try {
                    File file = new File("Admins.txt");
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(Files.getAdmins());
                }catch (Exception exe){
                    System.out.println("Error when login"+exe);
                }
                // Write Passengers
                try {
                    File file = new File("Passenger.txt");
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(Files.getPassengers());
                }catch (Exception exe){
                    System.out.println("Error when login"+exe);
                }
                stage.close();
            } else if (response == NoButton) {
                stage.close();
            }
        });


    }
}
