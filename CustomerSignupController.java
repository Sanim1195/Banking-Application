package com.example.project2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.EventObject;


public class CustomerSignupController {
        private Stage stage;
        private Scene scene;

        Connection connection;
        PreparedStatement preparedStatement;

        @FXML
        private TextField customerAddress;

        @FXML
        private TextField customerAge;

        @FXML
        private TextField customerCity;

        @FXML
        private TextField customerEmail;

        @FXML
        private  TextField customerFirstName;

        @FXML
        private TextField customerLastName;


        @FXML
        private TextField customerPassword;

        @FXML
        private TextField customerReEnterPassword;



//        Signing up a new user
        @FXML
        void newCustomerSignupButton(ActionEvent event) throws IOException {
                //Checking for null values

                if (customerFirstName.getText().trim() == "" || customerLastName.getText().trim()=="" || customerAddress.getText().trim() == "" || customerAge.getText().trim() == "" || customerEmail.getText().trim() =="" || customerCity.getText().trim() == "" || customerPassword.getText().trim() .isEmpty() || customerReEnterPassword.getText().trim().isEmpty() ) {
                        //Error Dialog Box/Alert!.
                        System.out.println("signup empty fields");
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText("Failed");
                        alert.setContentText("Please fill up all the fields");

                        alert.showAndWait();
                }else if (!customerPassword.getText().trim().equals(customerReEnterPassword.getText().trim())){
                        System.out.println("password dont match");
                        //Error Dialog Box Alert! .
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText("Wrong Password");
                        alert.setContentText("Sorry! Your Password doesn't match :(");
                        alert.showAndWait();





                }else{
                        try {
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp","root","");
                                String query = "Insert into customer (firstName,LastName,age,emailId,address, city, pwd ) VALUES (?,?,?,?,?,?,?)";
                                preparedStatement = con.prepareStatement(query);
                                preparedStatement.setString(1,customerFirstName.getText());
                                preparedStatement.setString(2,customerLastName.getText().trim());
                                preparedStatement.setString(3,customerAge.getText().trim());
                                preparedStatement.setString(4,customerEmail.getText().trim());
                                preparedStatement.setString(5,customerAddress.getText().trim());
                                preparedStatement.setString(6,customerCity.getText().trim());
                                preparedStatement.setString(7,customerPassword.getText().trim());

                                preparedStatement.executeUpdate();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText("Success");
                                alert.setContentText("Account Successfully Created ");
                                alert.showAndWait();

                                System.out.println("Done");
                                con.close();

                        }catch (Exception e){
                                System.out.println(e);

                        }
                }

        }


        @FXML
         void signUpBackButton(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Welcome To ANZ Bank");
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }


}
