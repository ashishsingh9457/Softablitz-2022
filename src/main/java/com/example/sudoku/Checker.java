package com.example.sudoku;

public class Checker {
    public static boolean isValidMove(int grid[][], int I, int J, int N)   {

        System.out.println(grid[I][J]);
        int SQN = (int)Math.sqrt(N);

        // check vertical line
        for(int i=0; i<N; i++) {
            if(grid[I][J] == grid[i][J] && i!=I) {
                System.out.println("Number Already present in vertical line");
                return false;
            }
        }

        // check horizontal line
        for(int j=0; j<N; j++) {
            if(grid[I][J] == grid[I][j] && j!=J) {
                System.out.println("Number Already present in horizontal line");
                return false;
            }
        }

        // check small square area
        for(int i = (I/SQN)*SQN ; i<(I/SQN + 1)*SQN; i++) {
            for(int j = (J/SQN)*SQN; j<(J/SQN + 1)*SQN; j++) {
                if(grid[I][J] == grid[i][j] && I!=i && J!=j){
                    System.out.println("Number Already present in small square");
                    return false;
                }
            }
        }
        return true;
    }
}
