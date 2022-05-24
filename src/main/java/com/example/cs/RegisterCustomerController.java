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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    @FXML
    private Label LabelFailure;



    ArrayList<String> names = new ArrayList<String>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File brandingFile = new File("src/main/resources/com/example/cs/service.png");
//        Image brandingImage = new Image(brandingFile.toURI().toString());
//        brandingImageView.setImage(brandingImage);

    }



    public void registerButtonOnAction(ActionEvent actionEvent){
        registerCustomer(actionEvent);
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

    public void registerCustomer(ActionEvent event){
        LabelFailure.setText("");
        LabelFailure.setText("");
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if(!tfc_username.getText().isEmpty() && !tfc_password.getText().isEmpty() && !tfc_phonenumber.getText().isEmpty() && !tfc_address.getText().isEmpty() && !tfc_carmanufacturer.getText().isEmpty() && !tfc_yearofproduction.getText().isEmpty()) {
            String username = tfc_username.getText();
            String password = tfc_password.getText();
            String phone = tfc_phonenumber.getText();
            String address = tfc_address.getText();
            String car = tfc_carmanufacturer.getText();
            String year = tfc_yearofproduction.getText();
            String crypt_pass=DatabaseConnection.encodePassword(username,password);

//            String insertFields = "insert into users (username,password,address,car,year,phone) values ('";
//            String insertValues = username + "','" + crypt_pass + "','" + address + "','" + car + "','" + year + "','" + phone+ "')";
//            String insertToRegister = insertFields + insertValues;

            try {
                Statement statement = connectDB.createStatement();
                insertValues(connectDB,username,password,address,car,year,phone);
                Labelsucces.setText("Customer registered successfully!");//dupa verificari ca text fields nu sunt empty
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
        else {
            LabelFailure.setText("All fields must be filled in!");
        }
    }

    public static void insertValues(Connection connection,String username,String password,String address,String car,String year,String phone){
        String sqlInsertwithParams = "insert into users(username,password,address,car,year,phone)" + "values(?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertwithParams);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,DatabaseConnection.encodePassword(username,password));
            preparedStatement.setString(3,address);
            preparedStatement.setString(4,car);
            preparedStatement.setString(5,year);
            preparedStatement.setString(6,phone);

            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }





}
