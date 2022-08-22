package com.example.project2.BankingApp;
/*
 * @author: SANIM POKHREL
 * */

import java.util.ArrayList;
import java.util.List;

public class Customers {

    // creating instance variables:
    String firstName ;     // firstName: holds the first name of the customer
    String lastName ;      //lastName: holds the last name of the customer
    //TO-Do Maybe make it dob and use get age to get the age
    int age ;               // age: to store the age of the customer
    String phoneNumber ;    // phoneNumber: to store the customers phone number
    String city ;           // city: to store the customers city
    String address;         // address: to store customers address
    String emailId;         // emailId to store customers emailId

     List<Accounts> accounts; // A list of accounts to belonging to the customers

    /*
     * A default constructor for the customer class:
     * */
    public Customers(){}

    /*
     * creating constructor method for Customers class
     * */
    public Customers(String firstName,String lastName,int age, String phoneNumber,String city,String address, String emailId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.address = address;
        this.emailId = emailId;
        accounts = new ArrayList<Accounts>();
    }

    public void  addAccount(Accounts account){
        accounts.add(account);

    }

    @Override
    public String toString() {
       String str =   " Customer Name: " + firstName + " " +lastName +" Email: "+ emailId+ " Adress: "+ address +" Phone Number: " + phoneNumber + "\n" ;
       // Adding the above str string to  accounts
       for (Accounts account : accounts)
           str += account;
           return str + "\n";
    }

    //Method to display the list of customers:
     public String toListString(){
        //return " Customer Name: " + firstName + " " +lastName + "\n";
         String str =  " Customer Name: " + firstName + " " +lastName ;
         for (Accounts account : accounts)
             str += account.toAccountNumber();
         return str;


     }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }
}
