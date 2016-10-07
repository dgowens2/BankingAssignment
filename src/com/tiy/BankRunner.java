package com.tiy;

import java.util.Scanner;

/**
 * Created by DTG2 on 10/07/16.
 */
public class BankRunner {
    public static void main(String[] args) {
        Bank myBank = new Bank();
        System.out.println("Welcome to " + myBank.bankName);

        System.out.println("What is your name?");

        Scanner userInput = new Scanner(System.in);

        String userName = userInput.nextLine();
        Customer myCustomer = new Customer();

        myCustomer.setUserName(userName);
        myBank.customerList.add(myCustomer);

        for (Customer customer : myBank.customerList) {
            System.out.println(customer.getUserName());
        }
        BankRunner myRunner = new BankRunner();
        BankAccount myAccount = new BankAccount();
        myRunner.mainMenu(myBank, myCustomer, myRunner, myAccount);
    }

    public void mainMenu(Bank myBank, Customer myCustomer, BankRunner myRunner, BankAccount myAccount) {
        Scanner userInput = new Scanner(System.in);

        int mainMenuChoice;
        while (true) {
            System.out.println("1. Open a new account");
            System.out.println("2. View my Account");
            System.out.println("0. Exit System");

            mainMenuChoice = Integer.valueOf(userInput.nextLine());

            if (mainMenuChoice == 1) {
                myBank.newAccountMenu(userInput, myCustomer, myBank, myAccount);
            } else if (mainMenuChoice == 2) {
                myBank.transactionMenu(userInput, myCustomer, myBank, myRunner, myAccount);
            } else if (mainMenuChoice == 0) {
//                myBank.writeToFile();
                break;
            } else {
                System.out.println("Invalid selection. Please try again.");
                mainMenu(myBank, myCustomer, myRunner, myAccount);
            }
        }
    }
}
