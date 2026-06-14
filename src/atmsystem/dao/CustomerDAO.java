package atmsystem.dao;

import atmsystem.db.DBConnection;
import atmsystem.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO 
{
    public void addCustomer(Customer customer) 
    {
        String sql = "INSERT INTO Customer (firstname, lastname, address, phone, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, customer.getFirstname());
            ps.setString(2, customer.getLastname());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getEmail());
            ps.executeUpdate();
            System.out.println("Customer added successfully");
        } 
        catch (SQLException e) 
        {
            System.err.println("Error adding customer: " + e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() 
    {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";

        try (Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) 
        {
            while (rs.next()) 
            {
                customers.add(new Customer
                (
                rs.getInt("customerID"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("email")
                ));
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error retrieving customers: " + e.getMessage());
        }

        return customers;
    }

    public Customer getCustomerByID(int customerID) 
    {
        String sql = "SELECT * FROM Customer WHERE customerID = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
            {
                return new Customer
                (
                rs.getInt("customerID"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("email")
                );
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error retrieving customer: " + e.getMessage());
        }

        return null;
    }

    public void updateCustomer(Customer customer) 
    {
        String sql = "UPDATE Customer SET firstname = ?, lastname = ?, address = ?, phone = ?, email = ? WHERE customerID = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, customer.getFirstname());
            ps.setString(2, customer.getLastname());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getEmail());
            ps.setInt(6, customer.getCustomerID());
            ps.executeUpdate();
        } 
        catch (SQLException e) 
        {
            System.err.println("Error updating customer: " + e.getMessage());
        }
    }

    public void deleteCustomer(int customerID) 
    {
        String sql = "DELETE FROM Customer WHERE customerID = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, customerID);
            ps.executeUpdate();
            System.out.println("Customer deleted successfully");
        } 
        catch (SQLException e) 
        {
            System.err.println("Error deleting customer: " + e.getMessage());
        }
    }
}