package com.tiy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

/**
 * Created by DTG2 on 10/07/16.
 */
public class BankAccount {
    double balance;
    String accountName;
    private LocalDateTime accountCreation = LocalDateTime.now();
    private LocalDateTime lastTransaction = LocalDateTime.now();
    DateTimeFormatter localFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    private long sleepTimeout = 0;
    private double interestRate = 1;

    public BankAccount() {
    }

    public BankAccount(String accountName, double balance) {
        this.accountName = accountName;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double deposit(double depositAmount) {
        lastTransaction = LocalDateTime.now();
        balance += (depositAmount);
        System.out.println("Your " + accountName + " balance is $" + balance + " as of " + lastTransaction.format(localFormat) + ".");
        return balance;
    }

    public double withdraw(double withdrawAmount) {
        lastTransaction = LocalDateTime.now();
        balance -= withdrawAmount;
        System.out.println("Your " + accountName + " balance is $" + balance + " as of " + lastTransaction.format(localFormat) + ".");
        return balance;
    }

    public void transfer(Scanner userInput, Customer thisCustomer, BankAccount activeAccount) {
        int index = 1;

        System.out.println("How much would you like to transfer?");
        double transferAmount = Double.valueOf(userInput.nextLine());
        System.out.println("Which account would you like to transfer into?");
        for (BankAccount accounts : thisCustomer.customerAccounts) {
            System.out.println(index + ": " + thisCustomer.getUserName() + " " + accounts.getAccountName() + " " + accounts.balance + " " + accounts.getBalance());
            index++;
        }
        int transferSelection = Integer.valueOf(userInput.nextLine());
        System.out.println("Transferring to Account: " + transferSelection);
        BankAccount transferAccount = thisCustomer.customerAccounts.get(transferSelection-1);
        if (transferAccount == null || transferAccount == activeAccount) {
            System.out.println("Invalid selection");
        } else {
            activeAccount.withdraw(transferAmount);
            transferAccount.deposit(transferAmount);
            System.out.println("Transfer successful");
        }
    }
}

