package com.example.cs;

import static org.junit.Assert.*;

public class LoginControllerTest {


    @org.junit.Test
    public void loginCustomerAction() {

    }

    @org.junit.Test
    public void registerCustomerAction() {
    }

    @org.junit.Test
    public void setUsername() {
    }

    @org.junit.Test
    public void getUsername() {
        String username_test = LoginController.getUsername();
        assertEquals(LoginController.getUsername(),username_test);
    }

    @org.junit.Test
    public void setManagerUsername() {
    }

    @org.junit.Test
    public void getServiceUsername() {
        String servicename_test=LoginController.getServiceUsername();
        assertEquals(LoginController.getServiceUsername(),servicename_test);
    }

    @org.junit.Test
    public void validateLoginCustomer() {
    }

    @org.junit.Test
    public void checkCredentials() {
    }

    @org.junit.Test
    public void loginManagerAction() {
        validateLoginManager();
    }

    @org.junit.Test
    public void registerManagerAction() {
    }

    @org.junit.Test
    public void validateLoginManager() {
    }

    @org.junit.Test
    public void createCustomerAccount() {
    }
}