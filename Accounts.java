package com.example.project2.BankingApp;

/*
* @author: SANIM POKHREL
* */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public  abstract class  Accounts {

    double balance;
    long accountNumber;
    enum Status {
        ACTIVE,
        CLOSED,
        BLOCKED;
    }
    Status status;

    Branches branch;

    LocalDate openDate;

    // stores various dates for transfer, withdraw ,deposits
    LocalDate date;
    enum AccountName{

        GO,
        JUMP_START,
        FREEDOM,
        CREDIT,
        SAVINGS_ACCOUNT,
        TERM_DEPOSITS;

    }


    AccountName accountName;
    Customers owner;
    //variable to hold value if overdraft is allowed or not
    Boolean allowOverdraft = false;

    float intrest;

    // TODO: 31/05/2022 add this list to a transiction class which stores the transactions
   // List<Transaction> transaction = new ArrayList<Transaction>();

    public Accounts(){

    }
    public Accounts (long accountNumber, Branches branch, Customers owner){
        this.balance = 0;
        this.status = Status.ACTIVE;
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.owner = owner;
        this.openDate = LocalDate.now();

    }

    public void deposit (double amount){


        this.balance += amount;

    }

    //Method that checks the balance of an account
    public double checkBalance(){
        return this.balance;
    }

    boolean withdraw (double  amount){
        if (checkBalance() - amount <0 ){
            notice("Cannot withdraw money that you don't have ");
            return false;
        }else
        balance -= amount;

        return true;
    }

    public void notice( String notice) {
        System.out.println(notice);
    }

    public boolean transfer(double amount, Accounts destAccount){
        if(destAccount.getStatus().equals(Status.ACTIVE) ){

            if( checkBalance() - amount < -50){ //overdraft
                notice(" Cannot perform transfer as amount transferring is less than your balance !");
                return false;
            } else
                this.balance -= amount;
                destAccount.balance += amount;
                return true;

        }else
            notice("Account either closed or blocked");
            return false;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    Boolean getAllowOverdraft(){
    return allowOverdraft;
    }


    public void setStatus(Status status){
        this.status = status;
    }


    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Account type " + accountName +" "+ accountNumber + " Branch: " + branch + " Balance: " + balance + " Account opened on: "+ openDate + " " +status + " \n";
    }
    public String toAccountNumber() {
        return  +accountNumber + " Account Type: "+ accountName+ "\n";
    }
}
