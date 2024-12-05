package com.example.flight_project_1.Base_classes;

import java.io.Serializable;
public abstract class Seat implements Serializable {
    protected String seatId;
    protected String seatClass;
    protected boolean seatStatus;

    public Seat(String seatId,String seatClass,boolean seatStatus){
        this.seatId=seatId;
        this.seatStatus=seatStatus;
        this.seatClass=seatClass;
    }

    public String getSeatNumber() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatId() {
        return seatId;
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

    public abstract String calcSeatPrice(Flight flight);

}