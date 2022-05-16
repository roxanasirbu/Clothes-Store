package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.MainApplication;
import manager.ConnectionUtil;


public class LoginController implements Initializable {

    public LoginController(){
        con = ConnectionUtil.conDB();
    }
    public void setmain(MainApplication mainApplication, Stage primaryStage) {
        this.main = mainApplication;
        this.primaryStage =primaryStage;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        File orderFile = new File("image/logo.jpeg");
        Image orderImage= new Image(orderFile.toURI().toString());
        orderImageView.setImage(orderImage);


    }
}
