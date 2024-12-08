module com.example.flight_project_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.flight_project_1 to javafx.fxml;
    exports com.example.flight_project_1;
}