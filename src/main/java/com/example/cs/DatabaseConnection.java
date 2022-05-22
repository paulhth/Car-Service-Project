package com.example.cs;

import com.mysql.cj.log.Log;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseConnection { // = mysqlconnect.java
    public static Connection databaseLink; // = conn
    private static String car;

    public static Connection getConnection(){ // = ConnectDb()
        String databaseName = "schema_fis";//schema name
        String databaseUser= "root";
        String databasePassword = "admin";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch(Exception e){
             e.printStackTrace();
             e.getCause();
        }
        return databaseLink;
    }

    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/schema_fis");
            return conn;
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
            return null;
        }
    }
    public static ObservableList<CustomerTableViewMP> getDataService(Connection connection){
//        Connection conn = ConnectDb();
        ObservableList<CustomerTableViewMP> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = connection.prepareStatement("select * from services");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new CustomerTableViewMP(rs.getString("Name"),rs.getString("Location"),rs.getString("Offers")));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return list;
    }

    public static ObservableList<ManagerTableViewMP> getDataRequests(Connection connection){
        ObservableList<ManagerTableViewMP> list = FXCollections.observableArrayList();
        try{
            String service = LoginController.getServiceUsername();
            PreparedStatement ps = connection.prepareStatement("select * from requests where ofertant = '" + LoginController.getServiceUsername() + "'");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new ManagerTableViewMP(rs.getString("customer"),rs.getString("options")));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return list;
    }

    public static ArrayList<String> getNameString(Connection connection){//names
        ArrayList<String> name = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement("select * from services");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                name.add(rs.getString("Name"));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return name;
    }

    public static String getCar(Connection connection){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from users where username = '" + LoginController.getUsername() + "'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) car=rs.getString("car") + " - " + rs.getString("year");
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return car;
    }

}
