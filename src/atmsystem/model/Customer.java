package atmsystem.model;

public class Customer 
{
    private int customerID;
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
    private String email;

    public Customer(int customerID, String firstname, String lastname, String address, String phone, String email) 
    {
        this.customerID = customerID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public void setCustomerID(int customerID) 
    {
        this.customerID = customerID;
    }

    public int getCustomerID() 
    {
        return customerID;
    }

    public void setFirstname(String firstname) 
    {
        this.firstname = firstname;
    }

    public String getFirstname() 
    {
        return firstname;
    }

    public void setLastname(String lastname) 
    {
        this.lastname = lastname;
    }

    public String getLastname() 
    {
        return lastname;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
}