package com.example.sudoku;

import java.io.Serializable;
import java.util.Date;

public class FinishedGameInfo implements Serializable {
    public String name;
    public int time;
    public Date date;
    public String gridSize;
    public String difficulty;


    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public String getGridSize() {
        return gridSize;
    }

    public String getDifficulty() {
        return difficulty;
    }


    public FinishedGameInfo(Game game) {
        this.name = game.getName();
        this.time = game.time.getTimeInSeconds();
        this.gridSize = game.getSize()+"x"+ game.getSize();
        this.date = new Date();
    }

    public FinishedGameInfo(String name, int time, String gridSize, String difficulty) {
        this.time = time;
        this.date = new Date();
        this.gridSize = gridSize;
        this.name = name;
        this.difficulty = difficulty;
    }
}
