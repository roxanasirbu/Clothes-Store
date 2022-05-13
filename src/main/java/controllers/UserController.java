package com.example.pos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController implements Initializable {

    Stage secondaryStage = new Stage();
    String username;
    MainApplication main;
    private ObservableList<Product> productList = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private ObservableList<Product> data;
    @FXML
    private Button btnBuy;

    @FXML
    private Button btnCancelOrder;

    @FXML
    private Hyperlink btnLogout;

    @FXML
    private TableColumn<Order, String> caddress;

    @FXML
    private TableColumn<Order, Double> ccprice;

    @FXML
    private TableColumn<Order, String> ccustname;

    @FXML
    private TableColumn<Order, LocalDate> cdate;

    @FXML
    private TableColumn<Product, String> cdesc;

    @FXML
    private TableColumn<Product, String> cname;

    @FXML
    private TableView<Order> corderTable;

    @FXML
    private TableColumn<Product, Double> cprice;

    @FXML
    private TableColumn<Order, Integer> cquantity;

    @FXML
    private  TableColumn<Order, String> cproductname;
    @FXML
    private TableColumn<Order, String> status;

    @FXML
    private TableColumn<Product, Integer> csize;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Tab orderTab;

    @FXML
    private Tab productTab;


    @FXML
    private Label userEmail;

    @FXML
    private Label userFirstName;

    @FXML
    private Label userLastName;

    @FXML
    private Label userPhoneNum;

    String firstName;
    String lastName;
    String phonenumber;
    String email;
    @FXML
    void BuyProduct(ActionEvent event) {
        Object index = corderTable.getSelectionModel().getSelectedItems().get(0);

        try {
            FXMLLoader loader= new FXMLLoader(MainApplication.class.getResource("AddOrderView.fxml"));
            AnchorPane pane;
            pane = loader.load();
            OrderController controller = loader.getController();
            controller.setmain(main);
            Scene scene = new Scene(pane);
            secondaryStage.setScene(scene);
            secondaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void Logout(ActionEvent event) {
        main.mainWindow();
    }


    public void userInfo(){
        try {
            username = main.getUsername();

            Connection con = ConnectionUtil.conDB();
            ResultSet rs = con.createStatement().executeQuery("select * from users where username = '"+username+"'");
            while (rs.next()) {


                firstName = rs.getNString("firstname");
                lastName = rs.getNString("lastname");
                email = rs.getNString("email");
                phonenumber = rs.getNString("phonenumber");


            }
            this.userFirstName.setText(firstName);
            this.userEmail.setText(email);
            this.userLastName.setText(lastName);
            this.userPhoneNum.setText(phonenumber);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

