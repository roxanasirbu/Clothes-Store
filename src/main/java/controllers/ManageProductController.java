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

