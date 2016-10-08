package com.tiy;

/**
 * Created by DTG2 on 10/08/16.
 */
public class CheckingAccount extends BankAccount {

    private String accountName;
    private double balance;
    private int type;

    public CheckingAccount(String accountName, double balance, int type) {
        setAccountName(accountName);
        this.setBalance(balance);
        this.setType(type);
    }

}
