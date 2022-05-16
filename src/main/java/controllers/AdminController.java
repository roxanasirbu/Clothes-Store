package controllers;
import app.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import manager.ConnectionUtil;
import model.Order;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



public class AdminController implements Initializable {

    MainApplication main;
    Stage primaryStage;
    Stage secondaryStage = new Stage();
    private ObservableList<Product> productList = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private ObservableList<Product> data;

    String firstName;
    String lastName;
    String phonenumber;
    String email;

    @FXML
    private Hyperlink btnLogout;

    @FXML
    private Label userEmail;

    @FXML
    private Label userFirstName;

    @FXML
    private Label userLastName;

    @FXML
    private Label userPhoneNum;

    @FXML
    private Button btnAcceptOrder;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnRejectOrder;

    @FXML
    private TableColumn<Order, String> caddress;

    @FXML
    private TableColumn<Order, Double> ccprice;

    @FXML
    private TableColumn<Order, String> ccustname;
    @FXML
    private TableColumn<Order, String> cproductname;

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
    private TableColumn<Order, String> status;

    @FXML
    private TableColumn<Product, Integer> csize;

    @FXML
    private Button deleteProduct;

    @FXML
    private Button editProduct;

    @FXML
    private Tab orderTab;

    @FXML
    private Tab productTab;

    @FXML
    private TableView<Product> productTable;

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
    void AddProduct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("AddProductView.fxml"));
            AnchorPane pane;
            pane = loader.load();
            ManageProductController controller = loader.getController();
            Scene scene = new Scene(pane);
            secondaryStage.setScene(scene);
            secondaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DeleteProduct(ActionEvent event) {
        ConnectionUtil connectNow = new ConnectionUtil();
        Connection connectDb = connectNow.conDB();
        int index = productTable.getSelectionModel().selectedIndexProperty().get();

        String insertFields = "DELETE FROM products WHERE idproducts = '" + (index + 1) + "'";

        try {
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertFields);
            productTable.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}