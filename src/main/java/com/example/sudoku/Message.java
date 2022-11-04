package com.example.sudoku;

public class Message {
    private String statement;
    private static final Message message = new Message();

    public static Message getInstance() {return message;}

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
