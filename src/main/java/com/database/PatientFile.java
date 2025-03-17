package com.database;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class PatientFile {
    private SimpleStringProperty name;
    private SimpleStringProperty date;
    private SimpleStringProperty mrn;
    private SimpleStringProperty etm;
    private SimpleStringProperty procedures;

    public PatientFile() {}

    public PatientFile(String date, String name, String mrn, String etm, String procedures) {
        this.date = new SimpleStringProperty(date);
        this.name = new SimpleStringProperty(name);
        this.mrn = new SimpleStringProperty(mrn);
        this.etm = new SimpleStringProperty(etm);
        this.procedures = new SimpleStringProperty(procedures);
    }

    public int compare(PatientFile other) {
        for (int i = 0; i < getDate().toCharArray().length; i++) {
            char c = getDate().toCharArray()[i];
            if (c > other.getDate().toCharArray()[i]) {
                return 1;
            } else if (c < other.getDate().toCharArray()[i]) {
                return -1;
            }
        }
        return 0;
    }

    public String getName() {
        return name.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getMrn() {
        return mrn.get();
    }

    public String getEtm() {
        return etm.get();
    }

    public String getProcedures() {
        return procedures.get();
    }
}
