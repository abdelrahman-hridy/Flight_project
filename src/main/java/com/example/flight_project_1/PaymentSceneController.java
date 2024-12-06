package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Flight;
import com.example.flight_project_1.Base_classes.Passenger;
import com.example.flight_project_1.Base_classes.Payment;
import com.example.flight_project_1.Base_classes.Seat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentSceneController implements Initializable {


    @FXML
    private TextField  paymentAmount;
    @FXML
    private TextField Card_Number,Expairy_Date,CVV,PayPal_mail,PayPal_Num;

    //    @FXML
//    private Payment paymentId;
    @FXML
    private TextArea PaymentID, total_cost, payment_status;
    //    @FXML
//    TextArea PaymentID= new TextArea();
    @FXML
    private Label Payment_id ,mylabeltoShow1,mylabeltoShow2, mylabeltoAlert,mylabeltowelcome,mylabeltoData;
    @FXML
    private ChoiceBox<String> paymentMethod;
    @FXML
    private Button submit;
    @FXML
    public ImageView PaymentImage;
    @FXML
    private ChoiceBox addtional_Services;
    private Parent root;
    private Stage stage;
    private Scene scene;

    private Passenger user;
    private Flight flight;
    private Seat seat;
    //private static final double TAX_RATE = 0.14;
// public  void assignPaymenetID(Payment paymentId){
//    this.paymentId=paymentId;
// }
    public void assignUser(Passenger user){
        this.user = user;
    }

    public void passingFlight(Flight flight){
        this.flight = flight;
    }
    public void passingSeat(Seat seat){
        this.seat = seat;
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
                case "Debit card":
                    setPaymentFieldsVisibility(true, false); // Show card fields, hide wallet fields
                    break;
                case "Digital wallets":
                    setPaymentFieldsVisibility(false, true); // Show wallet fields, hide card fields
                    break;
                default:
                    setPaymentFieldsVisibility(false, false); // Hide all fields
                    break;
            }
        });

       

        total_cost.setEditable(false);
        payment_status.setEditable(false);
        PaymentID.setEditable(false);


    }

    private void setPaymentFieldsVisibility(boolean showCardFields, boolean showWalletFields) {
        // Show or hide the card-related fields
        Card_Number.setVisible(showCardFields);
        Expairy_Date.setVisible(showCardFields);
        CVV.setVisible(showCardFields);

        // Show or hide the wallet-related fields
        PayPal_mail.setVisible(showWalletFields);
        PayPal_Num.setVisible(showWalletFields);
    }

    // Calculate total cost
    private double calc_totalCost(double baseFare, double fees) {
        double taxes = flight.getPrice() * 0.14; // 14% tax
        //fees=50;
        return flight.getPrice() + taxes + fees;
    }
    @FXML
    public void handleSubmit(ActionEvent event) {
        System.out.println("Submit button clicked!");
        try {
            // int paymentID= Integer.parseInt(paymentId.getText());
            int paymentamont= Integer.parseInt(paymentAmount.getText());
            //    Card_Number,Expairy_Date,CVV,PayPal_mail,PayPal_Num;
            int card_num= Integer.parseInt(Card_Number.getText());
            int exp_date= Integer.parseInt(Expairy_Date.getText());
            int cvv= Integer.parseInt(CVV.getText());
            int paypal_mail= Integer.parseInt(PayPal_mail.getText());
            int paypal_num= Integer.parseInt(PayPal_Num.getText());



            double flight_Price=flight.getPrice();
            System.out.println(flight_Price);
            double baseFare = Double.parseDouble( flight_Price+seat.calcSeatPrice(flight));

            double fees = 50;
            double totalCost = calc_totalCost(baseFare, fees);
            System.out.println(seat.calcSeatPrice(flight));


            if (paymentamont >= totalCost ) {
                total_cost.setText("Total Cost: $" + totalCost);
                mylabeltoAlert.setText("Processing payment...");
                payment_status.setText("Payment Completed!");
             //   savePaymentData(paymentAmount,totalCost, "Completed");
                //Card_Number,Expairy_Date,CVV,PayPal_mail,PayPal_Num,
                mylabeltoAlert.setStyle("-fx-text-fill: green;");
                // mylabeltoData.setStyle("fx-text-fill: green;");
            } else {
                mylabeltoAlert.setText("Payment Amount must be greater than or equal to Total Cost.");
                payment_status.setText("Payment Failed!");
                mylabeltoAlert.setStyle("-fx-text-fill: red;");
                // mylabeltoData.setStyle("fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            mylabeltoAlert.setText("Invalid Payment Amount,Please enter numbers only.");
            payment_status.setText("Payment Failed!");
            mylabeltoAlert.setStyle("-fx-text-fill: red;");
            // mylabeltoData.setStyle("fx-text-fill: red;");
        }
    }



    public void BackToShowSeatDetails(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowSeatDetail.fxml"));
            root = fxmlLoader.load();

            ShowSeatDetail showSeatDetail = fxmlLoader.getController();
            showSeatDetail.assignUser(user);
            showSeatDetail.passingFlight(flight);
            showSeatDetail.passingTheSeat(seat);
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
