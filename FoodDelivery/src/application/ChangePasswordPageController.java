package application;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import customer.Login;
import java.sql.*;
import javafx.event.ActionEvent;


public class ChangePasswordPageController {
    private Login customerLogin;
    @FXML
    AnchorPane changePasswordContainer;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    public ChangePasswordPageController()
    {
        customerLogin=new Login();
    }
    public void clearFocus()
    {
        changePasswordContainer.requestFocus();
    }
    public void setEmailId(String email)
    {
        
        customerLogin.setEmailId(email);
    }
    public void handleCloseClick(MouseEvent e)
    {
        Stage curStage=(Stage)((Node)e.getSource()).getScene().getWindow();
        curStage.close();
    }
    public void handleChangePasswordClick(ActionEvent e) throws Exception
    {
        String pwd=password.getText();
        String confirmPwd=confirmPassword.getText();
        if(!pwd.equals(confirmPwd))
        {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("dialogBox.fxml"));
            Parent dialogRoot=fxmlLoader.load();
            DialogBoxController dialogController=fxmlLoader.getController();
            dialogController.setText("Password Mismatch");
            Stage dialogStage=new Stage();
            dialogStage.setScene(new Scene(dialogRoot));
            dialogStage.initStyle(StageStyle.UNDECORATED);
            PauseTransition wait=new PauseTransition(Duration.seconds(2));
            dialogStage.show();
            
            wait.setOnFinished((y)->{
                dialogStage.close();
            });
            
            wait.play();
        }
        else
        {
            customerLogin.setPassword(password.getText());
            System.out.println(customerLogin.getPassword());
            String query="update login set password='"+customerLogin.getPassword()+"' where emailId='"+customerLogin.getEmailId()+"'";
            Connection con=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quickfoods","root","sidd");
            if(con == null)
            {
                System.out.println("Connection Failed !");
            }
            else
            {
                Statement st=con.createStatement();
                int x=st.executeUpdate(query);
                if(x>0)
                {
                    System.out.println("Updated");
                    FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("dialogBox.fxml"));
                    Parent dialogRoot=fxmlLoader.load();
                    Stage dialogStage=new Stage();
                    dialogStage.setScene(new Scene(dialogRoot));
                    dialogStage.initStyle(StageStyle.UNDECORATED);
                    DialogBoxController dialogController=fxmlLoader.getController();
                    dialogController.setText("Password Changed, Your food is password protected !");
                    PauseTransition wait=new PauseTransition(Duration.seconds(3));
                    dialogStage.show();
                    Stage currentStage=(Stage)((Node)e.getSource()).getScene().getWindow();
                    wait.setOnFinished((y)->{
                        dialogStage.close();
                        currentStage.close();
                    });
                    wait.play();
                }
                else
                {
                    System.out.println("Error in updation !");
                }
            }
        }
        
    }
}
