/*
package com.example.project2.BankingApp;
 * @author: SANIM POKHREL
 *


//import jdk.jfr.Name;

public class BankMain {


    public static void main (String[] args) {
        * TEST CASES FOR EACH METHOD
        *

        //Creating a customer who would then create an account in a brach of a bank
        Customers customer1 = new Customers("Sanim", "Pokhrel",22,"0275105344","Auckland","19 Camp Road, Mount wellington", "pokhs2@op.ac.nz");
        Customers customer2 = new Customers("James", "Bond",45,"0217654334","Pythagoras","101 catch me Road, Ifu Can", "bondhitsthebong@op.ac.nz");
        Customers customer3 = new Customers("James", "Cook", 44, "0934578233", "Otago", " 183 Te Arahi road", "cookme@yahoo.com");

        //OPening a bank
        ASB ASB_Bank = new ASB();
        ANZ ANZ_Bank = new ANZ();

        //Opening a branch for the above banks using the open branch method from a bank:
        Branches ANZ_Branch1 = ANZ_Bank.openBranch("Queen Street", "101 Queen Street");
        Branches ANZ_Branch2 = ANZ_Bank.openBranch("K Road", "66 K Road");

        Branches ASB_Branch1 = ASB_Bank.openBranch("Shortland Street", "37 Shortland Street");
        Branches ASB_Branch2 = ASB_Bank.openBranch("Queen Street","78Queen Street");


        //Opening an account in a particular branch of a bank for a customer using the open account method from branches:
        Accounts account1 = ANZ_Branch1.openAccount(customer1);
        Accounts account3 = ANZ_Branch1.openAccount(customer2);
        Accounts account2 = ASB_Branch1.openFreedomAccount(customer2);
        Accounts account4 = ANZ_Branch2.openCreditAccount(customer1);
        SavingAccount account5 =(SavingAccount) ASB_Branch2.openSavingAccount(customer3);


        //Depositing money in an account:
        double amount = 100000;
        System.out.println("Depositing money:");
        account5.deposit(2000);
        account1.deposit(amount);
        account2.deposit(amount);
        System.out.println(account1.accountNumber + "just received $" + amount + " from " + account1.owner.firstName + " " + account1.accountNumber);

        System.out.println("Depositing money:");
        account3.deposit(1010435);

        //Depositing money in a different  account:
        account1.transfer(100000,account4);
        System.out.println(account4.accountNumber + "just received $" + amount + " from " + account1.owner.firstName );
        System.out.println(" ");
        //Transferring money from savings account and gets a notice saying not allowed
        //account5.transfer(10,account4);

        // closing an account;
        System.out.println("Closing account:");
        ANZ_Branch1.closeAccount(account4);

        //transferring money to closed account:
        // Cannot make this transfer as account is already closed or blocked
        account1.transfer(10,account4);

        // Withdrawing money from an account:
        account2.withdraw(20);
        System.out.println(" ");
        System.out.println("Account 2 balance after withdrawing money");
        System.out.println(account2.balance);

        System.out.println(account1.checkBalance());



        System.out.println(" Customers interface: ");
        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println(customer3);
        System.out.println("Final amount with intrest over 3 years: ");
        System.out.println(account5.addinterest());
        System.out.println(customer3);


        System.out.println("Iterating over the list: ");
        //iterating over a list:
        for (Customers customer : ANZ_Branch1.getCustomers()){
            System.out.print(customer.toListString());

        }

        for (Branches branch : ANZ_Bank.branches) {

            System.out.println(branch);

        }
        System.out.println("");






        //System.out.println(ANZ_Branch1.getCustomers().get(0).toListString());







    }
}



*/
