package controllers;


import app.MainApplication;
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
import manager.ConnectionUtil;
import model.Order;
import model.Product;

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
    private Label userEmail;

    @FXML
    private Label userFirstName;

    @FXML
    private Label userLastName;

    @FXML
    private Label userPhoneNum;


    public void orderTable(){
        try {


            corderTable.getItems().clear();
            Connection con = ConnectionUtil.conDB();
            ResultSet rs = con.createStatement().executeQuery("select * from orders");

            while (rs.next()) {
                orderList.add(new Order(

                        rs.getString("Cname"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("address"),
                        rs.getDate("date"),
                        rs.getString("status"),
                        rs.getString("productname")

                ));
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ccprice.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
        cquantity.setCellValueFactory(new PropertyValueFactory<Order, Integer>("quantity"));
        caddress.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
        cdate.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("date"));
        status.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
        cproductname.setCellValueFactory(new PropertyValueFactory<Order, String>("productname"));
        corderTable.setItems(orderList);
    }


}