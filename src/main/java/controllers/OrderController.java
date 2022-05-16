package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class OrderController {

    MainApplication main;
    int index;
    @FXML
    private Label A;

    @FXML
    private Button btncancel;

    @FXML
    private Button btnsave;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductPrice;

    @FXML
    private TextField txtProductSize;

    @FXML
    void Cancel(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }

    @FXML
    void SaveOrder(ActionEvent event) {
        ConnectionUtil connectNow = new ConnectionUtil();
        Connection connectDb = connectNow.conDB();

        String productname = txtProductName.getText();
        double price = Double.parseDouble(txtProductPrice.getText());
        int quantity = Integer.parseInt(txtProductSize.getText());
        String address = txtAddress.getText();
        String Cname;
        String insertFields = "INSERT INTO orders(cname,quantity,price,address,date,status,productname) VALUES ('";
        String insertValues = "Nadir"+"','"+quantity +"','"+ price +"','" + address+"','" + "2022-04-19"+"','" +"Pending"+"','" +productname+"')";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertToRegister);
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setmain(MainApplication main) {
        this.main = main;
    }
}
