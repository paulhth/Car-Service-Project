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
import java.util.ResourceBundle;


public class MainPageManagerController implements Initializable {
    @FXML
    private Button button_logout;

    @FXML
    private Button button_logout1;

    @FXML
    private Button button_refresh;

    @FXML
    private Button button_send;

    @FXML
    private TableColumn<ManagerTableViewMP, String> col_name;

    @FXML
    private TableColumn<ManagerTableViewMP, String> col_request;

    @FXML
    private Label label_failure;

    @FXML
    private Label label_success;

    @FXML
    private TextField tf_username;

    @FXML
    private TextArea tfa_a_d_reason;

    @FXML
    private Label welcomeLabelManager;
    @FXML
    private TableView<ManagerTableViewMP> table_requests;


    ObservableList<ManagerTableViewMP> listS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            welcomeLabelManager.setText("Welcome " + LoginController.getServiceUsername() + "! ");
            Connection conn = DatabaseConnection.getConnection();
            col_name.setCellValueFactory(new PropertyValueFactory<ManagerTableViewMP,String>("name"));
            col_request.setCellValueFactory(new PropertyValueFactory<ManagerTableViewMP,String>("request"));
            listS = DatabaseConnection.getDataRequests(conn);
            table_requests.setItems(listS);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
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

    public void refreshTable(ActionEvent action){
        try {
            welcomeLabelManager.setText("Welcome " + LoginController.getServiceUsername() + "! ");
            Connection conn = DatabaseConnection.getConnection();
            col_name.setCellValueFactory(new PropertyValueFactory<ManagerTableViewMP,String>("name"));
            col_request.setCellValueFactory(new PropertyValueFactory<ManagerTableViewMP,String>("request"));
            listS = DatabaseConnection.getDataRequests(conn);
            table_requests.setItems(listS);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



}