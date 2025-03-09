package com.database;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class ETM {
    VBox box;
    boolean[] response;
    static String[] ETMList = {"code_99203",
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

    public ETM(VBox box) {
        this.box = box;
        boolean[] response = new boolean[box.getChildren().size()];
        // add stuf to add conents of boxes to array
    }

    public void printResponse() {
        for (int i = 0; i < box.getChildren().size(); i++) {
            CheckBox checkBox = (CheckBox) box.getChildren().get(i);
            System.out.println(checkBox.isSelected() + checkBox.getText());
        }
    }
}
