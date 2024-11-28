module com.example.flight_project_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.flight_project_1 to javafx.fxml;
    exports com.example.flight_project_1;
}