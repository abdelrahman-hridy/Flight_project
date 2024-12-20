package com.example.flight_project_1.Base_classes;

import java.io.Serializable;
public abstract class Seat implements Serializable {
    protected String seatId;
    protected String seatClass;
    protected boolean seatStatus;
    private int Seatservice;

    public int getSeatservice() {
        return Seatservice;
    }

    public void setSeatservice(int seatservice) {
        Seatservice = seatservice;
    }

    public Seat(String seatId, String seatClass, boolean seatStatus){
        this.seatId=seatId;
        this.seatStatus=seatStatus;
        this.seatClass=seatClass;
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

    public abstract double calcSeatPrice(Flight flight);

}