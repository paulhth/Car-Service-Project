package com.example.cs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection { // = mysqlconnect.java
    public Connection databaseLink; // = conn

    public Connection getConnection(){ // = ConnectDb()
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


}
