package com.tiy;

/**
 * Created by DTG2 on 10/08/16.
 */
public class SavingsAccount extends BankAccount implements  Runnable{

    public SavingsAccount(String accountName, double balance, int type) {
        setAccountName(accountName);
        this.setBalance(balance);
        this.setType(type);
        Thread savingsThread = new Thread(this);
        savingsThread.start();
    }

    public void run() {
        try {
            System.out.println("Running thread for savings");
            while (BankRunner.runInterestThread) {
                setBalance(getBalance() * 1.05);
                Thread.sleep(10000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
