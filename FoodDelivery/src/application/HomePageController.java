package application;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomePageController{
    
    private FXMLLoader fxmlLoader;
    public void handleCloseClick()
    {
        System.exit(0);
    }
    public void handleLoginClick(ActionEvent e) throws Exception
    {
        try
        {
            fxmlLoader=new FXMLLoader(getClass().getResource("loginPage.fxml"));
            Parent root=fxmlLoader.load();
            
            Stage window=new Stage();
            window.setScene(new Scene(root));
            window.initStyle(StageStyle.UNDECORATED);
            
            LoginPageController login= fxmlLoader.getController();
            login.clearFocus();
            
            window.show();
            
            Stage stage=(Stage)((Node) e.getSource()).getScene().getWindow();
            stage.close();
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void handleRegisterClick(ActionEvent e) throws Exception
    {
        try{
            System.out.println("Reg");
            fxmlLoader=new FXMLLoader(getClass().getResource("registerPage.fxml"));
            Parent root=fxmlLoader.load();
            Stage window=new Stage();
            window.setScene(new Scene(root));
            window.initStyle(StageStyle.UNDECORATED);
            
            RegisterPageController register=fxmlLoader.getController();
            register.clearFocus();
            
            window.show();
            Stage stage=(Stage)((Node) e.getSource()).getScene().getWindow();
            stage.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}