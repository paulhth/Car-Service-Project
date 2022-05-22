package com.example.cs;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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

    @FXML
    private Label labelCar;

    @FXML
    private Button btc_messages;//button customer messages

    ObservableList<CustomerTableViewMP> listM;

    ResultSet rs = null;
    PreparedStatement pst = null;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            welcomeLabel.setText("Welcome " + LoginController.getUsername() + "! ");
            Connection conn = DatabaseConnection.getConnection();
            labelCar.setText("Registered with " + DatabaseConnection.getCar(conn));
            col_name.setCellValueFactory(new PropertyValueFactory<CustomerTableViewMP,String>("name"));
            col_location.setCellValueFactory(new PropertyValueFactory<CustomerTableViewMP,String>("location"));
            col_offers.setCellValueFactory(new PropertyValueFactory<CustomerTableViewMP,String>("offers"));
            listM = DatabaseConnection.getDataService(conn);
            table_services.setItems(listM);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void showMessages(ActionEvent actionEvent){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("customer_received_messages.fxml"));
            Stage registerStage = (Stage) btc_messages.getScene().getWindow();
            registerStage.setTitle("Messages.");
            registerStage.setScene(new Scene(root, 700, 450));
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

    public boolean checkIfExists(Connection connection){

        try{
            ArrayList<String> lista_nume = DatabaseConnection.getNameString(connection);
            for(int i=0;i<lista_nume.size();i++){
                if(lista_nume.get(i).equals(tf_service_name.getText())){
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;

    }

    @FXML
    public void makeRequest(ActionEvent event){
        label_succes.setText("");
        label_failure.setText("");
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if(checkIfExists(connectDB) && !tfa_operation.getText().isEmpty()){//daca are numele in baza de date -> face request
            String customer = LoginController.getUsername();
            String operation = tfa_operation.getText();
            String ofertant = tf_service_name.getText();

            String insertFields = "insert into requests (customer,options,ofertant) values ('";
            String insertValues = customer + "','" + operation + "','" + ofertant + "')";
            String insertToRegister1 = insertFields + insertValues;

            insertFields = "insert into request_for_messages (customer,options,ofertant) values ('";
            String insertToRegisterMSG = insertFields + insertValues;


            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister1);
                statement.executeUpdate(insertToRegisterMSG);
                label_succes.setText("Request succesfully created!");;//dupa verificari ca text fields nu sunt empty

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }finally{
                tfa_operation.setText("");
                tf_service_name.setText("");
            }


        }
        else{
            label_failure.setText("Service not found or wanted offers not completed");
        }
    }
}
