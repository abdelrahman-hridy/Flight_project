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


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentSceneController implements Initializable {



    @FXML
    private TextField   paymentAmount;
    @FXML
    private TextField Card_Number,Expairy_Date,CVV,PayPal_mail,PayPal_Num;
    private Payment payment;
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
    private ChoiceBox addtional_Services;
    private Parent root;
    private Stage stage;
    private Scene scene;

    private Passenger user;
    private Flight flight;
    private Seat seat;
   // ShowSeatDetail price=new ShowSeatDetail();
    ArrayList<Flight> flights = Files.getFlights();

    public void assignPaymentID(Payment payment) {
        this.payment = payment;
        if (payment != null) {
            paymentID.setText(String.valueOf(payment.getpaymentId()));
        } else {
            paymentID.setText("No payment assigned");
        }
    }
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
    private double calc_totalCost(double baseFare, double fees) {
        double taxes = flight.getPrice() * 0.14; // 14% tax
        return flight.getPrice() + taxes + fees;
    }
    @FXML
    public void handleSubmit(ActionEvent event) {
        System.out.println("Submit button clicked!");
        try {

            int paymentamont= Integer.parseInt(paymentAmount.getText());

            if ((Card_Number.getText().isEmpty() || Expairy_Date.getText().isEmpty() || CVV.getText().isEmpty())
                    && (PayPal_mail.getText().isEmpty() || PayPal_Num.getText().isEmpty())) {
                mylabeltoAlert.setText("Invalid Data. Please check your input.");
                payment_status.setText("Payment Failed!");
                return;
            }


//double baseFare = Double.parseDouble(price.allprice );
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
                mylabeltoAlert.setStyle("-fx-text-fill: green;");
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
