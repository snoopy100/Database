package com.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class FieldController {
    FieldHelper helper;
    String dbPath;
    @FXML TextField MRNField;
    @FXML DatePicker dateField;
    @FXML TextField nameField;
    @FXML VBox CheckBoxContainer;
    @FXML AnchorPane pane;
    @FXML ComboBox dropDown;
    @FXML Button viewButton;
    @FXML Button saveButton;

    public void initialize() {
	    dropDown.getItems().setAll(FieldHelper.ETMList);
        helper = new FieldHelper(CheckBoxContainer, dropDown);
        helper.setProcMap(this.getProcedureArray());
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
        String ETM;
        ArrayList<String> nullResponses = new ArrayList<String>();

        // hashmap storing id of button from vbox.children and boolean if box has been checked
        ArrayList<Boolean> procedures = new ArrayList<Boolean>();
        for (int i = 0; i < CheckBoxContainer.getChildren().size(); i++) {
            if (((CheckBox) CheckBoxContainer.getChildren().get(i)).isSelected()) {
                procedures.add(((CheckBox) CheckBoxContainer.getChildren().get(i)).isSelected());
            }
        }

        // Check if fields are filled in, if not, add them to the nullResponses list
        if (nameField.getText() != null && !nameField.getText().isEmpty()) {
            name = nameField.getText();
        } else {
            nullResponses.add("Name");
        }

        if (dateField.getValue() != null) {
            date = dateField.getValue();
        } else {
            nullResponses.add("Date");
        }

        if (MRNField.getText() != null && !MRNField.getText().isEmpty()) {
            MRN = MRNField.getText();
        } else {
            nullResponses.add("MRN");
        }

        if (dropDown.getValue() != null) {
            ETM = dropDown.getValue().toString();
        } else {
            nullResponses.add("ETM");
        }

        // Create ETM instance and print checkbox responses
        FieldHelper fieldHelper = new FieldHelper(CheckBoxContainer, dropDown);
        fieldHelper.printResponse();
        // Convert ArrayList to String array and call nullPrompt
        if (procedures.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Don't forget to check off all the procedure completed");
            alert.showAndWait();
        }

        if (nullResponses.toArray().length > 0) {
            nullPrompt(nullResponses.toArray(new String[0]));
            return;
        }
        fieldHelper.setCode();
        // now the entries shoudl have been filled

        // now implement savig entry to sql query then runningit against h2 databse
        StringBuilder etmString = new StringBuilder();
        for (int i = 0; i < CheckBoxContainer.getChildren().size(); i++) {
            CheckBox box = (CheckBox) CheckBoxContainer.getChildren().get(i);
            if (box.isSelected()) {
                etmString.append("1");
            } else {
                etmString.append("0");
            }
        }

        StringBuilder queryString = new StringBuilder();
        queryString.append("INSERT INTO myTable (Date, Name, ETM_Visit, MRN, Proc) VALUES ");
        queryString.append("('" + dateField.getValue().toString() + "', '" + nameField.getText() + "', " + fieldHelper.getCode() + ", '" + MRNField.getText() + "', '" + etmString + "');");
        //queryString.append("show tables;");

        System.out.println(queryString.toString());

        try {
            File file = new File("DBName.txt");
            BufferedReader bufferedReadereader = new BufferedReader(new FileReader(file));
            String dbPath = bufferedReadereader.readLine();

            Connection connection = DriverManager.getConnection("jdbc:" + "h2:" + dbPath);
            System.out.println("jdbc:" + "h2:" + dbPath);
            Statement s = connection.createStatement();
            //System.out.println(s.executeQuery(queryString.toString()).getString(1));
            s.executeUpdate(queryString.toString());
            s = connection.createStatement();
            ResultSet resultSet = s.executeQuery("select * from myTable");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            System.out.print("connectin  is valid : " + connection.isValid(20));
        } catch (Exception e) {e.printStackTrace();}
    }


    public void nullPrompt(String[] nullResponses) {
        // add dialog with aray in text
        StringBuilder note = new StringBuilder();
        for (int i = 0; i < nullResponses.length; i++) {
            note.append(nullResponses[i] + "\n");
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "Warning: Value has not been entered for \n" + note);
        alert.showAndWait();
    }

    public void view(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("Search.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public HashMap<String, Boolean> getProcedureArray() {
        HashMap<String, Boolean> proc = new HashMap<String, Boolean>();
        for (int i = 0; i < CheckBoxContainer.getChildren().size(); i++) {
            CheckBox box = (CheckBox) CheckBoxContainer.getChildren().get(i);

            proc.put(box.getText(), box.isSelected());
        }
        return proc;
    }
}
