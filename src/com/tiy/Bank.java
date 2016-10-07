package com.tiy;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by DTG2 on 10/07/16.
 */
public class Bank {
    String bankName = "DonaldBank";
    ArrayList<Customer> customerList = new ArrayList<>();
//    BankAccount myBankAccount = new BankAccount();


    public Bank() {
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public void newAccountMenu(Scanner userInput, Customer thisCustomer, Bank myBank, BankAccount myAccount){

        BankRunner myRunner = new BankRunner();

        System.out.println("What type of account would you like to open?\n");

        while (true) {
            System.out.println("1. Checking account");
            System.out.println("2. Savings account");
            System.out.println("3. Retirement account");
            System.out.println("0. Back to main menu");

            int menuChoice = Integer.valueOf(userInput.nextLine());

            if (menuChoice == 1) {
                System.out.println("What would you like to call this account?");
                String checkingAccountName = userInput.nextLine();
                System.out.println("How much would you like to place in the account?");
                double initialBalance = Double.valueOf(userInput.nextLine());
                thisCustomer.customerAccounts.add(new BankAccount(checkingAccountName, initialBalance));
                for (BankAccount accounts : thisCustomer.customerAccounts) {
                    System.out.println(thisCustomer.getUserName() + " " + accounts.getAccountName() + " " + accounts.balance + " " + accounts.getBalance());
                }
            } else if (menuChoice == 2) {
                System.out.println("What would you like to call this account?");
                String savingsAccountName = userInput.nextLine();
                System.out.println("How much would you like to place in the account?");
                double initialBalance = Double.valueOf(userInput.nextLine());
                thisCustomer.customerAccounts.add(new BankAccount(savingsAccountName, initialBalance));
                for (BankAccount accounts : thisCustomer.customerAccounts) {
                    System.out.println(thisCustomer.getUserName() + " " + accounts.getAccountName() + " " + accounts.balance + " " + accounts.getBalance());
                }
            } else if (menuChoice == 3) {
                System.out.println("What would you like to call this account?");
                String retirementAccountName = userInput.nextLine();
                System.out.println("How much would you like to place in the account?");
                double initialBalance = Double.valueOf(userInput.nextLine());
                thisCustomer.customerAccounts.add(new BankAccount(retirementAccountName, initialBalance));
                for (BankAccount accounts : thisCustomer.customerAccounts) {
                    System.out.println(thisCustomer.getUserName() + " " + accounts.getAccountName() + " " + accounts.balance + " " + accounts.getBalance());
                }
            } else if (menuChoice == 0) {
                myRunner.mainMenu(myBank, thisCustomer, myRunner, myAccount);
            } else {
                System.out.println("Invalid selection. Please try again");
                newAccountMenu(userInput, thisCustomer, myBank, myAccount);
            }
        }
    }

    public void transactionMenu(Scanner userInput, Customer thisCustomer, Bank myBank, BankRunner myRunner, BankAccount myAccount) {

        System.out.println("which account would you like to view?");

        int index = 1;
        for (BankAccount accounts : thisCustomer.customerAccounts) {
            System.out.println(index + ": " + thisCustomer.getUserName() + " " + accounts.getAccountName() + " " + accounts.balance + " " + accounts.getBalance());
            index++;
        }

        int accountChoice = Integer.valueOf(userInput.nextLine());

        BankAccount activeAccount = thisCustomer.getCustomerAccounts().get(accountChoice -1);
        System.out.println(activeAccount.getAccountName());

        System.out.println("What would you like to do?");

        while (true) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Print Info");
            System.out.println("0. Back to main menu");

            int menuChoice = Integer.valueOf(userInput.nextLine());

            if (menuChoice == 1) {
                System.out.println("How much would you like to deposit?");
                double depositAmount = Double.valueOf(userInput.nextLine());
                activeAccount.deposit(depositAmount);
            } else if (menuChoice == 2) {
                System.out.println("How much would you like to withdraw?");
                double withdrawalAmount = Double.valueOf(userInput.nextLine());
                activeAccount.withdraw(withdrawalAmount);
            } else if (menuChoice == 3) {
                myAccount.transfer(userInput, thisCustomer, activeAccount);
            } else if (menuChoice == 0) {
                myRunner.mainMenu(myBank, thisCustomer, myRunner, myAccount);
            }
        }
    }
}
