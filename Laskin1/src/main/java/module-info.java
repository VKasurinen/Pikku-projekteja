module com.example.laskin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.laskin to javafx.fxml;
    exports com.example.laskin;
}