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
        try {
            scan = new Scanner(file);
            flightCounter = Integer.parseInt(scan.nextLine());
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
        Passenger.setCounterPassId(Files.getPassengers().size());


//

//        for(int i = 0; i < Files.getAdmins().size(); i++)
//            System.out.println(Files.getAdmins().get(i).getUsername());
//        for(int i = 0; i < Files.getPassengers().size(); i++)
//            System.out.println(Files.getPassengers().get(i).getName());
//        for(int i = 0; i < Files.getAirports().size(); i++)
//            System.out.println(Files.getAirports().get(i).getAirport_Name());
//        for(int i = 0; i < Files.getFlights().size(); i++)
//            System.out.println(Files.getFlights().get(i).getDeapartureAirport().getAirport_Name());



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
//        ArrayList<Admin> admins = new ArrayList<>();
//        admins.add(new Admin("Shehab", "11223344"));
//        admins.add(new Admin("Sohaib", "55435421"));
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Admins.txt"));
//            oos.writeObject(admins);
//        }catch (Exception exe){
//            System.out.println("Error when login"+exe);
//        }
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


//        ArrayList<Flight> flights_to_Write = new ArrayList<>();
//        flights_to_Write.add(new Flight(1, airports.get(0), airports.get(1), new Date(2024 - 1900, Calendar.NOVEMBER, 4, 6, 0),
//                new Date(2024 - 1900, Calendar.NOVEMBER, 4, 7, 20), new ArrayList<>(new ArrayList<>(Files.getSeats())), 2000));
//        flights_to_Write.add(new Flight(2, airports.get(1), airports.get(2), new Date(2024 - 1900, Calendar.NOVEMBER, 5, 5, 0),
//                new Date(2024 - 1900, Calendar.NOVEMBER, 5, 7, 0), new ArrayList<>(new ArrayList<>(Files.getSeats())) , 3000));
//        flights_to_Write.add(new Flight(3, airports.get(2), airports.get(3), new Date(2024 - 1900, Calendar.NOVEMBER, 6, 8, 0),
//                new Date(2024 - 1900, Calendar.NOVEMBER, 6, 10, 0), Files.getSeats() , 4000));
//        flights_to_Write.add(new Flight(4, airports.get(3), airports.get(0), new Date(2024 - 1900, Calendar.NOVEMBER, 5, 2, 0),
//                new Date(2024 - 1900, Calendar.NOVEMBER, 5, 5, 0), new ArrayList<>(new ArrayList<>(Files.getSeats())), 1000));
//
//
//        try {
//            ObjectOutputStream oos7 = new ObjectOutputStream(new FileOutputStream("Flights.txt"));
//            oos7.writeObject(flights_to_Write);
//            oos7.flush();
//            oos7.close();
//        }catch (Exception e){
//            System.out.println("Cant write The Flights");
//        }

//
//
//
//
//
//        ArrayList<Flight> flights=null;
//        try {
//            ObjectInputStream ois11 = new ObjectInputStream(new FileInputStream("Flights.txt"));
//            flights = (ArrayList<Flight>) ois11.readObject();
//            System.out.println("==================================");
//            for(int i=0;i<flights.size();i++){
//            System.out.println(flights.get(i).getPrice());
//            }
//        }catch (Exception ex){
//            System.out.println("Error cant Read The Flights");
//        }
//        Files.setFlights(flights);

//            Flight flight = new Flight(5, airports.get(3), airports.get(1), new Date(2024 - 1900, Calendar.JULY, 4, 12, 0),
//                    new Date(2024 - 1900, Calendar.JULY, 4, 14, 0), AllFlightSeats, 20000);
//            flights.add(flight);
//            try{
//                ObjectOutputStream oos44 = new ObjectOutputStream(new FileOutputStream("Admins.txt"));
//            oos44 = new ObjectOutputStream(new FileOutputStream(file));
//            oos44.writeObject(flights);
//            oos44.flush();
//            oos44.close();
//        }catch (Exception e){
//            System.out.println("Cant write The Flights");
//        }


        // Read Seats
        try {
            FileInputStream fis = new FileInputStream("AllSeats.txt");
            ois = new ObjectInputStream(fis);
            Files.setSeats((ArrayList<ArrayList<Seat>>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        launch(args);

    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userSign.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
            stage.setScene(scene);
        }catch (Exception e)
        {
            System.out.println("Can't see userSign");
        }


        stage.setTitle("Hello GUI");
        try {
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("DALL·E-2024-12-01-20.54.png"))));
        } catch (Exception e) {
            System.out.println("Can't open plane picture");
        }


        stage.show();
//        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent windowEvent) {
//                Platform.exit();
//                System.exit(0);
//            }
//        });

        stage.setOnCloseRequest(event ->{
            event.consume();
            logout(stage);
        });
    }
    public void logout(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logut");
        alert.setHeaderText("You 're about to logout");
        alert.setContentText("Do you want to save before exiting?: ");

        if(alert.showAndWait().get() == ButtonType.OK){
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
            System.out.println("Ok");
            stage.close();
        }

    }
}
