package atmsystem.dao;

import atmsystem.db.DBConnection;
import atmsystem.model.Card;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDAO 
{
    public void addCard(Card card) 
    {
        String sql = "INSERT INTO Card (cardNumber, accountID, pin, expirationDate, cvc) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setLong(1, card.getCardNumber());
            ps.setInt(2, card.getAccountID());
            ps.setString(3, card.getPin());
            ps.setString(4, card.getExpirationDate());
            ps.setInt(5, card.getCvc());
            ps.executeUpdate();
            System.out.println("Card added successfully");
        } 
        catch (SQLException e) 
        {
            System.err.println("Error adding card: " + e.getMessage());
        }
    }

    public List<Card> getAllCards() 
    {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM Card";

        try (Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) 
        {
            while (rs.next()) 
            {
                cards.add(new Card
                (
                rs.getLong("cardNumber"),
                rs.getInt("accountID"),
                rs.getString("pin"),
                rs.getString("expirationDate"),
                rs.getInt("cvc")
                ));
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error retrieving cards: " + e.getMessage());
        }

        return cards;
    }

    public Card getCardByNumber(long cardNumber) 
    {
        String sql = "SELECT * FROM Card WHERE cardNumber = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setLong(1, cardNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
            {
                return new Card
                (
                rs.getLong("cardNumber"),
                rs.getInt("accountID"),
                rs.getString("pin"),
                rs.getString("expirationDate"),
                rs.getInt("cvc")
                );
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error retrieving card: " + e.getMessage());
        }

        return null;
    }

    public void updateCard(Card card) 
    {
        String sql = "UPDATE Card SET accountID = ?, pin = ?, expirationDate = ?, cvc = ? WHERE cardNumber = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, card.getAccountID());
            ps.setString(2, card.getPin());
            ps.setString(3, card.getExpirationDate());
            ps.setInt(4, card.getCvc());
            ps.setLong(5, card.getCardNumber());
            ps.executeUpdate();
            System.out.println("Card updated successfully");
        } 
        catch (SQLException e) 
        {
            System.err.println("Error updating card: " + e.getMessage());
        }
    }

    public void deleteCard(long cardNumber) 
    {
        String sql = "DELETE FROM Card WHERE cardNumber = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setLong(1, cardNumber);
            ps.executeUpdate();
            System.out.println("Card deleted successfully");
        } 
        catch (SQLException e) 
        {
            System.err.println("Error deleting card: " + e.getMessage());
        }
    }

    public Card login(long cardNumber, String pin) 
    {
        String sql = "SELECT * FROM Card WHERE cardNumber = ? AND pin = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setLong(1, cardNumber);
            ps.setString(2, pin);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
            {
                return new Card
                (
                rs.getLong("cardNumber"),
                rs.getInt("accountID"),
                rs.getString("pin"),
                rs.getString("expirationDate"),
                rs.getInt("cvc")
                );
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Login error: " + e.getMessage());
        }

        return null;
    }
}