package com.example.sudoku;

public class Game {
    private String name;
    private String difficulty;
    private int size;
    private int[][] grid;
    private float time;
    private boolean isCompleted;

    Game(String name, String difficulty, int size)
    {
        this.name = name;
        this.difficulty = difficulty;
        this.size = size;
        isCompleted = false;
        time = 0;
        grid = new int[size][size];

        System.out.println("Game Created with name: " + this.name);
    }
}
