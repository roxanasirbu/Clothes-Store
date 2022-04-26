package app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import controllers.LoginController;
import java.io.IOException;

public class MainApplication extends Application {

    Stage primaryStage;
    Stage secondStage;
    static String username;
    String a;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        mainWindow();

    }

    public void mainWindow() {
        try {
            FXMLLoader loader= new FXMLLoader(MainApplication.class.getResource("SignIn.fxml"));
            AnchorPane pane;
            pane = loader.load();
            LoginController controller = loader.getController();
            controller.setmain(this,primaryStage);
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerWindow() {
        try {
            FXMLLoader loader= new FXMLLoader(MainApplication.class.getResource("SignUp.fxml"));
            AnchorPane pane;
            pane = loader.load();
            RegisterController controller = loader.getController();
            controller.setmain(this,primaryStage);
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void adminWindow() {
        try {
            FXMLLoader loader= new FXMLLoader(MainApplication.class.getResource("AdminView.fxml"));
            AnchorPane pane;
            pane = loader.load();
            AdminController controller = loader.getController();
            controller.setmain(this,primaryStage,username);
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void userWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("UserView.fxml"));
            AnchorPane pane;
            pane = loader.load();
            UserController controller = loader.getController();
            controller.setmain(this, primaryStage,username);
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    public  static String  getUsername(){
        return username;
    }






    public static void main(String[] args) {
        launch(args);
    }

}
