package com.example.project2.BankingApp;
/*
 * @author: SANIM POKHREL
 * */

public class CreditAccount extends Accounts{

    double monthlyFee;
    public CreditAccount(long accountNumber, Branches branches, Customers customer) {
        //Calling the super class (parent) and passing the value to it's constructor
        super(accountNumber,branches,customer);
        this.monthlyFee = 5;
        this.accountName = AccountName.CREDIT;
        takeMonthlyFee();

    }

    //credit fee:
    public double takeMonthlyFee(){
        balance -= monthlyFee;
        return balance;
    }
}
