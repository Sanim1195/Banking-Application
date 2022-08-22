package com.example.project2.BankingApp;
/*
 * @author: SANIM POKHREL
 * */

import java.util.ArrayList;
import java.util.List;

public class Branches  {
    Banks bank;
    int branchId;
    String branchName;
    String address;
    static long  totalAccounts;

    //Creating a list of customers
     List <Customers> customers = new ArrayList<>();

    //default Constructor:
    public Branches() {

    }

    //Constructor:
    public Branches(Banks bank, int branchId, String branchName, String address) {
        this.bank = bank;
        this.branchId = branchId;
        this.branchName = branchName;
        this.address = address;
        customers = new ArrayList<Customers>();


    }

    public Banks getBank() {
        return bank;
    }

    public void setBank(Banks bank) {
        this.bank = bank;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Closing an account
    public void closeAccount(Accounts account) {
        if( account.balance > 0){
            account.notice("Cannot do it");
        }else
        account.setStatus(Accounts.Status.CLOSED);
    }


    public Accounts openAccount(Customers customer) {
        totalAccounts++;
        long accountNumber = bank.getBankId()*10000000000L+ getBranchId()*1000000L+totalAccounts;
        Go account = new Go(accountNumber,this,customer);
       /* customer.addAccount(account);
        customers.add(customer)*/;
        return account;
    }

    public Accounts openJumpStartAccount(Customers customer) {
        totalAccounts++;
        long accountNumber = bank.getBankId()*10000000000L+ getBranchId()*2000000L+totalAccounts;
        JumpStart account = new JumpStart(accountNumber,this,customer);
        /*customer.addAccount(account);
        customers.add(customer);*/
        return account;
    }

    public Accounts openFreedomAccount(Customers customer) {
        totalAccounts++;
        long accountNumber = bank.getBankId()*10000000000L+ getBranchId()*3000000L+totalAccounts;
        Freedom account = new Freedom (accountNumber,this,customer);
        /*customer.addAccount(account);
        customers.add(customer);*/
        return account;
    }

    public Accounts openGoAccount(Customers customer) {
        totalAccounts++;
        long accountNumber = bank.getBankId()*10000000000L+ getBranchId()*4000000L+totalAccounts;
        Go account = new Go(accountNumber,this,customer);
        /*customer.addAccount(account);
        customers.add(customer);*/
        return account;
    }


    public Accounts openSavingAccount(Customers customer) {
        totalAccounts++;
        long accountNumber = bank.getBankId()*10000000000L+ getBranchId()*5000000L+totalAccounts;
        SavingAccount account = new SavingAccount(accountNumber,this,customer);
        /*customer.addAccount(account);
        customers.add(customer);*/
        return account;
    }

    public Accounts openEveryDayAccount(Customers customer) {
        totalAccounts++;
        long accountNumber = bank.getBankId()*10000000000L+ getBranchId()*6000000+totalAccounts;
        EveryDayAcount account = new EveryDayAcount(accountNumber,this,customer);
        /*customer.addAccount(account);
        customers.add(customer);*/
        return account;
    }


    public Accounts openCreditAccount(Customers customer) {
        totalAccounts++;
        long accountNumber = bank.getBankId()*10000000000L+ getBranchId()*7000000L+totalAccounts;
        CreditAccount account = new CreditAccount(accountNumber,this,customer);
        /*customer.addAccount(account);
        customers.add(customer);*/
        return account;
    }

    public Accounts openTermDepositAccount(Customers customer) {
        totalAccounts++;
        long accountNumber = bank.getBankId()*10000000000L+ getBranchId()*8000000L+totalAccounts;
        TermDeposits account = new TermDeposits (accountNumber,this,customer);
        /*customer.addAccount(account);
        customers.add(customer);*/
        return account;
    }

    public String toString(){
        return " Bank Name: " + bank.getBankName() +" Branch Name: "+ getBranchName()+ " Address : " + getAddress() ;
    }

    //method to get a list of customers in a branch:
    public List<Customers> getCustomers(){
        return customers;
    }




}