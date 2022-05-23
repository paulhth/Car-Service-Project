package com.example.cs;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class CustomerReceivedMessages implements Initializable {

    @FXML
    private TableColumn<CustomerMessage, String> col_message;

    @FXML
    private TableColumn<CustomerMessage, String> col_request;

    @FXML
    private TableColumn<CustomerMessage, String> col_service;

    @FXML
    private TableView<CustomerMessage> table_messages;

    @FXML
    private Button btc_backtomain;

    ObservableList<CustomerMessage> listM;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Connection conn = DatabaseConnection.getConnection();
            col_message.setCellValueFactory(new PropertyValueFactory<CustomerMessage,String>("message"));
            col_request.setCellValueFactory(new PropertyValueFactory<CustomerMessage,String>("request"));
            col_service.setCellValueFactory(new PropertyValueFactory<CustomerMessage,String>("service"));
            listM = DatabaseConnection.getDataMessages(conn);
            table_messages.setItems(listM);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void backToMain(){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("customer_main_page.fxml"));
                Stage registerStage = (Stage) btc_backtomain.getScene().getWindow();
                registerStage.setTitle("Logged in as customer.");
                registerStage.setScene(new Scene(root, 700, 450));
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
    }


}