package com.database;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class FieldController {
    @FXML TextField MRNField;
    @FXML TextField dateField;
    @FXML TextField nameField;
    @FXML VBox CheckBoxContainer;
    @FXML AnchorPane pane;
    @FXML ComboBox dropDown;

    String[] ETMList = {"code_99203",
            "code_99204",
            "code_99205",
            "code_99213",
            "code_99214",
            "code_99215",
            "code_99221",
            "code_99222",
            "code_99223",
            "code_99231",
            "code_99232",
            "code_99233"};

    public void initialize() {
	    dropDown.getItems().setAll(ETMList);
        // list index starts at 0

        /* iterates through children (checkboxes) of VBox and prints their id
        for (int i = 0; i < CheckBoxContainer.getChildren().size(); i++) {
            System.out.println(CheckBoxContainer.getChildren().get(i).getId());
        } */
    }
}
