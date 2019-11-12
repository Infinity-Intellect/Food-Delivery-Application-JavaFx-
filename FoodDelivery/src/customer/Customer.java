package customer;
public class Customer
{
    private String customerName;
    private long phoneNumber;
    private String emailId;
    
    public String getCustomerName()
    {
        return customerName;
    }
    public long getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getEmailId()
    {
        return emailId;
    }
    public void setDetails(String name,long number,String email)
    {
        customerName=name;
        phoneNumber=number;
        emailId=email;
    }
}