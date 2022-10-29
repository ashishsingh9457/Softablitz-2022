package com.example.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    @FXML
    protected TextField nameField;
    @FXML
    protected ChoiceBox<String> difficultyField;
    @FXML
    protected ChoiceBox<String> sizeField;
    @FXML
    protected Button startButton;
    @FXML
    protected TextField sizeTextField;
    public void onStartButtonClick(ActionEvent event) {
        String name = nameField.getText();
        String difficulty = "Medium";
        int size = 9;
        
        Game game = new Game(name, difficulty, size);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] diffs = {"Easy", "Medium", "Hard"};
        String[] sizes = {"9x9", "8x8", "5x5", "Custom"};
        difficultyField.getItems().addAll(diffs);
        difficultyField.setValue("Difficulty");
        sizeField.getItems().addAll(sizes);
        sizeField.setValue("Board Size");

        difficultyField.setOnAction(this::onSetDifficulty);
        sizeField.setOnAction(this::onSetSize);
    }

    private void onSetDifficulty(ActionEvent event) {

    }

    private void onSetSize(ActionEvent event)   {
        if(sizeField.getValue().equals("Custom"))
        {
            sizeTextField.setEditable(true);
        }else{
            sizeTextField.setText("");
            sizeTextField.setEditable(false);
        }
    }
}
