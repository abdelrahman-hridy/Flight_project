package com.example.flight_project_1.Base_classes;

public class FirstSeat extends Seat{
    double cost=1.5;
    public FirstSeat(String seatId,boolean seatStatus) {
        super(seatId,"First",seatStatus);
    }

    @Override
    public String calcSeatPrice(Flight flight) {
        return String.valueOf(Integer.valueOf((int) (cost*flight.getPrice())));
    }

    @Override
    public String toString() {
        return "FirstSeat{" +
                "cost=" + cost +
                ", seatStatus=" + seatStatus +
                ", seatClass='" + seatClass + '\'' +
                ", seatId='" + seatId + '\'' +
                '}';
    }
}
