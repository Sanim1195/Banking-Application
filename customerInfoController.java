package com.example.project2;/*
 *Author: Sanim Pokhrel
 *//*
 *Author: Sanim Pokhrel
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class customerInfoController {

    @FXML
    private TextField cusAdd;
    @FXML
    private TextField cusAge;

    @FXML
    private TextField cusCity;

    @FXML
    private TextField cusEmail;

    @FXML
    private TextField cusLastname;

    @FXML
    public TextField cusName;

    @FXML
    private TextField cusPhone;


    @FXML
    public void initialize(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp", "root", "");

            Statement statement  = con.createStatement();
            String query = "Select * from customer where id = ?";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setInt(1,MainController.loginId);

            ResultSet rs = prepStatement.executeQuery();



            while  (rs.next()) {
                String name = rs.getString("firstName");
                cusName.setText(name);
                cusLastname.setText(rs.getString("lastName"));
                cusAge.setText(rs.getString("age"));
                cusEmail.setText(rs.getString("emailId"));
                cusAdd.setText(rs.getString("address"));
                cusCity.setText(rs.getString("city"));
            }


            con.close();



        } catch (Exception e) {
            System.out.println(e);

        }
    }


    @FXML
    void updateValuesForCustomer(ActionEvent event) {

    }


}
