CREATE DATABASE IF NOT EXISTS bankmanagementsystem;
USE bankmanagementsystem;

CREATE TABLE IF NOT EXISTS customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    address VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS accounts (
    AccountID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    AccountType VARCHAR(255),
    OpeningDate DATE,
    balance DECIMAL(10, 2) DEFAULT 0,
    FOREIGN KEY (CustomerID) REFERENCES customers(CustomerID)
);

CREATE TABLE IF NOT EXISTS cardinfo (
    AccountID INT AUTO_INCREMENT PRIMARY KEY,
    cardno VARCHAR(20) UNIQUE,
    pin VARCHAR(4),
    FOREIGN KEY (AccountID) REFERENCES accounts(AccountID)
);

CREATE TABLE IF NOT EXISTS ATM_Location (
    ATM_ID INT AUTO_INCREMENT PRIMARY KEY,
    Location VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS transactions (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    AccountID INT,
    ATM_ID INT,
    TransactionType VARCHAR(20),
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    amount DECIMAL(10, 2),
    balance DECIMAL(10, 2),
    FOREIGN KEY (AccountID) REFERENCES accounts(AccountID),
    FOREIGN KEY (ATM_ID) REFERENCES ATM_Location(ATM_ID)
);
