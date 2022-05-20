module com.example.carserviceapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carserviceapp to javafx.fxml;
    exports com.example.carserviceapp;
}