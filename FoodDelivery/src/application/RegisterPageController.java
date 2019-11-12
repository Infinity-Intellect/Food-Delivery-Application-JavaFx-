package application;
import customer.Customer;
import customer.Address;
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
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class RegisterPageController{
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private TextField houseNumber;
    @FXML
    private TextField street;
    @FXML
    private TextField locality;
    @FXML
    private TextField pincode;
    @FXML
    AnchorPane registerContainer;
    
    private Customer customer;
    private Address customerAddress;
    private Login customerLogin;
    
    public RegisterPageController()
    {
        customer=new Customer();
        customerLogin=new Login();
        customerAddress=new Address();
    }
    public void clearFocus()
    {
        registerContainer.requestFocus();
    }
    @FXML
    private void initialize(URL url,ResourceBundle rb)
    {
    }
    @FXML
    private void handleCloseClick()
    {
        System.exit(0);
    }
    @FXML
    private void handleRegisterClick(ActionEvent e)
    {
        boolean flag=true;
        try
        {
            //System.out.println(name.getText());
            if(name.getText()== null || name.getText().trim().isEmpty() || email.getText()== null || email.getText().trim().isEmpty() || houseNumber.getText()== null || houseNumber.getText().trim().isEmpty() || street.getText()== null || street.getText().trim().isEmpty() || locality.getText()== null || locality.getText().trim().isEmpty() || pincode.getText()== null || pincode.getText().trim().isEmpty() ) 
            {
                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("dialogBox.fxml"));
                Parent root=fxmlLoader.load();
                Stage window=new Stage();
                window.initStyle(StageStyle.UNDECORATED);
                window.setScene(new Scene(root));
                window.show();
                DialogBoxController dialogController=fxmlLoader.getController();
                dialogController.setText("Empty Field!");
                flag=false;
            }
            customer.setDetails(name.getText(),Long.parseLong(phoneNumber.getText()),email.getText());
            customerAddress.setDetails(Integer.parseInt(houseNumber.getText()),street.getText(),locality.getText(),Long.parseLong(pincode.getText()), Long.parseLong(phoneNumber.getText()) );
            
            if(password.getText().equals(confirmPassword.getText()))
            {
                customerLogin.setDetails(email.getText(),password.getText());
            }
            else
            {
                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("dialogBox.fxml"));
                Parent root=fxmlLoader.load();
                Stage window=new Stage();
                window.initStyle(StageStyle.UNDECORATED);
                window.setScene(new Scene(root));
                window.show();
                DialogBoxController dialogController=fxmlLoader.getController();
                dialogController.setText("Password mismatch!");
                flag=false;
            }          
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        try
        {            
            if(flag==true)
            {
                System.out.println("Inside register1");
                Connection con=null;
                Class.forName("com.mysql.cj.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quickfoods","root","sidd");
                
                if(con!=null)
                {
                    System.out.println("Connection Established !");
                    try
                    {
                        String customerQuery="insert into customer values('"+customer.getCustomerName()+"','"+customer.getEmailId()+"',"+customer.getPhoneNumber()+")";
                        String loginQuery="insert into login values('"+customerLogin.getEmailId()+"','"+customerLogin.getPassword()+"')";
                        String addressQuery="insert into address values('"+customerAddress.getHouseNumber()+"','"+customerAddress.getStreet()+"','"+customerAddress.getLocality()+"',"+customerAddress.getPincode()+","+customerAddress.getPhoneNumber()+")";
                        Statement st=con.createStatement();
                        int res=st.executeUpdate(loginQuery);
                        if(res>0)
                        {
                            System.out.println("New Account(Customer) created !");
                        }
                        res=st.executeUpdate(customerQuery);
                        if(res>0)
                        {
                            System.out.println("New account(login) created !");
                        }
                        res=st.executeUpdate(addressQuery);
                        if(res>0)
                        {
                            System.out.println("New Account(address) created !");
                        }
                        
                        con.close();
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    
                
                    Parent root=FXMLLoader.load(getClass().getResource("loginPage.fxml"));
                    Stage window=new Stage();
                    window.setScene(new Scene(root));
                    window.initStyle(StageStyle.UNDECORATED);
                    
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("dialogBox.fxml"));
                    Parent dialogRoot=loader.load();
                    Stage dialogStage=new Stage();
                    dialogStage.setScene(new Scene(dialogRoot));
                    DialogBoxController dialog=loader.getController();
                    dialog.setText("Account Created! Please Login Again");
                    dialogStage.initStyle(StageStyle.UNDECORATED);
                    dialogStage.show();
                    
                    Stage curStage=(Stage)((Node)e.getSource()).getScene().getWindow();
                    PauseTransition wait=new PauseTransition(Duration.seconds(2));
                    wait.setOnFinished((y)->{
                       dialogStage.close();
                       window.show();
                       curStage.close();
                    });
                    wait.play();
                    
                    
                    
                    

                    
                    
                }
                
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
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
