package com.example.sudoku;

import java.util.Stack;

public class GameStack {
    public Stack<Game> undoStack;
    public Stack<Game> redoStack;

    GameStack(){
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
}
