package com.example.flight_project_1.Base_classes;

public class EconomySeat extends Seat {
    private double cost=1;


    public EconomySeat(String seatId,boolean seatStatus){
        super(seatId,"Economy",seatStatus);
    }

    @Override
    public String calcSeatPrice(Flight flight) {
        return String.valueOf(Integer.valueOf((int) (cost*flight.getPrice())));
    }

    @Override
    public String toString() {
        return "EconomySeat{" +
                "cost=" + cost +
                ", seatStatus=" + seatStatus +
                ", seatClass='" + seatClass + '\'' +
                ", seatId='" + seatId + '\'' +
                '}';
    }
}
