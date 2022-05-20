package com.example.cs;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;


public class RegisterCustomerController implements Initializable {

    @FXML
    private TextField tfc_username;
    @FXML
    private PasswordField tfc_password;
    @FXML
    private TextField tfc_phonenumber;
    @FXML
    private TextField tfc_address;
    @FXML
    private TextField tfc_carmanufacturer;
    @FXML
    private TextField tfc_yearofproduction;
    @FXML
    private Button button_register;
    @FXML
    private Button backloginButton;
    @FXML
    private Label Labelsucces;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File brandingFile = new File("src/main/resources/com/example/cs/service.png");
//        Image brandingImage = new Image(brandingFile.toURI().toString());
//        brandingImageView.setImage(brandingImage);

    }



    public void registerButtonOnAction(ActionEvent actionEvent){
        registerCustomer();


    }
    public void backToLogin(ActionEvent event){
        Stage stage = (Stage) backloginButton.getScene().getWindow();
        stage.close();
        //Platform.exit();
    }

    public void registerCustomer(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String username = tfc_username.getText();
        String password = tfc_password.getText();
        String phone = tfc_phonenumber.getText();
        String address = tfc_address.getText();
        String car = tfc_carmanufacturer.getText();
        String year = tfc_yearofproduction.getText();

        String insertFields = "insert into users (username,password,address,car,year,phone) values ('";
        String insertValues = username + "','" + password + "','" + address + "','" + car + "','" + year + "','" + phone + "')";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            Labelsucces.setText("Customer registered successfully!");//la final, dupa verificari ca text fields nu sunt empty
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
