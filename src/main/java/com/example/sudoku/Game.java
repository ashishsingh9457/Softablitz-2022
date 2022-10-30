package com.example.sudoku;

public class Game {
    private String name;
    private String difficulty;
    private int size;
    public int[][] grid;
    public Time time;
    public boolean isCompleted;

    Game(String name, String difficulty, int size)  {
        this.name = name;
        this.difficulty = difficulty;
        this.size = size;
        isCompleted = false;
        time = new Time();

        Brain br = new Brain(size, 10);
        br.fillValues();
        grid = br.getGrid();

        for(int i=0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getSize() {return size;}
    public String getName() {return name;}
}
