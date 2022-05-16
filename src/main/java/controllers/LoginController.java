package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.MainApplication;
import manager.ConnectionUtil;

public class LoginController {
    MainApplication main;
    Stage primaryStage;
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private TextField txtusername;

    @FXML
    private TextField txtpassword;

    @FXML
    private Button loginbtn;

    @FXML
    private ImageView loginBg;

    @FXML
    private Label errorLabel;
    @FXML
    private Hyperlink registerHyp;

    @FXML
    void GoToRegister(ActionEvent event) {
        main.registerWindow();
    }
    @FXML
    void Login(ActionEvent event) {
        String userName = txtusername.getText().toString();
        String pass = txtpassword.getText().toString();

        //
        String sql = "SELECT * FROM users Where username = ? and password = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,pass);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                errorLabel.setTextFill(Color.TOMATO);
                errorLabel.setText("Invalid Credentials!");
            }
            else {
                redirect(userName);
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void redirect(String userName){
        String sql = "SELECT * FROM users Where username = ? and role = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,"Seller");

            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                main.setUsername(userName);
                main.userWindow();
            }
            else {
                main.setUsername(userName);
                main.adminWindow();
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public LoginController(){
        con = ConnectionUtil.conDB();
    }
    public void setmain(MainApplication mainApplication, Stage primaryStage) {
        this.main = mainApplication;
        this.primaryStage =primaryStage;
    }
}
