package com.tiy;

/**
 * Created by DTG2 on 10/08/16.
 */
public class RetirementAccount extends BankAccount implements  Runnable{

    public RetirementAccount(String accountName, double balance, int type) {
        setAccountName(accountName);
        this.setBalance(balance);
        this.setType(type);

        Thread retirementThread = new Thread(this);
        retirementThread.start();
    }

   public void run() {
        try {
            System.out.println("Running thread for retirement");
            while (BankRunner.runInterestThread) {
                setBalance(getBalance() * 1.10);
                Thread.sleep(120000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
