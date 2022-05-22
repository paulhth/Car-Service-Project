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
import java.sql.Statement;
import java.util.ArrayList;
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
    static String request;

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

    public boolean checkIfExists(Connection connection){

        try{
            ArrayList<String> lista_nume = DatabaseConnection.getCustomerString(connection);
            for(int i=0;i<lista_nume.size();i++){
                if(lista_nume.get(i).equals(tf_username.getText())){
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void sendMessage(ActionEvent actionEvent){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if(checkIfExists(connectDB) && !tf_username.getText().isEmpty()){//daca are numele in baza de date -> send
            String ofertant = LoginController.getServiceUsername();
            String message = tfa_a_d_reason.getText();
            String customer = tf_username.getText();

            String insertFields = "insert into messages (ofertant,message,customer) values ('";
            String insertValues = ofertant + "','" + message + "','" + customer + "')";
            String insertToRegister = insertFields + insertValues;



            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                label_success.setText("Message succesfully sent!");;//dupa verificari ca text fields nu sunt empty
                //sterge din db:
//                PreparedStatement ps = connectDB.prepareStatement("select * from requests where username = '" + LoginController.getUsername() + "'");
//                ResultSet rs = ps.executeQuery();
//                setRequest(rs.getString("options"));

                String deleteCommand = "delete from requests where customer = '" + customer + "'";
                statement.executeUpdate(deleteCommand);
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }finally {
                tf_username.setText("");
                tfa_a_d_reason.setText("");
            }


        }
        else{
            label_failure.setText("Username not found or text area empty!");
        }
    }

//    public static void setRequest(String r){
//        request = r;
//    }
}