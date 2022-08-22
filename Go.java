package com.example.project2.BankingApp;
/*
 * @author: SANIM POKHREL
 * */

import java.time.LocalDate;

public class Go extends EveryDayAcount{

    public Go (long accountNumber, Branches branches, Customers customer) {
        this.balance = 0;
        this.status = Accounts.Status.ACTIVE;
        this.accountNumber = accountNumber;
        this.branch = branches;
        this.accountName = Accounts.AccountName.GO;
        this.owner = customer;
        this.allowOverdraft = true;
        this.openDate = LocalDate.now();
    }
}
