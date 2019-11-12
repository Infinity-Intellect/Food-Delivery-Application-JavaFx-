package application;
import customer.Customer;
import customer.Address;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class UserProfilePageController {
    private String emailId;
    private Customer customer;
    private Address customerAddress;
    @FXML
    private Label welcomePrompt;
    @FXML
    private Label customerName;
    @FXML
    private Label customerEmailId;
    @FXML
    private Label customerPhoneNumber;
    @FXML
    private Label houseNumber;
    @FXML
    private Label street;
    @FXML
    private Label locality;
    @FXML
    private Label pincode;
    
    
    public UserProfilePageController()
    {
        customer=new Customer();
        customerAddress=new Address();
    }
    public void handleCloseClick()
    {
        System.exit(0);
    }
    public void setWelcomePrompt(String userName)
    {
        String message="Welcome "+userName;
        welcomePrompt.setText(message);
    }
    public void setAllValues(String email)
    {
        emailId=email;
        customerEmailId.setText(emailId);
        String query="select *from customer where email='"+emailId+"'";
        try
        {
            Connection con=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quickfoods","root","sidd");
            if(con == null)
            {
                System.out.println("Failed to connect");
            }
            else
            {
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(query);
                while(rs.next())
                {
                    customer.setDetails(rs.getString("name"), Long.parseLong(rs.getString("phoneNumber")), emailId);
                }
                query="select *from address where phoneNumber='"+customer.getPhoneNumber()+"'";
                rs=st.executeQuery(query);
                while(rs.next())
                {
                    customerAddress.setDetails(Integer.parseInt(rs.getString("houseNumber")), rs.getString("street"), rs.getString("locality"), Long.parseLong(rs.getString("pincode")), customer.getPhoneNumber());
                }
                customerName.setText(customer.getCustomerName());
                customerEmailId.setText(customer.getEmailId());
                customerPhoneNumber.setText(Long.toString(customer.getPhoneNumber()));
                houseNumber.setText(Integer.toString(customerAddress.getHouseNumber()));
                street.setText(customerAddress.getStreet());
                locality.setText(customerAddress.getLocality());
                pincode.setText(Long.toString(customerAddress.getPincode()));
                
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void handleDeleteClick(ActionEvent e)
    {
        String query="delete from login where emailId='"+customer.getEmailId()+"'";
        Connection con=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quickfoods","root","sidd");
            if(con == null)
            {
                System.out.println("Connection Failed  !");
            }
            else
            {
                Statement st=con.createStatement();
                int rowsUpdated=st.executeUpdate(query);
                if(rowsUpdated>0)
                {
                    System.out.println("Deleted !");
                    FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("dialogBox.fxml"));
                    Parent dialogRoot=fxmlLoader.load();
                    Stage dialogBox=new Stage();
                    dialogBox.initStyle(StageStyle.UNDECORATED);
                    dialogBox.setScene(new Scene(dialogRoot));
                    
                    DialogBoxController dialog=fxmlLoader.getController();
                    dialog.setText("Aw, We'll Miss You! Our food will always be waiting for you");
                    dialogBox.show();
                    fxmlLoader=new FXMLLoader(getClass().getResource("homePage.fxml"));
                    Parent root=fxmlLoader.load();
                    Stage window=new Stage();
                    window.setScene(new Scene(root));
                    window.initStyle(StageStyle.UNDECORATED);
                    
                    
                    Stage curStage=(Stage)((Node)e.getSource()).getScene().getWindow();
                    
                    PauseTransition wait=new PauseTransition(Duration.seconds(2));
                    wait.setOnFinished((y)->{
                       dialogBox.close();
                       curStage.close();
                       window.show();
                    });
                    wait.play();
                    
                    
                    
                    
                    con.close();
                    
                }
                else
                {
                    System.out.println("Deletion Unsuccessful !");
                }
                con.close();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        
    }
    public void handleHomeClick(MouseEvent e)
    {
        try
        {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("userHomePage.fxml"));
            Parent root=fxmlLoader.load();
            Stage window=new Stage();
            
            UserHomePageController page=fxmlLoader.getController();
            System.out.println(customer.getEmailId());
            page.setWelcomePrompt(customer.getCustomerName());
            page.setEmailId(customer.getEmailId());
            
            window.setScene(new Scene(root));
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
            
            Stage curStage=(Stage)((Node)e.getSource()).getScene().getWindow();
            curStage.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    public void handleChangePasswordClick(MouseEvent e) throws Exception
    {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("changePasswordPage.fxml"));
        Parent changePasswordRoot=fxmlLoader.load();
        Stage changePasswordStage=new Stage();
        changePasswordStage.setScene(new Scene(changePasswordRoot));
        changePasswordStage.initStyle(StageStyle.UNDECORATED);
        
        ChangePasswordPageController passwordController=fxmlLoader.getController();
        passwordController.clearFocus();
        passwordController.setEmailId(customer.getEmailId());
        
        changePasswordStage.show();
    }
}
