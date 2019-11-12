package customer;

public class Address {
    private int houseNumber;
    private String street;
    private String locality;
    private long pincode;
    private long phoneNumber;
    
    public void setDetails(int hn,String st,String loc,long pin,long phno)
    {
        houseNumber=hn;
        street=st;
        locality=loc;
        pincode=pin;
        phoneNumber=phno;
    }
    public void setPhoneNumber(long ph)
    {
        phoneNumber=ph;
    }
    public int getHouseNumber()
    {
        return houseNumber;
    }
    public String getStreet()
    {
        return street;
    }
    public String getLocality()
    {
        return locality;
    }
    public long getPincode()
    {
        return pincode;
    }
    public long getPhoneNumber()
    {
        return phoneNumber;
    }
}
