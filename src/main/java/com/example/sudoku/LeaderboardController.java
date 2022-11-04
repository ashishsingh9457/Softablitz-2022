package com.example.sudoku;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable{

    public Label leaderboardLabel;
    public Stage stage;
    public Label list[];
    public VBox vbox;
    public ComboBox<String> difficultyComboBox;
    public ComboBox<String> modeComboBox;
    public TableView<FinishedGameInfo> table;
    public TableColumn<FinishedGameInfo,String> nameColumn;
    public TableColumn<FinishedGameInfo,Integer> gametimeColumn;
    public TableColumn<FinishedGameInfo,String> modeColumn;
    public TableColumn<FinishedGameInfo,String> difficultyColumn;

    public void populate(Stage stage) throws SQLException {
        this.stage = stage;

        Connection conn = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12531423", "sql12531423", "LACEJ2SjGm");
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = statement.executeQuery("SELECT * FROM GAMEDATA ORDER BY game_time");

        ArrayList<FinishedGameInfo> filist = new ArrayList<>();

        while(rs.next()) {
            FinishedGameInfo fi = new FinishedGameInfo(rs.getString("username"),Integer.parseInt(rs.getString("game_time")),rs.getString("mode"),rs.getString("difficulty"));
            filist.add(fi);
        }

        table.getItems().addAll(filist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] diffArray = {"Easy","Medium","Hard","Any"};
        String[] modeArray = {"4x4","9x9","16x16","Any"};

        difficultyComboBox.getItems().addAll(diffArray);
        modeComboBox.getItems().addAll(modeArray);

        nameColumn.setCellValueFactory(new PropertyValueFactory<FinishedGameInfo,String>("name"));
        gametimeColumn.setCellValueFactory(new PropertyValueFactory<FinishedGameInfo,Integer>("time"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<FinishedGameInfo,String>("difficulty"));
        modeColumn.setCellValueFactory(new PropertyValueFactory<FinishedGameInfo,String>("gridSize"));
    }

    public void onFilterButtonClick(ActionEvent event) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12531423", "sql12531423", "LACEJ2SjGm");
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs;

        String mode = modeComboBox.getValue();
        String diff = modeComboBox.getValue();

        if(mode.equals("Any") && diff.equals("Any"))
            rs = statement.executeQuery("SELECT * FROM GAMEDATA ORDER BY game_time");
        else if(mode.equals("Any"))
            rs = statement.executeQuery("SELECT * FROM GAMEDATA WHERE difficulty = '" + diff + "' ORDER BY game_time");
        else
            rs = statement.executeQuery("SELECT * FROM GAMEDATA WHERE mode = '" + mode + "' ORDER BY game_time");

        table.getItems().removeAll(new ArrayList<>(table.getItems()));
        ArrayList<FinishedGameInfo> filist = new ArrayList<>();

        while(rs.next()) {
            FinishedGameInfo fi = new FinishedGameInfo(rs.getString("username"),Integer.parseInt(rs.getString("game_time")),rs.getString("mode"),rs.getString("difficulty"));
            filist.add(fi);
        }
        table.getItems().addAll(filist);
    }
}
