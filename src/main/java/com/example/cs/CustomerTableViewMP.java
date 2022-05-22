package com.example.cs;

import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class CustomerTableViewMP{
    String name;
    String location;
    String offers;

    public CustomerTableViewMP(String name, String location, String offers) {
        this.name = name;
        this.location = location;
        this.offers = offers;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getOffers() {
        return offers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

}
