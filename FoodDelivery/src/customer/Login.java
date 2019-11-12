package customer;
public class Login{
    private String emailId;
    private String password;
    public void setDetails(String email,String pwd)
    {
        emailId=email;
        password=pwd;
    }
    public void setEmailId(String email)
    {
        emailId=email;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public String getEmailId()
    {
        return emailId;
    }
    public String getPassword()
    {
        return password;
    }
    
}
