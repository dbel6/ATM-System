package atmsystem.model;

public class Card 
{
    private long cardNumber;
    private int accountID;
    private String pin;
    private String expirationDate;
    private int cvc;

    public Card(long cardNumber, int accountID, String pin, String expirationDate, int cvc) 
    {
        this.cardNumber = cardNumber;
        this.accountID = accountID;
        this.pin = pin;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
    }

    public void setCardNumber(long cardNumber) 
    {
        this.cardNumber = cardNumber;
    }

    public long getCardNumber() 
    {
        return cardNumber;
    }

    public void setAccountID(int accountID) 
    {
        this.accountID = accountID;
    }

    public int getAccountID() 
    {
        return accountID;
    }

    public void setPin(String pin) 
    {
        this.pin = pin;
    }

    public String getPin() 
    {
        return pin;
    }

    public void setExpirationDate(String expirationDate) 
    {
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() 
    {
        return expirationDate;
    }

    public void setCvc(int cvc) 
    {
        this.cvc = cvc;
    }

    public int getCvc() 
    {
        return cvc;
    }
}
