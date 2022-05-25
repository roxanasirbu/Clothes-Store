package controllers;

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
import app.MainApplication;
import model.*;
import manager.ConnectionUtil;

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
        //Object index = corderTable.getSelectionModel().getSelectedItems().get(0);

        try {
            FXMLLoader loader= new FXMLLoader(MainApplication.class.getResource("/view/AddOrderView.fxml"));
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
    void CancelOrder(ActionEvent event) {
        int index = corderTable.getSelectionModel().selectedIndexProperty().get();
        ConnectionUtil connectNow = new ConnectionUtil();
        Connection connectDb = connectNow.conDB();

        String insertFields = "update orders set status = 'Cancelled' where idorders = " + (index+1);

        try{
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertFields);
        }
        catch (Exception e){
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

    public void setData(){

    }


//used for CLOT32 (from CLOT30) - see the products list
    void productsTable(){

        productTable.setItems(null);
        try {


            Connection con = ConnectionUtil.conDB();
            ResultSet rs = con.createStatement().executeQuery("select * from products");

            while (rs.next()) {
                productList.add(new Product(

                        rs.getString("name"),
                        rs.getInt("size"),
                        rs.getDouble("price"),
                        rs.getString("description")
                ));
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cname.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        cprice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        csize.setCellValueFactory(new PropertyValueFactory<Product, Integer>("size"));
        cdesc.setCellValueFactory(new PropertyValueFactory<Product, String>("desc"));
        productTable.setItems(productList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productsTable();
        orderTable();
        userInfo();
    }

    public void setmain(MainApplication mainApplication, Stage primaryStage, String username) {
        this.username = username;
        this.main =mainApplication;
    }
}

