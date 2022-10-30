package com.example.sudoku;

public class Time {
    private int minute;
    private int hour;
    private int second;

    public void tick()  {
        second ++;
        if(second == 60) {
            minute++;
            second = 0;
        }
        if(minute == 60) {
            hour++;
            minute = 0;
        }
    }

    public String getTime()   {
        return hour + " : " + minute + " : " + second;
    }
}
