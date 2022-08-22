package com.example.project2;

import com.example.project2.BankingApp.*;
import javafx.event.ActionEvent;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;



public class MainController {

    private Stage stage;
    private Scene scene;

    static Customers customer;

    static ANZ ANZ_Bank;
    static Branches ANZ_Branch_QueenStreet;
    static int branchId = 1;

    static ASB ASB_Bank;

    static Branches ASB_Branch_VictoriaStreet;
    static int ASB_Bank_Branchid = 2;
    @FXML
    private TextField cusPassword;

    @FXML
    private TextField cusUsername;

    @FXML
    private TextField customerId;

    @FXML
    private TextField depositAccountNumber;

    static  int loginId = 0 ;

    @FXML
    private TextField depositAmount;

    @FXML
    private TextField senderAccountNumber;

    static int bankMode = 0;
    Connection con;
    PreparedStatement preparedStatement;
    ResultSet rs;
    @FXML
    private Button CustomerLogin;

    @FXML
    private TextField fromAccount;

    @FXML
    private TextField toAccount;

    @FXML
    private TextField transferAmount;

    @FXML
    private TextField withdrawAccountNumber;

    @FXML
    private TextField withdrawAmount;

    @FXML
    private TextField withdrawCheckBalance;

    @FXML
    private   TextField checkBalanceAccountNumber;

    @FXML
    private  TextField checkBalanceAvailableAmount;







    @FXML
    void chooseANZBank(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome To ANZ Bank");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        bankMode = 1;

    }

    @FXML
    void chooseASBBank(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome To ASB Bank");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        bankMode = 2;

    }


    @FXML
    public void customerLogin(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("customerLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Login Page");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Method that takes user email and pwd and matches it against the database email and pwd for login
    public  boolean usernameFound(String email, String pwd) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp", "root", "");
            String query = "SELECT * FROM customer where emailId = ? and pwd = ? ";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,pwd);

            ResultSet rs =preparedStatement.executeQuery();

            if (rs.next()) {
                MainController.loginId = rs.getInt("id");
                return true;

            }
            con.close();


        } catch (Exception e) {
            System.out.println(e);

        }

        return false;
    }


    //    (LOGGIN IN THE USER) Checking if username and pwd matches with the one the customer provided
    @FXML
    public void customerSignIn(ActionEvent event) throws IOException {


        //If Credentials Are Correct.
        if (usernameFound(cusUsername.getText().trim(),cusPassword.getText().trim())) {
            //Open New Stage.
            //instantiating an object of the customer to access the baking system app:
            customer  = new Customers();

            Parent root = FXMLLoader.load(getClass().getResource("customerDashboard.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Login Signup Page");
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {

            System.out.println("Failed");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Account Not Found ");
            alert.setContentText("Please enter your Email Id and pwd and Try Again!");

            alert.showAndWait();

        }
    }




    //    takes user to signup form to take their details:
    @FXML
    public void customerSignUp(ActionEvent event) throws IOException {
        //Open New Stage.

        Parent root = FXMLLoader.load(getClass().getResource("customerSignup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Sign Up Form ");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void createEveryDayAccount(ActionEvent event) {

        if (bankMode == 1){
            ANZ ANZ_Bank = new ANZ();
            Branches ANZ_Branch1 = ANZ_Bank.openBranch("Queen Street", "101 Queen Street");
            Accounts account = ANZ_Branch1.openEveryDayAccount(customer);
            Long  l = account.getAccountNumber();
            int accountTypeId = 1;
            double d = account.checkBalance();
            float balance = (float)d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp","root","");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setLong(1,l);
                preparedStatement.setInt(2,accountTypeId);
                preparedStatement.setInt(3,loginId);
                preparedStatement.setFloat(4,balance);
                preparedStatement.setInt(5,branchId);

                preparedStatement.executeUpdate();
                System.out.println("Everyday Account Created :) ");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Everyday Account Successfully Created ");
                alert.showAndWait();
                con.close();

            }catch (Exception e){
                System.out.println(e);

            }

        } else if (bankMode == 2) {
            ASB ASB_Bank = new ASB();
            ASB_Branch_VictoriaStreet = ASB_Bank.openBranch("Victoria Street", "222 Queen Street");
            Accounts account =  ASB_Branch_VictoriaStreet.openEveryDayAccount(customer);
            Long  l = account.getAccountNumber();
            int accountTypeId = 1;
            double d = account.checkBalance();
            float balance = (float)d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp","root","");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setLong(1,l);
                preparedStatement.setInt(2,accountTypeId);
                preparedStatement.setInt(3,loginId);
                preparedStatement.setFloat(4,balance);
                preparedStatement.setInt(5,ASB_Bank_Branchid);

                preparedStatement.executeUpdate();
                System.out.println("Everyday Account Created :) ");
                //Success Prompt display
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Everyday Account has been successfully created " );
                alert.showAndWait();
                con.close();

            }catch (Exception e){
                System.out.println(e);

            }
        }
    }



    @FXML
    void createFreedomAccount(ActionEvent event) {
        if (bankMode == 1) {
            ANZ ANZ_Bank = new ANZ();
            Branches ANZ_Branch1 = ANZ_Bank.openBranch("Queen Street", "101 Queen Street");
            Accounts account = ANZ_Branch1.openFreedomAccount(customer);
            Long l = account.getAccountNumber();
            int accountTypeId = 2;
            double d = account.checkBalance();
            float balance = (float) d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp", "root", "");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setLong(1, l);
                preparedStatement.setInt(2, accountTypeId);
                preparedStatement.setInt(3, loginId);
                preparedStatement.setFloat(4, balance);
                preparedStatement.setInt(5, branchId);

                preparedStatement.executeUpdate();
                System.out.println("Freedom Account Created :) ");
                //Success Prompt display
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Freedom Account has been successfully created " );
                alert.showAndWait();
                con.close();

            } catch (Exception e) {
                System.out.println(e);

            }
        } else if (bankMode == 2) {
            ASB ASB_Bank = new ASB();
            ASB_Branch_VictoriaStreet = ASB_Bank.openBranch("Victoria Street", "222 Queen Street");
            Accounts account =  ASB_Branch_VictoriaStreet.openFreedomAccount(customer);
            Long  l = account.getAccountNumber();
            int accountTypeId = 2;
            double d = account.checkBalance();
            float balance = (float)d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp","root","");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setLong(1,l);
                preparedStatement.setInt(2,accountTypeId);
                preparedStatement.setInt(3,loginId);
                preparedStatement.setFloat(4,balance);
                preparedStatement.setInt(5,ASB_Bank_Branchid);

                preparedStatement.executeUpdate();
                System.out.println("Freedom Account Created :) ");
                //Success Prompt display
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Freedom Account has been successfully created " );
                alert.showAndWait();
                con.close();

            }catch (Exception e){
                System.out.println(e);

            }

        }

    }

    @FXML
    void createGoAccount(ActionEvent event) {

        if(bankMode == 1) {
            ANZ ANZ_Bank = new ANZ();
            Branches ANZ_Branch1 = ANZ_Bank.openBranch("Queen Street", "101 Queen Street");
            Accounts account = ANZ_Branch1.openGoAccount(customer);
            Long l = account.getAccountNumber();
            int accountNumber = l.intValue();

            int accountTypeId = 3;


            double d = account.checkBalance();
            float balance = (float) d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp", "root", "");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, accountNumber);
                preparedStatement.setInt(2, accountTypeId);
                preparedStatement.setInt(3, loginId);
                preparedStatement.setFloat(4, balance);
                preparedStatement.setInt(5, branchId);

                preparedStatement.executeUpdate();
                System.out.println("Go Account Created :) ");
                //Success Prompt display
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Go Account has been successfully created " );
                alert.showAndWait();
                con.close();

            } catch (Exception e) {
                System.out.println(e);

            }
        } else if (bankMode == 2) {
            ASB ASB_Bank = new ASB();
            ASB_Branch_VictoriaStreet = ASB_Bank.openBranch("Victoria Street", "222 Victoria Street");
            Accounts account =  ASB_Branch_VictoriaStreet.openGoAccount(customer);
            Long  l = account.getAccountNumber();
            int accountNumber = l.intValue();

            int accountTypeId = 3;

            double d = account.checkBalance();
            float balance = (float)d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp","root","");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1,accountNumber);
                preparedStatement.setInt(2,accountTypeId);
                preparedStatement.setInt(3,loginId);
                preparedStatement.setFloat(4,balance);
                preparedStatement.setInt(5,ASB_Bank_Branchid);

                preparedStatement.executeUpdate();
                System.out.println("Go Account Created :) ");
                //Success Prompt display
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Go Account has been successfully created " );
                alert.showAndWait();
                con.close();

            }catch (Exception e){
                System.out.println(e);

            }

        }

    }

    @FXML
    void createJumpstartAccount(ActionEvent event) {
        if (bankMode == 1) {
            ANZ ANZ_Bank = new ANZ();
            Branches ANZ_Branch1 = ANZ_Bank.openBranch("Queen Street", "101 Queen Street");
            Accounts account = ANZ_Branch1.openJumpStartAccount(customer);
            Long l = account.getAccountNumber();
            int accountNumber = l.intValue();
            int accountTypeId = 4;
            double d = account.checkBalance();
            float balance = (float) d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp", "root", "");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, accountNumber);
                preparedStatement.setInt(2, accountTypeId);
                preparedStatement.setInt(3, loginId);
                preparedStatement.setFloat(4, balance);
                preparedStatement.setInt(5, branchId);

                preparedStatement.executeUpdate();
                System.out.println("Jumpstart Account Created :) ");
                //Success Prompt display
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("JumpStart Account has been successfully created " );
                alert.showAndWait();
                con.close();

            } catch (Exception e) {
                System.out.println(e);

            }
        } else if (bankMode == 2) {
            ASB ASB_Bank = new ASB();
            ASB_Branch_VictoriaStreet = ASB_Bank.openBranch("Victoria Street", "222 Victoria Street");
            Accounts account =  ASB_Branch_VictoriaStreet.openJumpStartAccount(customer);
            Long  l = account.getAccountNumber();
            int accountNumber = l.intValue();

            int accountTypeId = 4;

            double d = account.checkBalance();
            float balance = (float)d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp","root","");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1,accountNumber);
                preparedStatement.setInt(2,accountTypeId);
                preparedStatement.setInt(3,loginId);
                preparedStatement.setFloat(4,balance);
                preparedStatement.setInt(5,ASB_Bank_Branchid);

                preparedStatement.executeUpdate();
                System.out.println("JumpStart Account Created :) ");
                //Success Prompt display
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("JumpStart Account has been successfully created " );
                alert.showAndWait();
                con.close();

            }catch (Exception e){
                System.out.println(e);

            }

        }


    }




    @FXML
    void createSavingAccount(ActionEvent event) {
        if (bankMode == 1) {
            ANZ ANZ_Bank = new ANZ();
            Branches ANZ_Branch1 = ANZ_Bank.openBranch("Queen Street", "101 Queen Street");
            Accounts account = ANZ_Branch1.openSavingAccount(customer);
            Long l = account.getAccountNumber();
            int accountNumber = l.intValue();

            int accountTypeId = 5;


            double d = account.checkBalance();
            float balance = (float) d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp", "root", "");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, accountNumber);
                preparedStatement.setInt(2, accountTypeId);
                preparedStatement.setInt(3, loginId);
                preparedStatement.setFloat(4, balance);
                preparedStatement.setInt(5, branchId);

                preparedStatement.executeUpdate();
                System.out.println("Savings account Created :) ");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Savings Account Successfully Created ");
                alert.showAndWait();
                con.close();

            } catch (Exception e) {
                System.out.println(e);

            }
        } else if (bankMode == 2) {
            ASB ASB_Bank = new ASB();
            ASB_Branch_VictoriaStreet = ASB_Bank.openBranch("Victoria Street", "222 Victoria Street");
            Accounts account =  ASB_Branch_VictoriaStreet.openSavingAccount(customer);
            Long  l = account.getAccountNumber();
            int accountNumber = l.intValue();

            int accountTypeId = 5;

            double d = account.checkBalance();
            float balance = (float)d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp","root","");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1,accountNumber);
                preparedStatement.setInt(2,accountTypeId);
                preparedStatement.setInt(3,loginId);
                preparedStatement.setFloat(4,balance);
                preparedStatement.setInt(5,ASB_Bank_Branchid);

                preparedStatement.executeUpdate();
                System.out.println("Savings Account Created :) ");
                //Success Prompt display
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Savings Account has been successfully created " );
                alert.showAndWait();
                con.close();

            }catch (Exception e){
                System.out.println(e);

            }

        }

    }

    @FXML
    void createCreditAccount(ActionEvent event) {
        if(bankMode == 1) {
            ANZ ANZ_Bank = new ANZ();
            Branches ANZ_Branch1 = ANZ_Bank.openBranch("Queen Street", "101 Queen Street");
            Accounts account = ANZ_Branch1.openCreditAccount(customer);
            Long l = account.getAccountNumber();
            int accountNumber = l.intValue();

            int accountTypeId = 6;


            double d = account.checkBalance();
            float balance = (float) d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp", "root", "");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, accountNumber);
                preparedStatement.setInt(2, accountTypeId);
                preparedStatement.setInt(3, loginId);
                preparedStatement.setFloat(4, balance);
                preparedStatement.setInt(5, branchId);

                preparedStatement.executeUpdate();
                System.out.println("Credit Account Created :) ");
                //Success Prompt display
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Credit Account has been successfully created " );
                alert.showAndWait();
                con.close();

            } catch (Exception e) {
                System.out.println(e);

            }
        } else if (bankMode == 2) {
            ASB ASB_Bank = new ASB();
            ASB_Branch_VictoriaStreet = ASB_Bank.openBranch("Victoria Street", "222 Victoria Street");
            Accounts account =  ASB_Branch_VictoriaStreet.openCreditAccount(customer);
            Long  l = account.getAccountNumber();
            int accountNumber = l.intValue();
            int accountTypeId = 6;

            double d = account.checkBalance();
            float balance = (float)d;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp","root","");

                String query = "Insert into accounts  (accountNumber, accountTypeId, customerId, balance, branchId) VALUES (?,?,?,?,?) ";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1,accountNumber);
                preparedStatement.setInt(2,accountTypeId);
                preparedStatement.setInt(3,loginId);
                preparedStatement.setFloat(4,balance);
                preparedStatement.setInt(5,ASB_Bank_Branchid);

                preparedStatement.executeUpdate();
                System.out.println("Credit Account Created :) ");

                //Success Prompt display
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Credit Account has been successfully created " );
                alert.showAndWait();

                con.close();

            }catch (Exception e){
                System.out.println(e);

            }

        }

    }



    //    Updating customers value
    @FXML
    void updateValuesForCustomer(ActionEvent event) {

    }

//    Checking Balance
    @FXML
    void checkBalanceButton(ActionEvent event) {
          Long accNumber =Long.parseLong( checkBalanceAccountNumber.getText().trim());

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp", "root", "");
            Statement statement  = con.createStatement();
            String query = "Select balance from accounts where accountNumber  = ?";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setLong(1,accNumber);

            ResultSet rs = prepStatement.executeQuery();


            while  (rs.next()) {
                checkBalanceAvailableAmount.setText(rs.getString("balance"));

            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);

        }

    }

    @FXML
    void withdrawlCheckBalanceButton(ActionEvent event) {

        Long accNumber =Long.parseLong( withdrawAccountNumber.getText().trim());

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp", "root", "");
            Statement statement  = con.createStatement();
            String query = "Select balance from accounts where accountNumber  = ?";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setLong(1,accNumber);

            ResultSet rs = prepStatement.executeQuery();



            while  (rs.next()) {
                withdrawCheckBalance.setText(rs.getString("balance"));

            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);

        }


    }

    //    Transferring Money
    @FXML
    void onTransferClick(ActionEvent event) {
        float balance = 0;
        float balance2 = 0;

        long fromAcc = Long.parseLong(fromAccount.getText().trim());
        long toAcc = Long.parseLong(toAccount.getText().trim());
        float amountToBeTransferred = Float.parseFloat (transferAmount.getText().trim());

        try{
            Class .forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaapp","root","");

            Statement stmt=con.createStatement();

            String query = "SELECT balance FROM accounts WHERE accountNumber = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1,fromAcc);
             rs = preparedStatement.executeQuery();
            while(rs.next()) {
                balance = rs.getFloat("balance");
                System.out.println(balance);

            }
             float newSenderBalance = balance - amountToBeTransferred ;
            if (newSenderBalance <= 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Failed");
                alert.setHeaderText("Transfer failed");
                alert.setContentText("Insufficient funds for transfer Go to work and get paid " );
                alert.showAndWait();
                con.close();

            } else {
                //Updating senders info
                String query2 = "update accounts set balance = ? where accountNumber = ?";
                PreparedStatement preparedStmt1 = con.prepareStatement(query2);
                preparedStmt1.setFloat(1, newSenderBalance);
                preparedStmt1.setLong(2, fromAcc);
                preparedStmt1.executeUpdate();

                String query3 = "SELECT balance FROM accounts WHERE accountNumber = ?";
                preparedStatement = con.prepareStatement(query3);
                preparedStatement.setLong(1, toAcc);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    balance2 = rs.getFloat("balance");
                    System.out.println(balance);

                }
                float newReceiverBalance = balance2 + amountToBeTransferred;

                String query1 = "update accounts set balance = ? where accountNumber = ?";
                PreparedStatement preparedStmt2 = con.prepareStatement(query1);
                preparedStmt2.setFloat(1, newReceiverBalance);
                preparedStmt2.setLong(2, toAcc);
                preparedStmt2.executeUpdate();
                //Success Prompt display
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Money has been Successfully transferred to account " + toAcc);
                alert.showAndWait();

                con.close();
            }

        } catch (SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);

        }

    }


    //Depositing money
    @FXML
    void onDepositClick(ActionEvent event) throws IOException {
        float balance = 0;

        Long depositAccNumber = Long.parseLong(depositAccountNumber.getText().trim());
        float amountToBeTransferred = Float.parseFloat (depositAmount.getText().trim());

        try{
            Class .forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaapp","root","");

            Statement stmt=con.createStatement();
            System.out.println(depositAccNumber);
            String query1 = "Select balance from accounts where accountNumber = ?";
            preparedStatement  = con.prepareStatement(query1);
            preparedStatement.setLong(1,Long.parseLong(depositAccountNumber.getText().trim()) );

            float newReceiverBalance = balance + amountToBeTransferred ;

            String query = "update accounts set balance = ? where accountNumber = ?";
            PreparedStatement preparedStmt1 = con.prepareStatement(query);
            preparedStmt1.setFloat(1, newReceiverBalance );
            preparedStmt1.setLong(2, depositAccNumber);

            preparedStmt1.executeUpdate();
            //Success Prompt display
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("Money has been Successfully deposited ");
            alert.showAndWait();

            System.out.println("Done");
            con.close();

        } catch (SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);

        }
    }

    @FXML
    void onWithdrawlClick(ActionEvent event) {

        float balance = 0;

        Long withdrawlAccount = Long.parseLong(withdrawAccountNumber.getText().trim());
        float amountToBeWithdrawled = Float.parseFloat (withdrawAmount.getText().trim());

        try{
            Class .forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaapp","root","");

            Statement stmt=con.createStatement();
            String query1 = "Select balance from accounts where accountNumber = ?";
            preparedStatement  = con.prepareStatement(query1);
//           rs=stmt.executeQuery("SELECT balance FROM accounts WHERE accountNumber = 1089210051");
            preparedStatement.setLong(1,withdrawlAccount);
            rs = preparedStatement.executeQuery();
            while(rs.next()){

                balance = rs.getFloat("balance");
            }

            float newBalance = balance - amountToBeWithdrawled ;

            String query = "update accounts set balance = ? where accountNumber = ?";
            PreparedStatement preparedStmt1 = con.prepareStatement(query);
            preparedStmt1.setFloat(1, newBalance );
            preparedStmt1.setLong(2, withdrawlAccount);


            preparedStmt1.executeUpdate();
            //Success Prompt display
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("Money has been Successfully Withdrawn ");
            alert.showAndWait();

            con.close();


        } catch (SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);

        }


    }

//    Logging Out
    @FXML
    void logoutButton(ActionEvent event) throws SQLException, IOException {
        //closing Sql connection before logging out the user
        if (con != null){
            con.close();
        }
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome To ANZ Bank");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }






}






