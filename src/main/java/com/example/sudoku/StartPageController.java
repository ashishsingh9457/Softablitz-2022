package com.example.sudoku;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {
    protected Stage stage;
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


    // creates a new scene for Sudoku Grid
    public void onStartButtonClick(ActionEvent event) throws IOException {
        String name = nameField.getText();
        String difficulty = difficultyField.getValue();
        int size = 9;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SudokuView.fxml"));
        BorderPane root = (BorderPane) fxmlLoader.load();
        GridPane gp = new GridPane();
        root.setCenter(gp);


        // Creating TextFields
        TextField[][] tfs = new TextField[size][size];
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++) {
                tfs[i][j] = new TextField();
                tfs[i][j].setPrefWidth(50);
                tfs[i][j].setPrefHeight(25);
                tfs[i][j].setFont(new Font(25));
                tfs[i][j].setAlignment(Pos.CENTER);
                gp.add(tfs[i][j], j, i);
            }



        // create necessary changes in the scene
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // populating necessary fields in SudokuController Object
        SudokuController sc = fxmlLoader.getController();
        sc.populate(new Game(name, difficulty, size), stage, root, tfs);



        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(true);
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
