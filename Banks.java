package com.example.project2.BankingApp;
/*
 * @author: SANIM POKHREL
 * */

import java.util.ArrayList;
import java.util.List;

public abstract class   Banks {
        // Storing a list of branches opened by a bank;
        List<Branches> branches = new ArrayList<Branches>();

        String bankName;
        int bankId;

        int totalBranches = 0;

        public int getBankId(){
                return bankId;
        }
        public String getBankName(){
                return bankName;
        }

        // method of type branches that opens a branch
        public Branches openBranch (  String branchName, String address){
                totalBranches++;
                Branches branch1 = new Branches( this,totalBranches, branchName, address);
                //Adding the newly opened ranch to a list:
                branches.add(branch1);
                return branch1;
        }





}
