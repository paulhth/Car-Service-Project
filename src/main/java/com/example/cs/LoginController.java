package com.example.cs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button button_sign_up_customer;

    @FXML
    private Button button_sign_up_service;
    @FXML
    private Button button_login_customer;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private Label msgLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/resources/com/example/cs/service.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

//        File lockFile = new File("src/main/resources/com/example/cs/lock.png");
//        Image  lockImage = new Image(lockFile.toURI().toString());
//        lockImageView.setImage(brandingImage);
    }
    ///////////////////////////////////////////////////////////////////////////////LogincreateCustomerAccount();
    ////////////////////////////////////////////////////////////////////////////pentru user (=customer)
    //username = Ion
    //parola = pizza

    public void loginCustomerAction(ActionEvent event){
        if(tf_username.getText().isBlank() == false && tf_password.getText().isBlank() == false){
            msgLabel.setText("Trying to log in ...");
            validateLoginCustomer();
        }else{
            msgLabel.setText("Enter your username and password");
        }
    }

    public void registerCustomerAction(ActionEvent event){
        Stage stage = (Stage) button_sign_up_customer.getScene().getWindow();
        stage.close();
    }

    public void validateLoginCustomer(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "select count(1) from users where username = '" + tf_username.getText() + "' and password = '" + tf_password.getText() + "'";

        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    msgLabel.setText("Successful login.");
                }else{
                    msgLabel.setText("Unsuccessful login.");
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    ////////////////////////////////////////////////////////////////////////////pentru manager:
    //username: asd auto
    //parola: asd

    public void loginManagerAction(ActionEvent event){
        if(tf_username.getText().isBlank() == false && tf_password.getText().isBlank() == false){
            msgLabel.setText("Trying to log in ...");
            validateLoginManager();
        }else{
            msgLabel.setText("Enter your username and password");
        }
    }

    public void registerManagerAction(ActionEvent event){
//        Stage stage = (Stage) button_sign_up_customer.getScene().getWindow();
//        stage.close();
        createCustomerAccount();
    }


    public void validateLoginManager(){
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String verifyLogin = "select count(1) from services where Name = '" + tf_username.getText() + "' and Password = '" + tf_password.getText() + "'";

        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    msgLabel.setText("Successful login.");
                }else{
                    msgLabel.setText("Unsuccessful login.");
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }




    ///////////////////////////////////////////////////////////////////////////////
        public void createCustomerAccount(){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("customer-registration.fxml"));
                //Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage registerStage = new Stage();
                registerStage.setTitle("Register");
                registerStage.setScene(new Scene(root, 600,400));
                registerStage.show();

            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }











}