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
    public ComboBox<String> modeComboBox;
    public ComboBox<String> boardComboBox;
    public TableView<FinishedGameInfo> table;
    public TableColumn<FinishedGameInfo,String> nameColumn;
    public TableColumn<FinishedGameInfo,String> gametimeColumn;
    public TableColumn<FinishedGameInfo,String> boardColumn;
    public TableColumn<FinishedGameInfo,String> modeColumn;
    public TableColumn<FinishedGameInfo,String> playedOnColumn;
    public TableColumn<FinishedGameInfo,String> rankColumn;

    public void populate(Stage stage) throws SQLException {
        this.stage = stage;

        Connection conn = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12531423", "sql12531423", "LACEJ2SjGm");
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = statement.executeQuery("SELECT *,MIN(game_time) FROM GAMEDATA GROUP BY uid  ORDER BY MIN(game_time)");

        ArrayList<FinishedGameInfo> filist = new ArrayList<>();

        int i=1;
        while(rs.next()) {
            FinishedGameInfo fi = new FinishedGameInfo(rs.getString("username"),Integer.parseInt(rs.getString("MIN(game_time)")),rs.getString("board"),rs.getString("mode"),rs.getString("createdAt"),i++ + "");
            filist.add(fi);
        }

        table.getItems().addAll(filist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] modeArray = {"Easy","Medium","Hard","Any"};
        String[] boardArray = {"4x4","9x9","16x16","Any"};

        modeComboBox.getItems().addAll(modeArray);
        boardComboBox.getItems().addAll(boardArray);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        gametimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        modeColumn.setCellValueFactory(new PropertyValueFactory<>("mode"));
        boardColumn.setCellValueFactory(new PropertyValueFactory<>("Ssize"));
        playedOnColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
    }

    public void onFilterButtonClick(ActionEvent event) throws SQLException {
        if(!checkInvalidFields())
            return;

        Connection conn = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12531423", "sql12531423", "LACEJ2SjGm");
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs;

        String mode = modeComboBox.getValue();
        String diff = modeComboBox.getValue();

        if(mode.equals("Any") && diff.equals("Any"))
            rs = statement.executeQuery("SELECT *,MIN(game_time) FROM GAMEDATA GROUP BY uid  ORDER BY MIN(game_time)");
        else if(mode.equals("Any"))
            rs = statement.executeQuery("SELECT *,MIN(game_time) FROM GAMEDATA WHERE difficulty = '" + diff + "' GROUP BY uid ORDER BY MIN(game_time)");
        else
            rs = statement.executeQuery("SELECT *,MIN(game_time) FROM GAMEDATA WHERE mode = '" + mode + "' GROUP BY uid ORDER BY MIN(game_time)");

        table.getItems().removeAll(new ArrayList<>(table.getItems()));
        ArrayList<FinishedGameInfo> filist = new ArrayList<>();

        int i=1;
        while(rs.next()) {
            FinishedGameInfo fi = new FinishedGameInfo(rs.getString("username"),Integer.parseInt(rs.getString("MIN(game_time)")),rs.getString("board"),rs.getString("mode"),rs.getString("createdAt"),i++ + "");
            filist.add(fi);
        }
        table.getItems().addAll(filist);
    }

    public boolean checkInvalidFields() {
        if(modeComboBox.getValue() == null)
            return false;
        if (boardComboBox.getValue() == null)
            return false;
        return true;
    }

}
