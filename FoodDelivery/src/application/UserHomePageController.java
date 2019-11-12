package application;
import customer.Login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class UserHomePageController{
    
    private String emailId;
    private String userName;
    @FXML
    private Label welcomeprompt;
    /*
    @FXML
    private Label restaurant1;
    @FXML
    private Label restaurant2;
    @FXML
    private Label restaurant3;
    @FXML
    private Label street1;
    @FXML
    private Label street2;
    @FXML
    private Label street3;
    @FXML
    private Label locality1;
    @FXML
    private Label locality2;
    @FXML
    private Label locality3;
    */
    public UserHomePageController()
    {
        
    }
    public void setWelcomePrompt(String userName)
    {
        this.userName=userName;
        welcomeprompt.setText("Welcome "+userName);
    }
    public void setEmailId(String email)
    {
        emailId=email;
    }
    @FXML
    public void handleCloseClick()
    {
        System.exit(0);
    }
    @FXML
    public void handleProfileClick(ActionEvent e)
    {
        try
        {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("userProfilePage.fxml"));
            Parent root=fxmlLoader.load();
            Stage window=new Stage();
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            
            
            window.show();
            UserProfilePageController userProfileController=new UserProfilePageController();
            userProfileController=fxmlLoader.getController();
            
            userProfileController.setWelcomePrompt(userName);
            userProfileController.setAllValues(emailId);
            
            Stage curStage=(Stage)((Node)e.getSource()).getScene().getWindow();
            curStage.close();
            
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void handleLogoutClick(ActionEvent e) throws Exception
    {
        
        try
        {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("homePage.fxml"));
            Parent root=fxmlLoader.load();
            Stage window=new Stage();
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            
            
            fxmlLoader=new FXMLLoader(getClass().getResource("dialogBox.fxml"));
            Parent dialogRoot=fxmlLoader.load();
            Stage dialogStage=new Stage();
            dialogStage.setScene(new Scene(dialogRoot));
            dialogStage.initStyle(StageStyle.UNDECORATED);            
            DialogBoxController dialogController=fxmlLoader.getController();
            dialogController.setText("Come Back Soon, Our food is yearning for you !");
            Stage currentStage=(Stage)((Node)e.getSource()).getScene().getWindow();
            dialogStage.show();
            PauseTransition wait=new PauseTransition(Duration.seconds(2));
            wait.setOnFinished((y)->{
                dialogStage.close();
                currentStage.close();
                window.show();
            });
            wait.play();
            
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    
}
