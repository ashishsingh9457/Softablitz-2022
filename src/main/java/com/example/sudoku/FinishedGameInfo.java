package com.example.sudoku;

import java.io.Serializable;
import java.util.Date;

public class FinishedGameInfo implements Serializable {
    public String name;
    public int time;
    public String date;
    public String Ssize;
    public String mode;


    public String getName() {
        return name;
    }

    public String getTime() {
        return Time.parseTime(time);
    }

    public String getSsize() {
        return Ssize;
    }

    public String getMode() {
        return mode;
    }

    public String getDate() {
        return date;
    }

    public FinishedGameInfo(String name, int time, String gridSize, String difficulty, String date) {
        this.time = time;
        this.date = date;
        this.Ssize = gridSize;
        this.name = name;
        this.mode = difficulty;
    }
}
