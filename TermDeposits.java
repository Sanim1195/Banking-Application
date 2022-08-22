package com.example.project2.BankingApp;
/*
 * @author: SANIM POKHREL
 * */

import java.time.LocalDate;

public class TermDeposits extends Accounts{

    public TermDeposits (long accountNumber, Branches branches, Customers customer) {
        super(accountNumber,branches,customer);
        this.balance = 0;
        this.status = Accounts.Status.ACTIVE;
        //this.accountNumber = accountNumber;
        //this.branch = branches;
        this.accountName = AccountName.TERM_DEPOSITS;
        //this.owner = customer;
        this.allowOverdraft = false;
        this.intrest = 5;
        this.openDate = LocalDate.now();
    }

    public void giveIntrest(){
        balance = balance+balance*intrest;

    }
}
