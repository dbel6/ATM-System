package atmsystem.model;

public class Transaction 
{
    private int transactionID;
    private int accountID;
    private String type;
    private double amount;
    private String dateOfTransaction;
    private int targetAccountID;

    public Transaction(int transactionID, int accountID, String type, double amount, String dateOfTransaction, int targetAccountID) 
    {
        this.transactionID = transactionID;
        this.accountID = accountID;
        this.type = type;
        this.amount = amount;
        this.dateOfTransaction = dateOfTransaction;
        this.targetAccountID = targetAccountID;
    }

    public void setTransactionID(int transactionID) 
    {
        this.transactionID = transactionID;
    }

    public int getTransactionID() 
    {
        return transactionID;
    }

    public void setAccountID(int accountID) 
    {
        this.accountID = accountID;
    }

    public int getAccountID() 
    {
        return accountID;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    public void setAmount(double amount) 
    {
        this.amount = amount;
    }

    public double getAmount() 
    {
        return amount;
    }

    public void setDateOfTransaction(String dateOfTransaction) 
    {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getDateOfTransaction() 
    {
        return dateOfTransaction;
    }

    public void setTargetAccountID(int targetAccountID) 
    {
        this.targetAccountID = targetAccountID;
    }

    public int getTargetAccountID() 
    {
        return targetAccountID;
    }
}
