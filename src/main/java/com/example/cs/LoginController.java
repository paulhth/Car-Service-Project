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
    private Button button_login_service;
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

    private static String username;
    private static String serviceUsername;
    private static String car;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/resources/com/example/cs/service.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

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
        createCustomerAccount();
    }

    public void setUsername(){
        username=tf_username.getText();
    }

    public static String getUsername(){//static, ca sa fie vizibil in MainPageCustomerController.Label
        return username;
    }

    public void setManagerUsername(){
        serviceUsername = tf_username.getText();
    }

//    public static String getCar(){
//        DatabaseConnection connection = new DatabaseConnection();
//        Connection connectDB = connection.getConnection();
//
//        return "";
//    }

    public static String getServiceUsername(){
        return serviceUsername;
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
                    msgLabel.setText("Successful login.");//////////plec spre main page la customer
                    setUsername();
                    try{
//                        Parent root = FXMLLoader.load(getClass().getResource("customer_main_page.fxml"));
////                        //Scene scene = new Scene(fxmlLoader.load(), 600, 400);
////                        Stage mainStage = new Stage();
////                        mainStage.setTitle("Manager registration");
////                        mainStage.setScene(new Scene(root, 600,400));
////                        mainStage.show();
                            Parent root = FXMLLoader.load(getClass().getResource("customer_main_page.fxml"));
                            Stage registerStage = (Stage) button_login_customer.getScene().getWindow();
                            registerStage.setTitle("Logged in as customer.");
                            registerStage.setScene(new Scene(root, 700, 450));


                    }catch(Exception e){
                        e.printStackTrace();
                        e.getCause();
                    }
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
        try{
//            Parent root = FXMLLoader.load(getClass().getResource("register_manager.fxml"));
//            //Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//            Stage registerStage = new Stage();
//            registerStage.setTitle("Manager registration");
//            registerStage.setScene(new Scene(root, 600,400));
//            registerStage.show();
            Parent root = FXMLLoader.load(getClass().getResource("register_manager.fxml"));
            Stage registerStage = (Stage) button_sign_up_service.getScene().getWindow();
            registerStage.setTitle("Manager registration page.");
            registerStage.setScene(new Scene(root,600,400));

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

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
                    msgLabel.setText("Successful login.");//////////plec spre main page la service
                    setManagerUsername();
                    try{
//                        Parent root = FXMLLoader.load(getClass().getResource("customer_main_page.fxml"));
////                        //Scene scene = new Scene(fxmlLoader.load(), 600, 400);
////                        Stage mainStage = new Stage();
////                        mainStage.setTitle("Manager registration");
////                        mainStage.setScene(new Scene(root, 600,400));
////                        mainStage.show();
                        Parent root = FXMLLoader.load(getClass().getResource("manager_main_page.fxml"));
                        Stage registerStage = (Stage) button_login_service.getScene().getWindow();
                        registerStage.setTitle("Logged in as manager.");
                        registerStage.setScene(new Scene(root, 700, 450));


                    }catch(Exception e){
                        e.printStackTrace();
                        e.getCause();
                    }
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
               // Parent root = FXMLLoader.load(getClass().getResource("customer-registration.fxml"));
                //Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//                Stage registerStage = new Stage();
//                registerStage.setTitle("Customer registration");
//                registerStage.setScene(new Scene(root, 600,400));
//                registerStage.show();
                Parent root = FXMLLoader.load(getClass().getResource("customer-registration.fxml"));
                Stage registerStage = (Stage) button_sign_up_customer.getScene().getWindow();
                registerStage.setTitle("Customer registration page");
                registerStage.setScene(new Scene(root,600,400));

            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }











}