package com.database;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class FieldController {
    @FXML public TextField MRNField;
    @FXML public TextField dateField;
    @FXML public TextField nameField;
    @FXML AnchorPane pane;
    @FXML ComboBox dropDown;

    public void initialize() {
	dropDown.getItems().setAll("taco1", "taco2");
    }
}
