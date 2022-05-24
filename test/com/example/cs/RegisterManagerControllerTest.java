package com.example.cs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;

public class RegisterManagerControllerTest {

    @Test
    public void initialize() {
    }

    @Test
    public void backToLogin() {
        try {
//            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//            Stage registerStage = (Stage) backloginButton.getScene().getWindow();
//            registerStage.setTitle("Log in page");
//            registerStage.setScene(new Scene(root, 600, 400));
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @Test
    public void registerButtonOnAction() {
        registerManager();
    }

    @Test
    public void registerManager() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

            String service_name = "service_test";
            String password = "password_test";
            String location = "location_test";
            String offers = "offers_test";
            String phone = "phone_test";

            String insertFields = "insert into services (Name,Password,Location,Offers,Phone_number) values ('";
            String insertValues = service_name + "','" + DatabaseConnection.encodePassword(service_name,password) + "','" + location + "','" + offers + "','" + phone + "')";
            String insertToRegister = insertFields + insertValues;

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                //labelSucces.setText("Service registered successfully!");//la final, dupa verificari ca text fields nu sunt empty
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }


    }
