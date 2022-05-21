package com.example.cs;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class MainPageCustomerController implements Initializable
{
    @FXML
    private Button button_logout;

    @FXML
    private Button button_make_request;

    @FXML
    private TableColumn<CustomerTableViewMP, String> col_location;

    @FXML
    private TableColumn<CustomerTableViewMP, String> col_name;

    @FXML
    private TableColumn<CustomerTableViewMP, String> col_offers;

    @FXML
    private Label label_failure;

    @FXML
    private Label label_succes;

    @FXML
    private TableView<CustomerTableViewMP> table_services;

    @FXML
    private TextField tf_service_name;

    @FXML
    private TextArea tfa_operation;

    @FXML
    private Label welcomeLabel;

    ObservableList<CustomerTableViewMP> listM;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setText("Welcome " + LoginController.getUsername() + "! ");

        col_name.setCellValueFactory(new PropertyValueFactory<CustomerTableViewMP,String>("Name"));
        col_name.setCellValueFactory(new PropertyValueFactory<CustomerTableViewMP,String>("Location"));
        col_name.setCellValueFactory(new PropertyValueFactory<CustomerTableViewMP,String>("Offered services"));

        listM = DataBaseConnectionTVCMP.getDataService();;//DataBaseConnectionTVCMP.getDataService();
        table_services.setItems(listM);
    }

    public void backToLogin(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage registerStage = (Stage) button_logout.getScene().getWindow();
            registerStage.setTitle("Log in page");
            registerStage.setScene(new Scene(root, 600, 400));
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
