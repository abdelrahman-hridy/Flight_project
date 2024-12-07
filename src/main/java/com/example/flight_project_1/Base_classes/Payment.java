package com.example.flight_project_1.Base_classes;

public class Payment {
    private final int paymentId;
    private double paymentAmount;
    private String paymentMethod;
    private String paymentStatus;
    private static int counterPassId=0;

    // Constructor
    public Payment(double paymentAmount, String paymentMethod, String paymentStatus) {
        this.paymentId = ++counterPassId;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public  Payment() {
      this.paymentId = ++counterPassId;
    }
    // Getters and Setters

    public int getpaymentId() {
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
