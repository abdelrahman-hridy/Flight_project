package com.example.flight_project_1.Base_classes;

public class EconomySeat extends Seat {
    private double cost=1;


    public EconomySeat(String seatId,boolean seatStatus){
        super(seatId,"Economy",seatStatus);
    }

    @Override
    public double calcSeatPrice(Flight flight) {
        return flight.getPrice()*this.cost;
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
