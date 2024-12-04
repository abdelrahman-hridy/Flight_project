package com.example.flight_project_1.Base_classes;

public class FirstSeat extends Seat{
    double cost=1.5;
    public FirstSeat(String seatId,boolean seatStatus) {
        super(seatId,"First",seatStatus);
    }

    @Override
    public double calcSeatPrice(Flight flight) {
        return flight.getPrice()*cost;
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
