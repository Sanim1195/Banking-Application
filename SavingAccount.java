package com.example.project2.BankingApp;
/*
 * @author: SANIM POKHREL
 * */

public class SavingAccount extends Accounts {
    static final int MONTHS_IN_YEAR = 12;
    int fixedDepositYear = 3;
    double rate = 0.05;

    public SavingAccount(long accountNumber, Branches branches, Customers customer) {
        super(accountNumber, branches, customer);
        this.accountName = AccountName.SAVINGS_ACCOUNT;
        this.owner = customer;
    }

    public double addinterest() {
        double amount = this.balance * Math.pow(1.0 + this.rate, this.fixedDepositYear);
        this.balance = amount;
        return this.balance;

    }

    public boolean transfer(double amount, Accounts destAccount) {
        super.notice("Transfer not allowed");
        return false;
    }
}
        
    


