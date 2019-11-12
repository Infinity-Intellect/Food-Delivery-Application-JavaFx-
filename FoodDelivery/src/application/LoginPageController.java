package application;
import customer.Login;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.sql.*;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.StageStyle;
import java.util.concurrent.TimeUnit;
import javafx.animation.PauseTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

 
public class LoginPageController
{
    @FXML 
    TextField email;
    @FXML 
    PasswordField password;
    @FXML
    AnchorPane loginContainer;
    public void clearFocus()
    {
        loginContainer.requestFocus();        
    }
    @FXML
    private void handleCloseClick()
    {
        System.exit(0);
        
    }
    @FXML
    private void handleLogin(ActionEvent event) throws Exception
    {
        System.out.println("Hello World");
        try{
            Connection con=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quickfoods","root","sidd");
            if(con!=null)
            {
               System.out.println("Connection Established ! "); 
            }
            else
            {
                System.out.println("Connection Failed !");
            }
            
            Login customer=new Login();
            
            
            customer.setDetails(email.getText(),password.getText());
            
            System.out.println(customer.getEmailId());
            
            String query="select * from login where emailId='"+customer.getEmailId()+"' and password='"+ customer.getPassword()+"'";
            Statement st=con.createStatement();
            
            ResultSet rs= st.executeQuery(query);
            if(rs.next())
            {
                System.out.println("Valid");
                
                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("userHomePage.fxml"));
                Parent root=fxmlLoader.load();
                
                UserHomePageController userHomePageController=fxmlLoader.getController();
                query="select name,email from customer where email='"+customer.getEmailId()+"'";
                String userNameForNextController="";
                String emailIdForNextController="";
                rs=st.executeQuery(query);
                while(rs.next())
                {
                    userNameForNextController=rs.getString("name");
                    emailIdForNextController=rs.getString("email");
                }
                
                
                
                userHomePageController.setWelcomePrompt(userNameForNextController);
                userHomePageController.setEmailId(emailIdForNextController);
                Stage window=new Stage();
                window.initStyle(StageStyle.UNDECORATED);
                window.setScene(new Scene(root));
                window.show();
                Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
            else
            {
                System.out.println("Invalid!");
                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("dialogBox.fxml"));
                Parent root=fxmlLoader.load();
                Stage window=new Stage();
                window.initStyle(StageStyle.UNDECORATED);
                window.setScene(new Scene(root));
                
                DialogBoxController dialogController= fxmlLoader.getController();
                dialogController.setText("Invalid Username/password");
                window.show();
                
                PauseTransition wait = new PauseTransition(Duration.seconds(1));
                wait.setOnFinished((e) -> {
                
                    window.close();
                }
                );
                wait.play();
                
                
                
                
            }
            con.close();
                 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    public void handleHomeButtonClick(MouseEvent e) throws Exception
    {
        Parent root=FXMLLoader.load(getClass().getResource("homePage.fxml"));
        Stage window=new Stage();
        window.setScene(new Scene(root));
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
        
        Stage curStage=(Stage)((Node)e.getSource()).getScene().getWindow();
        curStage.close();
    }
}