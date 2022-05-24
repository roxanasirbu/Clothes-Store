package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import app.MainApplication;
import manager.ConnectionUtil;
import model.*;


import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    MainApplication main;
    @FXML
    private ImageView ordersImageView;
    @FXML
    private ImageView logoImg;
    Stage primaryStage;
    String role;
    @FXML
    private Hyperlink hyperLinkLogin;

    @FXML
    private Button register;

    @FXML
    private ComboBox rolebox;
    @FXML
    private Label lblAddress;
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtfname;

    @FXML
    private TextField txtlname;


    @FXML
    void RedirectLogin(ActionEvent event) {
        main.mainWindow();
    }
    private User user = new User();


    @FXML
    void RegisterUser(ActionEvent event) {
        register();

    }
    public void register(){
        ConnectionUtil connectNow = new ConnectionUtil();
        Connection connectDb = connectNow.conDB();

        String fName = txtfname.getText() + " " + txtlname.getText();
        String lName = txtlname.getText();
        String emailId = txtEmail.getText();
        String password = txtPassword.getText();
        String userName = txtUsername.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();

        String insertFields = "INSERT INTO users(firstname,lastname,role,username,password,email,phonenumber,address) VALUES ('";
        String insertValues = fName+"','"+lName +"','"+ role +"','" + userName +"','" + password +"','" + emailId +"','" + phone +"','" + address+"')";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertToRegister);
            main.mainWindow();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
    @FXML
    void SelectRole(ActionEvent event) {
        role = rolebox.getSelectionModel().getSelectedItem().toString();
        if(role == "Customer"){
            txtAddress.setVisible(false);
            lblAddress.setVisible(false);
        }
        else {
            txtAddress.setVisible(true);
            lblAddress.setVisible(true);
        }
    }
    public void setmain(MainApplication main, Stage primaryStage) {
        this.main = main;
        this.primaryStage=primaryStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Seller",
                "Customer");
        rolebox.setItems(list);
        File ordersFile = new File("image/logo.jpg");
        Image ordersImage= new Image(ordersFile.toURI().toString());
        ordersImageView.setImage(ordersImage);

    }
}