package com.tiy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by DTG2 on 10/07/16.
 */
public class BankRunner {
    public static void main(String[] args) {
        BankRunner myRunner = new BankRunner();

        Bank myBank = new Bank();

        Customer myCustomer = new Customer();

        BankAccount myAccount = new BankAccount();

        System.out.println("Welcome to " + myBank.bankName);

        myRunner.getUserName(myRunner, myBank, myCustomer, myAccount);
    }

    public void getUserName (BankRunner myRunner, Bank myBank, Customer myCustomer, BankAccount myAccount){

        System.out.println("What is your name?");

        Scanner userInput = new Scanner(System.in);

        String userName = userInput.nextLine();

        myCustomer.setUserName(userName);

        myBank.customerList.add(myCustomer);

        myRunner.readBank(myBank.customerList, userName, myBank, myRunner, myAccount, myCustomer);

        System.out.println("Hello, " + myCustomer.getUserName() + " how may we help you today?");

        myRunner.mainMenu(myBank, myCustomer, myRunner, myAccount);
    }

    public void mainMenu(Bank myBank, Customer myCustomer, BankRunner myRunner, BankAccount myAccount) {
        Scanner userInput = new Scanner(System.in);

        int mainMenuChoice;
        while (true) {
            System.out.println("1. Open a new account");
            System.out.println("2. View my Account");
            System.out.println("4. Administrative User");
            System.out.println("0. Exit System");

            mainMenuChoice = Integer.valueOf(userInput.nextLine());

            if (mainMenuChoice == 1) {
                myBank.newAccountMenu(userInput, myCustomer, myBank, myAccount);
            } else if (mainMenuChoice == 2) {
                if (myCustomer.customerAccounts.isEmpty()){
                    System.out.println("We don't have an account for you yet.\n");
                    myBank.newAccountMenu(userInput, myCustomer, myBank, myAccount);
                }
                else {
                    myBank.transactionMenu(userInput, myCustomer, myBank, myRunner, myAccount);
                }
            } else if (mainMenuChoice == 4) {
                adminUser(userInput, myBank);
            } else if (mainMenuChoice == 0) {
               writeBankFile(myBank);
                break;
            } else {
                System.out.println("Invalid selection. Please try again.");
                break;
            }
        }
    }

    public void adminUser(Scanner userInput, Bank myBank) {
        while (true) {
            System.out.println("1. See a list of all customers");
            System.out.println("2. See total in deposits");
            System.out.println("0. Back to main menu");

            int mainMenuChoice = Integer.valueOf(userInput.nextLine());

            if (mainMenuChoice == 1) {
                for (Customer customer : myBank.customerList) {
                    System.out.println(customer.getUserName());
                }
            } else if (mainMenuChoice == 2) {
                for (Customer customers : myBank.customerList) {
                    for (BankAccount bankAccounts : customers.getCustomerAccounts()) {
                        System.out.println(customers.getUserName() + " " + bankAccounts.getAccountName() + " " + bankAccounts.getBalance());
                    }
                }
            } else if (mainMenuChoice == 0) {
                break;
            }
        }
    }

   public void writeBankFile(Bank myBank) {
        FileWriter bankWriter = null;
        FileWriter accountsWriter;

        try {
            File bankFile = new File("bank.txt");
            bankWriter = new FileWriter(bankFile);

            for (Customer thisCustomer : myBank.customerList){
                bankWriter.write(thisCustomer.getUserName() + ",");

                File customerFile = new File(thisCustomer.getUserName() + ".txt");
                accountsWriter = new FileWriter(customerFile);

                for (BankAccount currentAccount : thisCustomer.getCustomerAccounts()) {
                    accountsWriter.write("account.name= " + currentAccount.getAccountName() + "\r\n");
                    accountsWriter.write("account.balance= " + currentAccount.getBalance() + "\r\n");
                    accountsWriter.write("account.type=" + currentAccount.getType() + "\r\n");
                }
                accountsWriter.close();
            }
            bankWriter.close();
        }
        catch (Exception exception) {
            System.out.println("Exception while writing to file");
            exception.printStackTrace();
        }
        finally {
            if (bankWriter != null); {
                try {
                    bankWriter.close();
                } catch (Exception exception) {
                }
            }
        }
    }

    public void readBank(ArrayList<Customer> customerList, String userName, Bank myBank, BankRunner myRunner, BankAccount myBankAccount, Customer myCustomer) {
        try {
            ArrayList<BankAccount> customerAccounts;
            File bankFile = new File("bank.txt");
            Scanner fileScanner = new Scanner(bankFile);
            while(fileScanner.hasNext()){
                String nextLine = fileScanner.nextLine();
                String[] thisName = nextLine.split(",");
                for (String currentName : thisName) {
                    if (currentName.equals(userName)) {
                        myCustomer = new Customer(userName);
                        customerAccounts = new ArrayList<>();
                        File accountFile = new File(currentName + ".txt");
                        Scanner accountScanner = new Scanner(accountFile);
                        while (accountScanner.hasNext()) {
                            String accountName = accountScanner.nextLine().split("=")[1];
                            double balance = Double.valueOf(accountScanner.nextLine().split("=")[1]);
                            int type = Integer.valueOf(accountScanner.nextLine().split("=")[1]);
                            if (type == 1) {
                                myCustomer.customerAccounts.add(new CheckingAccount(accountName, balance, 1));
                            } else if (type == 2) {
                                myCustomer.customerAccounts.add(new SavingsAccount(accountName, balance, 2));
                            } else if (type == 3) {
                                myCustomer.customerAccounts.add(new RetirementAccount(accountName, balance, 3));
                            }
                        }
                        myBank.customerList.add(myCustomer);
                    }
                }
            }
            System.out.println("Customer information received for " + userName);
        } catch (IOException exception){
            System.out.println("No Files in System");
        }

        System.out.println("Hello, " + myCustomer.getUserName() + " how may we help you today?");

        myRunner.mainMenu(myBank, myCustomer, myRunner, myBankAccount);
    }

    public static boolean runInterestThread = true;

}
