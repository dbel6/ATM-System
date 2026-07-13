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

## What I Learned
- Designing structured applications using OOP
- Handling user input and errors effectively
- Building functional and interactive programs
