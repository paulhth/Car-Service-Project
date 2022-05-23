package com.example.cs;

public class ManagerTableViewMP {
    String name;
    String request;
    String car;

    public ManagerTableViewMP(String name, String request,String car) {
        this.name = name;
        this.request = request;
        this.car=car;
    }

    public String getCar(){
        return car;
    }

    public void setCar(String car){
        this.car=car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
