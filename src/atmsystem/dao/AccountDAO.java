package atmsystem.dao;

import atmsystem.db.DBConnection;
import atmsystem.model.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO 
{
    public void addAccount(Account account) 
    {
        String sql = "INSERT INTO Account (customerID, type, balance) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, account.getCustomerID());
            ps.setString(2, account.getType());
            ps.setDouble(3, account.getBalance());
            ps.executeUpdate();
            System.out.println("Account added successfully");
        } 
        catch (SQLException e) 
        {
            System.err.println("Error adding account: " + e.getMessage());
        }
    }

    public List<Account> getAllAccounts() 
    {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Account";

        try (Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) 
        {
            while (rs.next()) 
            {
                accounts.add(new Account
                (
                rs.getInt("accountID"),
                rs.getInt("customerID"),
                rs.getString("type"),
                rs.getDouble("balance")
                ));
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error retrieving accounts: " + e.getMessage());
        }

        return accounts;
    }

    public Account getAccountByID(int accountID) 
    {
        String sql = "SELECT * FROM Account WHERE accountID = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, accountID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
            {
                return new Account
                (
                rs.getInt("accountID"),
                rs.getInt("customerID"),
                rs.getString("type"),
                rs.getDouble("balance")
                );
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error retrieving account: " + e.getMessage());
        }

        return null;
    }

    public void updateAccount(Account account) 
    {
        String sql = "UPDATE Account SET customerID = ?, type = ?, balance = ? WHERE accountID = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, account.getCustomerID());
            ps.setString(2, account.getType());
            ps.setDouble(3, account.getBalance());
            ps.setInt(4, account.getAccountID());
            ps.executeUpdate();
            System.out.println("Account updated successfully");
        } 
        catch (SQLException e) 
        {
            System.err.println("Error updating account: " + e.getMessage());
        }
    }

    public void deleteAccount(int accountID) 
    {
        String sql = "DELETE FROM Account WHERE accountID = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, accountID);
            ps.executeUpdate();
            System.out.println("Account deleted successfully");
        } 
        catch (SQLException e) 
        {
            System.err.println("Error deleting account: " + e.getMessage());
        }
    }
}
