package com.example.cs;

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
