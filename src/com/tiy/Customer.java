package com.tiy;

import java.util.ArrayList;

/**
 * Created by DTG2 on 10/07/16.
 */
public class Customer {
    String userName;
    ArrayList<BankAccount> customerAccounts = new ArrayList<>();

    public Customer() {
    }

    public Customer(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addCustomerAccount(BankAccount thisBankAccount) {
        customerAccounts.add(thisBankAccount);
    }

    public ArrayList<BankAccount> getCustomerAccounts() {
        return customerAccounts;
    }

    public void setCustomerAccounts(ArrayList<BankAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }
}
