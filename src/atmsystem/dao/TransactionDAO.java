package atmsystem.dao;

import atmsystem.db.DBConnection;
import atmsystem.model.Transaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO 
{
    public void addTransaction(Transaction transaction) 
    {
        String sql = "INSERT INTO Transaction (accountID, type, amount, dateOfTransaction, targetAccountID) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, transaction.getAccountID());
            ps.setString(2, transaction.getType());
            ps.setDouble(3, transaction.getAmount());
            ps.setString(4, transaction.getDateOfTransaction());

            if (transaction.getTargetAccountID() == 0) 
            {
                ps.setNull(5, Types.INTEGER);
            } 
            else 
            {
                ps.setInt(5, transaction.getTargetAccountID());
            }

            ps.executeUpdate();
            System.out.println("Transaction added successfully");
        } 
        catch (SQLException e) 
        {
            System.err.println("Error adding transaction: " + e.getMessage());
        }
    }

    public List<Transaction> getAllTransactions() 
    {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction";

        try (Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) 
        {
            while (rs.next()) 
            {
                transactions.add(new Transaction
                (
                rs.getInt("transactionID"),
                rs.getInt("accountID"),
                rs.getString("type"),
                rs.getDouble("amount"),
                rs.getString("dateOfTransaction"),
                rs.getInt("targetAccountID")
                ));
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error retrieving transactions: " + e.getMessage());
        }

        return transactions;
    }

    public List<Transaction> getTransactionsByAccountID(int accountID) 
    {
        List<Transaction> transactions = new ArrayList<>();
        String sql = 
        "SELECT t.*, c.firstname, c.lastname, a.type AS accountType " + 
        "FROM Transaction t " + 
        "INNER JOIN Account a ON t.accountID = a.accountID " + 
        "INNER JOIN Customer c ON a.customerID = c.customerID " + 
        "WHERE t.accountID = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, accountID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                transactions.add(new Transaction
                (
                rs.getInt("transactionID"),
                rs.getInt("accountID"),
                rs.getString("type"),
                rs.getDouble("amount"),
                rs.getString("dateOfTransaction"),
                rs.getInt("targetAccountID")
                ));
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error retrieving transactions: " + e.getMessage());
        }

        return transactions;
    }

    public void updateTransaction(Transaction transaction) 
    {
        String sql = "UPDATE Transaction SET accountID = ?, type = ?, amount = ?, dateOfTransaction = ?, targetAccountID = ? WHERE transactionID = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, transaction.getAccountID());
            ps.setString(2, transaction.getType());
            ps.setDouble(3, transaction.getAmount());
            ps.setString(4, transaction.getDateOfTransaction());

            if (transaction.getTargetAccountID() == 0) 
            {
                ps.setNull(5, Types.INTEGER);
            } 
            else 
            {
                ps.setInt(5, transaction.getTargetAccountID());
            }

            ps.setInt(6, transaction.getTransactionID());
            ps.executeUpdate();
            System.out.println("Transaction updated successfully");
        } 
        catch (SQLException e) 
        {
            System.err.println("Error updating transaction: " + e.getMessage());
        }
    }

    public void deleteTransaction(int transactionID) 
    {
        String sql = "DELETE FROM Transaction WHERE transactionID = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, transactionID);
            ps.executeUpdate();
            System.out.println("Transaction deleted successfully");
        } 
        catch (SQLException e) 
        {
            System.err.println("Error deleting transaction: " + e.getMessage());
        }
    }
}