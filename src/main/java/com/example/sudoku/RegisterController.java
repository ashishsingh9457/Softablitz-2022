package com.example.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterController {
    public Button loginButton;
    public TextField usernameTf;
    public TextField passwordTf;
    public Button registerButton;
    public TextField cpasswordTf;
    public Scene scene;
    public Stage stage;
    public Label consoleLabel;
    public TextField emailTf;

    public void onRegisterButtonClick(ActionEvent event) {
        if(!checkInvalidFields())
            return;
        try {
            // register to database
            Connection conn = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12531423", "sql12531423", "LACEJ2SjGm");
            Statement statement = conn.createStatement();

            String query = "INSERT INTO USERS (email, username, password) VALUES ( '" + emailTf.getText() + "' , '" + usernameTf.getText() + "' , '" + passwordTf.getText() + "' )";
            statement.executeUpdate(query);
            consoleLabel.setText("Registered Successfully");
        }catch (SQLException e){
            consoleLabel.setText("Registration Failed");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void onLoginButtonClick(ActionEvent event) throws IOException {
        ((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartPageController.class.getResource("LoginPage.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public boolean checkInvalidFields(){
        if (!cpasswordTf.getText().equals(passwordTf.getText())) {
            consoleLabel.setText("Passwords don't match");
            return false;
        }

        if(emailTf.getText().equals("")){
            consoleLabel.setText("Empty email field");
            return false;
        }

        if(usernameTf.getText().equals("")){
            consoleLabel.setText("Empty username field");
            return false;
        }
        if(passwordTf.getText().equals("")){
            consoleLabel.setText("Empty password field");
            return false;
        }
        return true;
    }
}
