package com.example.project2.BankingApp;
/*
 * @author: SANIM POKHREL
 * */

import java.time.LocalDate;

public class EveryDayAcount extends Accounts {

    double monthlyFee;

     public EveryDayAcount(){

    }

    public EveryDayAcount(long accountNumber, Branches branches, Customers customer) {
        this.balance = 0;
        this.status = Status.ACTIVE;
        this.accountNumber = accountNumber;
        this.branch = branches;
        this.accountName = AccountName.GO;
        this.owner = customer;
        this.allowOverdraft = true;
        this.openDate = LocalDate.now();
        this.monthlyFee = 0;
    }



}