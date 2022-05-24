module com.example.carserviceapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.cs to javafx.fxml;
    exports com.example.cs;
//    exports;
//    opens to
}