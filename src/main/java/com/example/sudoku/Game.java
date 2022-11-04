package com.example.sudoku;

import java.io.Serializable;
import java.util.Arrays;

public class Game implements Serializable {
    private String name;
    private int size;
    public int[][] grid;
    public int[][] solutionGrid;
    public Time time;
    public boolean isCompleted;
    public int blanks;
    public String difficulty;

    Game(String name, int blanks, int size)  {
        this.name = name;
        this.size = size;
        isCompleted = false;
        time = new Time();
        this.blanks = blanks;

        Sudoku sudoku = new Sudoku(this.size, this.blanks);
        sudoku.fillValues();
        solutionGrid = sudoku.getGrid();
        sudoku.removeKDigits();
        grid = sudoku.getGrid();

        System.out.println("Solution: ");
        for(int i=0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(solutionGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    void parseDifficulty() {
        if(difficulty.equals("Hard")){
            int blanks = (int)(58 +  7*Math.random());
        }else if(difficulty.equals("Medium"))
        {
            int blanks = (int)(53 +  3*Math.random());
        }else
        {
            int blanks = (int)(48 +  5*Math.random());
        }
    }

    Game(Game game) {
        this.name = game.name;
        this.size = game.size;
        this.blanks = game.blanks;
        this.time = game.time;
        this.isCompleted = game.isCompleted;
        this.grid = new int[size][size];
        this.solutionGrid = new int[size][size];

        for(int i=0; i< game.size; i++){
            for(int j=0; j< game.size; j++){
                this.grid[i][j] = game.grid[i][j];
                this.solutionGrid[i][j] = game.solutionGrid[i][j];
            }
        }
    }

    public int getSize() {return size;}
    public String getName() {return name;}
    public boolean checkCompletedGame() {
        for (int i=0; i<size; i++) {
            for(int j=0; j<size; j++){
                if(grid[i][j]==0)
                    return false;
                if(grid[i][j] != solutionGrid[i][j])
                    return false;
            }
        }

        isCompleted = true;
        return true;
    }
}
