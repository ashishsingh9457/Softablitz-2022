package com.example.sudoku;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SudokuController   {

    public Label timeLabel;
    public Label consoleLabel;
    protected Parent root;
    protected Stage stage;
    protected Game game;
    protected TextField[][] tfs;

    public void populate(Game game, Stage stage, Parent root, TextField[][] tfs)  {
        System.out.println(game.getName());
        this.tfs = tfs;
        this.game=  game;
        this.root = root;
        this.stage = stage;

        for(int i=0; i<game.getSize(); i++)
            for(int j=0; j<game.getSize(); j++) {
                if(game.grid[i][j] != 0){
                    tfs[i][j].setText(game.grid[i][j] + "");
                    tfs[i][j].setEditable(false);
                }else{
                    tfs[i][j].addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent keyEvent) {

                            for(int i=0; i<game.getSize(); i++)
                            {
                                for (int j=0; j<game.getSize(); j++)
                                {
                                    if(keyEvent.getSource().equals(tfs[i][j])) {
                                        consoleLabel.setText("TextField " + i + " " + j);
                                        game.grid[i][j] = Integer.parseInt(keyEvent.getText());
                                    }
                                }
                            }
                        }
                    });
                }
            }


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!game.isCompleted){
                    Platform.runLater(()->{
                        timeLabel.setText("Time: " + game.time.getTime());
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    game.time.tick();
                }
            }
        }).start();
    }

    public void onSaveButtonClick(ActionEvent event) {
        new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    FileOutputStream fos = new FileOutputStream("C:\\Users\\Ashutosh Awasthi\\OneDrive\\Desktop\\SOFTABLITZ\\save.txt");
                    ObjectOutputStream os = new ObjectOutputStream(fos);
                    os.writeObject(new Time());
                    os.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
