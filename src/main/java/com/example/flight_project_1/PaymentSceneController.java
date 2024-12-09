package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import javax.imageio.IIOParam;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentSceneController implements Initializable {

    private Payment payment;
    private int AvailbleSeats;
//    private String allPrice;
//    private boolean payment_successful=false;
//    private FXMLLoader fxmlLoader;

    @FXML
    private TextField   paymentAmount;
    @FXML
    private TextField Card_Number,Expairy_Date,CVV,PayPal_mail,PayPal_Num;
    @FXML
    private TextArea paymentID,  total_cost, payment_status;
    @FXML
    private Label Payment_id ,mylabeltoShow1,mylabeltoShow2, mylabeltoAlert,mylabeltowelcome,mylabeltoData;
    @FXML
    private ChoiceBox<String> paymentMethod;
    @FXML
    private Button submit;
    @FXML
    public ImageView PaymentImage;
    @FXML
    private Button ConfirmButton;
    @FXML
    private ChoiceBox addtional_Services;
    private Parent root;
    private Stage stage;
    private Scene scene;

    private Passenger user;
    private Flight flight;
    private Seat seat;
   // ShowSeatDetail price=new ShowSeatDetail();
    ArrayList<Flight> flights = Files.getFlights();

//    public void assignPaymentID(Payment payment) {
//        this.payment = payment;
//        if (payment != null) {
//            paymentID.setText(String.valueOf(payment.getpaymentId()));
//        } else {
//            paymentID.setText("No payment assigned");
//        }
//    }
//    public void assignUser(Passenger user){
//        this.user = user;
//    }
//
//    public void passingFlight(Flight flight){
//        this.flight = flight;
//    }
//    public void passingSeat(Seat seat){
//        this.seat = seat;
//    }


    public void PassingSeatDetailsValues( Passenger user, Flight flight, Seat seat, String allPrice,Payment payment,int AvailbleSeats) {
        this.flight = flight;
        this.seat = seat;
        this.user = user;
        payment.setPaymentAmount(allPrice);
        this.payment=payment;
        this.AvailbleSeats=AvailbleSeats;
        paymentID.setText(String.valueOf(payment.getpaymentId()));
        total_cost.setText(String.valueOf(allPrice));
    }

    private String[] methods = {"~All~","Credit card", "Debit card", "Digital wallets"};
    private String[] addtionalServices={"~All~","Seat Upgrades"," addtional Packages"," Wi-Fi Access"};
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paymentMethod.getItems().addAll(methods);
        paymentMethod.setValue(methods[0]); // Default selection
        addtional_Services.getItems().addAll(addtionalServices);
        addtional_Services.setValue(addtionalServices[0]);

      setPaymentFieldsVisibility(false, false);
       paymentMethod.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
           switch (newValue) {
               case "Credit card":
                   payment.setPaymentMethod("Credit card");
                   case "Debit card":
                       payment.setPaymentMethod("Debit card");
                   setPaymentFieldsVisibility(true, false); // Show card fields, hide wallet fields
                    break;
            case "Digital wallets":
                payment.setPaymentMethod("Digital wallets");
                   setPaymentFieldsVisibility(false, true); // Show wallet fields, hide card fields
                    break;
               default:
                   setPaymentFieldsVisibility(false, false); // Hide all fields
                   break;
            }
      });
        total_cost.setEditable(false);
        payment_status.setEditable(false);
        paymentID.setEditable(false);


    }

   private void setPaymentFieldsVisibility(boolean showCardFields, boolean showWalletFields) {

      Card_Number.setVisible(showCardFields);
     Expairy_Date.setVisible(showCardFields);
     CVV.setVisible(showCardFields);

        PayPal_mail.setVisible(showWalletFields);
        PayPal_Num.setVisible(showWalletFields);
    }

    // Calculate total cost
//    private double calc_totalCost(double baseFare, double fees) {
//        double taxes = flight.getPrice() * 0.14; // 14% tax
//        return flight.getPrice() + taxes + fees;
//    }
    @FXML
    public void handleSubmit(ActionEvent event) {
        System.out.println("Submit button clicked!");
        try {

            int paymentamont= Integer.parseInt(paymentAmount.getText());

            if ((Card_Number.getText().isEmpty() || Expairy_Date.getText().isEmpty() || CVV.getText().isEmpty())
                    && (PayPal_mail.getText().isEmpty() || PayPal_Num.getText().isEmpty())) {
                mylabeltoAlert.setText("Invalid Data. Please check your Card Info.");
                payment_status.setText("Payment Failed!");
                return;
            }


//double baseFare = Double.parseDouble(price.allprice );
//            double flight_Price=flight.getPrice();
//            System.out.println(flight_Price);
//            double baseFare = Double.parseDouble( flight_Price+seat.calcSeatPrice(flight));
//
//            double fees = 50;
            double totalCost = Double.parseDouble(payment.getPaymentAmount());
//            System.out.println(seat.calcSeatPrice(flight));


            if (paymentamont >= totalCost ) {
                total_cost.setText("Total Cost: $" + totalCost);
                mylabeltoAlert.setText("Processing payment...");
                payment_status.setText("Payment Completed!");
                mylabeltoAlert.setStyle("-fx-text-fill: green;");
//                payment_successful=true;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BookingConfrimScene.fxml"));
                    root = fxmlLoader.load();

                    BookingConfirmationCont booking = fxmlLoader.getController();
                    booking.setData(user, flight, seat, payment,AvailbleSeats);

                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }catch (Exception e){
                    System.out.println("Error to go to The Booking Confirmation"+e);
                }


//                    ShowSeatDetail showSeatDetail=new ShowSeatDetail();
//                    showSeatDetail.passingPayment(new Payment());


            } else {
                mylabeltoAlert.setText("PaymentAmount must be greater than or equal Total Cost.");
                payment_status.setText("Payment Failed!");
                mylabeltoAlert.setStyle("-fx-text-fill: red;");

            }
        } catch (NumberFormatException e) {
            mylabeltoAlert.setText("Invalid Payment Amount,Please Enter numbers only.");
            payment_status.setText("Payment Failed!");
            mylabeltoAlert.setStyle("-fx-text-fill: red;");
        }
    }
//    public void goToBookingCinfirmation(ActionEvent event){
//        try{
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BookingConfrimScene.fxml"));
//            root =fxmlLoader.load();
//
//            BookingConfirmationCont booking = fxmlLoader.getController();
//            booking.setData(user,flight,seat,payment,allPrice);
//
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//
//        } catch (Exception e) {
//            System.out.println("There is an error loading Booking confirmation  "+e);
//        }
//    }


    public void BackToShowSeatDetails(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowSeatDetail.fxml"));
            root = fxmlLoader.load();

            ShowSeatDetail showSeatDetail = fxmlLoader.getController();
            showSeatDetail.assignUser(user);
            showSeatDetail.passingFlight(flight);
            showSeatDetail.passingTheSeat(seat);
            showSeatDetail.passingPayment(payment);
            showSeatDetail.passingAvailbleSeats(AvailbleSeats);
            showSeatDetail.SetDataOfTheSeat(event);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            System.out.println("Error When Got To Show Seat Details"+ex);
        }
    }
}
