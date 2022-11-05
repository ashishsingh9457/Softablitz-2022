package com.example.sudoku;

import java.io.Serializable;

public class Time implements Serializable {
    private int minute;
    private int hour;
    private int second;

    public void tick()  {
        second ++;
        if(second >= 60) {
            minute++;
            second = 0;
        }
        if(minute >= 60) {
            hour++;
            minute = 0;
        }
    }

    public void tick(int n){
        second+=n;
        if(second >= 60) {
            minute += second/60;
            second = 0;
        }
        if(minute >= 60) {
            hour += hour/60;
            minute = 0;
        }
    }

    public String getTime()   {
        return hour + " : " + minute + " : " + second;
    }

    public int getTimeInSeconds(){
        return second + minute*60 + hour*3600;
    }

    public static String parseTime(int seconds) {
        int hrs = seconds/3600;
        seconds = seconds%3600;
        int mins = seconds/60;
        seconds = seconds%60;
        return hrs+" : "+mins+" : "+seconds;
    }
}
