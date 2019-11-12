package application;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import java.lang.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

public class FoodDelivery extends Application {
    private Stage window;
    private Parent root;
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        window.setTitle("QuickFood Inc.");
        window.initStyle(StageStyle.UNDECORATED);
        window.setScene(new Scene(root));
        window.show();
                        
        
    }
    public static void main(String[] args)
    {
        launch(args);
    }

}