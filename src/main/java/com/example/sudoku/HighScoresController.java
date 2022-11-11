package com.example.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HighScoresController implements Initializable {
    public Label consoleLabel;
    public TableColumn<FinishedGameInfo,String> bestColumn;
    public ComboBox<String> boardComboBox;
    public ComboBox<String> modeComboBox;
    @FXML
    protected Label titleLabel;
    public TableColumn<FinishedGameInfo,String> gameTimeColumn;
    public TableColumn<FinishedGameInfo,String> boardColumn;
    public TableColumn<FinishedGameInfo,String> modeColumn;
    public TableColumn<FinishedGameInfo,String> playedOnColumn;
    public TableView<FinishedGameInfo> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleLabel.setText("High Scores: " + User.getInstance().getUsername());
        gameTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        boardColumn.setCellValueFactory(new PropertyValueFactory<>("Ssize"));
        modeColumn.setCellValueFactory(new PropertyValueFactory<>("mode"));
        playedOnColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        bestColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

        String[] modeArray = {"Easy","Medium","Hard","Any"};
        String[] boardArray = {"9x9","16x16","Any"};

        modeComboBox.getItems().addAll(modeArray);
        boardComboBox.getItems().addAll(boardArray);

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Settings.getInstance().getDB_URI(), Settings.getInstance().getDB_USERNAME(), Settings.getInstance().getDB_PASSWORD());
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = statement.executeQuery("SELECT *  FROM GAMEDATA WHERE uid = '"+ User.getInstance().getId() +"' ORDER BY game_time");
            ArrayList<FinishedGameInfo> filist = new ArrayList<>();

            int i=1;
            while(rs.next()) {
                FinishedGameInfo fi = new FinishedGameInfo(rs.getString("username"),Integer.parseInt(rs.getString("game_time")),rs.getString("board"),rs.getString("mode"),rs.getString("createdAt"), i++ +"");
                filist.add(fi);
            }
            table.getItems().addAll(filist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onFilterButtonClick(ActionEvent event) throws SQLException {
        if(!checkInvalidFields())
            return;

        Connection conn = DriverManager.getConnection(Settings.getInstance().getDB_URI(), Settings.getInstance().getDB_USERNAME(), Settings.getInstance().getDB_PASSWORD());
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs;

        String mode = modeComboBox.getValue();
        String board = boardComboBox.getValue();

        if(mode.equals("Any") && board.equals("Any"))
            rs = statement.executeQuery("SELECT * FROM GAMEDATA WHERE uid = '" + User.getInstance().getId() +"' ORDER BY game_time");
        else if(mode.equals("Any"))
            rs = statement.executeQuery("SELECT * FROM GAMEDATA WHERE uid = '" + User.getInstance().getId() + "' && board = '" + board + "' ORDER BY game_time");
        else if(board.equals("Any"))
            rs = statement.executeQuery("SELECT * FROM GAMEDATA WHERE uid = '" + User.getInstance().getId() + "' && mode = '" + mode + "' ORDER BY game_time");
        else
            rs = statement.executeQuery("SELECT * FROM GAMEDATA WHERE uid = '" + User.getInstance().getId() + "' && mode = '" + mode + "' && board = '" + board + "' ORDER BY game_time");

        table.getItems().removeAll(new ArrayList<>(table.getItems()));
        ArrayList<FinishedGameInfo> filist = new ArrayList<>();

        int i=1;
        while(rs.next()) {
            FinishedGameInfo fi = new FinishedGameInfo(rs.getString("username"),Integer.parseInt(rs.getString("game_time")),rs.getString("board"),rs.getString("mode"),rs.getString("createdAt"),i++ + "");
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
