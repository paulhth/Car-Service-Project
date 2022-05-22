package com.example.cs;
//TVCMP = TableView for CustomerMainPage = mysqlconnect.java

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DataBaseConnectionTVCMP {
    Connection conn = null;

    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/schema_fis");
            return conn;
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
            return null;
        }
    }
    public static ObservableList<CustomerTableViewMP> getDataService(){
        Connection conn = ConnectDb();
        ObservableList<CustomerTableViewMP> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("select name,location,offers from services;");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new CustomerTableViewMP(rs.getString("name"),rs.getString("location"),rs.getString("offers")));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return null;
    }
}
