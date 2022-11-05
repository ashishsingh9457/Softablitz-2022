package com.example.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    public ColorPicker primaryCp;
    public ColorPicker secondaryCp;
    public ColorPicker fontCp;
    public Button applyButton;

    public void onApplyButtonClick(ActionEvent event) throws IOException {
        Settings.getInstance().setFontColor("#"+fontCp.getValue().toString().substring(2));
        Settings.getInstance().setTilesPrimary("#"+primaryCp.getValue().toString().substring(2));
        Settings.getInstance().setTilesSecondary("#"+secondaryCp.getValue().toString().substring(2));

        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        FXMLLoader fxmlLoader = new FXMLLoader(StartPageController.class.getResource("StartPage.fxml"));
        // create necessary changes in the scene
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(Objects.requireNonNull(Init.class.getResourceAsStream("mainico.png"))));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fontCp.setValue(Color.valueOf(Settings.getInstance().getFontColor()));
        primaryCp.setValue(Color.valueOf(Settings.getInstance().getTilesPrimary()));
        secondaryCp.setValue(Color.valueOf(Settings.getInstance().getTilesSecondary()));
    }
}
