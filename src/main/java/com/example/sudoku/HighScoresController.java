package com.example.sudoku;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12531423", "sql12531423", "LACEJ2SjGm");
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = statement.executeQuery("SELECT *  FROM GAMEDATA WHERE username = '"+ User.getInstance().getUsername() +"' ORDER BY game_time");
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
}
