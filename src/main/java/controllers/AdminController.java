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
    void AcceptOrder(ActionEvent event) {
        ConnectionUtil connectNow = new ConnectionUtil();
        Connection connectDb = connectNow.conDB();
        int index = corderTable.getSelectionModel().selectedIndexProperty().get();

        String insertFields = "update orders set status = 'Accepted' where idorders = " + (index+1);

        try{
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertFields);
            corderTable.refresh();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void AddProduct(ActionEvent event) {
        int index = productTable.getSelectionModel().selectedIndexProperty().get();
        System.out.println("inddex in add product: "+ index);

        try {

            FXMLLoader loader= new FXMLLoader(MainApplication.class.getResource("/view/AddProductView.fxml"));
            AnchorPane pane;
            pane = loader.load();
            ManageProductController controller = loader.getController();
            controller.setmain(this,index);
            Scene scene = new Scene(pane);
            secondaryStage.setScene(scene);
            secondaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            System.out.println("In the finally block");
            productsTable();
        }
    }
    @FXML
    void Logout(ActionEvent event) {
        main.mainWindow();
    }

    @FXML
    void DeleteProduct(ActionEvent event) {
        ConnectionUtil connectNow = new ConnectionUtil();
        Connection connectDb = connectNow.conDB();
        int index = productTable.getSelectionModel().selectedIndexProperty().get();
        System.out.println("IN the delete product index: ."+ (index+1));

        String insertFields = "DELETE FROM products WHERE idproducts = '" + (index+1)+"'";



        try{
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertFields);


        }
        catch (Exception e){
            e.printStackTrace();
        }

        // productTable.getItems().clear();
        productsTable();

    }
    @FXML 
    void EditProduct(ActionEvent event) {
        int index = productTable.getSelectionModel().selectedIndexProperty().get();
        try {
            FXMLLoader loader= new FXMLLoader(MainApplication.class.getResource("/viex/UpdateProductView.fxml"));
            AnchorPane pane;
            pane = loader.load();
            ManageProductController controller = loader.getController();
            controller.setmain(this,index);
            Scene scene = new Scene(pane);

            secondaryStage.setScene(scene);
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            productsTable();
        }
    }


    @FXML
    void RejectOrder(ActionEvent event) {
        ConnectionUtil connectNow = new ConnectionUtil();
        Connection connectDb = connectNow.conDB();
        int index = corderTable.getSelectionModel().selectedIndexProperty().get();

        String insertFields = "update orders set status = 'Rejected' where idorders = " + (index+1);

        try{
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertFields);
            corderTable.refresh();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void Cancel(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }



    public void userInfo(){
        try {
            String username = main.getUsername();

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
    public void setmain(MainApplication mainApplication, Stage primaryStage, String username) {
        this.primaryStage = primaryStage;
        this.main = mainApplication;
    }

    public void orderTable(){
        try {


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
        ccustname.setCellValueFactory(new PropertyValueFactory<Order, String>("Cname"));
        ccprice.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
        cquantity.setCellValueFactory(new PropertyValueFactory<Order, Integer>("quantity"));
        caddress.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
        cdate.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("date"));
        status.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
        cproductname.setCellValueFactory(new PropertyValueFactory<Order, String>("productname"));
        corderTable.setItems(orderList);
    }

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

}

