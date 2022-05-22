package com.example.cs;

public class CustomerMessage {
    String service;
    String request;
    String message;

    public CustomerMessage(String service, String request, String message) {
        this.service = service;
        this.request = request;
        this.message = message;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
