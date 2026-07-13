# ATM System

A Java Swing desktop application simulating a real world ATM experience, connected to a MySQL database backend. Users authenticate with a card number and PIN, then manage customers, accounts, cards and transactions through a fully functional GUI.

## Summary
The ATM System allows users to perform banking operations including withdrawals, deposits and transfers. It follows an object oriented design structured across model, DAO and GUI layers, with a Java Swing frontend connected to a MySQL database. Full CRUD operations are supported for customers, accounts, cards and transactions, along with input validation and error handling throughout.

## The Problem
Banks need a reliable way to manage customer accounts and process financial transactions securely. This project simulates that by building a functioning ATM application where users log in via card number and PIN, then interact with their accounts through a clean interface. The challenge was to design a system that handled real banking constraints such as insufficient funds, invalid inputs and cascading data relationships across multiple database tables.

## My Role & Contribution
- Designed and implemented the full MySQL database schema with four linked tables: Customer, Account, Card and Transaction
- Built all DAO classes (AccountDAO, CardDAO, CustomerDAO, TransactionDAO) handle CRUD operations via JDBC
- Developed the Java Swing GUI across five panels: Login, Customer, Account, Card and Transaction
- Implemented input validation and error handling throughout all panels
- Wrote the inner join query linking Transaction, Account and Customer tables
- Produced the full project documentation including requirements, ER diagram, test cases and code snippets

## Approach
The system is structed into three layers:
The model layer holds the four data classes: Customer, Account, Card and Transaction, each with full getters and setters.
The DAO layer connects to MySQL via JDBC through DBConnection.java and provides create, read, update and delete methods for each entity.
TransactionDAO uses an inner join across the Transaction, Account and Customer tables to retrieve enriched transaction records.
The GUI layer is built entirely in Java Swing. LoginFrame handles authentication by validating a card number and PIN against the database. MainFrame provides tabbed navigation between the four management panels. Each panel displays a live table of records and a form for adding, updating or deleting entries, with confirmation dialogs and error messages for invalid input.

### Database tables:

## Challenges
- Keeping the GUI panels in sync after CRUD operations required refreshing combo boxes and tables across panels whenever related data changed, such as updating the account dropdown in the Card panel after a new account is added
- Handling the transfer transaction type required preventing transfers to the same account, checking for sufficient funds, and updating balances on two accounts atomically
- Designing input validation that covered all edge cases across five different panels, including regex for names and emails, numeric checks for card numbers and PIN, and range checks for balances and amounts
- Managing the database relationships meant deletes had to be handled carefully to avoid orphaned records across the four linked tables

## Solution
```java
// Authentication checks card number and PIN against the database
Card card = cardDAO.login(cardNumber, pinInput);
if (card != null) {
    new MainFrame(card).setVisible(true);
}

// Inner join retrieves transaction history with customer and account info
String sql =
    "SELECT t.*, c.firstname, c.lastname, a.type AS accountType " +
    "FROM Transaction t " +
    "INNER JOIN Account a ON t.accountID = a.accountID " +
    "INNER JOIN Customer c ON a.customerID = c.customerID " +
    "WHERE t.accountID = ?";

// Balance validation before processing a withdrawal
if (amount > account.getBalance()) {
    JOptionPane.showMessageDialog(this, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}
```

## What I Learned
- Designing structured applications using OOP
- Handling user input and errors effectively
- Building functional and interactive programs
