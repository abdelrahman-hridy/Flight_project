package com.example.flight_project_1.Base_classes;

public class Payment {
    private final int paymentId;
    private String paymentAmount;
    private String paymentMethod;
    private String paymentStatus;
    private static int counterPaymentId=0;

    // Constructor
    public Payment(String paymentAmount, String paymentMethod, String paymentStatus) {
        this.paymentId = ++counterPaymentId;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public  Payment() {
      this.paymentId = ++counterPaymentId;
    }
    // Getters and Setters

    public int getpaymentId() {
        return paymentId;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
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
