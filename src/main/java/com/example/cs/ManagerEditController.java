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

public class ManagerEditController implements Initializable {
    @FXML
    private Button button_back_main;

    @FXML
    private Label labelFailure;

    @FXML
    private Label labelSucces;

    @FXML
    private Label serviceName;

    @FXML
    private TextField tf_location;

    @FXML
    private TextField tf_offered_services;

    @FXML
    private PasswordField tf_password;

    @FXML
    private TextField tf_phone;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            serviceName.setText(LoginController.getServiceUsername());
            Connection conn = DatabaseConnection.getConnection();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void editManager(){
        labelFailure.setText("");
        labelSucces.setText("");
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if(!tf_password.getText().isEmpty() && !tf_location.getText().isEmpty() && !tf_offered_services.getText().isEmpty() && !tf_phone.getText().isEmpty()) {
            //String service_name = tf_service_name.getText();
            String password = tf_password.getText();
            String location = tf_location.getText();
            String offers = tf_offered_services.getText();
            String phone = tf_phone.getText();

            String insertFields = "update services set Password = '" + DatabaseConnection.encodePassword(LoginController.getServiceUsername(),password) + "', Location = '" + location + "', Offers = '" + offers + "', Phone_number = '" + phone + "' where Name = '" + LoginController.getServiceUsername() + "'";
             String insertValues = "";
            String insertToRegister = insertFields + insertValues;

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                labelSucces.setText("Edit profile successfully done!");//la final, dupa verificari ca text fields nu sunt empty
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
        else{
            labelFailure.setText("All fields must be filled in!");
        }
    }
    public void backToMainPage(ActionEvent actionEvent){
        try{
//
            Parent root = FXMLLoader.load(getClass().getResource("manager_main_page.fxml"));
            Stage registerStage = (Stage) button_back_main.getScene().getWindow();
            registerStage.setTitle("Logged in as manager.");
            registerStage.setScene(new Scene(root, 700, 450));


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
