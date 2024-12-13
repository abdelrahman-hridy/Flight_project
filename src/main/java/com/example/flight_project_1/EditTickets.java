package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Files;
import com.example.flight_project_1.Base_classes.Passenger;
import com.example.flight_project_1.Base_classes.Seat;
import com.example.flight_project_1.Base_classes.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class EditTickets {

    @FXML
    GridPane seatNumberGrid, seatIdGrid, seatClassGrid, seatPriceGrid, SellButtonGrid;
    private Label[]seatNumberLabels = new Label[4];
    private Label[]seatIdLabels = new Label[4];
    private Label[]seatClassLabels = new Label[4];
    private Label[]seatPriceLabels = new Label[4];
    private Button[]sellButtons = new Button[4];

    private ArrayList <Seat> seats;

    private Passenger user;
    private Ticket ticket;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void passUser(Passenger user){
        this.user = user;
    }
    public void passTicket(Ticket ticket){
        this.ticket = ticket;
        seats = ticket.getBookingTicket().getSeat();
        start();
    }

    public void start() {
        for (int i = 0; i < 4; i++) {

            seatNumberLabels[i] = (Label) seatNumberGrid.getChildren().get(i);
            seatIdLabels[i] = (Label) seatIdGrid.getChildren().get(i);
            seatClassLabels[i] = (Label) seatClassGrid.getChildren().get(i);
            seatPriceLabels[i] = (Label) seatPriceGrid.getChildren().get(i);
            sellButtons[i] = (Button) SellButtonGrid.getChildren().get(i);

            seatNumberLabels[i].setText("Seat " + (i + 1));
            seatIdLabels[i].setText("ID : ");
            seatClassLabels[i].setText(("Class : "));
            seatPriceLabels[i].setText("Price : ");


            seatNumberLabels[i].setVisible(false);
            seatIdLabels[i].setVisible(false);
            seatClassLabels[i].setVisible(false);
            seatPriceLabels[i].setVisible(false);
            sellButtons[i].setVisible(false);

        }
        showSeats();
    }

    public void showSeats() {
        for (int i = 0; i < seats.size(); i++) {

            seatNumberLabels[i].setVisible(true);

            seatIdLabels[i].setVisible(true);
            seatIdLabels[i].setText(seatIdLabels[i].getText() + seats.get(i).getSeatId());

            seatClassLabels[i].setVisible(true);
            seatClassLabels[i].setText(seatClassLabels[i].getText() + seats.get(i).getSeatClass());

            seatPriceLabels[i].setVisible(true);
            seatPriceLabels[i].setText(seatPriceLabels[i].getText() + (seats.get(i).calcSeatPrice(ticket.getBookingTicket().getFlight()) + seats.get(i).getSeatservice()));

            sellButtons[i].setVisible(true);
            sellButtons[i].setOnAction(this::sellSeat);
            sellButtons[i].setCursor(Cursor.HAND);
        }
    }

    private void sellSeat(ActionEvent event) {
        int soldSeatIndex = (((Button) event.getSource()).getId().charAt(((Button) event.getSource()).getId().length()  - 1)) - '0';


        user.setPocket(user.getPocket() + seats.get(soldSeatIndex).calcSeatPrice(ticket.getBookingTicket().getFlight()) + seats.get(soldSeatIndex).getSeatservice());
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                if(ticket.getBookingTicket().getFlight().getSeats().get(i).get(j).getSeatId().equals(ticket.getBookingTicket().getSeat().get(soldSeatIndex).getSeatId()))
                {
                    ticket.getBookingTicket().getFlight().getSeats().get(i).get(j).setSeatStatus(true);
                }
            }
        }

        ticket.getBookingTicket().setBookingPrice(ticket.getBookingTicket().getBookingPrice() - seats.get(soldSeatIndex).calcSeatPrice(ticket.getBookingTicket().getFlight()) - seats.get(soldSeatIndex).getSeatservice());
        seats.remove(soldSeatIndex);

        seatNumberLabels[soldSeatIndex].setVisible(false);
        seatIdLabels[soldSeatIndex].setVisible(false);
        seatClassLabels[soldSeatIndex].setVisible(false);
        seatPriceLabels[soldSeatIndex].setVisible(false);
        sellButtons[soldSeatIndex].setVisible(false);

        if(ticket.getBookingTicket().getSeat().isEmpty())
        {
            user.getTickets().remove(ticket);
            backToTicketsShow(event);
        }
        start();
    }


    public void backToTicketsShow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Multi_used_methods.class.getResource("manageBooking.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            ManageBooking mb = loader.getController();
            mb.StandBy();
            mb.passUser(user);
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("buttonLogoutStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            System.out.println("Can't Open manageBooking.fxml");
        }
    }

    public void SellTicket(ActionEvent event) {
        user.setPocket(user.getPocket() + ticket.getBookingTicket().getBookingPrice());

        for(int k = 0; k  < ticket.getBookingTicket().getSeat().size(); k++)
        {
            ticket.getBookingTicket().getSeat().get(k).setSeatStatus(true);
        }
        user.getTickets().remove(ticket);
        backToTicketsShow(event);
    }
}
