package com.example.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class LoginController {
    public Button loginButton;
    public TextField passwordTf;
    public TextField emailTf;
    public Label consoleLabel;

    public void onLoginButtonClick(ActionEvent event) throws SQLException, IOException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12531423", "sql12531423", "LACEJ2SjGm");
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs;

        rs = statement.executeQuery("SELECT * FROM USERS WHERE email='"+emailTf.getText()+"' && password='"+passwordTf.getText()+"'");
        while(rs.next()) {
            System.out.println(rs.getString("username") + " " + rs.getString("password"));
            User user = User.getInstance();
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setId(rs.getString("id"));

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Init.class.getResource("startPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Sudoku");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        }

        consoleLabel.setText("login failed");
    }

    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        FXMLLoader fxmlLoader = new FXMLLoader(StartPageController.class.getResource("RegisterPage.fxml"));
        Stage stage = new Stage();

        stage.getIcons().add(new Image(Objects.requireNonNull(Init.class.getResourceAsStream("mainico.png"))));
        stage.setTitle("Sudoku");
        stage.setResizable(false);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    public void onGuestButtonClick(ActionEvent event) throws IOException {
        User user = User.getInstance();
        System.out.println(user.getUsername());

        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        FXMLLoader fxmlLoader = new FXMLLoader(StartPageController.class.getResource("StartPage.fxml"));
        Stage stage = new Stage();

        stage.setTitle("Sudoku");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(Init.class.getResourceAsStream("mainico.png"))));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }
}
