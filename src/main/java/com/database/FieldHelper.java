/*
* ETM is a class to simplify controlling the checkboxes and dropdown fields in field.fxml
* by providing easier ways to store values outside of the controller
* */

package com.database;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class FieldHelper {
    HashMap<String, Boolean> procMap = new HashMap<String, Boolean>();
    VBox box;

    ComboBox dropDown;
    boolean[] response;
    static String[] ETMList = {"99203",
            "99204",
            "99205",
            "99213",
            "99214",
            "99215",
            "99221",
            "99222",
            "99223",
            "99231",
            "99232",
            "99233"};
    String selectedCode;

   public FieldHelper() {}

    public FieldHelper(VBox box, ComboBox dropDown) {
        this.box = box;
        boolean[] response = new boolean[box.getChildren().size()];
        this.dropDown = dropDown;
        // add stuf to add conents of boxes to array
    }

    public void setProcMap(HashMap<String, Boolean> map) {
        this.procMap = map;
    }

    public HashMap<String, Boolean> getProcMap() {
       return procMap;
    }

    public void printResponse() {
        for (int i = 0; i < box.getChildren().size(); i++) {
            CheckBox checkBox = (CheckBox) box.getChildren().get(i);
            System.out.println(checkBox.isSelected() + checkBox.getText());
        }
    }

    public void setCode() {
        this.selectedCode = dropDown.getValue().toString();
    }

    public String getCode() {
        return this.selectedCode;
    }
}
