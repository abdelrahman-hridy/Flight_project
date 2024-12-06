package com.example.flight_project_1.Base_classes;

import java.util.UUID;

public class Payment {
    private String paymentId;
    private double paymentAmount;
    private String paymentMethod;
    private String paymentStatus;

    // Constructor
    public Payment(double paymentAmount,String paymentId, String paymentMethod, String paymentStatus) {
        this.paymentId = paymentId;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }


    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


//    public void savePaymentDetails() throws Exception {
//        String paymentDetails = "Payment ID: " + paymentId + "\n" +
//                "Payment Amount: " + paymentAmount + "\n" +
//                "Payment Method: " + paymentMethod + "\n" +
//                "Payment Status: " + paymentStatus + "\n";
//
//        FileHandler.saveToFile("payment_details.txt", paymentDetails);
//    }

}
