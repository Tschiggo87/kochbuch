module com.example.kochbuch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kochbuch to javafx.fxml;
    exports com.example.kochbuch;
}