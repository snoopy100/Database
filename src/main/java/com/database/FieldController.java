package com.database;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import java.time.LocalDate;

public class FieldController {
    @FXML TextField MRNField;
    @FXML DatePicker dateField;
    @FXML TextField nameField;
    @FXML VBox CheckBoxContainer;
    @FXML AnchorPane pane;
    @FXML ComboBox dropDown;
    @FXML Button viewButton;
    @FXML Button saveButton;

    public void initialize() {
	    dropDown.getItems().setAll(ETM.ETMList);
        // list index starts at 0

        /* iterates through children (checkboxes) of VBox and prints their id
        for (int i = 0; i < CheckBoxContainer.getChildren().size(); i++) {
            System.out.println(CheckBoxContainer.getChildren().get(i).getId());
        } */
    }

    public void save() {
        String name;
        LocalDate date;
        String MRN;
        // and add class ETM for the checkboxes
        if (nameField != null) {
            name = nameField.getText();
        } else {
           // show prompt dialog
        }

        if (dateField != null) {
            date = dateField.getValue();
        } else {
            // show prompt dialog
        }

        if (MRNField.getText() != null) {
            MRN = MRNField.getText();
        } else {
            // show prompt dialog
        }
        ETM etm = new ETM(CheckBoxContainer);
        etm.printResponse();
        // add to sql query and run
        // example query
        /* Connection connection = DriverManager.getConnection("jdbc:" + "h2:" + database path);
            Statement s = connection.createStatement();
            ResultSet resultset = s.executeQuery(do stuff); */
    }
}
