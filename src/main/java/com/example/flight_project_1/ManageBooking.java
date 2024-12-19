package com.example.flight_project_1;

import com.example.flight_project_1.Base_classes.Files;
import com.example.flight_project_1.Base_classes.Passenger;
import com.example.flight_project_1.Base_classes.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageBooking {
    @FXML
    private TableColumn<TicketsTable, Integer> ticketIdColumn;
    @FXML
    private TableColumn<TicketsTable, String> departureAirportColumn;
    @FXML
    private TableColumn<TicketsTable, String> arrivalAirportColumn;
    @FXML
    private TableColumn<TicketsTable, Double> priceColumn;
    @FXML
    private TableColumn<TicketsTable, String> seatsColumn;
    @FXML
    private TableColumn<TicketsTable, String> seatsClassColumn;
    @FXML
    private TableColumn<TicketsTable, String> departureTimeColumn;
    @FXML
    private TableColumn<TicketsTable, String> arrivalTimeColumn;
    @FXML
    private TableColumn<TicketsTable, String> editColumn;
    @FXML
    private TableView<TicketsTable> myTable;
    ObservableList<TicketsTable> data;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Passenger user;

    public void passUser(Passenger user){
        this.user = user;
        showTable();
    }

    public void StandBy(){};

    public void showTable() {
        data = FXCollections.observableArrayList();
        myTable.setItems(data);
        ticketIdColumn.setCellValueFactory(new PropertyValueFactory<TicketsTable, Integer>("ticketID"));
        departureAirportColumn.setCellValueFactory(new PropertyValueFactory<TicketsTable, String>("departureAirportName"));
        arrivalAirportColumn.setCellValueFactory(new PropertyValueFactory<TicketsTable, String>("arrivalAirportName"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<TicketsTable, String>("seats"));
        seatsClassColumn.setCellValueFactory(new PropertyValueFactory<TicketsTable, String>("classes"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<TicketsTable, String>("departureTime"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<TicketsTable, String>("arrivalTime"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<TicketsTable, Double>("price"));
        editColumn.setCellValueFactory(new PropertyValueFactory<TicketsTable, String>("button"));

        Label placeholderLabel = new Label("No Tickets Available Yet");
        myTable.setPlaceholder(placeholderLabel);
        myTable.setStyle("-fx-font-size: 16px;");
        TicketsTable row;

        for(int i = 0; i < user.getTickets().size(); i++) {
            String seats = "";
            String seatsClass = "";
            for (int j = 0; j < user.getTickets().get(i).getBookingTicket().getSeat().size(); j++) {
                if (j != user.getTickets().get(i).getBookingTicket().getSeat().size() - 1) {
                    seats += user.getTickets().get(i).getBookingTicket().getSeat().get(j).getSeatId() + ", ";
                    seatsClass += user.getTickets().get(i).getBookingTicket().getSeat().get(j).getSeatClass() + ", ";

                }else {
                    seats += user.getTickets().get(i).getBookingTicket().getSeat().get(j).getSeatId();
                    seatsClass += user.getTickets().get(i).getBookingTicket().getSeat().get(j).getSeatClass();
                }
            }
            row = new TicketsTable(user.getTickets().get(i).getTicketNumber(), user.getTickets().get(i).getBookingTicket().getFlight().getDeapartureAirport().getAirport_Name(), user.getTickets().get(i).getBookingTicket().getFlight().getArrivalAirport().getAirport_Name(),
                    seats, seatsClass,
                    user.getTickets().get(i).getBookingTicket().getFlight().getDepartureTime().toString(), user.getTickets().get(i).getBookingTicket().getFlight().getArrivalTime().toString()
                    , user.getTickets().get(i).getBookingTicket().getBookingPrice());

            row.getButton().setId(String.valueOf(user.getTickets().get(i).getTicketNumber()));
            row.getButton().getStyleClass().add("tableButton");
            row.getButton().setOnAction(this::editBooking);
            row.getButton().setCursor(Cursor.HAND);
            data.add(row);
        }
    }

    // Go to edit payment choosen
    private void editBooking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ticketEdit.fxml"));
            root = loader.load();
            EditTickets sF = loader.getController();
            sF.passUser(user);
            Ticket ticketChooced = null;
            for (int i = 0; i < user.getTickets().size(); i++)
            {
                if (Integer.parseInt(((Button) event.getSource()).getId()) == user.getTickets().get(i).getTicketNumber())
                {
                    ticketChooced = user.getTickets().get(i);
                }
            }
            sF.passTicket(ticketChooced);


            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("buttonsStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException exe) {
            System.out.println("Can't Open EditTicket.fxml "+exe);
        }
    }
    public void backToUserProfile(ActionEvent event){
        Multi_used_methods.GoToProfile(event, user);
    }


}
