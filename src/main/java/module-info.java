module com.example.kochbuch {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.kochbuch to javafx.fxml;
    exports com.example.kochbuch;
    exports com.example.kochbuch.controller;
    opens com.example.kochbuch.controller to javafx.fxml;
}