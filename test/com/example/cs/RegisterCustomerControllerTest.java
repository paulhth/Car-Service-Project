package com.example.cs;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class RegisterCustomerControllerTest {

    @Test
    public void initialize() {
    }

    @Test
    public void registerButtonOnAction() {
    }

    @Test
    public void backToLogin() {
    }

    @Test
    public void registerCustomer() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

            try {
                Statement statement = connectDB.createStatement();
                insertValues();
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
    }


    @Test
    public void insertValues() {
        String sqlInsertwithParams = "insert into users(username,password,address,car,year,phone)" + "values(?,?,?,?,?,?)";
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connection = connectNow.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertwithParams);
            preparedStatement.setString(1, "victor");
            preparedStatement.setString(2, DatabaseConnection.encodePassword("victor", "shaworma"));
            preparedStatement.setString(3, "oltenia nr 4");
            preparedStatement.setString(4, "matiz");
            preparedStatement.setString(5, "2005");
            preparedStatement.setString(6, "123456");

            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}