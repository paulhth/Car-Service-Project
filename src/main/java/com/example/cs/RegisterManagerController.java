package com.example.cs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;


public class RegisterManagerController implements Initializable {

    @FXML
    private TextField tf_service_name;
    @FXML
    private TextField tf_location;
    @FXML
    private TextField tf_offered_services;
    @FXML
    private TextField tf_phone;
    @FXML
    private PasswordField tf_password;
    @FXML
    private Label labelSucces;
    @FXML
    private Button backloginButton;
    @FXML
    private Label labelFailure;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




    public void backToLogin(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage registerStage = (Stage) backloginButton.getScene().getWindow();
            registerStage.setTitle("Log in page");
            registerStage.setScene(new Scene(root, 600, 400));
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        //Platform.exit();
    }

    public void registerButtonOnAction(ActionEvent actionEvent){
        registerManager();
    }

    public void registerManager(){
        labelFailure.setText("");
        labelSucces.setText("");
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if(!tf_service_name.getText().isEmpty() && !tf_password.getText().isEmpty() && !tf_location.getText().isEmpty() && !tf_offered_services.getText().isEmpty() && !tf_phone.getText().isEmpty()) {
            String service_name = tf_service_name.getText();
            String password = tf_password.getText();
            String location = tf_location.getText();
            String offers = tf_offered_services.getText();
            String phone = tf_phone.getText();

            String insertFields = "insert into services (Name,Password,Location,Offers,Phone_number) values ('";
            String insertValues = service_name + "','" + DatabaseConnection.encodePassword(service_name,password) + "','" + location + "','" + offers + "','" + phone + "')";
            String insertToRegister = insertFields + insertValues;

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                labelSucces.setText("Service registered successfully!");//la final, dupa verificari ca text fields nu sunt empty
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
        else{
            labelFailure.setText("All fields must be filled in!");
        }
    }
}
