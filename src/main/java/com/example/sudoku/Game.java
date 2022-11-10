package com.example.sudoku;

import java.io.Serializable;
import java.util.Arrays;

public class Game implements Serializable {
    public String id;
    public String name;
    public int size;
    public String Ssize;
    public int[][] grid;
    public int[][] solutionGrid;
    public Time time;
    public boolean isCompleted;
    public int blanks;
    public String difficulty;
    public int numberOfHints;

    Game(String name, String mode, String Ssize) {
        this.id = User.getInstance().getId();
        this.name = name;
        this.difficulty = mode;
        this.Ssize = Ssize;
        this.size = sizeToIntegerParser(Ssize);
        this.time = new Time();
        parseModeToBlanks();
        this.isCompleted = false;
        this.numberOfHints = (int)(this.size*this.size*0.4);

        Sudoku sudoku = new Sudoku(this.size, this.blanks);
        sudoku.fillValues();
        this.solutionGrid = sudoku.getGrid();
        for (int[] x: this.solutionGrid) {
            for (int y : x)
                System.out.print(y + " ");
            System.out.println("");
        }

        sudoku.removeKDigits();
        this.grid = sudoku.getGrid();
    }

    void parseModeToBlanks() {
        if(difficulty.equals("Hard")){
            this.blanks = (int)((0.71 +  0.08*Math.random())*size*size);
        }else if(difficulty.equals("Medium"))
        {
            this.blanks = (int)((0.65 +  0.03*Math.random())*size*size);
        }else if(difficulty.equals("Easy"))
        {
            this.blanks = (int)((0.59 +  0.06*Math.random())*size*size);
        }else{
            this.blanks = 1;
        }
//        System.out.println(this.blanks);
    }

    Game(Game game) {
        this.name = game.name;
        this.size = game.size;
        this.blanks = game.blanks;
        this.time = game.time;
        this.Ssize = game.Ssize;
        this.difficulty = game.difficulty;
        this.isCompleted = game.isCompleted;
        this.grid = new int[size][size];
        this.solutionGrid = new int[size][size];
        this.numberOfHints = game.numberOfHints;

        for(int i=0; i< game.size; i++){
            for(int j=0; j< game.size; j++){
                this.grid[i][j] = game.grid[i][j];
                this.solutionGrid[i][j] = game.solutionGrid[i][j];
            }
        }
    }

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

    public String sizeToStringParser(int size) {
        return size + "x" + size;
    }

    public int sizeToIntegerParser(String Ssize) {
        if(Ssize.charAt(1) == 'x')
            return Integer.parseInt(Ssize.charAt(0) + "");
        return Integer.parseInt(Ssize.substring(0,2));
    }

    public int getSize() {return size;}

    public String getName() {return name;}
    public int[][] getGrid() {
        return grid;
    }

    public int[][] getSolutionGrid() {
        return solutionGrid;
    }

    public Time getTime() {
        return time;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getBlanks() {
        return blanks;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public int getNumberOfHints() {
        return numberOfHints;
    }
    public String getSsize() {
        return Ssize;
    }

    public String getId() {
        return id;
    }
}
