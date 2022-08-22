package com.example.project2.BankingApp;
/*
 * @author: SANIM POKHREL
 * */

import java.time.LocalDate;

public class Freedom extends EveryDayAcount{

    public Freedom (long accountNumber, Branches branches, Customers customer) {
        this.balance = 0;
        this.status = Accounts.Status.ACTIVE;
        this.accountNumber = accountNumber;
        this.branch = branches;
        this.accountName = AccountName.FREEDOM;
        this.owner = customer;
        this.allowOverdraft = true;
        this.openDate = LocalDate.now();

        // TODO: 31/05/2022  $5 monthly fee waived if deposit is up to 2500 a month.
        this.monthlyFee = 0;

    }


}
