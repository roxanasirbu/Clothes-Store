package com.example.pos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ManageProductController {

    int index = 0;
    AdminController adminController;
    @FXML
    private Button btncancel;

    @FXML
    private Button btnsave;

    @FXML
    private TextArea txtProductDescription;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductPrice;

    @FXML
    private TextField txtProductSize;

    @FXML
    private Button btnupdate;

    @FXML
    private TextArea txtUpdateProductDescription;

    @FXML
    private TextField txtUpdateProductName;

    @FXML
    private TextField txtUpdateProductPrice;

    @FXML
    private TextField txtUpdateProductSize;

    @FXML
    void Cancel(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }


    @FXML
    void UpdateProduct(ActionEvent event) {

        try{
            ConnectionUtil connectNow = new ConnectionUtil();
            Connection connectDb = connectNow.conDB();
            PreparedStatement stmt = connectDb.prepareStatement("UPDATE products SET name = ? , size = ? , price = ? , description = ?  Where idproducts = '" +(index+1)+"'");
            stmt.setString(1, txtUpdateProductName.getText());
            stmt.setString(2, txtUpdateProductPrice.getText());
            stmt.setString(3, txtUpdateProductSize.getText());
            stmt.setString(4, txtUpdateProductDescription.getText());

            stmt.executeUpdate();
            stmt.close();
            connectDb.close();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

            adminController.productsTable();

        }catch(Exception e){
            System.err.print( e.getClass().getName() + ": " + e.getMessage());
        }
    }
    @FXML
    void SaveProduct(ActionEvent event) {
        ConnectionUtil connectNow = new ConnectionUtil();
        Connection connectDb = connectNow.conDB();

        String name = txtProductName.getText();
        double price = Double.parseDouble(txtProductPrice.getText());
        int size = Integer.parseInt(txtProductSize.getText());
        String descr = txtProductDescription.getText();

        String insertFields = "INSERT INTO products(name,size,price,description) VALUES ('";
        String insertValues = name+"','"+size +"','"+ price +"','" + descr+"')";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertToRegister);
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            adminController.productsTable();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setmain(AdminController adminController, int primaryStage) {
        this.index = index;
        this.adminController = adminController;
    }
}
