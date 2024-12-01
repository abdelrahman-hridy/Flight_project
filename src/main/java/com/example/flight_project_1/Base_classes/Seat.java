package com.example.flight_project_1.Base_classes;

import java.io.Serializable;
public class Seat implements Serializable {
    private int seatNumber;
    private String seatClass;
    private boolean seatStatus;

    public Seat(int seatNumber,String seatClass,boolean seatStatus){
        this.seatNumber=seatNumber;
        this.seatStatus=seatStatus;
        this.seatClass=seatClass;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public boolean isSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(boolean seatStatus) {
        this.seatStatus = seatStatus;
    }

}