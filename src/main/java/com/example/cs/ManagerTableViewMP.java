package com.example.cs;

public class ManagerTableViewMP {
    String name;
    String request;

    public ManagerTableViewMP(String name, String request) {
        this.name = name;
        this.request = request;
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
